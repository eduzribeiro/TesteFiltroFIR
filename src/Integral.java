
public class Integral {
	double last_g=0;
	double last_t=0;
	
	double EvaluateValue(double f,double t)
	
	{
		double g;
		
		g=f*(t-last_t)+last_g;
		last_g=g;
		last_t=t;
		return g;
	}
}
