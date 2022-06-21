package br.com.utilities.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface IOrmToDtoExtAdapter<O, DTO> {

	public abstract DTO toDTO(O org, Object[] objs);

	public default List<DTO> toDTO(List<O> list, Object[] objs) {
		List<DTO> result = new ArrayList<DTO>();
		list.stream().forEach(o -> {
			result.add(toDTO(o, objs));
		});
		return result;
	}
}
