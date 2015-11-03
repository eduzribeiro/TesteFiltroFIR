
import java.util.Scanner;

import net.trucomanx.pdsplibj.pdsdf.PdsFir;
import net.trucomanx.pdsplibj.pdsextras.*;
import net.trucomanx.pdsplibj.pdsra.PdsVector;


public class FIR_Integral {

	public static void main(String[] args) {
		
		int N = 36;
		
		Integral Int1=new Integral();
		Integral Int2=new Integral();
		
		PdsVector conf = new PdsVector(N);
		conf.SetValue(0, -0.00072935);
		conf.SetValue(1, -0.0017);
		conf.SetValue(2, -0.0011);
		conf.SetValue(3, 0.0016);
		conf.SetValue(4, 0.0046);
		conf.SetValue(5, 0.0032);
		conf.SetValue(6, -0.0045);
		conf.SetValue(7, -0.0121);
		conf.SetValue(8, -0.0080);
		conf.SetValue(9, 0.0105);
		conf.SetValue(10, 0.0273);
		conf.SetValue(11, 0.0177);
		conf.SetValue(12, -0.0230);
		conf.SetValue(13, -0.0608);
		conf.SetValue(14, -0.0416);
		conf.SetValue(15, 0.0609);
		conf.SetValue(16, 0.2092);
		conf.SetValue(17, 0.3185);
		conf.SetValue(18, 0.3185);
		conf.SetValue(19, 0.2092);
		conf.SetValue(20, 0.0609);
		conf.SetValue(21, -0.0416);
		conf.SetValue(22, -0.0608);
		conf.SetValue(23, -0.0230);
		conf.SetValue(24, 0.0177);
		conf.SetValue(25, 0.0273);
		conf.SetValue(26, 0.0105);
		conf.SetValue(27, -0.0080);
		conf.SetValue(28, -0.0121);
		conf.SetValue(29, -0.0045);
		conf.SetValue(30, 0.0032);
		conf.SetValue(31, 0.0046);
		conf.SetValue(32, 0.0016);
		conf.SetValue(33, -0.0011);
		conf.SetValue(34, -0.0017);
		conf.SetValue(35, -0.00072935);
		//.....
		
		PdsFir filtro1 = new PdsFir(conf);
		PdsFir filtro2 = new PdsFir(conf);
		PdsFir filtro3 = new PdsFir(conf);
		
		PdsReadData fd1=new PdsReadData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\SalidaActivity.txt");
		PdsWriteData fd2=new PdsWriteData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\Gerados\\Atividade\\AceleracaoFiltrada_Atividade.txt"/*caminho onde vai salvar*/);
		PdsWriteData fd3=new PdsWriteData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\Gerados\\Atividade\\Velocidade_Atividade.txt"/*caminho onde vai salvar*/);
		PdsWriteData fd4=new PdsWriteData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\Gerados\\Atividade\\VelocidadeFiltrada_Atividade.txt"/*caminho onde vai salvar*/);
		PdsWriteData fd5=new PdsWriteData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\Gerados\\Atividade\\Deslocamento_Atividade.txt"/*caminho onde vai salvar*/);
		PdsWriteData fd6=new PdsWriteData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\Gerados\\Atividade\\DeslocamentoFiltrado_Atividade.txt"/*caminho onde vai salvar*/);
		PdsWriteData fd7=new PdsWriteData("C:\\Users\\Eduardo\\Desktop\\Diversos\\UFLA\\Mestrado\\Dados TXT\\Gerados\\Atividade\\Aceleracao_Atividade.txt"/*caminho onde vai salvar*/);
		
		double a,t;
		double af,vi,vif,di,dif;
		
		for(int i=0;i<3000;i++) //i < ? Alterar o valor de i para o número de linhas do arquivo que vai ser lido
		{
			String s=fd1.Scan();
			Scanner scanner = new Scanner (s);
			t=scanner.nextDouble (); // Tempo lido do TXT
			a=scanner.nextDouble ();    // Aceleração lida do TXT
			scanner.close();
			
			
			
			af=filtro1.EvaluateValue(a);
			vi=Int1.EvaluateValue(af,t);
			vif=filtro2.EvaluateValue(vi);
			di=Int2.EvaluateValue(vif,t);
			dif=filtro3.EvaluateValue(di);
			
			
			fd2.Println(t+"\t"+af);
			fd3.Println(t+"\t"+vi);
			fd4.Println(t+"\t"+vif);
			fd5.Println(t+"\t"+di);
			fd6.Println(t+"\t"+dif);
			fd7.Println(t+"\t"+a);
			
			System.out.println("Tempo lido:"+t);
			System.out.println("Aceleração lida:"+a);
			System.out.println("a_filtrada:"+af);
			System.out.println("v:"+vi);
			System.out.println("v_filtrada:"+vif);
			System.out.println("d:"+di);
			System.out.println("d_filtrado:"+dif);
			
		}
		
		fd2.Close();
		fd3.Close();
		fd4.Close();
		fd5.Close();
		fd6.Close();
		fd7.Close();
	}
		

	}








