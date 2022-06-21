package br.com.utilities.datetime;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author gustavo
 * @version 1.0
 *
 */
public final class DateBuilder {

	public Calendar c;

	public static DateBuilder self;

	/**
	 * 
	 * @return
	 */
	public static final DateBuilder instance() {
		if (self == null) {
			self = new DateBuilder();
		}
		return self;
	}

	/**
	 * 
	 * @return
	 */
	public static final DateBuilder newInstance() {
		self = new DateBuilder();
		return self;
	}

	public DateBuilder() {
		c = Calendar.getInstance();
	}

	/**
	 * 
	 * @param calendar
	 * @return
	 */
	public DateBuilder setCalendar(Calendar calendar) {
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
	
	public DateBuilder clearOffset() {
		c.clear(Calendar.ZONE_OFFSET);
		return this;
	}

	public DateBuilder now() {
		c.setTime(new Date());	
		return this;
	}
	
	public DateBuilder startOfDay() {
		c.set(Calendar.HOUR_OF_DAY, 00);
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 00);
		c.set(Calendar.MILLISECOND, 000);	
		return this;
	}
	
	public DateBuilder startOfMonth() {
		c.set(Calendar.DAY_OF_MONTH, 01);
		return startOfDay();
	}
	
	public DateBuilder endOfDay() {
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return this;
	}
	
	public DateBuilder endOfMonth() {
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		return endOfDay();
	}

	/**
	 * 
	 * @param date
	 * @return
	 */
	public DateBuilder setDate(Date date) {
		if (date != null) {
			c.setTime(date);
		}
		return this;
	}

	/**
	 * 
	 * @param field
	 * @param amount
	 * @return
	 */
	public DateBuilder add(int field, int amount) {
		c.add(field, amount);
		return this;
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addYears(int amount) {
		return add(Calendar.YEAR, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addMonths(int amount) {
		return add(Calendar.MONTH, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addDays(int amount) {
		return add(Calendar.DAY_OF_MONTH, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addHours(int amount) {
		return add(Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addMinutes(int amount) {
		return add(Calendar.MINUTE, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addSeconds(int amount) {
		return add(Calendar.SECOND, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder addMilliseconds(int amount) {
		return add(Calendar.MILLISECOND, amount);
	}

	/**
	 * 
	 * @param field
	 * @param amount
	 * @return
	 */
	public DateBuilder sub(int field, int amount) {
		c.add(field, -1 * amount);
		return this;
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subYears(int amount) {
		return sub(Calendar.YEAR, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subMonths(int amount) {
		return sub(Calendar.MONTH, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subDays(int amount) {
		return sub(Calendar.DAY_OF_MONTH, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subHours(int amount) {
		return sub(Calendar.HOUR_OF_DAY, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subMinutes(int amount) {
		return sub(Calendar.MINUTE, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subSeconds(int amount) {
		return sub(Calendar.SECOND, amount);
	}

	/**
	 * 
	 * @param amount
	 * @return
	 */
	public DateBuilder subMiliseconds(int amount) {
		return sub(Calendar.MILLISECOND, amount);
	}

	/**
	 * 
	 * @param field
	 * @param value
	 * @return
	 */
	public DateBuilder set(int field, int value) {
		c.set(field, value);
		return this;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setYears(int value) {
		return set(Calendar.YEAR, value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setMonths(int value) {
		return set(Calendar.MONTH, value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setDays(int value) {
		return set(Calendar.DAY_OF_MONTH, value);
	}

	public DateBuilder setTime(Date time) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(time);
		set(Calendar.HOUR_OF_DAY, c1.get(Calendar.HOUR_OF_DAY));
		set(Calendar.MINUTE, c1.get(Calendar.MINUTE));
		set(Calendar.SECOND, c1.get(Calendar.SECOND));
		set(Calendar.MILLISECOND, c1.get(Calendar.MILLISECOND));
		return self;
	}

	public DateBuilder setTime(long time) {
		return setTime(new Date(time));
	}

	/**
	 * string no formato HH:mm:ss.SSS
	 * 
	 * @param time
	 * @return
	 */
	public DateBuilder setTime(String time) {
		return setTime(DateParser.parse(DateFormats.TIME_MILI, time));
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setHours(int value) {
		return set(Calendar.HOUR, value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setMinutes(int value) {
		return set(Calendar.MINUTE, value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setSeconds(int value) {
		return set(Calendar.SECOND, value);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public DateBuilder setMilliseconds(int value) {
		return set(Calendar.MILLISECOND, value);
	}

	/**
	 * 
	 */
	public void clear() {
		c = null;
		self = null;
	}
}
