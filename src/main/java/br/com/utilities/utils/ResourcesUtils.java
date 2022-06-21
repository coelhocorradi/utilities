package br.com.utilities.utils;

import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.collections4.map.HashedMap;

public class ResourcesUtils {

	private ResourceBundle bundle;

	public ResourcesUtils() {
	}

	public ResourceBundle getBundle(String bundleName, Locale locale) {
		bundle = ResourceBundle.getBundle(bundleName, locale);
		return bundle;
	}

	public ResourceBundle getBundle(String bundleName) {
		Locale locale = Locale.getDefault();
		bundle = ResourceBundle.getBundle(bundleName, locale);
		return bundle;
	}

	public Enumeration<String> getKeys() {
		return bundle.getKeys();
	}

	public Map<String, Object> getKeysValues() {
		Map<String, Object> map = new HashedMap<String, Object>();
		Enumeration<String> keys = getKeys();
		while (keys.hasMoreElements()) {
			String key = keys.nextElement();
			map.put(key, bundle.getObject(key));
		}
		return map;
	}

	public String getString(String field) {
		return bundle.getString(field);
	}

	public Double getDouble(String field, Double defValue) {
		String value = getString(field);
		return value != null ? Double.parseDouble(value) : defValue;
	}

	public Double getDouble(String field) {
		return getDouble(field, null);
	}

	public Float getFloat(String field, Float defValue) {
		String value = getString(field);
		return value != null ? Float.parseFloat(value) : defValue;
	}

	public Float getFloat(String field) {
		return getFloat(field, null);
	}

	public Long getLong(String field, Long defValue) {
		String value = getString(field);
		return value != null ? Long.parseLong(value) : defValue;
	}

	public Long getLong(String field) {
		return getLong(field, null);
	}

	public Integer getInteger(String field, Integer defValue) {
		String value = getString(field);
		return value != null ? Integer.parseInt(value) : defValue;
	}

	public Integer getInteger(String field) {
		return getInteger(field, null);
	}

	public Short getShort(String field, Short defValue) {
		String value = getString(field);
		return value != null ? Short.parseShort(value) : defValue;
	}

	public Short getShort(String field) {
		return getShort(field, null);
	}

	public Byte getByte(String field, Byte defValue) {
		String value = getString(field);
		return value != null ? Byte.parseByte(value) : defValue;
	}

	public Byte getByte(String field) {
		return getByte(field, null);
	}

	public Boolean getBoolean(String field, Boolean defValue) {
		String value = getString(field);
		return value != null ? Boolean.parseBoolean(value) : defValue;
	}

	public Boolean getBoolean(String field) {
		return getBoolean(field, null);
	}

	public char[] getCharArray(String field, char[] defValue) {
		String value = getString(field);
		return value != null ? value.toCharArray() : defValue;
	}

	public char[] getCharArray(String field) {
		return getCharArray(field, null);
	}

	public byte[] getByteArray(String field, byte[] defValue) {
		String value = getString(field);
		return value != null ? value.getBytes() : defValue;
	}

	public byte[] getByteArray(String field) {
		return getByteArray(field, null);
	}

	public String[] getStringArray(String key, String[] defValue) {
		String[] value = bundle.getStringArray(key);
		return value != null ? value : defValue;
	}

	public String[] getStringArray(String key) {
		return getStringArray(key, null);
	}

}
