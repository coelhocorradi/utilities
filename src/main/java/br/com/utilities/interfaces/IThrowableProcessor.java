package br.com.utilities.interfaces;

import br.com.utilities.trowables.HttpResultThrowable;

public interface IThrowableProcessor<R> {

	void process() throws HttpResultThrowable;
}
