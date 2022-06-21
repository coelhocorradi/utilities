package br.com.utilities.color;

public interface IRGB {

	public void setRGB(int[] rgb);

	public void setRGB(int red, int green, int blue);

	public int[] getRGB();

	public RGB getIRGB();

	public void setRed(int red);

	public void setGreen(int green);

	public void setBlue(int blue);

	public int getRed();

	public int getGreen();

	public int getBlue();

}
