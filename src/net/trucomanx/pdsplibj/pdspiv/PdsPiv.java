package net.trucomanx.pdsplibj.pdspiv;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;

import java.awt.Point;

import net.trucomanx.pdsplibj.pdsra.PdsMatrix;

public class PdsPiv{

	private BufferedImage bImage1 = null;
	private BufferedImage bImage2 = null;
	private int W;
	private int H;
	
	
	public PdsPiv()
	{
		H=0;
		W=0;
	}

	/*
	public void load(double[][] array1,double[][] array2)
	{
		int nrow = array1.length;
		int ncol = array1[0].length;
	}
	*/
	public void load(String Filename1,String Filename2)
	{
		File imagefile1 = null;
		File imagefile2 = null;
		int H1,H2;
		int W1,W2;
		
		String Aviso1="The images only should have 1 color component. ";
		String AvisoW="The images should have the same width. ";
		String AvisoH="The images should have the same height. ";
		
		int numbits1,numbits2;

		try
		{	//ImageIO.write(bImage1, "jpg",new File("salida.jpg"));
			imagefile1 = new File(Filename1);
            bImage1 = ImageIO.read(imagefile1);
            
            imagefile2 = new File(Filename2);
            bImage2 = ImageIO.read(imagefile2);
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
		
		// Um check si esta en escala de grises.
		numbits1=bImage1.getColorModel().getNumComponents();
		if(numbits1!=1)
		throw new IllegalArgumentException(Aviso1+"Components="+numbits1+" :"+Filename1);
		
		numbits2=bImage2.getColorModel().getNumComponents();
		if(numbits2!=1)
		throw new IllegalArgumentException(Aviso1+"Components="+numbits2+" :"+Filename2);
		
		// Check que las matrices son del mismo tamanho
		W1 = bImage1.getWidth();		H1 = bImage1.getHeight();
		W2 = bImage2.getWidth();		H2 = bImage2.getHeight();
		
		if(W1!=W2)		throw new IllegalArgumentException(AvisoW);
		if(H1!=H2)		throw new IllegalArgumentException(AvisoH);
		
		H=H1;	W=W1;
	}
	
	// retorna o valor de ym pixel  na linha e coluna da BufferedImage bImage1
	// 0 es negro e 255 es blanco
	public int get_pixel_of_bimage1(int linha, int coluna)
	{
		int blue   = bImage1.getRGB(coluna,linha)& 0x000000ff; 
		return blue;
	}
	
	// retorna o valor de ym pixel  na linha e coluna da BufferedImage bImage2
	// 0 es negro e 255 es blanco
	public int get_pixel_of_bimage2(int linha, int coluna)
	{
		int blue   = bImage2.getRGB(coluna,linha)& 0x000000ff; 
		return blue;
	}

	//sigue ROI de los puntos P1 en imagem1 en la imagen 2, restricto a conf.
	// Retorna una nueva estructura PdsPoints de los puntos si los encuentra o
	// Retorna null en los puntos que no encuentra.
	public PdsPoints tracking_points(PdsPoints P1,PdsPivConf Conf)
	{

		int N=P1.get_length();
		PdsPoints P2 = new PdsPoints(N);
		Point p1=null;
		Point p2=null;
		boolean flag=Conf.get_verbose_flag();
		
		for(int i=0;i<N;i++)
		{
			p1=P1.get_point_from_id(i);// retorna referencia ao ponto
			p2=tracking_point(p1,Conf);
			if (p2==null)
				P2.nullifies_the_point(i);
			else
				P2.set_point(i,p2);
				
			if(flag==true)	
			{
				if((i+1)!=N)
				System.out.print("Analyzing the point "+(i+1)+"/"+N+"     \r");
				else
				System.out.print("Analyzing the point "+(i+1)+"/"+N+"     \n");
			}
			
		}

		return P2;
	}
	
	//sigue ROI del punto P en imagem1 en la imagen 2, restricto a conf.
	// Retorna un nuevo punto si lo encuentra o
	// Retorna null si no lo encuentra
	public Point tracking_point(Point P,PdsPivConf Conf)
	{
		int		search_step		= Conf.get_search_step_size();
		int		search_max		= Conf.get_search_max_length();
		double 	search_threshold= Conf.get_search_threshold();
		
		int N=(int)Math.ceil(search_max*1.0/search_step);
		double x2,y2;
		
		Point     P2 = new Point(0,0);
		Point     ID;		
		PdsMatrix C ;
		
		C=corr_pearson_matrix(P,Conf);
		
		//ID va desde -(N-1) a +(N-1) em ambos ejes.
		ID=find_id_of_max_value_in_matrix(C,search_threshold,0);
		
		if(ID!=null)
		{
			x2=P.getX()+ID.getX()*search_step;
			y2=P.getY()+ID.getY()*search_step;
			P2.setLocation(x2,y2);
			return P2;
		}
		else return null;
		
	}

	// Enche uma matriz com correlaciones ao redor de P na figura 2.
	PdsMatrix corr_pearson_matrix(Point P,PdsPivConf Conf)
	{
		int wsize             = Conf.get_roi_window_size();
		int search_step       = Conf.get_search_step_size();
		int search_max        = Conf.get_search_max_length();
		
		int N=(int)Math.ceil(search_max*1.0/search_step);
		double corr;
		double x2,y2;
		
		
		PdsMatrix C  = new PdsMatrix(2*N-1,2*N-1);
		Point     P2 = new Point(0,0);
		
		C.InitValue(-1.0);
		
		for( int lin=-(N-1);lin<N;lin++)
		for( int col=-(N-1);col<N;col++)
		{
			x2=P.getX()+lin*search_step;
			y2=P.getY()+col*search_step;
			
			if( (x2>=0)&&(y2>=0)&&(y2<=(W-wsize))&&(x2<=(H-wsize)) )
			{
				P2.setLocation(x2,y2);
				corr=corr_pearson(P,P2,wsize);
				C.SetValue(lin+(N-1),col+(N-1),corr);
			}
		}
		return C;
	}
	
	
	// El centro es zero. falta implementar varUmbral a variancia do umbral
	// Los extremos son positivos y negativos
	Point find_id_of_max_value_in_matrix(PdsMatrix C,double Umbral,double varUmbral)
	{
		int Nlin = C.GetNlin();
		int Ncol = C.GetNcol();
		int lin_max=-1;
		int col_max=-1;
		double max=Umbral;
		double val;
		
		for(int lin=0;lin<Nlin;lin++)
		for(int col=0;col<Ncol;col++)
		{
			val=C.GetValue(lin,col);
			
			if(val>=Umbral)
			{
				Umbral=val;
				lin_max=lin;
				col_max=col;
			}
		}
		
		if(lin_max==-1)	return null;
		
		Point P = new Point(lin_max-(Nlin-1)/2,col_max-(Ncol-1)/2);
		
		return P;
	}
	
	// Correlacion de pearson de punto P1 em imagem 1 a punto P2 em imagem 2.
	// Faz a correlacao na regiao existente e procura ate wsize/2 de regiao existente.
	public double corr_pearson(Point P1,Point P2, int wsize)
	{
		double mu1=mean_of_image1(P1,wsize);
		double mu2=mean_of_image2(P2,wsize);
		
		int lin,col;
		double corr=0;
		int X1,Y1;
		int X2,Y2;
		double var1=0;
		double var2=0;
		double cov=0;
		double val1,val2;
		
		for(lin=0;lin<wsize;lin++)
		for(col=0;col<wsize;col++)
		{
			X1=(int)P1.getX()+lin;	Y1=(int)P1.getY()+col;
			X2=(int)P2.getX()+lin;	Y2=(int)P2.getY()+col;
			
			if( (X2>=0) && (X2<(H-wsize/2)) && (Y2>=0) && (Y2<(W-wsize/2)) )
			if( (X1>=0) && (X1<(H-wsize/2)) && (Y1>=0) && (Y1<(W-wsize/2)) )
			{
				val1=get_pixel_of_bimage1(X1,Y1);
				val2=get_pixel_of_bimage2(X2,Y2);
				
				cov=cov+(val1-mu1)*(val2-mu2);
				var1=var1+(val1-mu1)*(val1-mu1);
				var2=var2+(val2-mu2)*(val2-mu2);
			}
		}
		
		corr=cov/Math.sqrt(var1*var2);
		
		return corr;
	}
	
	// valor medio desde el punto P1 em imagem 1 com un ROI de wsize
	public double mean_of_image1(Point P1, int wsize)
	{
		int lin,col;
		double val=0;
		int X,Y;
		
		for(lin=0;lin<wsize;lin++)
		for(col=0;col<wsize;col++)
		{
			X=(int)P1.getX()+lin;
			Y=(int)P1.getY()+col;
			
			if( (X>=0) && (X<H) && (Y>=0) && (Y<W) )
			val=val+get_pixel_of_bimage1(X,Y);
		}
		return val/(wsize*wsize);
	}

	// valor medio desde el punto P2 em imagem 2 com un ROI de wsize
	public double mean_of_image2(Point P2, int wsize)
	{
		int lin,col;
		double val=0;
		int X,Y;
		
		for(lin=0;lin<wsize;lin++)
		for(col=0;col<wsize;col++)
		{
			X=(int)P2.getX()+lin;
			Y=(int)P2.getY()+col;
			
			if( (X>=0) && (X<H) && (Y>=0) && (Y<W) )
			val=val+get_pixel_of_bimage2(X,Y);
		}
		return val/(wsize*wsize);
	}
		
	public PdsPivData match(PdsPivConf Conf)
	{	
		int  N;
		PdsPivData D = new PdsPivData();
		
		D.initial_points = make_grid_points(Conf);
		D.final_points   = tracking_points(D.initial_points,Conf);

		D.vectors        = D.final_points.new_diff_with(D.initial_points);

		return D;
	}
	
	// para cuadricula completa gscol==(W-wsize)/(points_by_columns-1)
	// para cuadricula completa gslin==(H-wsize)/(points_by_lines-1);
	public PdsPoints make_grid_points(PdsPivConf Conf)
	{
		String AvisoC="To much point by columns";
		String AvisoL="To much point by lines";
		int lin,col,id;
		int points_by_lines   = Conf.get_points_by_lines();
		int points_by_columns = Conf.get_points_by_columns();
		int wsize             = Conf.get_roi_window_size();
		int search_step       = Conf.get_search_step_size();
		int search_max        = Conf.get_search_max_length();
		int no_used;
		
		int gscol=(W-wsize)/(points_by_columns-1);
		if(gscol<=0)		throw new IllegalArgumentException(AvisoC);
		
		int gslin=(H-wsize)/(points_by_lines-1);
		if(gslin<=0)		throw new IllegalArgumentException(AvisoL);
		
		no_used=W-(gscol*(points_by_columns-1)+wsize);
		if(no_used>0)		System.out.println("Column pixels no used:"+no_used+" lasts");
		
		no_used=H-(gslin*(points_by_lines-1)+wsize);
		if(no_used>0)		System.out.println("Lines pixels no used:"+no_used+" lasts");
		
		PdsPoints P = new PdsPoints(points_by_columns*points_by_lines);
		
		id=0;
		for(lin=0;lin<points_by_lines;lin++)
		for(col=0;col<points_by_columns;col++)
		{
			
			P.set_point(id,lin*gslin,col*gscol);
			id=id+1;
		}
		
		return P;
	}
	
	public int get_image_width()
	{
		return W;
	}
	
	public int get_image_ncolumns()
	{
		return W;
	}	

	public int get_image_height()
	{
		return H;
	}
	
	public int get_image_nlines()
	{
		return H;
	}
	
}
