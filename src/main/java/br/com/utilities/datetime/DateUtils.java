package br.com.utilities.datetime;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

import br.com.utilities.regexes.RegexUtils;
import br.com.utilities.utils.Pair;

/**
 * 
 * @author gustavo
 * @version 1.0
 *
 */
public abstract class DateUtils {

	/**
	 * 
	 * @return
	 */
	public static final Date now() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Amount seguir� o padr�o do php XYXMXDXHXIXSXE onde Y s�o anos, M s�o meses, D
	 * s�o dias, H s�o horas, I s�o minutos e S s�o segundos e E s�o milisegundos
	 * 
	 * @param date
	 * @param amount
	 * @return se erro, retorna a data sem altera��o
	 */
	public static final Date opr(Date date, String amount, int signal) {
		Date result = null;
		try {
			if (amount == null) {
				throw new Exception("Amount can not be null!");
			}
			if (amount.trim().isEmpty()) {
				throw new Exception("Amount can not be empty!");
			}
			String regex = "^(\\+|\\-)?((\\d{1,})Y)?((\\d{1,})M)?((\\d{1,})D)?((\\d{1,})H)?((\\d{1,})I)?((\\d{1,})S)?((\\d{1,})E)?$";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(amount);
			if (m.find()) {

				Calendar c = Calendar.getInstance();
				c.setTime(date);

				signal = signal != 0 ? signal / Math.abs(signal) : 1;
				if (!m.group(1).isEmpty()) {
					if (m.group(1).equals("-")) {
						signal = -1 * signal;
					}
				}
				if (!m.group(3).isEmpty()) {
					int years = Integer.parseInt(m.group(3));
					c.add(Calendar.YEAR, signal * years);
				}
				if (!m.group(5).isEmpty()) {
					int months = Integer.parseInt(m.group(5));
					c.add(Calendar.MONTH, signal * months);
				}
				if (!m.group(7).isEmpty()) {
					int days = Integer.parseInt(m.group(7));
					c.add(Calendar.DAY_OF_MONTH, signal * days);
				}
				if (!m.group(9).isEmpty()) {
					int hours = Integer.parseInt(m.group(9));
					c.add(Calendar.HOUR_OF_DAY, signal * hours);
				}
				if (!m.group(11).isEmpty()) {
					int minutes = Integer.parseInt(m.group(11));
					c.add(Calendar.MINUTE, signal * minutes);
				}
				if (!m.group(13).isEmpty()) {
					int seconds = Integer.parseInt(m.group(13));
					c.add(Calendar.SECOND, signal * seconds);
				}
				if (!m.group(15).isEmpty()) {
					int milliseconds = Integer.parseInt(m.group(15));
					c.add(Calendar.MILLISECOND, signal * milliseconds);
				}

				result = c.getTime();
			} else {
				throw new Exception("Amount format is not valid!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = date;
		}
		return result;
	}

	public static final Date add(Date date, String amount) {
		return opr(date, amount, 1);
	}

	public static final Date sub(Date date, String amount) {
		return opr(date, amount, -1);
	}

	public static Pair<Date, Date> getDates(Date date, String amount) {
		Pair<Date, Date> pair = new Pair<Date, Date>();
		pair.setFirst(sub(date, amount));
		pair.setSecond(add(date, amount));
		return pair;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addYears(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.YEAR, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addMonths(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addDays(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addHours(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR_OF_DAY, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addMinutes(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MINUTE, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addSeconds(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.SECOND, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date addMilliseconds(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MILLISECOND, amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subYears(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.YEAR, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subMonth(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MONTH, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subDays(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.DAY_OF_MONTH, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subHours(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.HOUR_OF_DAY, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subMinutes(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MINUTE, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subSeconds(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.SECOND, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param amount
	 * @return
	 */
	public static final Date subMilliseconds(Date date, int amount) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.add(Calendar.MILLISECOND, -1 * amount);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setYears(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.YEAR, value);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setMonth(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.MONTH, value);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setDays(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.DAY_OF_MONTH, value);
			result = c.getTime();
		}
		return result;
	}

	public static final Date setTime(Date date, Date time) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			Calendar c1 = Calendar.getInstance();
			c1.setTime(time);
			c.set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
			c.set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
			c.set(Calendar.SECOND, c1.get(Calendar.SECOND));
			c.set(Calendar.MILLISECOND, c1.get(Calendar.MILLISECOND));
			result = c.getTime();
		}
		return result;
	}

	public static final Date setTime(Date date, long time) {
		return setTime(date, new Date(time));
	}

	/**
	 * string no formato HH:mm:ss.SSS
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static final Date setTime(Date date, String time) {
		return setTime(date, parse(DateFormats.TIME_MILI, time));
	}

	@SuppressWarnings("resource")
	public static final String getFullTime(String time) {
		String result = null;
		Pattern p = Pattern.compile(RegexUtils.fulltime);
		Matcher m = p.matcher(time);
		if (m.find()) {
			String hours = "00", minutes = "00", seconds = "00", milliseconds = "000";
			if (m.group(6) != null) {
				int size = m.group(6).length();
				if (size >= 3) {
					milliseconds = m.group(6).substring(0, 3);
				} else if (size > 0) {
					milliseconds = Strings.padEnd(m.group(6), 3, '0');
				}
			}
			if (m.group(4) != null) {
				seconds = m.group(4);
			}
			if (m.group(2) != null) {
				minutes = m.group(2);
			}
			if (m.group(1) != null) {
				hours = m.group(1);
			}
			result = new Formatter().format("$1:$2:$3.$4", hours, minutes, seconds, milliseconds).toString();
		}
		return result;
	}

	public static final Date setFullTime(Date date, String time) {
		Date result = null;
		Pattern p = Pattern.compile(RegexUtils.fulltime);
		Matcher m = p.matcher(time);
		if (m.find()) {
			String hours = "00", minutes = "00", seconds = "00", milliseconds = "000";
			if (m.group(6) != null) {
				int size = m.group(6).length();
				if (size >= 3) {
					milliseconds = m.group(6).substring(0, 3);
				} else if (size > 0) {
					milliseconds = Strings.padEnd(m.group(6), 3, '0');
				}
			}
			if (m.group(4) != null) {
				seconds = m.group(4);
			}
			if (m.group(2) != null) {
				minutes = m.group(2);
			}
			if (m.group(1) != null) {
				hours = m.group(1);
			}

			Calendar c = Calendar.getInstance();
			c.setTime(date);

			c = DateUtils.forceStartOfDay(c);
			c.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hours));
			c.set(Calendar.MINUTE, Integer.parseInt(minutes));
			c.set(Calendar.SECOND, Integer.parseInt(seconds));
			c.set(Calendar.MILLISECOND, Integer.parseInt(milliseconds));

			result = c.getTime();

		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setHours(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.HOUR_OF_DAY, value);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setMinutes(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.MINUTE, value);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setSeconds(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.SECOND, value);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param date
	 * @param value
	 * @return
	 */
	public static final Date setMilliseconds(Date date, int value) {
		Date result = null;
		if (date != null) {
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(Calendar.MILLISECOND, value);
			result = c.getTime();
		}
		return result;
	}

	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour
	 * @param minute
	 * @param second
	 * @return
	 */
	public static final Date createDate(Integer year, Integer month, Integer day, Integer hour, Integer minute,
			Integer second) {
		Calendar c = Calendar.getInstance();
		// c.clear(Calendar.AM_PM);
		c.clear(Calendar.ZONE_OFFSET);
		c.clear(Calendar.MILLISECOND);
		c.set(//
				year == null ? c.get(Calendar.YEAR) : year, //
				month == null ? c.get(Calendar.MONTH) : month - 1, //
				day == null ? c.get(Calendar.DAY_OF_MONTH) : day, //
				hour == null ? c.get(Calendar.HOUR_OF_DAY) : hour, //
				minute == null ? c.get(Calendar.MINUTE) : minute, //
				second == null ? c.get(Calendar.SECOND) : second//
		);
		return c.getTime();
	}

	public static Date forceStartOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceEndOfMonth(c).getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar forceStartOfMonth(Calendar date) {
		// date.clear(Calendar.AM_PM);
		date.clear(Calendar.ZONE_OFFSET);
		date.set(Calendar.DAY_OF_MONTH, 01);
		date.set(Calendar.HOUR_OF_DAY, 00);
		date.set(Calendar.MINUTE, 00);
		date.set(Calendar.SECOND, 00);
		date.set(Calendar.MILLISECOND, 000);
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar toStartOfMonth(Calendar date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date.getTime());
		return forceStartOfMonth(c);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date toStartOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceStartOfMonth(c).getTime();
	}

	public static Date forceStartOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceStartOfDay(c).getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar forceStartOfDay(Calendar date) {
		// date.clear(Calendar.AM_PM);
		date.clear(Calendar.ZONE_OFFSET);
		date.set(Calendar.HOUR_OF_DAY, 00);
		date.set(Calendar.MINUTE, 00);
		date.set(Calendar.SECOND, 00);
		date.set(Calendar.MILLISECOND, 000);
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar toStartOfDay(Calendar date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date.getTime());
		return forceStartOfDay(c);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date toStartOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceStartOfDay(c).getTime();
	}

	public static Date forceEndOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceEndOfMonth(c).getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar forceEndOfMonth(Calendar date) {
		// date.clear(Calendar.AM_PM);
		date.clear(Calendar.ZONE_OFFSET);
		date.set(Calendar.DAY_OF_MONTH, date.getActualMaximum(Calendar.DAY_OF_MONTH));
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
		return date;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar toEndOfMonth(Calendar date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date.getTime());
		return forceEndOfMonth(c);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date toEndOfMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceEndOfMonth(c).getTime();
	}

	public static Date forceEndOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceEndOfDay(c).getTime();
	}

	/**
	 * altera o valor do calendario
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar forceEndOfDay(Calendar date) {
		// date.clear(Calendar.AM_PM);
		date.clear(Calendar.ZONE_OFFSET);
		date.set(Calendar.HOUR_OF_DAY, 23);
		date.set(Calendar.MINUTE, 59);
		date.set(Calendar.SECOND, 59);
		date.set(Calendar.MILLISECOND, 999);
		return date;
	}

	/**
	 * cria um novo calendario sem alterar o valor do calendario base;
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar toEndOfDay(Calendar date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date.getTime());
		return forceEndOfDay(c);
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Date toEndOfDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return forceEndOfDay(c).getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar firstDayOfMonthAfter(Calendar date) {
		Calendar c = toEndOfMonth(date);
		c.add(Calendar.MILLISECOND, 001);
		return c;
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static Calendar lastDayOfMonthBefore(Calendar date) {
		Calendar c = toStartOfMonth(date);
		c.add(Calendar.MILLISECOND, -1 * 001);
		return c;
	}

	/**
	 * 
	 * @param date       Calendar|Date|Long|String
	 * @param _class     Calendar|Date|Long|String or null to return the same type
	 *                   of date
	 * @param dateFormat if _class is String or _class is null and date is string,
	 *                   dateFormat would be a valid string
	 * @return Calendar|Date|Long|String or same type of date
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <E> E toDateConvert(Object date, Class<?> _class, String dateFormat, byte convertTo)
			throws Exception {

		E result = null;

		try {
			if (date == null) {
				throw new Exception("A data n�o pode ser nula!");
			} else if (!(date instanceof Calendar || date instanceof Date || date instanceof Long
					|| date instanceof String)) {
				throw new Exception("A data precisa ser em um dos formatos indicados Calendar|Date|Long|string");
			} else if (((date instanceof String && _class == null) || _class.isInstance(String.class))
					&& (dateFormat == null || dateFormat.isEmpty())) {
				throw new Exception("O formato de convers�o da data deve ser incidado!");
			}

			Calendar c = Calendar.getInstance();

			// transformando os valores de data para calendario
			if (date instanceof String) {
				SimpleDateFormat sdf = new SimpleDateFormat();
				Date d = sdf.parse((String) date);
				c.setTime(d);
			} else if (date instanceof Long) {
				Date d = new Date((Long) date);
				c.setTime(d);
			} else if (date instanceof Date) {
				c.setTime((Date) date);
			} else if (date instanceof Calendar) {
				c.setTime(c.getTime());
			}

			// operando o tipo de convers�o de data
			switch (convertTo) {
			case DateOptions.TO_START_OF_MONTH: {
				c = forceStartOfMonth(c);
			}
				break;
			case DateOptions.TO_END_OF_MONTH: {
				c = forceEndOfMonth(c);
			}
				break;
			case DateOptions.TO_START_OF_DAY: {
				c = forceStartOfDay(c);
			}
				break;
			case DateOptions.TO_END_OF_DAY: {
				c = forceEndOfDay(c);
			}
				break;
			case DateOptions.TO_FIRST_DAY_OF_MONTH_AFTER: {
				c = firstDayOfMonthAfter(c);
			}
				break;
			case DateOptions.TO_LAST_DAY_OF_MONTH_BEFORE: {
				c = lastDayOfMonthBefore(c);
			}
				break;
			default: {
				throw new Exception("O tipo de convers�o deve ser indicada!");
			}
			}

			// transformando o resultado da data para o tipo desejado...
			if (_class != null) {
				if (_class.isInstance(Date.class)) {
					result = (E) c.getTime();
				} else if (_class.isInstance(Calendar.class)) {
					result = (E) c;
				} else if (_class.isInstance(String.class)) {
					result = (E) new SimpleDateFormat(dateFormat).format(c.getTime());
				} else if (_class.isInstance(Long.class)) {
					result = (E) ((Long) c.getTime().getTime());
				}
			}
			// ... ou definindo o tipo padr�o
			else {
				if (date instanceof String) {
					result = (E) new SimpleDateFormat(dateFormat).format(c.getTime());
				} else if (date instanceof Long) {
					result = (E) ((Long) c.getTime().getTime());
				} else if (date instanceof Date) {
					result = (E) c.getTime();
				} else if (date instanceof Calendar) {
					result = (E) c;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean sameDay(Date start, Date end) {
		return toStartOfDay(start).equals(toStartOfDay(end));
	}

	/**
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public static boolean sameDay(Calendar start, Calendar end) {
		return toStartOfDay(start).equals(toStartOfDay(end));
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isMonday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isTuesday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isWednesday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isThursday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isFriday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isWeekDay(Calendar c) {
		return isMonday(c) || isTuesday(c) || isWednesday(c) || isThursday(c) || isFriday(c);
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isSaturday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isSunday(Calendar c) {
		return c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY;
	}

	/**
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isWeekend(Calendar c) {
		return isSaturday(c) || isSunday(c);
	}

	/**
	 * 
	 * @param before
	 * @param after
	 * @return
	 */
	public static boolean isAfterday(Calendar before, Calendar after) {
		boolean result = false;
		if (before.get(Calendar.YEAR) == after.get(Calendar.YEAR)) {
			if (before.get(Calendar.MONTH) == after.get(Calendar.MONTH)) {
				return after.get(Calendar.DAY_OF_MONTH) > before.get(Calendar.DAY_OF_MONTH);
			}
		}
		return result;
	}

	public static int getField(int field) {
		return getField(now(), field);
	}

	public static int getField(Date data, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(data);
		return c.get(field);
	}

	public static int getMilliseconds(Date data) {
		return getField(data, Calendar.MILLISECOND);
	}

	public static int getSeconds(Date data) {
		return getField(data, Calendar.SECOND);
	}

	public static int getMinutes(Date data) {
		return getField(data, Calendar.MINUTE);
	}

	public static int getHourOfDay(Date data) {
		return getField(data, Calendar.HOUR_OF_DAY);
	}

	public static int getDayOfMonth(Date data) {
		return getField(data, Calendar.DAY_OF_MONTH);
	}

	public static int getMonth(Date data) {
		return getField(data, Calendar.MONTH);
	}

	public static int getYear(Date data) {
		return getField(data, Calendar.YEAR);
	}

	public static int getEra(Date data) {
		return getField(data, Calendar.ERA);
	}

	/**
	 * format Date to String
	 * 
	 * @See DateUtils.Formats
	 * @param format - Use DateUtils.Formats
	 * @param date
	 * @return
	 */
	public static final String format(String format, Date date) {
		return DateFormater.format(format, date);
	}

	/**
	 * parse String to Date
	 * 
	 * @see DateUtils.DateFormats
	 * @param format - Use DateUtils.Formats
	 * @param source
	 * @return
	 */
	public static final Date parse(String format, String source) {
		return DateParser.parse(format, source);
	}

	/**
	 * 
	 * @param birthday
	 * @return
	 */
	public static final long howOldAre(Date birthday) {
		return RangeUtils.years(birthday, now());
	}

	public static final Date dateWithoutTimezone() {
		Calendar c = Calendar.getInstance();
		c.clear(Calendar.ZONE_OFFSET);
		return c.getTime();
	}

	/*
	 * public static final Long toLong(Date date) { String stamp =
	 * format(Formats.TIMESTAMP, date); return Long.parseLong(stamp); }
	 */

	/**
	 * Generate long id using order of 10^17
	 * 
	 * yyyyMMddHHmmssSSS -> yy.yy.MMdd.HHm.mss.SSS
	 * 
	 * @return negative value
	 */
	public static final Long generatePointer() {
		SimpleDateFormat gmt = new SimpleDateFormat(DateFormats.TIMESTAMP);
		gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String id = gmt.format(new Date());
		return -1 * Long.parseLong(id);
	}

	/**
	 * Generate long id using order of 10^26
	 * 
	 * yyyyMMddHHmmssSSSSSSSSSSSS -> yy.yy.MMdd.HHm.mss.SSS.SSS.SSS.SSS
	 * 
	 * @return
	 */
	public static final Long generateLongId() {
		SimpleDateFormat gmt = new SimpleDateFormat(DateFormats.TIMESTAMP_PICO);
		gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String id = gmt.format(new Date());
		return Long.parseLong(id);
	}

	/**
	 * 
	 * @return
	 */
	public static final Integer generateId() {
		SimpleDateFormat gmt = new SimpleDateFormat(DateFormats.TIMESTAMP_PICO);
		gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String id = gmt.format(new Date());
		return Integer.parseInt(id);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return
	 * @throws Exception
	 */
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDateTime dateToLocalDateTime(Date date) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(date.getTime()), ZoneOffset.UTC);
	}

	/**
	 * 
	 * @param localDateTime
	 * @return
	 * @throws Exception
	 */
	public static Calendar localDateTimeToCalendar(LocalDateTime localDateTime) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
		return calendar;
	}

	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public static LocalDateTime calendarToLocalDateTime(Calendar calendar) {
		return LocalDateTime.ofInstant(Instant.ofEpochMilli(calendar.getTimeInMillis()), ZoneOffset.UTC);
	}

	/**
	 * seta a dataInicio no calendarioInicio com horas, minutos, segundos e
	 * milesegundos compadr�o 00:00:00:000
	 * 
	 * trocar calendarInicio por este
	 * 
	 * @param calendarInicio
	 * @param dataInicio
	 */
	public static void startCalendarAt(Calendar date, Date start) {
		if (date != null && start != null) {
			date.setTime(start);
			// date.clear(Calendar.AM_PM);
			date.clear(Calendar.ZONE_OFFSET);
			date.set(Calendar.HOUR_OF_DAY, 00);
			date.set(Calendar.MINUTE, 00);
			date.set(Calendar.SECOND, 00);
			date.set(Calendar.MILLISECOND, 000);
		}
	}

	/**
	 * seta a dataFim no calendarioFim com horas, minutos, segundos e milisegundos
	 * com padr�o 23:59:59:999
	 * 
	 * trocar calendarFinal por este
	 * 
	 * @param calendarFinal
	 * @param dataFinal
	 */
	public static void endCalendarAt(Calendar date, Date end) {
		if (date != null && end != null) {
			date = Calendar.getInstance();
			date.setTime(end);
			// date.clear(Calendar.AM_PM);
			date.clear(Calendar.ZONE_OFFSET);
			date.set(Calendar.HOUR_OF_DAY, 23);
			date.set(Calendar.MINUTE, 59);
			date.set(Calendar.SECOND, 59);
			date.set(Calendar.MILLISECOND, 999);
		}
	}

	/**
	 * seta a data inicioInicio no calendarioInicio, e a dataFim no calendarioFim.
	 * 
	 * @param calendarInicio
	 * @param calendarFinal
	 * @param dataInicio
	 * @param dataFinal
	 */
	public static void setCalendarRange(Calendar calendarInicio, Calendar calendarFinal, Date dataInicio,
			Date dataFinal) {
		if (calendarInicio != null && calendarFinal != null && dataInicio != null && dataFinal != null) {
			startCalendarAt(calendarInicio, dataInicio);
			endCalendarAt(calendarFinal, dataFinal);
		}
	}

	public static int getAge(Date birthday) {

		Calendar c = new GregorianCalendar();
		c.setTime(birthday);

		Calendar today = Calendar.getInstance();

		int age = today.get(Calendar.YEAR) - c.get(Calendar.YEAR);
		c.add(Calendar.YEAR, age);
		if (today.before(c)) {
			age--;
		}

		return age;
	}

	
}
