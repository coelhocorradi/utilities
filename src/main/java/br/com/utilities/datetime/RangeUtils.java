package br.com.utilities.datetime;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

public abstract class RangeUtils {

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
