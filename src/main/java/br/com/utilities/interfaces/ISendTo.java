package br.com.utilities.interfaces;

import br.com.utilities.trowables.HttpResultThrowable;

public interface ISendTo<T> {

	@SuppressWarnings("unchecked")
	void execute(T... args) throws HttpResultThrowable, Exception;
}
