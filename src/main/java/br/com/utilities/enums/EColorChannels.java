package br.com.utilities.enums;

public enum EColorChannels {

	RED(0), GREEN(1), BLUE(2),

	CIAN(0), MAGENTA(1), YELLOW(2), KEY(3),

	HUE(0), SATURATION(1), BRIGHTNESS(2);

	public int pos;

	EColorChannels(int pos) {
		this.pos = pos;
	}

	public int getPos() {
		return pos;
	}
}