package br.com.utilities.color;

public class RGB extends ColorBuilder {//implements IColorBuilder, ICMYK, IHSB {

	public RGB() {
	}

	public RGB(int[] rgb) {
		setRGB(rgb);
	}

	public RGB(int red, int green, int blue) {
		setRGB(red, green, blue);
	}

}
