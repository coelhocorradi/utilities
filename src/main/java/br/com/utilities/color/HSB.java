package br.com.utilities.color;

public class HSB extends ColorBuilder implements IColorBuilder, IRGB, ICMYK {

	public HSB() {
	}

	public HSB(float[] hsb) {
		setHSB(hsb);
	}

	public HSB(float hue, float saturation, float brightness) {
		setHSB(hue, saturation, brightness);
	}

}
