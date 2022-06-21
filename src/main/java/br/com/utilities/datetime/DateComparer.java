package br.com.utilities.datetime;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public abstract class DateComparer {

	public static long compare(Date d1, Date d2) {
		return Long.compare(d1.getTime(), d2.getTime());
	}

	public static boolean compare(Date first, Date second, ChronoUnit unit) {
		if (first == null && second == null) return true;
		if (first == null || second == null) return false;
		return Long.compare(Duration.of(first.getTime(), unit).getSeconds(),
				Duration.of(second.getTime(), unit).getSeconds()) == 0;
	}

	public static boolean compareInSeconds(Date first, Date second) {
		return compare(first, second, ChronoUnit.SECONDS);
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
}
