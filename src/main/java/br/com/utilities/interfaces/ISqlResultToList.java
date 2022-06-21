package br.com.utilities.interfaces;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface ISqlResultToList<DTO, O> {

	<T> ResponseEntity<List<DTO>> sqlResultToList(IExecutor<List<O>, T> executor);
}
