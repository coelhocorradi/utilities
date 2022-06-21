package br.com.utilities.interfaces;

import java.util.List;

import br.com.utilities.trowables.HttpResultThrowable;

public interface ISendListTo<R, T> {

	@SuppressWarnings("unchecked")
	List<R> execute(T... args) throws HttpResultThrowable, Exception;
}
