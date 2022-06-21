package br.com.utilities.interfaces;

import java.util.List;

import br.com.utilities.trowables.HttpResultThrowable;

public interface IReceiveListFrom<DTO, T> {

	@SuppressWarnings("unchecked")
	void execute(List<DTO> list, T... args) throws HttpResultThrowable, Exception;
}
