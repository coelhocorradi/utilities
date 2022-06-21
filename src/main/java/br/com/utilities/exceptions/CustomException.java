package br.com.utilities.exceptions;

public class CustomException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2458194770987295561L;

	protected Object obj;

	public CustomException() {
		super();
	}

	public CustomException(String message) {
		super(message);
	}

	public <R extends Object> CustomException(R obj, String message) {
		super(message);
		this.obj = obj;
	}

	public <R extends Object> void setObject(R obj) {
		this.obj = obj;
	}

	@SuppressWarnings("unchecked")
	public <R extends Object> R getObject() {
		return (R) obj;
	}

}
