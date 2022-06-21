package br.com.utilities.color;

public class CMYK extends ColorBuilder implements IColorBuilder, IRGB, IHSB {

	public CMYK() {
	}

	public CMYK(int[] cmyk) {
		setCMYK(cmyk);
	}

	public CMYK(int cian, int magenta, int yellow, int key) {
		setCMYK(cian, magenta, yellow, key);
	}
}
