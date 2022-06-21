package br.com.utilities.datetime;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DateFormater {

	/**
	 * 
	 * @param format
	 * @param date
	 * @return
	 */
	public static final String format(String format, Date date) {
		String result = null;
		if (format != null && date != null) {
			result = new SimpleDateFormat(format).format(date);
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String toDate(Date date) {
		return format(DateFormats.DATE, date);
	}

	public static final String toDateStamp(Date date) {
		return format(DateFormats.DATE_STAMP, date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String toDateTime(Date date) {
		return format(DateFormats.DATE_TIME, date);
	}

	public static final String toCanonical(Date date) {
		return format(DateFormats.CANONICAL, date);
	}

	public static final String toSimpleTimestamp(Date date) {
		return format(DateFormats.SIMPLE_TIMESTAMP, date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String toCalendarDate(Date date) {
		return format(DateFormats.CALENDAR_DATE, date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String toCalendar(Date date) {
		return format(DateFormats.CALENDAR, date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String toDateISO8601(Date date) {
		return format(DateFormats.DATE_ISO8601, date);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static final String picoSecondsStamp(Date date) {
		return format(DateFormats.TIMESTAMP_PICO, date);
	}
}
