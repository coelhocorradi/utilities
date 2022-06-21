package br.com.utilities.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author gustavo
 *
 */
public abstract class DateParser {

	/**
	 * 
	 * @param format
	 * @param source
	 * @return
	 */
	public static final Date parse(String format, String source) {
		Date result = null;
		try {
			if (format != null && source != null) {
				result = new SimpleDateFormat(format).parse(source);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromDate(String source) {
		return parse(DateFormats.DATE, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromDatestamp(String source) {
		return parse(DateFormats.DATE_STAMP, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromDateTime(String source) {
		return parse(DateFormats.DATE_TIME, source);
	}

	public static final Date fromCanonical(String source) {
		return parse(DateFormats.CANONICAL, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromSimpleTimestamp(String source) {
		return parse(DateFormats.SIMPLE_TIMESTAMP, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromCalendarDate(String source) {
		return parse(DateFormats.CALENDAR_DATE, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromCalendar(String source) {
		return parse(DateFormats.CALENDAR, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date fromDateISO8601(String source) {
		return parse(DateFormats.DATE_ISO8601, source);
	}

	/**
	 * 
	 * @param source
	 * @return
	 */
	public static final Date _fromDateISO8601_(String source) {
		Date result = null;
		if (source != null) {			
			SimpleDateFormat df = new SimpleDateFormat("Y-m-d'T'H:i:s.S");
			Calendar c = Calendar.getInstance();
			try {
				c.setTime(df.parse(source));
				c.clear(Calendar.ZONE_OFFSET);
				result = c.getTime();
			} catch (ParseException e) {					
				e.printStackTrace();
			}
		}
		return result;
	}
}