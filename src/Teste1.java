import java.util.Scanner;

import net.sourceforge.pdsplibj.pdsextras.*;

public class Teste1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integral Int1=new Integral();
		PdsReadData fd1=new PdsReadData("C:\\Users\\Eduardo\\workspace\\Teste\\src\\text1.txt");
		PdsWriteData fd2=new PdsWriteData("C:\\Users\\Eduardo\\workspace\\Teste\\src\\text2.txt");
		
		double a,t,b;
		double v;
		
		for(int i=0;i<5;i++)
		{
			String s=fd1.Scan();
			Scanner scanner = new Scanner (s);
			t=scanner.nextDouble (); // t
			a=scanner.nextDouble ();    // A 
			scanner.close();
			
			v=Int1.EvaluateValue(a,t);
			
			fd2.Println(t+"\t"+v);
			System.out.println("v:"+v);
		}
		fd2.Close();
	}

}
