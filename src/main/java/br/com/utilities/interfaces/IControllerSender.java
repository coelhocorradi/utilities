package br.com.utilities.interfaces;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.utilities.exceptions.CustomException;
import br.com.utilities.trowables.HttpResultThrowable;

public interface IControllerSender<DTO> {
	
	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<DTO> response(ISendItemTo<DTO, T> listener, T... args) {
		return response(HttpStatus.OK, HttpStatus.NO_CONTENT, HttpStatus.BAD_REQUEST, listener, args);
	}
		
	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<DTO> response(HttpStatus statusOk, HttpStatus statusCustomError,
			HttpStatus statusError, ISendItemTo<DTO, T> listener, T... args) {

		ResponseEntity<DTO> result = null;
		DTO dto = null;
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
			result = new ResponseEntity<DTO>(dto, statusOk);
		} catch (HttpResultThrowable e) {
			dto = e.getContent() != null ? (DTO) e.getContent() : null;
			HttpStatus hs = e.getStatus() != null ? e.getStatus() : statusCustomError;
			if (dto != null) {
				result = new ResponseEntity<DTO>(dto, hs);
			} else {
				result = new ResponseEntity<DTO>(hs);
			}
			e.printStackTrace();
		} catch (CustomException e) {
			dto = e.getObject() != null ? (DTO) e.getObject() : null;
			if (dto != null) {
				result = new ResponseEntity<DTO>(dto, statusCustomError);
			} else {
				result = new ResponseEntity<DTO>(statusCustomError);
			}
			e.printStackTrace();
		} catch (Exception e) {
			result = new ResponseEntity<DTO>(statusError);
			e.printStackTrace();
		}
		return result;

	}

	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<List<DTO>> response(ISendListTo<DTO, T> listener, T... args) {
		return response(HttpStatus.OK, HttpStatus.NO_CONTENT, HttpStatus.BAD_REQUEST, listener, args);
	}
	
	@SuppressWarnings("unchecked")
	public default <T> ResponseEntity<List<DTO>> response(HttpStatus statusOk, HttpStatus statusCustomError,
			HttpStatus statusError, ISendListTo<DTO, T> listener, T... args) {
		ResponseEntity<List<DTO>> result = null;
		List<DTO> ldto = null;
		try {
			if (listener == null) {
				throw new Exception();
			}
			if (args == null) {
				ldto = listener.execute();
			} else {
				ldto = listener.execute(args);
			}
			if (ldto == null) {
				throw new Exception();
			} else if (ldto.size() <= 0) {
				throw new Exception();
			}
			result = new ResponseEntity<List<DTO>>(ldto, statusOk);
		} catch (HttpResultThrowable e) {
			ldto = e.getContent() != null ? (List<DTO>) e.getContent() : null;
			HttpStatus hs = e.getStatus() != null ? e.getStatus() : statusCustomError;
			if (ldto != null) {
				result = new ResponseEntity<List<DTO>>(ldto, hs);
			} else {
				result = new ResponseEntity<List<DTO>>(hs);
			}
			e.printStackTrace();
		} catch (CustomException e) {
			ldto = e.getObject() != null ? (List<DTO>) e.getObject() : null;
			if (ldto != null) {
				result = new ResponseEntity<List<DTO>>(ldto, statusCustomError);
			} else {
				result = new ResponseEntity<List<DTO>>(statusCustomError);
			}
			e.printStackTrace();
		} catch (Exception e) {
			result = new ResponseEntity<List<DTO>>(statusError);
			e.printStackTrace();
		}
		return result;
	}	
}
