package br.com.utilities.interfaces;

public interface IExecutor<R, T> {

	@SuppressWarnings("unchecked")
	R executor(T... arr);
}
