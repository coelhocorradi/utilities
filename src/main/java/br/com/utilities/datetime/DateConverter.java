package br.com.utilities.datetime;

import java.util.Date;

public abstract class DateConverter {

	public static Long convert(Date date) {
		return date != null ? date.getTime() : null;
	}

	public static Date convert(Long date) {
		return date != null ? new Date(date) : null;
	}
	
	public static String toLongString(Date date) {
		return date != null ? Long.toString(date.getTime()) : "";
	}

	public static Date fromLongString(String date) {
		return date != null ? new Date(Long.parseLong(date)) : null;
	}
	
	/**
	 * Aten��o pois alguns convers�es podem gerar resultados estranhos
	 * 
	 * @param de
	 * @param para
	 * @param data
	 * @return
	 */
	public static final String convertFormat(String de, String para, String data) {
		return DateFormater.format(para, DateParser.parse(de, data));
	}

}
