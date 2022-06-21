package br.com.utilities.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

import br.com.utilities.trowables.HttpResultThrowable;

public interface IExtHttpResult extends IHttpResult {

	default <R> void throwResult(HttpStatus status, R r, String message) throws HttpResultThrowable {
		throw new HttpResultThrowable(status, r, message);
	}

	default void throwResult(HttpStatus status, String message) throws HttpResultThrowable {
		throw new HttpResultThrowable(status, null, message);
	}

	default <R> void throwResult(HttpStatus status, R r) throws HttpResultThrowable {
		throw new HttpResultThrowable(status, r, "");
	}

	default void throwResult(HttpStatus status) throws HttpResultThrowable {
		throw new HttpResultThrowable(status, null, "");
	}

	default void throwResult(HttpStatus status, MultiValueMap<String, String> header) throws HttpResultThrowable {
		throw new HttpResultThrowable(status, header, null, "");
	}

	default <R> void throwResult(HttpStatus status, MultiValueMap<String, String> header, R r)
			throws HttpResultThrowable {
		throw new HttpResultThrowable(status, header, r, "");
	}
}
