package br.com.utilities.gsonutils;

import java.io.StringReader;
import java.util.Date;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

public abstract class GsonAdapter {

	private static Gson _gson;

	public static void setGson(Gson gson) {
		_gson = gson;
	}

	public static void clearGson() {
		_gson = null;
	}

	public static Gson defaultGson() {
		return defaultBuilder().create();
	}

	public static GsonBuilder defaultBuilder() {
		return new GsonBuilder()//
				.setPrettyPrinting()//
				.serializeNulls()//
				// .setLenient()//
				.registerTypeHierarchyAdapter(Date.class, DateTypeAdapter.getAdapter());//
	}

	public static GsonBuilder customBuilder(TypeAdapter<Date> dateTypeAdapter,
			FieldNamingStrategy fieldNamingStrategy) {
		GsonBuilder builder = new GsonBuilder();
		builder
				// .setLenient()//
				.serializeNulls()//
				.setPrettyPrinting();
		if (dateTypeAdapter != null) {
			builder.registerTypeHierarchyAdapter(Date.class, dateTypeAdapter);
		}
		if (fieldNamingStrategy != null) {
			builder.setFieldNamingStrategy(fieldNamingStrategy);
		}
		return builder;
	}

	public static JsonElement parser(String str) throws Exception {
		if (_gson == null) {
			_gson = defaultGson();
		}
		return parser(_gson, str);
	}

	public static JsonElement parser(Gson gson, String str) throws Exception {
		//JsonReader reader = new JsonReader(new StringReader(str));
		//JsonParser parser = new JsonParser();
		//return parser.parse(reader);
		JsonReader reader = gson.newJsonReader(new StringReader(str));
		return JsonParser.parseReader(reader);
	}
}
