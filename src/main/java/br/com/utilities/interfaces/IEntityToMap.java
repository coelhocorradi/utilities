package br.com.utilities.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IEntityToMap<O> {

	Map<String, Object> toMap(O org);

	default List<Map<String, Object>> toMap(List<O> list) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		list.forEach(m -> result.add(toMap(m)));
		return result;
	}
}
