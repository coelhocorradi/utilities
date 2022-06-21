package br.com.utilities.datetime;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public abstract class IntervalUtils {

	public static long getInterval(Date start, Date end, ChronoUnit chronoUnit) {
		return getDuration(start, end).get(chronoUnit);
	}

	public static long getIntervalOnYears(Date start, Date end) {
		return getDuration(start, end).get(ChronoUnit.YEARS);
	}

	public static double getIntervalOnDoubleYears(Date start, Date end) {
		double months = getIntervalOnDoubleMonths(start, end);
		return months / 12;
	}

	public static long getIntervalOnMonths(Date start, Date end) {
		return getDuration(start, end).get(ChronoUnit.MONTHS);
	}

	public static double getIntervalOnDoubleMonths(Date start, Date end) {
		double days = getIntervalOnDoubleDays(start, end);
		return days / 30;
	}

	public static long getIntervalOnWeeks(Date start, Date end) {
		return getDuration(start, end).get(ChronoUnit.WEEKS);
	}

	public static double getIntervalOnDoubleWeeks(Date start, Date end) {
		double days = getIntervalOnDoubleDays(start, end);
		return days / 7;
	}

	public static long getIntervalOnDays(Date start, Date end) {
		return getDuration(start, end).toDays();
	}

	public static double getIntervalOnDoubleDays(Date start, Date end) {
		double hours = getIntervalOnDoubleHours(start, end);
		return hours / 24;
	}

	public static long getIntervalOnHours(Date start, Date end) {
		return getDuration(start, end).toHours();
	}

	public static double getIntervalOnDoubleHours(Date start, Date end) {
		double minutes = getIntervalOnDoubleMinutes(start, end);
		return minutes / 60;
	}

	public static long getIntervalOnMinutes(Date start, Date end) {
		return getDuration(start, end).toMinutes();
	}

	public static double getIntervalOnDoubleMinutes(Date start, Date end) {
		double seconds = getIntervalOnDoubleSeconds(start, end);
		return seconds / 60;
	}

	public static long getIntervalOnSeconds(Date start, Date end) {
		return getDuration(start, end).getSeconds();
	}

	public static double getIntervalOnDoubleSeconds(Date start, Date end) {
		long millis = getIntervalOnMilliseconds(start, end);
		return (double) millis / (double) 1000;
	}

	public static long getIntervalOnMilliseconds(Date start, Date end) {
		return getDuration(start, end).toMillis();
	}

	public static long getIntervalOnNanoseconds(Date start, Date end) {
		return getDuration(start, end).toNanos();
	}

	public static Duration getDuration(Date start, Date end) {
		Instant before = start.toInstant();
		Instant after = end.toInstant();

		Duration duration = Duration.between(before, after);

		return duration;
	}

	public static String toSimpletime(Date start, Date end) {
		Instant before = start.toInstant();
		Instant after = end.toInstant();

		Duration duration = Duration.between(before, after);

		long hours = duration.toHours();
		long minutes = duration.toMinutes() % 60;
		// long seconds = duration.getSeconds() % 3600;

		String hms = String.format("%02d:%02d", hours, minutes);

		return hms;
	}

	public static String toSimpletime(long hours, long minutes) {

		hours = hours + minutes / 60;
		minutes = minutes % 60;

		String hms = String.format("%02d:%02d", hours, minutes);

		return hms;
	}

	public static String toSimpletimePlusDays(long days, long hours, long minutes) {
		days = days + (hours + (minutes / 60)) / 24;
		hours = (hours + minutes / 60) % 24;
		minutes = minutes % 60;

		String dhms = String.format("%d dias e %02d:%02d horas", days, hours, minutes);

		return dhms;
	}

}
