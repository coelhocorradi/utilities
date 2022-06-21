package br.com.utilities.datetime;

/**
 * 
 * @author gustavo
 *
 */
public abstract class DateFormats {

	public static final String MILI = "SSS";

	public static final String MICRO = "SSSSSS";

	public static final String NANO = "SSSSSSSSS";

	public static final String PICO = "SSSSSSSSSSSS";

	public static final String SIMPLE_TIME = "HH:mm";

	public static final String HM_STAMP = "HHmm";

	public static final String TIME = "HH:mm:ss";

	public static final String TIME_MILI = TIME + "." + MILI;

	public static final String TIME_MICRO = TIME + "." + MICRO;

	public static final String TIME_NANO = TIME + "." + NANO;

	public static final String TIME_PICO = TIME + "." + PICO;

	public static final String HMS_STAMP = "HHmmss";

	public static final String CALENDAR_DATE = "dd/MM/yyyy";

	public static final String DATE = "yyyy-MM-dd";

	public static final String DATE_STAMP = "yyyyMMdd";

	public static final String DATE_TIME = DATE + " " + TIME;

	public static final String CANONICAL = DATE_TIME;

	public static final String DATE_TIME_MILI = DATE_TIME + "." + MILI;

	public static final String DATE_TIME_MICRO = DATE_TIME + "." + MICRO;

	public static final String DATE_TIME_NANO = DATE_TIME + "." + NANO;

	public static final String DATE_TIME_PICO = DATE_TIME + "." + PICO;

	public static final String SIMPLE_TIMESTAMP = "yyyyMMddHHmmss";

	public static final String DATE_TIME_TOTVS_S = "ddMMyyyy HHmmss";

	public static final String TIMESTAMP = "yyyyMMddHHmmssSSS";

	public static final String TIMESTAMP_MICRO = "yyyyMMddHHmmssSSSSSS";

	public static final String TIMESTAMP_NANO = "yyyyMMddHHmmssSSSSSSSSS";

	public static final String TIMESTAMP_PICO = "yyyyMMddHHmmssSSSSSSSSSSSS";

	public static final String CALENDAR = CALENDAR_DATE + " " + TIME;

	public static final String CALENDAR_MILI = CALENDAR + "." + MILI;

	public static final String CALENDAR_MICRO = CALENDAR + "." + MICRO;

	public static final String CALENDAR_NANO = CALENDAR + "." + NANO;

	public static final String CALENDAR_PICO = CALENDAR + "." + PICO;

	public static final String DATE_ISO8601 = DATE + "'T'" + TIME;

	public static final String DATE_ISO8601_MILI = DATE_ISO8601 + "." + MILI;

	public static final String DATE_ISO8601_MICRO = DATE_ISO8601 + "." + MICRO;

	public static final String DATE_ISO8601_NANO = DATE_ISO8601 + "." + NANO;

	public static final String DATE_ISO8601_PICO = DATE_ISO8601 + "." + PICO;
	
	public static final String JAVA_DEFAULT = "dow mon dd hh:mm:ss zzz yyy";
}