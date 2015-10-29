package net.trucomanx.pdsplibj.pdspiv;

public class PdsPivData{
	public PdsPoints initial_points;
	public PdsPoints final_points;
	public PdsPoints vectors;

	public PdsPivData(int N)
	{
		initial_points = new PdsPoints(N);
		final_points   = new PdsPoints(N);
		vectors        = new PdsPoints(N);
	}
	
	public PdsPivData()
	{
		initial_points = null;
		final_points   = null;
		vectors        = null;
	}


	/*
	 * Função para sobre escribir referencia de los puntos iniciales
	 */
	public void set_initial_points(PdsPoints pts)
	{
		initial_points=pts;
	}

/*
	 * Função para escribir dados de los puntos iniciales.
	 */
	public void set_data_of_initial_points(PdsPoints pts)
	{
		initial_points.set_points(pts);
	}

	/*
	 * Função para pedir referenci de dados.
	 */
	public PdsPoints get_initial_points()
	{
		return initial_points;
	}

	/*
	 * Função para pedir referenci de dados.
	 */
	public PdsPoints get_final_points()
	{
		return final_points;
	
	}

	/*
	 * Função para pedir referenci de dados.
	 */
	public PdsPoints get_vectors()
	{
		return vectors;
	}
}
