package br.com.utilities.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface IOrmFromDtoAdapter<O, DTO> {

	public abstract O fromDTO(DTO org);

	public default List<O> fromDTO(List<DTO> list) {
		List<O> result = new ArrayList<O>();
		list.stream().forEach(dto -> {
			result.add(fromDTO(dto));
		});
		return result;
	}
}
