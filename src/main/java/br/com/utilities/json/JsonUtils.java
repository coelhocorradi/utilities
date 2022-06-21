package br.com.utilities.json;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public abstract class JsonUtils {

	/**
	 * No type safe return, be careful
	 * 
	 * @param jsonFile
	 * @return
	 */
	public static <E> E readJsonFile(String jsonFile) {
		return readJsonFile(jsonFile, new TypeToken<E>() {
		}.getType());
	}

	/**
	 * 
	 * @param jsonFile
	 * @param type
	 * @return
	 */
	public static <E> E readJsonFile(String jsonFile, Type type) {
		E result = null;
		try {
			result = new Gson().fromJson(new JsonReader(new FileReader(jsonFile)), type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param jsonFile
	 * @param type
	 * @return
	 */
	public static <E> E readJsonFile(String jsonFile, Class<E> type) {
		E result = null;
		try {
			result = new Gson().fromJson(new JsonReader(new FileReader(jsonFile)), type);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return result;
	}
}
