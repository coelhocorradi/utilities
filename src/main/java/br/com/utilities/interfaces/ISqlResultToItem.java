package br.com.utilities.interfaces;

import org.springframework.http.ResponseEntity;

public interface ISqlResultToItem<DTO, O> {

	<T> ResponseEntity<DTO> sqlResultToItem(IExecutor<O, T> executor);

}
