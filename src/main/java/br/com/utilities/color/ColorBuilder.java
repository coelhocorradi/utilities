package br.com.utilities.color;

import br.com.utilities.enums.EColorChannels;

public class ColorBuilder implements IColorBuilder, IHSB, IRGB, ICMYK {

	// red, green, blue
	private int[] rgb = { 0, 0, 0 };

	public static final int KEY = 3;
	// cian, magenta, yellow, key
	private int[] cmyk = { 0, 0, 0, 0 };

	// hue, saturation, brightness
	private float[] hsb = { 0f, 0f, 0f };

	@Override
	public void setRGB(int red, int green, int blue) {
		rgb[0] = red;
		rgb[1] = green;
		rgb[2] = blue;
		cmyk = rgbToCmyk(rgb);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setRGB(int[] rgb) {
		this.rgb = rgb;
		cmyk = rgbToCmyk(rgb);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setCMYK(int cian, int magenta, int yellow, int key) {
		cmyk[0] = cian;
		cmyk[1] = magenta;
		cmyk[2] = yellow;
		cmyk[3] = key;
		rgb = cmykToRgb(cmyk);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setCMYK(int[] cmyk) {
		this.cmyk = cmyk;
		rgb = cmykToRgb(cmyk);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setHSB(float hue, float saturation, float brightness) {
		hsb[0] = hue;
		hsb[1] = saturation;
		hsb[2] = brightness;
		rgb = hsbToRgb(hsb);
		cmyk = rgbToCmyk(rgb);
	}

	@Override
	public void setHSB(float[] hsb) {
		this.hsb = hsb;
		rgb = hsbToRgb(hsb);
		cmyk = rgbToCmyk(rgb);
	}

	@Override
	public void setCian(int cian) {
		cmyk[0] = cian;
		rgb = cmykToRgb(cmyk);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setMagenta(int magenta) {
		cmyk[1] = magenta;
		rgb = cmykToRgb(cmyk);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setYellow(int yellow) {
		cmyk[2] = yellow;
		rgb = cmykToRgb(cmyk);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setKey(int key) {
		cmyk[3] = key;
		rgb = cmykToRgb(cmyk);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setRed(int red) {
		rgb[0] = red;
		cmyk = rgbToCmyk(rgb);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setGreen(int green) {
		rgb[1] = green;
		cmyk = rgbToCmyk(rgb);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setBlue(int blue) {
		rgb[2] = blue;
		cmyk = rgbToCmyk(rgb);
		hsb = rgbToHsb(rgb);
	}

	@Override
	public void setHue(float hue) {
		hsb[0] = hue;
		rgb = hsbToRgb(hsb);
		cmyk = rgbToCmyk(rgb);
	}

	@Override
	public void setSaturation(float saturation) {
		hsb[1] = saturation;
		rgb = hsbToRgb(hsb);
		cmyk = rgbToCmyk(rgb);
	}

	@Override
	public void setBrightness(float brightness) {
		hsb[2] = brightness;
		rgb = hsbToRgb(hsb);
		cmyk = rgbToCmyk(rgb);
	}

	@Override
	public int getRed() {
		return rgb[0];
	}

	@Override
	public int getGreen() {
		return rgb[1];
	}

	@Override
	public int getBlue() {
		return rgb[2];
	}

	@Override
	public int getCian() {
		return cmyk[0];
	}

	@Override
	public int getMagenta() {
		return cmyk[1];
	}

	@Override
	public int getYellow() {
		return cmyk[2];
	}

	@Override
	public int getKey() {
		return cmyk[3];
	}

	@Override
	public float getHue() {
		return hsb[0];
	}

	@Override
	public float getSaturation() {
		return hsb[1];
	}

	@Override
	public float getBrightness() {
		return hsb[2];
	}

	@Override
	public float getChannel(EColorChannels channel) {
		float result = 0;
		switch (channel) {
		case RED:
		case GREEN:
		case BLUE:
			result = rgb[channel.getPos()];
			break;
		case CIAN:
		case MAGENTA:
		case YELLOW:
		case KEY:
			result = cmyk[channel.getPos()];
			break;
		case HUE:
		case SATURATION:
		case BRIGHTNESS:
			result = hsb[channel.getPos()];
			break;
		default:
			break;
		}
		return result;
	}

	@Override
	public int[] getRGB() {
		return rgb;
	}

	@Override
	public int[] getCMYK() {
		return cmyk;
	}

	@Override
	public float[] getHSB() {
		return hsb;
	}

	@Override
	public CMYK getICMYK() {
		return (CMYK) this;
	}

	@Override
	public RGB getIRGB() {
		return (RGB) this;
	}

	@Override
	public HSB getIHSB() {
		return (HSB) this;
	}
}
