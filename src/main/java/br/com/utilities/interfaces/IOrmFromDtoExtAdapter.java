package br.com.utilities.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface IOrmFromDtoExtAdapter<O, DTO> {

	public abstract O fromDTO(DTO org, Object[] objs);

	public default List<O> fromDTO(List<DTO> list, Object[] objs) {
		List<O> result = new ArrayList<O>();
		list.stream().forEach(dto -> {
			result.add(fromDTO(dto, objs));
		});
		return result;
	}
}
