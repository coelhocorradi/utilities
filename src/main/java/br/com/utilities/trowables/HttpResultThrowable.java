package br.com.utilities.trowables;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;

public class HttpResultThrowable extends CustomThrowable {

	private static final long serialVersionUID = -8015367440220608364L;

	protected HttpStatus status;

	protected MultiValueMap<String, String> header;

	protected Object result;

	public HttpResultThrowable() {
		super();
	}

	public HttpResultThrowable(String message) {
		super(message);
		super.content = message;
	}

	public HttpResultThrowable(String message, HttpStatus status) {
		super(message);
		super.content = message;
		this.status = status;
	}

	public <R extends Object> HttpResultThrowable(HttpStatus status, R body) {
		super(contentToString(body));
		this.status = status;
		super.content = body;
	}

	public <R extends Object> HttpResultThrowable(HttpStatus status, R body, String message) {
		super(message);
		this.status = status;
		super.content = body;
	}

	public <R extends Object> HttpResultThrowable(HttpStatus status, MultiValueMap<String, String> header, R body,
			String message) {
		super(message);
		this.status = status;
		this.header = header;
		super.content = body;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setHeader(MultiValueMap<String, String> header) {
		this.header = header;
	}

	public MultiValueMap<String, String> getHeader() {
		return header;
	}
}
