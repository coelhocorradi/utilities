package br.com.utilities.gsonutils;

import java.lang.reflect.Field;

import com.google.common.base.CaseFormat;
import com.google.gson.FieldNamingStrategy;

public abstract class FieldNamingStrategies {

	// private static FieldNamingStrategy lowerUnderscoreToDefaultJavaFieldFormat =
	// null;

	public static FieldNamingStrategy getLowerUnderscoreToDefaultJavaFieldFormat() {
		/*
		 * if (lowerUnderscoreToDefaultJavaFieldFormat == null) {
		 * lowerUnderscoreToDefaultJavaFieldFormat =
		 */
		return new FieldNamingStrategy() {

			@Override
			public String translateName(Field f) {
				String fname = f.getName();
				fname = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, fname);
				fname = Character.toLowerCase(fname.charAt(0)) + fname.substring(1);
				return fname;
			}
		};

		// return lowerUnderscoreToDefaultJavaFieldFormat;
	}

	// private static FieldNamingStrategy defaultJavaFieldFormatToLowerUnderscore =
	// null;

	public static FieldNamingStrategy getDefaultJavaFieldFormatToLowerUnderscore() {
		/*
		 * if (defaultJavaFieldFormatToLowerUnderscore == null) {
		 * defaultJavaFieldFormatToLowerUnderscore =
		 */
		return new FieldNamingStrategy() {

			@Override
			public String translateName(Field f) {
				String fname = "";
				char[] chars = f.getName().toCharArray();
				for (char c : chars) {
					if (Character.toString(c).toUpperCase().equals(Character.toString(c))) {
						fname += "_" + Character.toString(c).toLowerCase();
					} else {
						fname += Character.toString(c);
					}
				}
				return fname;
			}
		};

		// return defaultJavaFieldFormatToLowerUnderscore;
	}

}
