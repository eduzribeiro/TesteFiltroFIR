
import java.util.Scanner;

import net.trucomanx.pdsplibj.pdsdf.*;
import net.trucomanx.pdsplibj.pdsextras.*;
import net.trucomanx.pdsplibj.pdscalc.*;
import net.trucomanx.pdsplibj.pdsds.*;
import net.trucomanx.pdsplibj.pdsra.PdsVector;

/*
	javac  -classpath . FIR_Integral_repouso.java
	java   -classpath . FIR_Integral_repouso
*/


public class FIR_Integral_repouso {

	public static void main(String[] args) {

		PdsQuantizer Q=new PdsQuantizer(-0.042211, 4*0.050016);

		PdsIntegrator Int1=new PdsIntegrator(2);
		PdsIntegrator Int2=new PdsIntegrator();
		
		PdsVector H = new PdsVector("../data/params/ValoresH.dat");

		double h=1.0;
		double r=0.001;
		double A=0.01;
		double q=0.4;

		PdsKalman1D filtro=new PdsKalman1D(A,h,q,r);

		PdsFir filtro1 = new PdsFir(H);
		PdsFir filtro2 = new PdsFir(1);//H);
		PdsFir filtro3 = new PdsFir(1);//H);
		
		PdsReadData  fd1=new PdsReadData("../data/data_from_cell/caminando/SalidaRepouso1.dat");
		PdsWriteData fd2=new PdsWriteData("../data/Gerados/caminando/AceleracaoFiltrada_repouso.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd3=new PdsWriteData("../data/Gerados/caminando/Velocidade_repouso.txt");			/*caminho onde vai salvar*/
		PdsWriteData fd4=new PdsWriteData("../data/Gerados/caminando/VelocidadeFiltrada_repouso.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd5=new PdsWriteData("../data/Gerados/caminando/Deslocamento_repouso.txt");			/*caminho onde vai salvar*/
		PdsWriteData fd6=new PdsWriteData("../data/Gerados/caminando/DeslocamentoFiltrada_repouso.txt");	/*caminho onde vai salvar*/

		double a,t;
		double ai,af,vi,vif,di,dif;
		
		for(int i=0;i<746;i++) //i < ? Alterar o valor de i para o numero de linhas do arquivo que vai ser lido
		{
			String s=fd1.Scan();

			Scanner scanner = new Scanner (s);
			t=Double.parseDouble(scanner.next ()); // Tempo lido do TXT
			a=Double.parseDouble(scanner.next ());    // Aceleracao lida do TXT X
			a=Double.parseDouble(scanner.next ());    // Aceleracao lida do TXT Y
			scanner.close();
			
			
			ai = Q.EvaluateValue(a);
			af = filtro1.EvaluateValue(ai);
			//af =filtro.EvaluateValue(ai);
			vi = Int1   .EvaluateValue(af,t);
			vif= filtro2.EvaluateValue(vi);
			di = Int2   .EvaluateValue(vif,t);
			dif= filtro3.EvaluateValue(di);
			
			
			fd2.Println(t+"\t"+af);
			fd3.Println(t+"\t"+vi);
			fd4.Println(t+"\t"+vif);
			fd5.Println(t+"\t"+di);
			fd6.Println(t+"\t"+dif);
			
			
		}
		
		fd2.Close();
		fd3.Close();
		fd4.Close();
		fd5.Close();
		fd6.Close();
	}
		

	}








