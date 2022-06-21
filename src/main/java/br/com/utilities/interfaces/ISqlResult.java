package br.com.utilities.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ISqlResult<DTO, O> {

	<T> ResponseEntity<List<DTO>> sqlResultToList(IExecutor<List<O>, T> executor);

	<T> ResponseEntity<DTO> sqlResultToItem(IExecutor<O, T> executor);

}
