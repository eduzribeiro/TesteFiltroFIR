
import java.util.Scanner;

import net.trucomanx.pdsplibj.pdsdf.PdsFir;
import net.trucomanx.pdsplibj.pdsextras.*;
import net.trucomanx.pdsplibj.pdscalc.*;
import net.trucomanx.pdsplibj.pdsds.*;
import net.trucomanx.pdsplibj.pdsra.PdsVector;

/*
	javac  -classpath . FIR_Integral_caminando.java
	java   -classpath . FIR_Integral_caminando
*/


public class FIR_Integral_caminando {

	public static void main(String[] args) {

		PdsQuantizer Q=new PdsQuantizer(-5.70026986e-03, 3*4.82532766e-02);

		PdsIntegrator Int1=new PdsIntegrator(20);
		PdsIntegrator Int2=new PdsIntegrator();
		
		PdsVector H = new PdsVector("../data/params/ValoresH.dat");

		PdsFir filtro1 = new PdsFir(1);//H);
		PdsFir filtro2 = new PdsFir(1);//H);
		PdsFir filtro3 = new PdsFir(1);//H);
		
		PdsReadData  fd1=new PdsReadData("../data/data_from_cell/caminando/SalidaCaminandoY.dat");
		PdsWriteData fd2=new PdsWriteData("../data/Gerados/caminando/AceleracaoFiltrada_caminando.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd3=new PdsWriteData("../data/Gerados/caminando/Velocidade_caminando.txt");			/*caminho onde vai salvar*/
		PdsWriteData fd4=new PdsWriteData("../data/Gerados/caminando/VelocidadeFiltrada_caminando.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd5=new PdsWriteData("../data/Gerados/caminando/Deslocamento_caminando.txt");			/*caminho onde vai salvar*/
		PdsWriteData fd6=new PdsWriteData("../data/Gerados/caminando/DeslocamentoFiltrada_caminando.txt");	/*caminho onde vai salvar*/

		double a,t;
		double ai,af,vi,vif,di,dif;
		
		for(int i=0;i<150;i++) //i < ? Alterar o valor de i para o numero de linhas do arquivo que vai ser lido
		{
			String s=fd1.Scan();
		}
		for(int i=150;i<718;i++) //i < ? Alterar o valor de i para o numero de linhas do arquivo que vai ser lido
		{
			String s=fd1.Scan();
			Scanner scanner = new Scanner (s);
			t=Double.parseDouble(scanner.next ()); // Tempo lido do TXT
			a=Double.parseDouble(scanner.next ());    // Aceleracao lida do TXT X
			a=Double.parseDouble(scanner.next ());    // Aceleracao lida do TXT Y
			scanner.close();
			
			
			ai =a;//= Q.EvaluateValue(a);
			af = filtro1.EvaluateValue(ai);
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








