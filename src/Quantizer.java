/*
The Quantizer block passes its input signal u through a stair-step function 
so that many neighboring points on the input axis are mapped to one point 
on the output axis. The effect is to quantize a smooth signal into a 
stair-step output. The output is computed using the round-to-nearest 
method, which produces an output that is symmetric about zero.

y = Q * round((u-Offset)/Q)

where y is the output, u the input, Offset is an offset, and Q the Quantization interval parameter.

y=0 for u-Offset in <-Q/2,+Q/2>

 */
public class Quantizer {
	private double Q=0;
	private double Offset=0;

	public Quantizer(double offset,double q)
	{
		this.Q=q;
		this.Offset=offset;
	}
	
	double EvaluateValue(double u)
	
	{
		double y;
		
		y=this.Q * Math.round((u-this.Offset)/this.Q);

		return y;
	}
}
