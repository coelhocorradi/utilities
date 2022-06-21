package br.com.utilities.json;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * 
 * @author gustavo
 *
 */
public class JsonParserUtil {

	private static JsonParserUtil instance;

	/**
	 * 
	 * @return
	 */
	public static JsonParserUtil getInstance() {
		if (instance != null) {
			instance = new JsonParserUtil();
		}
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	public static JsonParserUtil newInstance() {
		return new JsonParserUtil();
	}

	/**
	 * 
	 */
	public void clear() {
		instance = null;
	}

	/**
	 * 
	 * @param o
	 * @param fileName
	 * @return
	 */
	public boolean saveJsonFile(Object o, String fileName) {
		boolean result = true;
		try {
			File f = new File(fileName);
			if (!f.exists()) {
				result = f.createNewFile();
			}
			if (result && f.exists() && f.isFile()) {
				f.setWritable(true);
				FileOutputStream fos = new FileOutputStream(f);
				byte[] buffer = toJsonString(o).getBytes();
				fos.write(buffer);
				fos.flush();
				fos.close();
			}
			System.out.println("Arquivo " + fileName + " criado com sucesso!");
		} catch (IOException e) {
			result = false;
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	public String toJsonString(Object o) {
		return toJsonString(o, true);
	}

	/**
	 * 
	 * @param o
	 * @param ext
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String toJsonString(Object o, boolean ext) {
		String result = "";

		if (o.getClass() == List.class) {
			result += "[";
			for (Object o1 : (List<Object>) o) {
				toJsonString(o1);
			}
			result += "]";
		} else {
			result += "{";
			Field[] fields = o.getClass().getFields();
			String vg = "";
			for (Field field : fields) {
				result += vg + (ext ? getObject(o, field) : getObjectExt(o, field));
				vg = " , ";
			}
			result += "}";
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @param field
	 * @return
	 */
	private String getObjectExt(Object o, Field field) {
		String result = "";
		Exception ex = null;
		try {
			field.setAccessible(true);
			Object object = field.get(o);
			result += "\"" + field.getName() + "\" : ";
			String tipo = null, valor = null;
			if (object != null) {
				boolean pegarTipo = true;
				if (object instanceof Boolean) {
					valor = Boolean.toString((Boolean) object);
				} else if (object instanceof Byte) {
					valor = Byte.toString((Byte) object);
				} else if (object instanceof Short) {
					valor = Short.toString((Short) object);
				} else if (object instanceof Integer) {
					valor = Integer.toString((Integer) object);
				} else if (object instanceof Long) {
					valor = Long.toString((Long) object);
				} else if (object instanceof Float) {
					valor = Float.toString((Float) object);
				} else if (object instanceof Double) {
					valor = Double.toString((Double) object);
				} else if (object instanceof BigInteger) {
					valor = ((BigInteger) object).toString();
				} else if (object instanceof BigDecimal) {
					valor = ((BigDecimal) object).toString();
				} else if (object instanceof Character) {
					valor = "\"" + ((Character) object).toString() + "\"";
				} else if (object instanceof String) {
					valor = "\"" + ((String) object) + "\"";
				} else if (object instanceof char[]) {
					// String.valueOf((char[]) object)
					valor = "\"" + (new String((char[]) object)) + "\"";
				} else if (object instanceof JsonObject) {
					valor = ((JsonObject) object).toString();
				} else if (object instanceof JsonArray) {
					valor = ((JsonArray) object).toString();
				} else if (object instanceof List<?>) {
					valor = ((List<?>) object).toString();
				} else if (object instanceof Object) {
					valor = object.toString();
				} else {
					pegarTipo = false;
					valor = "undefined";
					ex = new Exception("Tipo do campo " + field.getName() + " : " + object.getClass().getName()
							+ " n�o pr�rio para o json!");
				}
				if (pegarTipo) {
					tipo = "\"" + object.getClass().getSimpleName() + "\"";
				} else {
					tipo = "undefined";
				}
			} else {
				tipo = "\"null\"";
				valor = "\"null\"";
			}
			result += "{ \"type\" : " + tipo + " , \"value\" : " + valor + " }";//
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @param field
	 * @return
	 */
	private String getObject(Object o, Field field) {
		String result = "";
		Exception ex = null;
		try {
			field.setAccessible(true);
			Object object = field.get(o);
			result += "\"" + field.getName() + "\" : ";

			if (object != null) {
				if (object instanceof Boolean) {
					result += Boolean.toString((Boolean) object);
				} else if (object instanceof Byte) {
					result += Byte.toString((Byte) object);
				} else if (object instanceof Short) {
					result += Short.toString((Short) object);
				} else if (object instanceof Integer) {
					result += Integer.toString((Integer) object);
				} else if (object instanceof Long) {
					result += Long.toString((Long) object);
				} else if (object instanceof Float) {
					result += Float.toString((Float) object);
				} else if (object instanceof Double) {
					result += Double.toString((Double) object);
				} else if (object instanceof BigInteger) {
					result += ((BigInteger) object).toString();
				} else if (object instanceof BigDecimal) {
					result += ((BigDecimal) object).toString();
				} else if (object instanceof Character) {
					result += "\"" + ((Character) object).toString() + "\"";
				} else if (object instanceof String) {
					result += "\"" + ((String) object) + "\"";
				} else if (object instanceof char[]) {
					// String.valueOf((char[]) object)
					result += "\"" + (new String((char[]) object)) + "\"";
				} else if (object instanceof JsonObject) {
					result = ((JsonObject) object).toString();
				} else if (object instanceof JsonArray) {
					result = ((JsonArray) object).toString();
				} else if (object instanceof List<?>) {
					result = ((List<?>) object).toString();
				} else if (object instanceof Object) {
					result = object.toString();
				} else {
					result += "undefined";
					ex = new Exception("Tipo do campo " + field.getName() + " : " + object.getClass().getName()
							+ " não próprio para o json!");
				}
			} else {
				result += "null";
			}
			throw ex;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public String toJson(Object obj) {
		return new Gson().toJson(obj);
	}
}
