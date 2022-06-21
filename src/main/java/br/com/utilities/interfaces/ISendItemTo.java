package br.com.utilities.interfaces;

import br.com.utilities.trowables.HttpResultThrowable;

public interface ISendItemTo<R, T> {

	@SuppressWarnings("unchecked")
	R execute(T... args) throws HttpResultThrowable, Exception;
}
