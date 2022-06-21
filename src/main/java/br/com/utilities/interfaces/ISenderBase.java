package br.com.utilities.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import br.com.utilities.BuildConfig;
import br.com.utilities.exceptions.CustomException;
import br.com.utilities.trowables.HttpResultThrowable;

public interface ISenderBase {

	@SuppressWarnings("unchecked")
	default <R, T> ResponseEntity<String> response(ISendTo<T> listerner, T... args) {
		return response(HttpStatus.CREATED, HttpStatus.FORBIDDEN, HttpStatus.BAD_REQUEST, listerner, args);
	}

	@SuppressWarnings("unchecked")
	default <R, T> ResponseEntity<R> response(HttpStatus statusOk, HttpStatus statusCustomError, HttpStatus statusError,
			ISendTo<T> listerner, T... args) {
		ResponseEntity<R> result = null;
		try {
			if (args == null) {
				listerner.execute();
			} else {
				listerner.execute(args);
			}
			result = new ResponseEntity<R>(statusOk);
		} catch (HttpResultThrowable e) {
			HttpStatus hs = e.getStatus() != null ? e.getStatus() : statusOk;
			MultiValueMap<String, String> header = e.getHeader();
			R r = e.getContent() != null ? e.getContent() : null;
			if (r != null && header != null) {
				result = new ResponseEntity<R>(r, header, hs);
			} else if (r != null) {
				result = new ResponseEntity<R>(r, hs);
			} else if (header != null) {
				result = new ResponseEntity<R>(header, hs);
			} else {
				result = new ResponseEntity<R>(hs);
			}
		} catch (CustomException e) {
			R r = e.getObject() != null ? e.getObject() : null;
			if (r != null) {
				result = new ResponseEntity<R>(r, statusCustomError);
			} else {
				result = new ResponseEntity<R>(statusCustomError);
			}
			if (BuildConfig.SHOW_STACKTRACE) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			result = new ResponseEntity<R>(statusError);
			if (BuildConfig.SHOW_STACKTRACE) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
