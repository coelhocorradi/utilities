package br.com.utilities.utils;

import java.util.Date;

public abstract class NumberUtils {

	public static <E extends Number> boolean differentTo(E number, E defValue) {
		boolean result = number == null && defValue == null;
		if (result) {
			return false;
		} else {
			result = number == null;
			if (!result) {
				result = !number.equals(defValue);
			}
		}
		return result;
	}

	public static Byte convertToByte(Object value) {
		Byte result = null;
		// Byte, Short, Integer, Long, Double, Float
		if (value instanceof Boolean) {
			result = (byte) ((Boolean) value).compareTo(Boolean.TRUE);
		} else if (value instanceof Byte) {
			result = (Byte) value;
		} else if (value instanceof Number) {
			result = ((Number) value).byteValue();
		}
		return result;
	}

	public static Short convertToShort(Object value) {
		Short result = null;
		// Byte, Short, Integer, Long, Double, Float
		if (value instanceof Short) {
			result = (Short) value;
		} else if (value instanceof Number) {
			result = ((Number) value).shortValue();
		}
		return result;
	}

	public static Integer convertToInt(Object value) {
		Integer result = null;
		// Byte, Short, Integer, Long, Double, Float
		if (value instanceof Integer) {
			result = (Integer) value;
		} else if (value instanceof Number) {
			result = ((Number) value).intValue();
		}
		return result;
	}

	public static Long convertToLong(Object value) {
		Long result = null;
		// Byte, Short, Integer, Long, Double, Float
		if (value instanceof Long) {
			result = (Long) value;
		} else if (value instanceof Number) {
			result = ((Number) value).longValue();
		} else if (value instanceof Date) {
			result = ((Date) value).getTime();
		}
		return result;
	}

	public static Float convertToFloat(Object value) {
		Float result = null;
		if (value instanceof Float) {
			result = (Float) value;
		} else if (value instanceof Number) {
			result = ((Number) value).floatValue();
		}
		return result;
	}

	public static Double convertToDouble(Object value) {
		Double result = null;
		if (value instanceof Double) {
			result = (Double) value;
		} else if (value instanceof Number) {
			result = ((Number) value).doubleValue();
		}
		return result;
	}

}
