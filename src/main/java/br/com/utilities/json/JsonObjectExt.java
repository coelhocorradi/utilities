package br.com.utilities.json;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public class JsonObjectExt {

	protected JsonObject jsono;

	/**
	 * 
	 */
	public JsonObjectExt() {
		jsono = new JsonObject();
		setDateType(Types.Values.LONG);
	}

	/**
	 * 
	 * @return
	 */
	public JsonObject getJsono() {
		return jsono;
	}

	/**
	 * 
	 * @param jsonString
	 */
	public void setJson(String jsonString) {
		// JsonElement jsone = JsonParser.parseString(jsonString);
		JsonElement jsone = new JsonParser().parse(jsonString);
		String p = null, m = null;
		if (jsone == null) {
			p = "null type";
			m = "null";
		} else if (jsone.isJsonArray()) {
			p = "jsone is array";
			m = jsone.toString();
		} else if (jsone.isJsonObject()) {
			p = "jsone is object";
			m = jsone.toString();
		} else if (jsone.isJsonPrimitive()) {
			p = "jsone is primitive";
			m = jsone.toString();
		} else if (jsone.isJsonNull()) {
			p = "jsone is null";
			m = jsone.toString();
		} else {
			p = "unknow type";
			m = jsone.toString();
		}
		System.out.println(p + " : " + m);
		JsonObject jsono = jsone.getAsJsonObject();
		clone(jsono);
	}

	/**
	 * 
	 * @param jsono
	 */
	public void setJsono(JsonObject jsono) {
		clone(jsono);
	}

	/**
	 * 
	 * @param jsono
	 */
	public final void clone(JsonObject jsono) {
		try {
			Exception e = null;
			if (jsono == null) {
				throw new Exception("the origin json object can be null");
			}
			if (this.jsono == null) {
				throw new Exception("the target jsono object can be null!");
			}
			Iterator<Entry<String, JsonElement>> it = jsono.entrySet().iterator();
			System.out.println(it != null ? "it is not null" : "it is null");
			while (it.hasNext()) {
				Entry<String, JsonElement> entry = it.next();
				System.out.println(entry != null ? "entry is not null" : "entry is null");
				String key = entry.getKey();
				System.out.println(key != null ? "key is " + key : "key is null");
				System.out.println(this.jsono.has(key) ? "key " + key + " exists into jsono target"
						: "key " + key + " exists into jsono target");
				JsonElement my = this.jsono.get(key), newValue = entry.getValue();
				System.out.println(my != null ? "my is not null" : "my is null");
				if (my == null) {
					my = JsonNull.INSTANCE;
				}
				System.out.println(newValue != null ? "newValue is not null" : "newValue is null");
				boolean addValue = true;
				if (newValue == null) {
					e = new Exception("the entry value is null!");
					addValue = false;
				} else if (my.isJsonPrimitive() && !newValue.isJsonPrimitive()) {
					e = new Exception("The types of key " + key
							+ " is diferent between json objects. It's no more JsonPrimitive!");
				} else if (my.isJsonObject() && !newValue.isJsonObject()) {
					e = new Exception(
							"The types of key " + key + " is diferent between json objects. It's no more JsonObject!");
				} else if (my.isJsonArray() && !newValue.isJsonArray()) {
					e = new Exception(
							"The types of key " + key + " is diferent between json objects. It's no more JsonArray!");
				} else if (my.isJsonNull() && !newValue.isJsonNull()) {
					e = new Exception(
							"The types of key " + key + " is diferent between json objects. It's no more JsonNull!");
				}
				if (addValue) {
					this.jsono.add(key, newValue);
					System.out.println("add into " + key + " value " + newValue.toString());
				} else {
					this.jsono.add(key, JsonNull.INSTANCE);
					System.out.println("add into " + key + " value null");
				}
				try {
					if (e != null) {
						System.out.println(e.getMessage());
					}
				} catch (Exception ex) {
					System.out.println("!!!antes!!!");
					ex.printStackTrace();
					System.out.println("!!!depois!!!");
				}
				e = null;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Boolean
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Boolean value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Byte
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Byte value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Short
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Short value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Integer
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Integer value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Long
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Long value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Float
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Float value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Double
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Double value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * BigInteger
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, BigInteger value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * BigDecimal
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, BigDecimal value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Number
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Number value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Character
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Character value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * String
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, String value) {
		if (value != null) {
			jsono.addProperty(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * char[]
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, char[] value) {
		if (value != null) {
			jsono.addProperty(key, new String(value));
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * byte[]
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, byte[] value) {
		if (value != null) {
			jsono.addProperty(key, new String(value));
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * Date
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Date value) {
		if (value != null) {
			jsono.addProperty(key, value.getTime());
			// jsono.addProperty(key, new
			// SimpleDateFormat(Constants.completeDate).format(value));
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * JsonArray
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, JsonArray value) {
		if (value != null) {
			jsono.add(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * JsonObject
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, JsonObject value) {
		if (value != null) {
			jsono.add(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * JsonPrimitive
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, JsonPrimitive value) {
		if (value != null) {
			jsono.add(key, value);
		} else {
			put(key);
		}
		return this;
	}

	/**
	 * JsonNull
	 * 
	 * @param key
	 * @return
	 */
	public JsonObjectExt put(String key) {
		jsono.add(key, JsonNull.INSTANCE);
		return this;
	}

	/**
	 * 
	 * @param map
	 * @return
	 */
	public JsonObjectExt mapToJson(Map<String, Object> map) {
		Iterator<Entry<String, Object>> i = map.entrySet().iterator();
		while (i.hasNext()) {
			entryToJson(i.next());
		}
		return this;
	}

	/**
	 * 
	 * @param entry
	 * @return
	 */
	public JsonObjectExt entryToJson(Entry<String, Object> entry) {
		try {
			String key = entry.getKey();
			Object value = entry.getValue();
			put(key, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * create all keys with null value
	 * 
	 * @param keys
	 * @return
	 */
	public JsonObjectExt put(String[] keys) {
		return put(keys, null);
	}

	/**
	 * values can be null, or has less items than keys. If values has more items
	 * than keys, this will not be used! the pair key->values will be create by
	 * union of keys[i]->values[i], when i = 0,1,2,... i < keys.length
	 * 
	 * @param keys
	 * @param values
	 * @return
	 */
	public JsonObjectExt put(String[] keys, Object[] values) {
		try {
			if (keys == null) {
				throw new Exception("The 'keys' param can not be null!");
			} else if (keys.length <= 0) {
				throw new Exception("the 'keys' param can not be empty!");
			}
			for (int i = 0; i < keys.length; i++) {
				String key = keys[i];
				Object value = null;
				if (values != null) {
					if (values.length > 0) {
						if (i < values.length) {
							value = values[i];
						}
					}
				}
				put(key, value);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * Object can be
	 * null|Boolean|Byte|Short|Integer|Long|Float|Double|BigInteger|BigDecimal|Character|String|char[]|byte[]|Date|JsonArray|JsonObject|JsonPrimitive|JsonNumber|JsonNull
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public JsonObjectExt put(String key, Object value) {
		try {
			if (key == null) {
				throw new Exception("The 'key' param can not ne null!");
			} else if (key.trim().isEmpty()) {
				throw new Exception("The 'key' param can not be empty!");
			}
			if (value == null) {
				jsono.add(key, JsonNull.INSTANCE);
			} else if (value instanceof Boolean) {
				jsono.addProperty(key, (Boolean) value);
			} else if (value instanceof Byte) {
				jsono.addProperty(key, (Byte) value);
			} else if (value instanceof Short) {
				jsono.addProperty(key, (Short) value);
			} else if (value instanceof Integer) {
				jsono.addProperty(key, (Integer) value);
			} else if (value instanceof Long) {
				jsono.addProperty(key, (Long) value);
			} else if (value instanceof Float) {
				jsono.addProperty(key, (Float) value);
			} else if (value instanceof Double) {
				jsono.addProperty(key, (Double) value);
			} else if (value instanceof BigInteger) {
				jsono.addProperty(key, (BigInteger) value);
			} else if (value instanceof BigDecimal) {
				jsono.addProperty(key, (BigDecimal) value);
			} else if (value instanceof Character) {
				jsono.addProperty(key, (Character) value);
			} else if (value instanceof String) {
				jsono.addProperty(key, (String) value);
			} else if (value instanceof char[]) {
				jsono.addProperty(key, new String((char[]) value));
			} else if (value instanceof byte[]) {
				jsono.addProperty(key, new String((byte[]) value));
			} else if (value instanceof Date) {
				// jsono.addProperty(key, new
				// SimpleDateFormat(Constants.completeDate).format((Date) value));
				jsono.addProperty(key, ((Date) value).getTime());
			} else if (value instanceof JsonArray) {
				jsono.add(key, (JsonArray) value);
			} else if (value instanceof JsonObject) {
				jsono.add(key, (JsonObject) value);
			} else if (value instanceof JsonPrimitive) {
				jsono.add(key, (JsonPrimitive) value);
			} else if (value instanceof Number) {
				jsono.addProperty(key, (Number) value);
			} else if (value instanceof JsonNull) {
				jsono.add(key, (JsonNull) value);
			} else if (value instanceof JsonObjectExt) {
				try {
					System.out.println("!!!verificar se nÃ£o vai dar erro quando pegar pelo metodo protegido!!!");
					jsono.add(key, ((JsonObjectExt) value).getJsono());
				} catch (Exception e) {
					e.printStackTrace();
					// JsonElement je = JsonParser.parseString(((JsonObjectExt)
					// value).toJsonString());
					JsonElement je = new JsonParser().parse(((JsonObjectExt) value).toJsonString());
					jsono.add(key, je);
				}
			} else {
				System.out.println("!!!tipo do elemento invÃ¡lido para o json: " + value.getClass().getName() + " !!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public JsonElement getObject(String key) {
		System.out.println("getObject " + key + " 001");
		JsonElement result = null;
		System.out.println("getObject " + key + " 002");
		if (jsono.has(key)) {
			System.out.println("getObject " + key + " 003");
			result = jsono.get(key);
			System.out.println("getObject " + key + " 004");
		}
		System.out.println("getObject " + key + " 005");
		return result;
	}

	/**
	 * JsonElement o == null;
	 * 
	 * @param key
	 * @return
	 */
	public Boolean isNull(String key) {
		System.out.println("isNull " + key + " 001");
		Boolean result = null;
		System.out.println("isNull " + key + " 002");
		JsonElement o = getObject(key);
		System.out.println("isNull " + key + " 003");
		result = o == null; // esta fazendo assim nomomento, deixar assim mesmo
		System.out.println("isNull " + key + " 004");
		return result;
	}

	/**
	 * o.getAsBoolean()
	 * 
	 * @param key
	 * @return
	 */
	public Boolean getBoolean(String key) {
		Boolean result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsBoolean() : null;
		}
		return result;
	}

	/**
	 * o.getAsByte()
	 * 
	 * @param key
	 * @return
	 */
	public Byte getByte(String key) {
		Byte result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsByte() : null;
		}
		return result;
	}

	/**
	 * o.getAsShort()
	 * 
	 * @param key
	 * @return
	 */
	public Short getShort(String key) {
		Short result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsShort() : null;
		}
		return result;
	}

	/**
	 * o.getAsInt()
	 * 
	 * @param key
	 * @return
	 */
	public Integer getInteger(String key) {
		Integer result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsInt() : null;
		}
		return result;
	}

	/**
	 * o.getAsLong()
	 * 
	 * @param key
	 * @return
	 */
	public Long getLong(String key) {
		Long result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsLong() : null;
		}
		return result;
	}

	/**
	 * o.getAsFloat()
	 * 
	 * @param key
	 * @return
	 */
	public Float getFloat(String key) {
		Float result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsFloat() : null;
		}
		return result;
	}

	/**
	 * o.getAsDouble()
	 * 
	 * @param key
	 * @return
	 */
	public Double getDouble(String key) {
		Double result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsDouble() : null;
		}
		return result;
	}

	/**
	 * o.getAsBigInteger()
	 * 
	 * @param key
	 * @return
	 */
	public BigInteger getBigInteger(String key) {
		BigInteger result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsBigInteger() : null;
		}
		return result;
	}

	/**
	 * o.getAsBigDecimal()
	 * 
	 * @param key
	 * @return
	 */
	public BigDecimal getBigDecimal(String key) {
		BigDecimal result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsBigDecimal() : null;
		}
		return result;
	}

	/**
	 * o.getAsCharacter()
	 * 
	 * @param key
	 * @return
	 */
	public Character getCharacter(String key) {
		Character result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsString().charAt(0) : null;
			// result = !o.isJsonNull() ? o.getAsCharacter() : null;
		}
		return result;
	}

	/**
	 * o.getAsString()
	 * 
	 * @param key
	 * @return
	 */
	public String getString(String key) {
		String result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsString() : null;
		}
		return result;
	}

	/**
	 * o.getAsAString().toCharArray()
	 * 
	 * @param key
	 * @return
	 */
	public char[] getCharArray(String key) {
		char[] result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsString().toCharArray() : null;
		}
		return result;
	}

	/**
	 * o.getAsString().getBytes();
	 * 
	 * @param key
	 * @return
	 */
	public byte[] getByteArray(String key) {
		byte[] result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsString().getBytes() : null;
		}
		return result;
	}

	/**
	 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(o.getAsString())
	 * 
	 * @param key
	 * @return
	 */
	public Date getDate(String key) {
		Date result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			// try {
			result = !o.isJsonNull() ? new Date(o.getAsLong()) : null;
			// result = new SimpleDateFormat(Constants.completeDate).parse(o.getAsString());
			// } catch (ParseException e) {
			// e.printStackTrace();
			// }
		}
		return result;
	}

	/**
	 * o.getAsJsonArray()
	 * 
	 * @param key
	 * @return
	 */
	public JsonArray getJsonArray(String key) {
		JsonArray result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsJsonArray() : null;
		}
		return result;
	}

	/**
	 * o.getAsJsonObject()
	 * 
	 * @param key
	 * @return
	 */
	public JsonObject getJsonObject(String key) {
		JsonObject result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsJsonObject() : null;
		}
		return result;
	}

	/**
	 * o.getAsJsonPrimitive()
	 * 
	 * @param key
	 * @return
	 */
	public JsonPrimitive getJsonPrimitive(String key) {
		JsonPrimitive result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsJsonPrimitive() : null;
		}
		return result;
	}

	/**
	 * o.getAsNumber()
	 * 
	 * @param key
	 * @return
	 */
	public Number getNumber(String key) {
		Number result = null;
		JsonElement o = getObject(key);
		if (o != null) {
			result = !o.isJsonNull() ? o.getAsNumber() : null;
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	protected boolean isJsonPrimitive(JsonElement o) {
		boolean result = false;
		if (o != null) {
			if (o.isJsonPrimitive()) {
				result = true;
			} else if (o.isJsonObject()) {
				System.out.println("the json element type is object but primitive is expected!");
			} else if (o.isJsonArray()) {
				System.out.println("the json element type is array but primitive is expected!");
			} else if (o.isJsonNull()) {
				System.out.println("the json element type is null but primitive is expected!");
			}
		} else {
			System.out.println("the json element is null!");
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	protected boolean isJsonObject(JsonElement o) {
		boolean result = false;
		if (o != null) {
			if (o.isJsonPrimitive()) {
				System.out.println("the json element type is primitive but object is expected!");
			} else if (o.isJsonObject()) {
				result = true;
			} else if (o.isJsonArray()) {
				System.out.println("the json element type is array but object is expected!");
			} else if (o.isJsonNull()) {
				System.out.println("the json element type is null but object is expected!");
			}
		} else {
			System.out.println("the json element is null!");
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	protected boolean isJsonArray(JsonElement o) {
		boolean result = false;
		if (o != null) {
			if (o.isJsonPrimitive()) {
				System.out.println("the json element type is primitive but array is expected!");
			} else if (o.isJsonObject()) {
				System.out.println("the json element type is object but array is expected!");
			} else if (o.isJsonArray()) {
				result = true;
			} else if (o.isJsonNull()) {
				System.out.println("the json element type is null but array is expected!");
			}
		} else {
			System.out.println("the json element is null!");
		}
		return result;
	}

	/**
	 * 
	 * @param o
	 * @return
	 */
	protected boolean isJsonNull(JsonElement o) {
		boolean result = false;
		if (o != null) {
			if (o.isJsonPrimitive()) {
				System.out.println("the json element type is primitive but null is expected!");
			} else if (o.isJsonObject()) {
				System.out.println("the json element type is object but null is expected!");
			} else if (o.isJsonArray()) {
				System.out.println("the json element type is array but null is expected!");
			} else if (o.isJsonNull()) {
				result = true;
			}
		} else {
			System.out.println("the json element is null!");
		}
		return result;
	}

	/**
	 * o.getAsJsonNull()
	 * 
	 * @param key
	 * @return
	 */
	public JsonNull getJsonNull(String key) {
		System.out.println("getJsonNull " + key + " 000");
		JsonNull result = null;
		System.out.println("getJsonNull " + key + " 001");
		JsonElement o = getObject(key);
		System.out.println("getJsonNull " + key + " 002");
		if (o != null) {
			System.out.println("getJsonNull " + key + " 003");
			result = o.getAsJsonNull();
			System.out.println("getJsonNull " + key + " 004");
		}
		System.out.println("getJsonNull " + key + " 005");
		return result;
	}

	/**
	 * 
	 * @return
	 */
	public String toJsonString() {
		String result = null;
		result = jsono.toString();
		return result;
	}

	/**
	 * 
	 * @param type
	 */
	public void setDateType(int type) {
		JsonObject dateType = new JsonObject();
		switch (type) {
		// case Types.Values.TIMESTAMP:
		case Types.Values.DATE:
		case Types.Values.DATETYPE: {
			dateType.addProperty(Fields.dateTypeName, Types.Names.DATE);
			dateType.addProperty(Fields.dateTypeValue, Types.Values.DATETYPE);
		}
			break;
		case Types.Values.DOUBLE: {
			dateType.addProperty(Fields.dateTypeName, Types.Names.DOUBLE);
			dateType.addProperty(Fields.dateTypeValue, Types.Values.DOUBLE);
		}
			break;
		case Types.Values.STRING: {
			dateType.addProperty(Fields.dateTypeName, Types.Names.STRING);
			dateType.addProperty(Fields.dateTypeValue, Types.Values.STRING);
		}
			break;
		case Types.Values.LONG:
		default: {
			dateType.addProperty(Fields.dateTypeName, Types.Names.LONG);
			dateType.addProperty(Fields.dateTypeValue, Types.Values.LONG);
		}
			break;
		}
		jsono.add(Fields.dateType, dateType);
	}

	/**
	 * 
	 * @author gustavo
	 *
	 */
	public static final class Fields {

		public static final String dateType = "dateType";

		public static final String dateTypeValue = "value";

		public static final String dateTypeName = "name";
	}

	/**
	 * 
	 * @author gustavo
	 *
	 */
	public static final class Types {

		/**
		 * 
		 * @author gustavo
		 *
		 */
		public static final class Names {

			public static final String NULL = "Null";

			public static final String BOOLEAN = "Boolean";

			public static final String BYTE = "Byte";

			/**
			 * used on some programming languages to generated number like half
			 */
			public static final String SHOT = "Short";

			/**
			 * used on some programming languages to generated number like short
			 */
			@Deprecated
			public static final String HALF = "Half";

			public static final String INTEGER = "Integer";

			public static final String LONG = "Long";

			public static final String FLOAT = "Float";

			public static final String DOUBLE = "Double";

			/**
			 * some languages use this format for append number when values at higher values
			 * than long
			 */
			public static final String BIGINTEGER = "BigInteger";

			/**
			 * some languages use this format for append number when values at higher values
			 * or higher precision than double
			 */
			public static final String BIGDECIMAL = "BigDecimal";

			/**
			 * generic json format for byte, short, integer, long, float, double,
			 * big-integer or big-decimal
			 */
			public static final String NUMBER = "Number";

			/**
			 * used on some programming languages to generated character like char
			 */
			public static final String CHARACTER = "Character";

			/**
			 * used on some programming languages to generated char like character
			 */
			@Deprecated
			public static final String CHAR = "Char";

			public static final String STRING = "String";

			public static final String BYTEARRAY = "ByteArray";

			public static final String CHARARRAY = "CharArray";

			public static final String DATE = "Date";

			@Deprecated
			public static final String TIMESTAMP = "Timestamp";

			public static final String JSONOBJECT = "JsonObject";

			public static final String JSONARRAY = "JsonArray";

			public static final String JSONPRIMITIVE = "JsonPrimitive";

			public static final String JSONNULL = "JsonNull";
		}

		/**
		 * 0xFF is null, 0x0X is numbers types, 0x2X is array types like string, 0x4X is
		 * date types, 0x6X is json(gson) types
		 * 
		 * @author gustavo
		 *
		 */
		public static final class Values {

			public static final int NULL = 0xFF;

			/**
			 * TYPE && NUMBERTYPE == NUMBERTYPE
			 */
			public static final int NUMBERTYPE = 0x00;

			public static final int BOOLEAN = 0x01;

			public static final int BYTE = 0x02;

			public static final int SHORT = 0x03;

			@Deprecated
			public static final int HALF = 0x03;

			public static final int INTEGER = 0x04;

			public static final int LONG = 0x05;

			public static final int FLOAT = 0x06;

			public static final int DOUBLE = 0x07;

			public static final int BIGINTEGER = 0x08;

			public static final int BIGDECIMAL = 0x09;

			public static final int NUMBER = 0x0A;

			/**
			 * TYPE && ARRAYTYPE == ARRAYTYPE
			 */
			public static final int ARRAYTYPE = 0x20;

			public static final int CHARACTER = 0x21;

			@Deprecated
			public static final int CHAR = 0x21;

			public static final int STRING = 0x22;

			public static final int BYTEARRAY = 0x23;

			public static final int CHARARRAY = 0x24;

			/**
			 * TYPE && DATETYPE == DATETYPE
			 */
			public static final int DATETYPE = 0x40;

			public static final int DATE = 0x41;

			@Deprecated
			public static final int TIMESTAMP = 0x41;

			/**
			 * TYPE && JSONTYPE == JSONTYPE
			 */
			public static final int JSONTYPE = 0x60;

			public static final int JSONOBJECT = 0x61;

			public static final int JSONARRAY = 0x62;

			public static final int JSONPRIMITIVE = 0x63;

			public static final int JSONNULL = 0x64;
		}
	}
}
