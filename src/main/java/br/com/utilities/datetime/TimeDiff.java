package br.com.utilities.datetime;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public abstract class TimeDiff {

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
