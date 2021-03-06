
import java.util.Scanner;

import net.trucomanx.pdsplibj.pdsdf.PdsFir;
import net.trucomanx.pdsplibj.pdsextras.*;
import net.trucomanx.pdsplibj.pdscalc.*;
import net.trucomanx.pdsplibj.pdsds.*;
import net.trucomanx.pdsplibj.pdsra.PdsVector;

/*
	javac  -classpath . FIR_Integral.java
	java   -classpath . FIR_Integral
*/


public class FIR_Integral {

	public static void main(String[] args) {

		PdsQuantizer Q=new PdsQuantizer(-5.70026986e-03, 3*4.82532766e-02);

		PdsIntegrator Int1=new PdsIntegrator(2.0);
		PdsIntegrator Int2=new PdsIntegrator();
		
		PdsVector H = new PdsVector("../data/params/ValoresH.dat");

		PdsFir filtro1 = new PdsFir(H);
		PdsFir filtro2 = new PdsFir(1);//H);
		PdsFir filtro3 = new PdsFir(1);//H);
		
		PdsReadData  fd1=new PdsReadData("../data/data_from_cell/random_activity/SalidaAtividade.dat");
		PdsWriteData fd2=new PdsWriteData("../data/Gerados/random_activity/AceleracaoFiltrada_Atividade.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd3=new PdsWriteData("../data/Gerados/random_activity/Velocidade_Atividade.txt");			/*caminho onde vai salvar*/
		PdsWriteData fd4=new PdsWriteData("../data/Gerados/random_activity/VelocidadeFiltrada_Atividade.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd5=new PdsWriteData("../data/Gerados/random_activity/Deslocamento_Atividade.txt");			/*caminho onde vai salvar*/
		PdsWriteData fd6=new PdsWriteData("../data/Gerados/random_activity/DeslocamentoFiltrada_Atividade.txt");	/*caminho onde vai salvar*/
		PdsWriteData fd7=new PdsWriteData("../data/Gerados/random_activity/Aceleracao_Atividade.txt");			/*caminho onde vai salvar*/
		
		double a,t;
		double ai,af,vi,vif,di,dif;
		
		for(int i=0;i<699;i++) //i < ? Alterar o valor de i para o numero de linhas do arquivo que vai ser lido
		{
			String s=fd1.Scan();

			Scanner scanner = new Scanner (s);
			t=Double.parseDouble(scanner.next ()); // Tempo lido do TXT
			a=Double.parseDouble(scanner.next ());    // Aceleracao lida do TXT X
			a=Double.parseDouble(scanner.next ());    // Aceleracao lida do TXT Y
			scanner.close();
			
			
			ai = Q      .EvaluateValue(a);
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
			fd7.Println(t+"\t"+a);
			
			//System.out.println("Tempo lido:"+t);
			//System.out.println("Aceleracao lida:"+a);
			//System.out.println("a_filtrada:"+af);
			//System.out.println("v:"+vi);
			//System.out.println("v_filtrada:"+vif);
			//System.out.println("d:"+di);
			//System.out.println("d_filtrado:"+dif);
			
		}
		
		fd2.Close();
		fd3.Close();
		fd4.Close();
		fd5.Close();
		fd6.Close();
		fd7.Close();
	}
		

	}








