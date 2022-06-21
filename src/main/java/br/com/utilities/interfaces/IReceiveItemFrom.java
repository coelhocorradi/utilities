package br.com.utilities.interfaces;

import br.com.utilities.trowables.HttpResultThrowable;

public interface IReceiveItemFrom<DTO, T> {

	@SuppressWarnings("unchecked")
	void execute(DTO item, T... args) throws HttpResultThrowable, Exception;
}
