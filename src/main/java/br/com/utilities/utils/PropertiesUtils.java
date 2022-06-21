package br.com.utilities.utils;

import java.io.InputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import com.google.common.base.Objects;
import com.google.gson.Gson;

public abstract class PropertiesUtils {

	public static Properties load(String fileName) {
		Properties result = null;
		try {
			InputStream is = Objects.class.getClassLoader().getResourceAsStream(fileName);
			if (is != null) {
				result = new Properties();
				result.load(is);
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void load(Properties prop, String fileName) {
		try {
			if (prop == null) {
				throw new Exception("Properties object can't be null!");
			}
			InputStream is = Objects.class.getClassLoader().getResourceAsStream(fileName);
			if (is != null) {
				Properties aux = new Properties();
				aux.load(is);
				prop.putAll(aux);
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Object getPropertyByName(Properties prop, String propName) {
		return prop.containsKey(propName) ? prop.getProperty(propName) : propName;
	}

	public static Character getCharacter(Properties prop, String propName) {
		return (Character) getPropertyByName(prop, propName);
	}

	public static String getString(Properties prop, String propName) {
		return (String) getPropertyByName(prop, propName);
	}

	public static char[] getCharArray(Properties prop, String propName) {
		return (char[]) getPropertyByName(prop, propName);
	}

	public static Number getNumber(Properties prop, String propName) {
		return (Number) getPropertyByName(prop, propName);
	}

	public static Byte getByte(Properties prop, String propName) {
		return (Byte) getPropertyByName(prop, propName);
	}

	public static Short getShort(Properties prop, String propName) {
		return (Short) getPropertyByName(prop, propName);
	}

	public static Integer getInteger(Properties prop, String propName) {
		return (Integer) getPropertyByName(prop, propName);
	}

	public static Long getLong(Properties prop, String propName) {
		return (Long) getPropertyByName(prop, propName);
	}

	public static BigInteger getBigInteger(Properties prop, String propName) {
		return (BigInteger) getPropertyByName(prop, propName);
	}

	public static Float getFloat(Properties prop, String propName) {
		return (Float) getPropertyByName(prop, propName);
	}

	public static Double getDouble(Properties prop, String propName) {
		return (Double) getPropertyByName(prop, propName);
	}

	public static BigDecimal getBigDecimal(Properties prop, String propName) {
		return (BigDecimal) getPropertyByName(prop, propName);
	}

	/**
	 * use prop format like <propname::string:=<value::[string,number,...]>
	 * 
	 * @param prop
	 * @param value
	 * @return
	 */
	public static String getPropertynameByValue(Properties prop, Object value) {
		String result = null;
		boolean found = false;
		Iterator<Map.Entry<Object, Object>> i = prop.entrySet().iterator();
		while (!found && i.hasNext()) {
			Map.Entry<Object, Object> e = i.next();
			found = e.getValue().equals(value);
			if (found)
				result = (String) e.getValue();
		}
		return result;
	}

	public static void setProperty(Properties prop, String propName, Object value) {
		prop.put(propName, value);
	}

	/**
	 * merge properties in first properties object
	 * 
	 * @param prop
	 * @param props
	 */
	public static void merge(Properties prop, Properties... props) {
		for (Properties p : props) {
			prop.putAll(p);
		}
	}

	/**
	 * merge properties in a new properties object
	 * 
	 * @param props
	 * @return
	 */
	public static Properties merge(Properties... props) {
		Properties result = new Properties();
		for (Properties p : props) {
			result.putAll(p);
		}
		return result;
	}

	public static String toJson(Properties prop) {
		return new Gson().toJson(prop);
	}

	public static Properties fromJson(String jsonString) {
		return new Gson().fromJson(jsonString, Properties.class);
	}

}
