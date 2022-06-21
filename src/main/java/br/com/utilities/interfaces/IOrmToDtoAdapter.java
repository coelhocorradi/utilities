package br.com.utilities.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface IOrmToDtoAdapter<O, DTO> {

	public abstract DTO toDTO(O org);

	public default List<DTO> toDTO(List<O> list) {
		List<DTO> result = new ArrayList<DTO>();
		list.stream().forEach(o -> {
			result.add(toDTO(o));
		});
		return result;
	}
}
