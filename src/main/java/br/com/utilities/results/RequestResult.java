package br.com.utilities.results;

import br.com.utilities.interfaces.IEntity;

public class RequestResult implements IEntity<RequestResult> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4866849489902492714L;

	private String url;

	private String method;

	private int statusCode;

	private String result;

	public RequestResult() {
		super();
	}

	public RequestResult(String url, String method, int statusCode, String result) {
		super();
		this.url = url;
		this.method = method;
		this.statusCode = statusCode;
		this.result = result;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
