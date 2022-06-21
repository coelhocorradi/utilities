package br.com.utilities.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.google.common.base.Strings;

import br.com.utilities.regexes.RegexUtils;

/**
 * 
 * @author gustavo
 * @version 1.0
 *
 */
public final class DateUtils {

	public static final byte TO_START_OF_MONTH = 0x00;

	public static final byte TO_END_OF_MONTH = 0x01;

	public static final byte TO_START_OF_DAY = 0x10;

	public static final byte TO_END_OF_DAY = 0x11;

	public static final byte TO_FIRST_DAY_OF_MONTH_AFTER = 0x21;

	public static final byte TO_LAST_DAY_OF_MONTH_BEFORE = 0x22;

	public Calendar c;

	public static DateUtils self;

	/**
	 * 
	 * @return
	 */
	public static final DateUtils instance() {
		if (self == null) {
			self = new DateUtils();
		}
		return self;
	}

	/**
	 * 
	 * @return
	 */
	public static final DateUtils newInstance() {
		self = new DateUtils();
		return self;
	}

	/**
	 * 
	 * @return
	 */
	public static final Date now() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public DateUtils setCalendar(Calendar calendar) {
		c = calendar;
		return this;
	}

	/**
	 * 
	 * @return
	 */
	public Calendar getCalendar() {
		return c;
	}

	/**
	 * 
	 * @return
	 */
	public Date getTime() {
		return c.getTime();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public DateUtils setDate(Date date) {
		if (date != null) {
			c.setTime(date);
		}
		return this;
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
	 * @param field
	 * @param amount
	 * @return
	 */
	public DateUtils add(int field, int amount) {
		c.add(field, amount);
		return this;
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateUtils addYears(int amount) {
		return add(Calendar.YEAR, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils addMonths(int amount) {
		return add(Calendar.MONTH, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils addDays(int amount) {
		return add(Calendar.DAY_OF_MONTH, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils addHours(int amount) {
		return add(Calendar.HOUR_OF_DAY, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils addMinutes(int amount) {
		return add(Calendar.MINUTE, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils addSeconds(int amount) {
		return add(Calendar.SECOND, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils addMilliseconds(int amount) {
		return add(Calendar.MILLISECOND, amount);
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
	 * @param field
	 * @param amount
	 * @return
	 */
	public DateUtils sub(int field, int amount) {
		c.add(field, -1 * amount);
		return this;
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateUtils subYears(int amount) {
		return sub(Calendar.YEAR, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils subMonths(int amount) {
		return sub(Calendar.MONTH, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils subDays(int amount) {
		return sub(Calendar.DAY_OF_MONTH, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils subHours(int amount) {
		return sub(Calendar.HOUR_OF_DAY, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils subMinutes(int amount) {
		return sub(Calendar.MINUTE, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils subSeconds(int amount) {
		return sub(Calendar.SECOND, amount);
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
	 * @param amount
	 * @return
	 */
	public DateUtils subMiliseconds(int amount) {
		return sub(Calendar.MILLISECOND, amount);
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
	 * @param field
	 * @param value
	 * @return
	 */
	public DateUtils set(int field, int value) {
		c.set(field, value);
		return this;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateUtils setYears(int value) {
		return set(Calendar.YEAR, value);
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
	 * @param value
	 * @return
	 */
	public DateUtils setMonths(int value) {
		return set(Calendar.MONTH, value);
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
	 * @param value
	 * @return
	 */
	public DateUtils setDays(int value) {
		return set(Calendar.DAY_OF_MONTH, value);
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

	public DateUtils setTime(Date time) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(time);
		set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
		set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
		set(Calendar.SECOND, c1.get(Calendar.SECOND));
		set(Calendar.MILLISECOND, c1.get(Calendar.MILLISECOND));
		return self;
	}

	public DateUtils setTime(long time) {
		return setTime(new Date(time));
	}

	/**
	 * string no formato HH:mm:ss.SSS
	 * 
	 * @param time
	 * @return
	 */
	public DateUtils setTime(String time) {
		return setTime(parse(Formats.TIME_MILI, time));
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
		return setTime(date, parse(Formats.TIME_MILI, time));
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
	 * @param value
	 * @return
	 */
	public DateUtils setHours(int value) {
		return set(Calendar.HOUR, value);
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
	 * @param value
	 * @return
	 */
	public DateUtils setMinutes(int value) {
		return set(Calendar.MINUTE, value);
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
	 * @param value
	 * @return
	 */
	public DateUtils setSeconds(int value) {
		return set(Calendar.SECOND, value);
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
	 * @param value
	 * @return
	 */
	public DateUtils setMilliseconds(int value) {
		return set(Calendar.MILLISECOND, value);
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
	 */
	public void clear() {
		c = null;
		self = null;
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

	/**
	 * return true if date inside of range, false otherwise
	 * 
	 * @param before
	 * @param after
	 * @param myDate
	 * @return
	 */
	public static final boolean between(Date startDate, Date endDate, Date myDate) {
		return myDate.after(startDate) && myDate.before(endDate);
	}

	/**
	 * return true if date is out of range, false otherwise
	 * 
	 * @param before
	 * @param after
	 * @param myDate
	 * @return
	 */
	public static final boolean outOfRange(Date startDate, Date endDate, Date myDate) {
		return myDate.before(startDate) || myDate.after(endDate);
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
			case TO_START_OF_MONTH: {
				c = forceStartOfMonth(c);
			}
				break;
			case TO_END_OF_MONTH: {
				c = forceEndOfMonth(c);
			}
				break;
			case TO_START_OF_DAY: {
				c = forceStartOfDay(c);
			}
				break;
			case TO_END_OF_DAY: {
				c = forceEndOfDay(c);
			}
				break;
			case TO_FIRST_DAY_OF_MONTH_AFTER: {
				c = firstDayOfMonthAfter(c);
			}
				break;
			case TO_LAST_DAY_OF_MONTH_BEFORE: {
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
	 * calcule range of durations
	 * 
	 * @author gustavo
	 *
	 */
	public static class Range {

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Duration getDuration(Date start, Date end) {
			return Duration.between(start.toInstant(), end.toInstant());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Duration getDuration(Calendar start, Calendar end) {
			return Duration.between(start.getTime().toInstant(), end.getTime().toInstant());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @param unit
		 * @return
		 */
		public static Long getDuration(Date start, Date end, ChronoUnit unit) {
			return getDuration(start, end).get(unit);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @param unit
		 * @return
		 */
		public static Long getDuration(Calendar start, Calendar end, ChronoUnit unit) {
			return getDuration(start.getTime(), end.getTime(), unit);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long forever(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.FOREVER);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long forever(Calendar start, Calendar end) {
			return forever(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long eras(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.ERAS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long eras(Calendar start, Calendar end) {
			return eras(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long millennia(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.MILLENNIA);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long millennia(Calendar start, Calendar end) {
			return millennia(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long centuries(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.CENTURIES);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long centuries(Calendar start, Calendar end) {
			return centuries(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long decades(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.DECADES);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long decades(Calendar start, Calendar end) {
			return decades(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long years(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.YEARS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long years(Calendar start, Calendar end) {
			return years(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long months(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.MONTHS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long months(Calendar start, Calendar end) {
			return months(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long weeks(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.WEEKS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long weeks(Calendar start, Calendar end) {
			return weeks(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long days(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.DAYS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long days(Calendar start, Calendar end) {
			return days(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long halfDays(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.HALF_DAYS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long hasfDays(Calendar start, Calendar end) {
			return halfDays(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long hours(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.HOURS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long hours(Calendar start, Calendar end) {
			return hours(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long minutes(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.MINUTES);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long minutes(Calendar start, Calendar end) {
			return minutes(start.getTime(), start.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long seconds(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.SECONDS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long seconds(Calendar start, Calendar end) {
			return seconds(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long millis(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.MILLIS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long millis(Calendar start, Calendar end) {
			return millis(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long micros(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.MICROS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long micros(Calendar start, Calendar end) {
			return micros(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long nanos(Date start, Date end) {
			return getDuration(start, end, ChronoUnit.NANOS);
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @return
		 */
		public static Long nanos(Calendar start, Calendar end) {
			return nanos(start.getTime(), end.getTime());
		}

		/**
		 * 
		 * [start,end] used to first range of time to compare
		 * 
		 * [before,after] used to second range of time to compare
		 * 
		 * @param start
		 * @param end
		 * @param before
		 * @param after
		 * @param unit
		 * @return
		 */
		public static int compareTo(Date start, Date end, Date before, Date after, ChronoUnit unit) {
			return getDuration(start, end, unit).compareTo(getDuration(before, after, unit));
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @param before
		 * @param after
		 * @param unit
		 * @return
		 */
		public static int compareTo(Calendar start, Calendar end, Calendar before, Calendar after, ChronoUnit unit) {
			return compareTo(start.getTime(), end.getTime(), before.getTime(), after.getTime(), unit);
		}

		/**
		 * [start,end] used to first range of time to compare
		 * 
		 * value is range calculated for other times
		 * 
		 * @param start
		 * @param end
		 * @param value
		 * @param unit
		 * @return
		 */
		public static int compareTo(Date start, Date end, Long value, ChronoUnit unit) {
			return getDuration(start, end, unit).compareTo(Duration.of(value, unit).get(unit));
		}

		/**
		 * 
		 * @param start
		 * @param end
		 * @param value
		 * @param unit
		 * @return
		 */
		public static int compareTo(Calendar start, Calendar end, Long value, ChronoUnit unit) {
			return compareTo(start.getTime(), end.getTime(), value, unit);
		}

		/**
		 * 
		 * @param first
		 * @param second
		 * @param unit
		 * @return
		 */
		public static int compareTo(Long first, Long second, ChronoUnit unit) {
			return Duration.of(first, unit).compareTo(Duration.of(second, unit));
		}

	}

	/**
	 * 
	 * @author gustavo
	 *
	 */
	public static class TimeDiff {

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMonths(Calendar before, Calendar after) {
			int difmonth = after.get(Calendar.MONTH) - before.get(Calendar.MONTH);
			int difyears = (after.get(Calendar.YEAR) - before.get(Calendar.YEAR));
			return difmonth + difyears * 12;
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMonths(Date before, Date after) {
			Calendar cbefore = Calendar.getInstance();
			cbefore.setTime(before);
			Calendar cafter = Calendar.getInstance();
			cafter.setTime(after);
			return calculateDiffMonths(cbefore, cafter);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffDays(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffDays(Calendar before, Calendar after) {
			return calculateDiffDays(before.getTime(), after.getTime());
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffHours(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffHours(Calendar before, Calendar after) {
			return calculateDiffHours(before.getTime(), after.getTime());
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMinutes(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMinutes(Calendar before, Calendar after) {
			return calculateDiffMinutes(before.getTime(), after.getTime());
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffSeconds(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.SECONDS.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffSeconds(Calendar before, Calendar after) {
			return calculateDiffSeconds(before.getTime(), after.getTime());
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMilliseconds(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.MILLISECONDS.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiifMilliseconds(Calendar before, Calendar after) {
			return calculateDiffMilliseconds(before.getTime(), after.getTime());
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMicroseconds(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.MICROSECONDS.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffMicroseconds(Calendar before, Calendar after) {
			return calculateDiffMicroseconds(before.getTime(), after.getTime());
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffNanoseconds(Date before, Date after) {
			long diff = after.getTime() - before.getTime();
			return TimeUnit.NANOSECONDS.convert(diff, TimeUnit.MILLISECONDS);
		}

		/**
		 * 
		 * @param before
		 * @param after
		 * @return
		 */
		public static long calculateDiffNanoseconds(Calendar before, Calendar after) {
			return calculateDiffNanoseconds(before.getTime(), after.getTime());
		}
	}

	/**
	 * order asc
	 * 
	 * @param dates
	 * @return
	 */
	public static final List<Date> orderAsc(List<Date> dates) {
		return dates.stream().sorted((date1, date2) -> {
			return date1.compareTo(date2);
		}).collect(Collectors.toList());
	}

	/**
	 * order desc
	 * 
	 * @param dates
	 * @return
	 */
	public static final List<Date> orderDesc(List<Date> dates) {
		return dates.stream().sorted((date1, date2) -> {
			return -1 * date1.compareTo(date2);
		}).collect(Collectors.toList());
	}

	/**
	 * return true if mydate is hither of date + amount, false otherwise
	 * 
	 * @param before
	 * @param myDate
	 * @param amount
	 * @param field  - Use Calendar.Fields
	 * @return
	 */
	public static final boolean higtherOf(Date before, Date myDate, int amount, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(before);
		c.add(field, amount);
		return c.getTime().after(before);
	}

	/**
	 * return true if mydate is lower of date - amount, false otherwise
	 * 
	 * @param after
	 * @param myDate
	 * @param amount
	 * @param field  - Use Calendar.Fields
	 * @return
	 */
	public static final boolean lowerOf(Date after, Date myDate, int amount, int field) {
		Calendar c = Calendar.getInstance();
		c.setTime(after);
		c.add(field, -1 * amount);
		return c.getTime().before(after);
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
		return Format.todo(format, date);
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
		return Format.todo(para, Parse.todo(de, data));
	}

	/**
	 * Format date to string
	 * 
	 * @author gustavo
	 *
	 */
	public static final class Format {

		/**
		 * 
		 * @param format
		 * @param date
		 * @return
		 */
		protected static final String todo(String format, Date date) {
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
			return Format.todo(Formats.DATE, date);
		}

		public static final String toDateStamp(Date date) {
			return Format.todo(Formats.DATE_STAMP, date);
		}

		/**
		 * 
		 * @param date
		 * @return
		 */
		public static final String toDateTime(Date date) {
			return Format.todo(Formats.DATE_TIME, date);
		}

		public static final String toCanonical(Date date) {
			return Format.todo(Formats.CANONICAL, date);
		}

		public static final String toSimpleTimestamp(Date date) {
			return Format.todo(Formats.SIMPLE_TIMESTAMP, date);
		}

		/**
		 * 
		 * @param date
		 * @return
		 */
		public static final String toCalendarDate(Date date) {
			return Format.todo(Formats.CALENDAR_DATE, date);
		}

		/**
		 * 
		 * @param date
		 * @return
		 */
		public static final String toCalendar(Date date) {
			return Format.todo(Formats.CALENDAR, date);
		}

		/**
		 * 
		 * @param date
		 * @return
		 */
		public static final String toDateISO8601(Date date) {
			return Format.todo(Formats.DATE_ISO8601, date);
		}

		/**
		 * 
		 * @param date
		 * @return
		 */
		public static final String picoSecondsStamp(Date date) {
			return Format.todo(Formats.TIMESTAMP_PICO, date);
		}
	}

	/**
	 * parse String to Date
	 * 
	 * @see DateUtils.Formats
	 * @param format - Use DateUtils.Formats
	 * @param source
	 * @return
	 */
	public static final Date parse(String format, String source) {
		return Parse.todo(format, source);
	}

	/**
	 * 
	 * @author gustavo
	 *
	 */
	public static final class Parse {

		/**
		 * 
		 * @param format
		 * @param source
		 * @return
		 */
		protected static final Date todo(String format, String source) {
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
			return Parse.todo(Formats.DATE, source);
		}

		/**
		 * 
		 * @param source
		 * @return
		 */
		public static final Date fromDatestamp(String source) {
			return Parse.todo(Formats.DATE_STAMP, source);
		}

		/**
		 * 
		 * @param source
		 * @return
		 */
		public static final Date fromDateTime(String source) {
			return Parse.todo(Formats.DATE_TIME, source);
		}

		public static final Date fromCanonical(String source) {
			return Parse.todo(Formats.CANONICAL, source);
		}

		/**
		 * 
		 * @param source
		 * @return
		 */
		public static final Date fromSimpleTimestamp(String source) {
			return Parse.todo(Formats.SIMPLE_TIMESTAMP, source);
		}

		/**
		 * 
		 * @param source
		 * @return
		 */
		public static final Date fromCalendarDate(String source) {
			return Parse.todo(Formats.CALENDAR_DATE, source);
		}

		/**
		 * 
		 * @param source
		 * @return
		 */
		public static final Date fromCalendar(String source) {
			return Parse.todo(Formats.CALENDAR, source);
		}

		/**
		 * 
		 * @param source
		 * @return
		 */
		public static final Date fromDateISO8601(String source) {
			return Parse.todo(Formats.DATE_ISO8601, source);
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

	/**
	 * 
	 * @param birthday
	 * @return
	 */
	public static final long howOldAre(Date birthday) {
		return Range.years(birthday, now());
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
		SimpleDateFormat gmt = new SimpleDateFormat(Formats.TIMESTAMP);
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
		SimpleDateFormat gmt = new SimpleDateFormat(Formats.TIMESTAMP_PICO);
		gmt.setTimeZone(TimeZone.getTimeZone("GMT"));
		String id = gmt.format(new Date());
		return Long.parseLong(id);
	}

	/**
	 * 
	 * @return
	 */
	public static final Integer generateId() {
		SimpleDateFormat gmt = new SimpleDateFormat(Formats.TIMESTAMP_PICO);
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
	 * 
	 * @author gustavo
	 *
	 */
	public static final class Formats {

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

	public static Long convert(Date date) {
		return date != null ? date.getTime() : null;
	}

	public static Date convert(Long date) {
		return date != null ? new Date(date) : null;
	}

	public static String toLongString(Date date) {
		return date != null ? Long.toString(date.getTime()) : "";
	}

	public static Date getLongString(String date) {
		return date != null ? new Date(Long.parseLong(date)) : null;
	}

	/**
	 * -1 se periodo menor que o determinado, 0 se igual e 1 se maior
	 * 
	 * @param begin
	 * @param end
	 * @param field
	 * @param amount
	 * @return
	 */
	public static int periodMoreOfThan(Date begin, Date end, int field, long amount) {
		Long p = end.getTime() - begin.getTime(), pp = null;
		switch (field) {
		case Calendar.MILLISECOND: {
			pp = amount;
		}
			break;
		case Calendar.SECOND: {
			pp = amount * 1000;
		}
			break;
		case Calendar.MINUTE: {
			pp = amount * 60 * 1000;
		}
			break;
		case Calendar.HOUR:
		case Calendar.HOUR_OF_DAY: {
			pp = amount * 60 * 60 * 1000;
		}
			break;
		case Calendar.DAY_OF_MONTH:
		default: {
			pp = amount * 24 * 60 * 60 * 1000;
		}
			break;
		}
		return p.compareTo(pp);
	}

	public static long compare(Date d1, Date d2) {
		return Long.compare(d1.getTime(), d2.getTime());
	}

	public static Date max(Date... dates) {
		Date result = null;
		if (dates != null) {
			if (dates.length >= 1) {
				result = dates[0];
				for (Date date : dates) {
					if (result == null && date != null) {
						result = date;
					} else if (date != null) {
						if (Long.compare(result.getTime(), date.getTime()) < 0) {
							result = date;
						}
					}
				}
			}
		}
		return result;
	}

	public static Date min(Date... dates) {
		Date result = null;
		if (dates != null) {
			if (dates.length >= 1) {
				result = dates[0];
				for (Date date : dates) {
					if (result == null && date != null) {
						result = date;
					} else if (date != null) {
						if (Long.compare(result.getTime(), date.getTime()) > 0) {
							result = date;
						}
					}
				}
			}
		}
		return result;
	}
}
