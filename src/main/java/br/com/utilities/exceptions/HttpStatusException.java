package br.com.utilities.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class HttpStatusException extends CustomException {

	private static final long serialVersionUID = -8015367440220608364L;

	protected HttpStatus status;

	protected MultiValueMap<String, String> header;

	public HttpStatusException() {
		super();
		this.status = HttpStatus.NOT_FOUND;
	}

	public HttpStatusException(HttpStatus status) {
		super();
		this.status = status;
	}

	public HttpStatusException(String message) {
		super(message);
		this.status = HttpStatus.NOT_FOUND;
	}

	public HttpStatusException(HttpStatus status, String message) {
		super(message);
		this.status = status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

}
