package br.com.utilities.utils;

public abstract class EnumUtils {

	public static <T extends Enum<T>> T findByName(T[] values, String name, T defValue) {
		T result = null;
		for (int index = 0; index < values.length && result == null; index++) {
			if (values[index].name().equals(name)) {
				result = values[index];
			}
		}
		return result != null ? result : defValue;
	}

	public static <T extends Enum<T>> T findByName(Class<T> enumType, String name, T defValue) {
		T[] values = enumType.getEnumConstants();
		return findByName(values, name, defValue);
	}

	public static <T extends Enum<T>> T findByName(T[] values, String name) {
		return findByName(values, name, values.length > 0 ? values[values.length - 1] : null);
	}

	public static <T extends Enum<T>> T findByName(Class<T> enumType, String name) {
		T[] values = enumType.getEnumConstants();
		return findByName(values, name, values.length > 0 ? values[values.length - 1] : null);
	}
}
