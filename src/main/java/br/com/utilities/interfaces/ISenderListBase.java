package br.com.utilities.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import br.com.utilities.BuildConfig;
import br.com.utilities.exceptions.CustomException;
import br.com.utilities.trowables.HttpResultThrowable;

public interface ISenderListBase {

	@SuppressWarnings("unchecked")
	default <T, R> ResponseEntity<List<R>> response(ISendListTo<R, T> listener, T... args) {
		return response(HttpStatus.OK, HttpStatus.NO_CONTENT, HttpStatus.BAD_REQUEST, listener, args);
	}

	@SuppressWarnings("unchecked")
	default <T, R> ResponseEntity<List<R>> response(HttpStatus statusOk, HttpStatus statusCustomError,
			HttpStatus statusError, ISendListTo<R, T> listener, T... args) {
		ResponseEntity<List<R>> result = null;
		List<R> ldto = null;
		try {
			if (listener == null) {
				throw new Exception(statusError.getReasonPhrase());
			}
			if (args == null) {
				ldto = listener.execute();
			} else {
				ldto = listener.execute(args);
			}
			if (ldto == null) {
				throw new Exception(statusCustomError.getReasonPhrase());
			} else if (ldto.isEmpty()) {
				throw new CustomException(ldto, statusCustomError.getReasonPhrase());
			} else {
				result = new ResponseEntity<List<R>>(ldto, statusOk);
			}
		} catch (HttpResultThrowable e) {
			HttpStatus hs = e.getStatus() != null ? e.getStatus() : statusOk;
			MultiValueMap<String, String> header = e.getHeader();
			ldto = e.getContent() != null ? e.getContent() : null;
			if (ldto != null && header != null) {
				result = new ResponseEntity<List<R>>(ldto, header, hs);
			} else if (ldto != null) {
				result = new ResponseEntity<List<R>>(ldto, hs);
			} else if (header != null) {
				result = new ResponseEntity<List<R>>(header, hs);
			} else {
				result = new ResponseEntity<List<R>>(hs);
			}
		} catch (CustomException e) {
			ldto = e.getObject() != null ? e.getObject() : null;
			if (ldto != null) {
				result = new ResponseEntity<List<R>>(ldto, statusCustomError);
			} else {
				result = new ResponseEntity<List<R>>(statusCustomError);
			}
			if (BuildConfig.SHOW_STACKTRACE) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			result = new ResponseEntity<List<R>>(statusError);
			if (BuildConfig.SHOW_STACKTRACE) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
