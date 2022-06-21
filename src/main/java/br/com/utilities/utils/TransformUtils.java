
package br.com.utilities.utils;

import java.math.BigDecimal;
import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;

/**
 * 
 * @author Simon
 * @author Gustavo
 *
 */
public abstract class TransformUtils {

	public static String validate(String value) {
		return value != null ? value.trim() : null;
	}

	/**
	 * string default to return is empty but not null
	 * 
	 * @param value
	 * @return
	 */
	public static String objectToString(Object value) {
		return TransformUtils.objectToString(value, null);
	}

	public static String jsonElementToString(JsonElement value, String def) {
		String result = def != null ? def : "";
		if (value != null) {
			if (value.isJsonNull()) {
				result = "";
			} else if (value.isJsonPrimitive()) {
				JsonPrimitive jp = value.getAsJsonPrimitive();
				if (jp.isBoolean()) {
					result = Boolean.toString(jp.getAsBoolean());
				} else if (jp.isNumber()) {
					result = Double.toString(jp.getAsDouble());
				} else if (jp.isString()) {
					result = jp.getAsString();
				} else if (jp.isJsonArray()) {
					// FIXME nda. no momento
					result = jsonElementToString(jp.getAsJsonArray(), "[]");
				} else if (jp.isJsonObject()) {
					result = jp.toString();
				}
			}
		}
		return result;
	}

	/**
	 * valido para tipos de objeto que herdam de tipos primitivos como: Boolean,
	 * Byte, Short, Integer, Long, Float, Double, String, Date
	 * 
	 * defina a string padrão de retorno, caso nulo será string vazia mas não nula
	 * 
	 * @param value
	 * @Param def
	 * @return
	 */
	public static String objectToString(Object value, String def) {
		String result = def != null ? def : "";
		if (value != null) {
			if (value instanceof Boolean) {
				result = Boolean.toString((Boolean) value);
			} else if (value instanceof Byte) {
				result = Byte.toString((Byte) value);
			} else if (value instanceof Short) {
				result = Short.toString((Short) value);
			} else if (value instanceof Integer) {
				result = Integer.toString((Integer) value);
			} else if (value instanceof Long) {
				result = Long.toString((Long) value);
			} else if (value instanceof Float) {
				result = Float.toString((Float) value);
			} else if (value instanceof Double) {
				result = Double.toString((Double) value);
			} else if (value instanceof String) {
				result = (String) value;
			} else if (value instanceof Date) {
				result = ((Date) value).toString();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean validateBoolean(Object value) {
		Boolean result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = (Boolean) value;
			} else if (value instanceof Byte) {
				result = ((Byte) value).byteValue() != 0;
			} else if (value instanceof Short) {
				result = ((Short) value).shortValue() != 0;
			} else if (value instanceof Integer) {
				result = ((Integer) value).intValue() != 0;
			} else if (value instanceof Long) {
				result = ((Long) value).longValue() != 0;
			} else if (value instanceof Float) {
				result = ((Float) value).floatValue() != 0.0;
			} else if (value instanceof Double) {
				result = ((Double) value).doubleValue() != 0.0;
			} else if (value instanceof String) {
				result = Boolean.parseBoolean((String) value);
			} else if (value instanceof Date) {
				result = ((Date) value).equals(new Date());
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Boolean forceBoolean(Object obj, Boolean defValue) {
		Boolean result = TransformUtils.validateBoolean(obj);
		if (result == null) {
			result = defValue != null ? defValue : false;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Boolean forceBoolean(Object obj) {
		return TransformUtils.forceBoolean(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Byte validateByte(Object value) {
		Byte result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? (byte) 1 : (byte) 0;
			} else if (value instanceof Byte) {
				result = (Byte) value;
			} else if (value instanceof Short) {
				result = ((Short) value).byteValue();
			} else if (value instanceof Integer) {
				result = ((Integer) value).byteValue();
			} else if (value instanceof Long) {
				result = ((Long) value).byteValue();
			} else if (value instanceof Float) {
				result = ((Float) value).byteValue();
			} else if (value instanceof Double) {
				result = ((Double) value).byteValue();
			} else if (value instanceof String) {
				result = Byte.parseByte((String) value);
			} else if (value instanceof Date) {
				result = ((Date) value).equals(new Date()) ? (byte) 1 : (byte) 0;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Byte forceByte(Object obj, Byte defValue) {
		Byte result = TransformUtils.validateByte(obj);
		if (result == null) {
			result = defValue != null ? defValue : (byte) 0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Byte forceByte(Object obj) {
		return TransformUtils.forceByte(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Short validateShort(Object value) {
		Short result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? (short) 1 : (short) 0;
			} else if (value instanceof Byte) {
				result = ((Byte) value).shortValue();
			} else if (value instanceof Short) {
				result = ((Short) value);
			} else if (value instanceof Integer) {
				result = ((Integer) value).shortValue();
			} else if (value instanceof Long) {
				result = ((Long) value).shortValue();
			} else if (value instanceof Float) {
				result = ((Float) value).shortValue();
			} else if (value instanceof Double) {
				result = ((Double) value).shortValue();
			} else if (value instanceof String) {
				result = Short.parseShort((String) value);
			} else if (value instanceof Date) {
				result = ((Date) value).equals(new Date()) ? (short) 1 : (short) 0;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Short forceShort(Object obj, Short defValue) {
		Short result = TransformUtils.validateShort(obj);
		if (result == null) {
			result = defValue != null ? defValue : (short) 0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Short forceShort(Object obj) {
		return TransformUtils.forceShort(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Integer validateInteger(Object value) {
		Integer result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? (int) 1 : (int) 0;
			} else if (value instanceof Byte) {
				result = ((Byte) value).intValue();
			} else if (value instanceof Short) {
				result = ((Short) value).intValue();
			} else if (value instanceof Integer) {
				result = ((Integer) value);
			} else if (value instanceof Long) {
				result = ((Long) value).intValue();
			} else if (value instanceof Float) {
				result = ((Float) value).intValue();
			} else if (value instanceof Double) {
				result = ((Double) value).intValue();
			} else if (value instanceof String) {
				result = Integer.parseInt((String) value);
			} else if (value instanceof Date) {
				result = ((Date) value).equals(new Date()) ? (int) 1 : (int) 0;
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Integer forceInteger(Object obj, Integer defValue) {
		Integer result = TransformUtils.validateInteger(obj);
		if (result == null) {
			result = defValue != null ? defValue : (int) 0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Integer forceInteger(Object obj) {
		return TransformUtils.forceInteger(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Long validateLong(Object value) {
		Long result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? (long) 1 : (long) 0;
			} else if (value instanceof Byte) {
				result = ((Byte) value).longValue();
			} else if (value instanceof Short) {
				result = ((Short) value).longValue();
			} else if (value instanceof Integer) {
				result = ((Integer) value).longValue();
			} else if (value instanceof Long) {
				result = ((Long) value);
			} else if (value instanceof Float) {
				result = ((Float) value).longValue();
			} else if (value instanceof Double) {
				result = ((Double) value).longValue();
			} else if (value instanceof String) {
				result = Long.parseLong((String) value);
			} else if (value instanceof Date) {
				result = ((Date) value).getTime();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Long forceLong(Object obj, Long defValue) {
		Long result = TransformUtils.validateLong(obj);
		if (result == null) {
			result = defValue != null ? defValue : (long) 0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Long forceLong(Object obj) {
		return TransformUtils.forceLong(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Float validateFloat(Object value) {
		Float result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? (float) 1 : (float) 0;
			} else if (value instanceof Byte) {
				result = ((Byte) value).floatValue();
			} else if (value instanceof Short) {
				result = ((Short) value).floatValue();
			} else if (value instanceof Integer) {
				result = ((Integer) value).floatValue();
			} else if (value instanceof Long) {
				result = ((Long) value).floatValue();
			} else if (value instanceof Float) {
				result = ((Float) value);
			} else if (value instanceof Double) {
				result = ((Double) value).floatValue();
			} else if (value instanceof String) {
				result = Float.parseFloat((String) value);
			} else if (value instanceof Date) {
				result = (float) ((Date) value).getTime();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Float forceFloat(Object obj, Float defValue) {
		Float result = TransformUtils.validateFloat(obj);
		if (result == null) {
			result = defValue != null ? defValue : (float) 0.0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Float forceFloat(Object obj) {
		return TransformUtils.forceFloat(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Double validateDouble(Object value) {
		Double result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? (double) 1 : (double) 0;
			} else if (value instanceof Byte) {
				result = ((Byte) value).doubleValue();
			} else if (value instanceof Short) {
				result = ((Short) value).doubleValue();
			} else if (value instanceof Integer) {
				result = ((Integer) value).doubleValue();
			} else if (value instanceof Long) {
				result = ((Long) value).doubleValue();
			} else if (value instanceof Float) {
				result = ((Float) value).doubleValue();
			} else if (value instanceof Double) {
				result = ((Double) value);
			} else if (value instanceof String) {
				result = Double.parseDouble((String) value);
			} else if (value instanceof Date) {
				result = (double) ((Date) value).getTime();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Double forceDouble(Object obj, Double defValue) {
		Double result = TransformUtils.validateDouble(obj);
		if (result == null) {
			result = defValue != null ? defValue : (double) 0.0;
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Double forceDouble(Object obj) {
		return TransformUtils.forceDouble(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static String validateString(Object value) {
		String result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? Boolean.TRUE.toString() : Boolean.FALSE.toString();
			} else if (value instanceof Byte) {
				result = Byte.toString((Byte) value);
			} else if (value instanceof Short) {
				result = Short.toString((Short) value);
			} else if (value instanceof Integer) {
				result = Integer.toString((Integer) value);
			} else if (value instanceof Long) {
				result = Long.toString((Long) value);
			} else if (value instanceof Float) {
				result = Float.toString((Float) value);
			} else if (value instanceof Double) {
				result = Double.toString((Double) value);
			} else if (value instanceof String) {
				result = (String) value;
			} else if (value instanceof Date) {
				result = ((Date) value).toString();
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static String forceString(Object obj, String defValue) {
		String result = TransformUtils.validateString(obj);
		if (result == null) {
			result = defValue != null ? defValue : "";
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static String forceString(Object obj) {
		return TransformUtils.forceString(obj, null);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	public static Date validateDate(Object value) {
		Date result = null;
		if (value != null) {
			if (value instanceof Boolean) {
				result = ((Boolean) value) ? new Date() : null;
			} else if (value instanceof Byte) {
				result = ((Byte) value) != (byte) 0 ? new Date() : null;
			} else if (value instanceof Short) {
				result = ((Short) value) != (short) 0 ? new Date() : null;
			} else if (value instanceof Integer) {
				result = ((Integer) value) != 0 ? new Date() : null;
			} else if (value instanceof Long) {
				result = new Date((Long) value);
			} else if (value instanceof Float) {
				result = new Date(((Float) value).longValue());
			} else if (value instanceof Double) {
				result = new Date(((Double) value).longValue());
			} else if (value instanceof String) {
				try {
					result = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((String) value);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				// result = new Date((String) value);
			} else if (value instanceof Date) {
				result = ((Date) value);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @param defValue
	 * @return
	 */
	public static Date forceDate(Object obj, Date defValue) {
		Date result = TransformUtils.validateDate(obj);
		if (result == null) {
			result = defValue != null ? defValue : new Date();
		}
		return result;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static Date forceDate(Object obj) {
		return TransformUtils.forceDate(obj);
	}

	/**
	 * 
	 * @param entries
	 * @param extend
	 * @return
	 */
	public static String processEntrySet(Set<Entry<String, Object>> entries, boolean extend) {
		String result = "{ ", vg = "";
		Iterator<Entry<String, Object>> itr = entries.iterator();
		while (itr.hasNext()) {
			Entry<String, Object> entry = itr.next();
			if (extend) {
				result += vg + "\"" + entry.getKey() + "\" : " + "{ \"type\" : \""
						+ entry.getValue().getClass().getSimpleName() + "\" ,";
				vg = (entry.getValue() instanceof String || entry.getValue() instanceof Date) ? "\"" : "";
				result += " \"value\" : " + vg + objectToString(entry.getValue()) + vg + " }";
			} else {
				result += vg + "\"" + entry.getKey() + "\" : " + entry.getValue();
			}
			vg = " , ";
		}
		result += " }";
		return result;
	}

	/**
	 * 
	 * @param entries
	 * @return
	 */
	public static String processEntrySet(Set<Entry<String, Object>> entries) {
		return processEntrySet(entries, false);
	}

	/**
	 * 
	 * @param entries
	 * @return
	 */
	public static String processEntrySetExt(Set<Entry<String, Object>> entries) {
		return processEntrySet(entries, true);
	}

	/**
	 * 0, 0., .0, 0000..., 00000.0, 0000.00000, todos os zeros possíveis somente se
	 * ponto \. e string vazia ou apenas espaços e se objeto for nulo
	 * 
	 * @param object
	 * @return
	 */
	public static boolean isEmpty(Object object) {
		boolean result = false;
		if (object == null) {
			result = true;
		} else
		// 0, 0., .0, 0000..., 00000.0, 0000.00000, todos os zeros possíveis
		// somente se ponto \. e string vazia ou apenas espaços
		if (object.toString().trim().matches("/^0+(\\.(0)*)?$|^0*\\.0+$|\\.|^\\s$/gm")) {
			result = true;
		}
		return result;
	}

	/**
	 * trim string if not null or not empty, otherwise return null
	 * 
	 * @param value
	 * @return
	 */
	public static String trim(String value) {
		return !isEmpty(value) ? value.trim() : null;
	}

	public static String totvsTrim(String value) {
		return !isEmpty(value) ? value.trim() : " ";
	}

	public static Boolean bool(Character value) {
		return !isEmpty(value) ? value == '1' : false;
	}

	public static Boolean bool(String value) {
		return !isEmpty(value) ? !value.trim().equals("0") || value.trim().toLowerCase().equals("on")
				|| value.trim().toLowerCase().equals("true") : false;
	}

	public static Double doubleValue(String value) {
		return !isEmpty(value) ? Double.valueOf(value) : null;
	}

	public static Double doubleValue(BigDecimal value) {
		return !isEmpty(value) ? value.doubleValue() : null;
	}

	public static Long longValue(String value) {
		return !isEmpty(value) ? Long.valueOf(value) : null;
	}

	public static Long longValue(BigDecimal value) {
		return !isEmpty(value) ? value.longValue() : null;
	}

	public static Integer intValue(String value) {
		return !isEmpty(value) ? Integer.valueOf(value) : null;
	}

	public static Integer intValue(BigDecimal value) {
		return !isEmpty(value) ? value.intValue() : null;
	}

	public static Short shortValue(String value) {
		return !isEmpty(value) ? Short.parseShort(value) : null;
	}

	public static Short shortValue(BigDecimal value) {
		return !isEmpty(value) ? value.shortValue() : null;
	}

	public static String blobToString(Blob value) {
		return !isEmpty(value) ? StringUtils.blodToString(value) : null;
	}

}