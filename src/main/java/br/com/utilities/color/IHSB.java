package br.com.utilities.color;

public interface IHSB {

	public void setHSB(float[] hsb);

	public void setHSB(float hue, float saturation, float brightness);

	public float[] getHSB();

	public HSB getIHSB();

	public void setHue(float hue);

	public void setSaturation(float saturation);

	public void setBrightness(float brightness);

	public float getHue();

	public float getSaturation();

	public float getBrightness();
}
