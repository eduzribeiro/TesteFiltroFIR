package net.trucomanx.pdsplibj.pdspiv;

import java.awt.Point;

public class PdsPoints{
	private Point[] P;
	private int Nel;
	
	// crea los puntos y los inicializa con 0,0
	public PdsPoints(int N) 
	{
		int i;

		Nel=N;

		P= new Point[Nel];

		for(i=0;i<Nel;i++)
		{
			// Sin esto P[i] es null.
			P[i]=new Point(0,0);
		}
		
	}

	////////////////////////////////////////////////////////////////////
	public Point get_point_from_id(int id)
	{
		if((id>=Nel)||(id<0))	return null;

		return P[id];
	}

	public Point get_new_point_from_id(int id)
	{
		if((id>=Nel)||(id<0))	return null;
		
		if (P[id]==null)		return null;
		
		return new Point(P[id]);
	}
	
	public double[] get_array_from_id(int id)
	{
		double p[]= new double[2];
		
		if((id>=Nel)||(id<0))	return null;
		
		if (P[id]==null)		return null;
		
		p[0]=P[id].getX();
		p[1]=P[id].getY();

		return p;
	}
	
	public double[] get_array_from_value0()
	{
		double p[]= new double[Nel];
		
		for(int i=0;i<Nel;i++)
		{
			if(P[i]!=null)
			p[i]=P[i].getX();
			else
			p[i]=Double.NaN;
		}

		return p;
	}
	
	public double[] get_array_from_value1()
	{
		double p[]= new double[Nel];
		
		for(int i=0;i<Nel;i++)
		{
			if(P[i]!=null)
			p[i]=P[i].getY();
			else
			p[i]=Double.NaN;
		}

		return p;
	}	
	////////////////////////////////////////////////////////////////////
	public int get_length()
	{
		return Nel;
	}

	////////////////////////////////////////////////////////////////////
	public void set_points( PdsPoints pts )
	{
		int Nel2=pts.get_length();
		
		for(int i=0;(i<Nel)&&(i<Nel2);i++)
		P[i].setLocation(pts.get_point_from_id(i));
	}

	public void set_point( int id, Point p )
	{
		P[id].setLocation(p);
	}

	public void set_point( int id, double x, double y )
	{
		P[id].setLocation(x,y);
	}
	public void set_point( int id, int x, int y )
	{
		P[id].setLocation(x,y);
	}	
	////////////////////////////////////////////////////////////////////
	// P e P2 devem ter a mesma quantidade de pontos.
	// Retorna un nuevo P[i]-P2[i]  ate onde sea possibleseguindo i.
	public PdsPoints new_diff_with(PdsPoints P2)
	{
		double x,y;
		Point p1;
		Point p2;
		
		if (Nel!=P2.get_length())
		{
			throw new IllegalArgumentException("Subtracting lists of points of different sizes.");
		}
		PdsPoints Pnew = new PdsPoints(Nel);
		
		for(int i=0;i<Nel;i++)
		{
			p1=P[i];
			p2=P2.get_point_from_id(i);
			
			if((p1!=null)&&(p2!=null))
			{
				x=p1.getX()-p2.getX();
				y=p1.getY()-p2.getY();
				Pnew.set_point(i,x,y);
			}
			else
			{
				Pnew.nullifies_the_point(i);
			}
			
		}
		
		return Pnew;
	}
	////////////////////////////////////////////////////////////////////
	public void nullifies_the_point(int id)
	{
		P[id]=null;
	}
	////////////////////////////////////////////////////////////////////
	
	public String toString()
	{
		String S="";
		for(int i=0;i<Nel;i++)
		{
			S=S	+"("+	get_point_from_id(i).getX()+ 	","+
						get_point_from_id(i).getY()+	")\n";
		}
		return S;
	}	
}