package br.com.utilities.color;

public interface ICMYK {

	public void setCMYK(int[] cmyk);

	public void setCMYK(int cian, int magenta, int yellow, int key);

	public int[] getCMYK();

	public CMYK getICMYK();

	public void setCian(int cian);

	public void setMagenta(int magenta);

	public void setYellow(int yellow);

	public void setKey(int key);

	public int getCian();

	public int getMagenta();

	public int getYellow();

	public int getKey();
}
