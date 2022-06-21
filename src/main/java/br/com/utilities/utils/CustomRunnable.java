package br.com.utilities.utils;

public abstract class CustomRunnable<T> implements Runnable {

	private T[] args;

	@SuppressWarnings("unchecked")
	public CustomRunnable(T... args) {
		this.args = args;
	}

	@SuppressWarnings("unchecked")
	public abstract void run(T... args);

	@Override
	public void run() {
		run(args);
	}

}
