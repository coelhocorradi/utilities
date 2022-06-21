package br.com.utilities.interfaces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IMapToEntity<O> {

	O toEntity(Map<String, Object> map);

	default List<O> toOrm(List<Map<String, Object>> maps) {
		List<O> result = new ArrayList<O>();
		maps.forEach(m -> result.add(toEntity(m)));
		return result;
	}
}
