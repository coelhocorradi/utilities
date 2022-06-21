package br.com.utilities.utils;

/**
 * 
 * @author gustavo
 *
 */
public abstract class QueryUtils {

	public static final byte nanosecond = 0;

	public static final byte milisecond = 1;

	public static final byte second = 2;

	public static final byte minute = 3;

	public static final byte hour = 4;

	public static final byte day = 5;

	public static final byte week = 6;

	public static final byte month = 7;

	public static final byte year = 8;

	/**
	 * field formation '^:?[_a-bA-B][_a-bA-B0-9]*'
	 * 
	 * @param field
	 * @param value
	 * @param unit
	 * @return
	 */
	public static final String mountInterval(String field, Integer value, byte unit) {
		int val = value != null ? value.intValue() : 0;
		boolean signal = val < 0;
		val = signal ? -1 * val : val;
		String result = "(";
		if (field.trim().matches("^[_a-bA-B][_a-bA-B0-9]*")) {
			result += ":" + field;
		} else if (field.trim().matches("^:[_a-bA-B][_a-bA-B0-9]*")) {
			result += field;
		}
		switch (unit) {
		case nanosecond: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' nanosecond" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case milisecond: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' milisecond" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case second: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' second" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case minute: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' minute" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case hour: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' hour" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case day: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' day" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case week: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' week" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case month: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' month" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		case year: {
			result += signal ? " - " : " + ";
			result += "interval (";
			result += Integer.toString(val) + " || \' year" + (val > 1 ? "s" : "") + "\'";
			result += ")";
		}
			break;
		default:
			break;
		}
		result += ")";
		return result;
	}
}
