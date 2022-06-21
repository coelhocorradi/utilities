package br.com.utilities.color;

import java.awt.Color;

import br.com.utilities.enums.EColorChannels;

public interface IColorBuilder {

	public abstract float getChannel(EColorChannels channel);

	/**
	 *
	 * R' = R/255
	 * 
	 * G' = G/255
	 * 
	 * B' = B/255
	 * 
	 * K = 1-max(R', G', B')
	 * 
	 * C = (1-R'-K) / (1-K)
	 * 
	 * M = (1-G'-K) / (1-K)
	 * 
	 * Y = (1-B'-K) / (1-K)
	 * 
	 * 
	 */
	default int[] rgbToCmyk(int r, int g, int b) {
		double percentageR = r / 255.0 * 100;
		double percentageG = g / 255.0 * 100;
		double percentageB = b / 255.0 * 100;

		double k = 100 - Math.max(Math.max(percentageR, percentageG), percentageB);

		if (k == 100) {
			return new int[] { 0, 0, 0, 100 };
		}

		int c = (int) ((100 - percentageR - k) / (100 - k) * 100);
		int m = (int) ((100 - percentageG - k) / (100 - k) * 100);
		int y = (int) ((100 - percentageB - k) / (100 - k) * 100);

		int[] cmyk = new int[] { c, m, y, (int) k };

		return cmyk;
	}

	default int[] rgbToCmyk(int[] rgb) {
		return rgbToCmyk(rgb[0], rgb[1], rgb[2]);
	}

	/**
	 * 
	 * R = 255 × (1-C) × (1-K)
	 * 
	 * G = 255 × (1-M) × (1-K)
	 * 
	 * B = 255 × (1-Y) × (1-K)
	 * 
	 * 
	 * @param c
	 * @param m
	 * @param y
	 * @param k
	 * @return
	 */
	default int[] cmykToRgb(int c, int m, int y, int k) {

		int r = 255 * (1 - c / 100) * (1 - k / 100);
		int g = 255 * (1 - m / 100) * (1 - k / 100);
		int b = 255 * (1 - y / 100) * (1 - k / 100);

		int[] rgb = new int[] { r, g, b };

		return rgb;
	}

	default int[] cmykToRgb(int[] cmyk) {
		return cmykToRgb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
	}

	default int[] hsbToRgb(float hue, float saturation, float brightness) {

		int rgbx = Color.HSBtoRGB(hue, saturation, brightness);
		int r = (rgbx >> 16) & 0xFF;
		int g = (rgbx >> 8) & 0xFF;
		int b = rgbx & 0xFF;

		int[] rgb = new int[] { r, g, b };

		return rgb;
	}

	default int[] hsbToRgb(float[] hsb) {
		return hsbToRgb(hsb[0], hsb[1], hsb[2]);
	}

	/**
	 *
	 *
	 * float hue = hsb[0];
	 * 
	 * float saturation = hsb[1];
	 * 
	 * float brightness = hsb[2];
	 * 
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	default float[] rgbToHsb(int r, int g, int b) {
		return Color.RGBtoHSB(r, g, b, null);
	}

	default float[] rgbToHsb(int[] rgb) {
		return Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], null);
	}

	default float[] cmykToHsb(int c, int m, int y, int k) {
		int[] rgb = cmykToRgb(c, m, y, k);
		return rgbToHsb(rgb[0], rgb[1], rgb[2]);
	}

	default float[] cmykToHsb(int[] cmyk) {
		return cmykToHsb(cmyk[0], cmyk[1], cmyk[2], cmyk[3]);
	}

	default int[] hsbToCmyk(float h, float s, float b) {
		int[] rgb = hsbToRgb(h, s, b);
		return rgbToCmyk(rgb[0], rgb[1], rgb[2]);
	}

	default int[] hsbToCmyk(float[] hsb) {
		return hsbToCmyk(hsb[0], hsb[1], hsb[2]);
	}

}
