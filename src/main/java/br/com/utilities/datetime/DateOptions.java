package br.com.utilities.datetime;

public abstract class DateOptions {

	public static final byte TO_START_OF_MONTH = 0x00;

	public static final byte TO_END_OF_MONTH = 0x01;

	public static final byte TO_START_OF_DAY = 0x10;

	public static final byte TO_END_OF_DAY = 0x11;

	public static final byte TO_FIRST_DAY_OF_MONTH_AFTER = 0x21;

	public static final byte TO_LAST_DAY_OF_MONTH_BEFORE = 0x22;
}
