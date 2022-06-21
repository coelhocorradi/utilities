package br.com.utilities.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.utilities.exceptions.CustomException;
import br.com.utilities.trowables.HttpResultThrowable;

public interface IControllerReceiver<DTO> {

	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<String> receive(IReceiveItemFrom<DTO, T> listerner, T... args) {
		return receive(HttpStatus.CREATED, HttpStatus.FORBIDDEN, HttpStatus.BAD_REQUEST, listerner, null, args);
	}
	
	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<String> receive(IReceiveItemFrom<DTO, T> listerner, DTO item, T... args) {
		return receive(HttpStatus.CREATED, HttpStatus.FORBIDDEN, HttpStatus.BAD_REQUEST, listerner, item, args);
	}

	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<String> receive(HttpStatus statusOk, HttpStatus statusCustomError,
			HttpStatus statusError, IReceiveItemFrom<DTO, T> listerner, DTO item, T... args) {
		ResponseEntity<String> result = null;
		try {						
			if (args == null) {
				listerner.execute(item);
			} else {
				listerner.execute(item, args);
			}
			result = new ResponseEntity<String>("", statusOk);
		} catch (HttpResultThrowable e) {
			HttpStatus hs = e.getStatus() != null ? e.getStatus() : statusOk;
			if (e.getMessage() != null) {
				result = new ResponseEntity<String>(e.getMessage(), hs);
			} else {
				result = new ResponseEntity<String>(hs);	
			}			
		} catch (CustomException e) {
			result = new ResponseEntity<String>(e.getMessage(), statusCustomError);
			e.printStackTrace();
		} catch (Exception e) {
			result = new ResponseEntity<String>(statusError);
			e.printStackTrace();
		} 
		return result;
	}

	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<String> receive(IReceiveListFrom<DTO, T> listerner, T... args) {
		return receive(HttpStatus.CREATED, HttpStatus.FORBIDDEN, HttpStatus.BAD_REQUEST, listerner, null, args);
	}
	
	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<String> receive(IReceiveListFrom<DTO, T> listerner, List<DTO> list, T... args) {
		return receive(HttpStatus.CREATED, HttpStatus.FORBIDDEN, HttpStatus.BAD_REQUEST, listerner, list, args);
	}

	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<String> receive(HttpStatus statusOk, HttpStatus statusCustomError,
			HttpStatus statusError, IReceiveListFrom<DTO, T> listerner, List<DTO> list, T... args) {
		ResponseEntity<String> result = null;
		try {
			if (args == null) {
				listerner.execute(list);
			} else {
				listerner.execute(list, args);
			}
			result = new ResponseEntity<String>("", statusOk);
		} catch (HttpResultThrowable e) {
			HttpStatus hs = e.getStatus() != null ? e.getStatus() : statusOk;
			if (e.getMessage() != null) {
				result = new ResponseEntity<String>(e.getMessage(), hs);
			} else {
				result = new ResponseEntity<String>(hs);	
			}			
		} catch (CustomException e) {
			result = new ResponseEntity<String>(e.getMessage(), statusCustomError);
			e.printStackTrace();
		} catch (Exception e) {
			result = new ResponseEntity<String>(statusError);
			e.printStackTrace();
		} 
		return result;
	}
	
	public default void throwResult(String message, HttpStatus status) throws HttpResultThrowable {
		throw new HttpResultThrowable(message, status);
	}
}
