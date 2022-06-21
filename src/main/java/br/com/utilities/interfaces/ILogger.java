package br.com.utilities.interfaces;

import java.lang.reflect.Type;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.reflect.TypeToken;

public interface ILogger<E> {

	public default Logger getLogger() {
		@SuppressWarnings("serial")
		Type type = new TypeToken<E>() {
		}.getType();
		return LoggerFactory.getLogger(type.getClass());
	}
}
