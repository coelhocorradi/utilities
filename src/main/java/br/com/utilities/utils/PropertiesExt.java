package br.com.utilities.utils;

import java.util.Properties;

public class PropertiesExt {

	private static PropertiesExt self;

	private Properties p;

	public static final PropertiesExt builder() {
		if (self == null) {
			self = new PropertiesExt();
		}
		return self;
	}

	private PropertiesExt() {
		p = System.getProperties();
	}

	public PropertiesExt setProperty(String key, String value) {
		p.setProperty(key, value);
		return self;
	}

	public Properties build() {
		self = null;
		return p;
	}

}
