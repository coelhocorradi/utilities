package br.com.utilities.config;

import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;

public class BundleUtils {

	private ResourceBundle bundle;

	public BundleUtils(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public JsonObject configToJson() {
		JsonObject json = new JsonObject();
		for (String str : bundle.keySet()) {

			String[] arr = str.split("\\.");

			if (arr.length > 0) {
				child(json, arr, 0, str);
			}
		}
		return json;
	}

	private void child(JsonObject json, String[] arr, Integer position, String field) {
		if (arr.length == position + 1) {
			String value = bundle.getString(field).trim();
			String regex = "(\\d{1,}+\\.\\d{1,}+|\\d{1,}+\\.|\\.\\d{1,}+)|(\\d{1,}+)|(false|true)|([\\_\\-a-zA-Z]([\\_\\-a-zA-Z0-9\\.\\:\\/\\?\\&\\=\\'\\\"\\(\\)\\!\\+\\,\\;]+)?)";
			Pattern p = Pattern.compile(regex);
			Matcher m = p.matcher(value);
			if (m.find()) {
				if (m.group(1) != null) {
					if (!m.group(1).isEmpty()) {
						json.addProperty(arr[position], Double.parseDouble(m.group(1)));
					}
				} else if (m.group(2) != null) {
					if (!m.group(2).isEmpty()) {
						json.addProperty(arr[position], Integer.parseInt(m.group(2)));
					}
				} else if (m.group(3) != null) {
					if (!m.group(3).isEmpty()) {
						json.addProperty(arr[position], Boolean.parseBoolean(m.group(3)));
					}
				} else if (m.group(4) != null) {
					if (!m.group(4).isEmpty()) {
						json.addProperty(arr[position], m.group(4));
					}
				}
			}
		} else if (arr.length > position + 1) {
			if (json.has(arr[position])) {
				JsonObject aux = json.get(arr[position]).getAsJsonObject();
				child(aux, arr, position + 1, field);
				// json.add(arr[position], aux);
			} else {
				JsonObject aux = new JsonObject();
				child(aux, arr, position + 1, field);
				json.add(arr[position], aux);
			}
		}
	}

}
