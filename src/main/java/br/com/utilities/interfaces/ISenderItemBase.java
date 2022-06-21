package br.com.utilities.interfaces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import br.com.utilities.BuildConfig;
import br.com.utilities.exceptions.CustomException;
import br.com.utilities.trowables.HttpResultThrowable;

public interface ISenderItemBase {

	@SuppressWarnings("unchecked")
	default <T, R> ResponseEntity<R> response(ISendItemTo<R, T> listener, T... args) {
		return response(HttpStatus.OK, HttpStatus.NO_CONTENT, HttpStatus.BAD_REQUEST, listener, args);
	}

	@SuppressWarnings("unchecked")
	default <T, R> ResponseEntity<R> response(HttpStatus statusOk, HttpStatus statusCustomError, HttpStatus statusError,
			ISendItemTo<R, T> listener, T... args) {

		ResponseEntity<R> result = null;
		R dto = null;
		try {
			if (listener == null) {
				throw new Exception();
			}
			if (args == null) {
				dto = listener.execute();
			} else {
				dto = listener.execute(args);
			}
			if (dto == null) {
				throw new Exception();
			}
			result = new ResponseEntity<R>(dto, statusOk);
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
			dto = e.getObject() != null ? e.getObject() : null;
			if (dto != null) {
				result = new ResponseEntity<R>(dto, statusCustomError);
			} else {
				result = new ResponseEntity<R>(statusCustomError);
			}
			if (BuildConfig.SHOW_STACKTRACE) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			result = new ResponseEntity<R>(statusCustomError);
			if (BuildConfig.SHOW_STACKTRACE) {
				e.printStackTrace();
			}
		}
		return result;

	}
}
