package br.com.utilities.utils;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ColletionMapping {

	private Class<?> from;

	private Field[] fields;

	private ArrayList<Map<String, Object>> mapping;

	public void fromObject(Class<?> from) {
		this.from = from;
		this.fields = this.from.getClass().getDeclaredFields();
	}

	public ArrayList<Map<String, Object>> mapping(Collection<Class<?>> collection) {
		mapping = new ArrayList<>();
		for (Class<?> obj : collection) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (Field f : fields) {
				try {
					map.put(f.getName(), obj.getDeclaredField(f.getName()).get(obj));
				} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException
						| SecurityException e) {
					e.printStackTrace();
				}
			}
			mapping.add(map);
		}
		return mapping;
	}

	public void toCsv(String filename) {
		CsvWriter csv = CsvWriter.createDefault(new File(filename));
		String[] headers = new String[fields.length];
		int i = 0;
		for (Field f : fields) {
			headers[i] = f.getName();
			i++;
		}
		csv.writeLine(headers);
		for (Map<String, Object> map : mapping) {
			String[] values = new String[fields.length];
			i = 0;
			for (String key : headers) {
				values[i] = map.get(key).toString();
				i++;
			}
			csv.writeLine(values);
		}
		csv.save();
	}
}
