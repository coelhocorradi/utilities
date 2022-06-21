package br.com.utilities.utils;

import java.util.HashMap;
import java.util.Map;

public abstract class MapUtils {

	public static Map<String, Object> toMap(Object[] values, String[] map) {
		int max = Math.max(values.length, map.length);
		HashMap<String, Object> result = new HashMap<String, Object>();
		for (int i = 0; i < max; i++) {
			Object v = i < values.length ? values[i] : null;
			String m = i < map.length ? map[i] : StringUtils.format6dHex("field", i);
			result.put(m, v);
		}
		return result;
	}
}
