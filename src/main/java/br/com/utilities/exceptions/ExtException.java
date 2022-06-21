package br.com.utilities.exceptions;

public class ExtException extends Exception {

	private static final long serialVersionUID = -5359990944759569788L;

	private Object entity;

	public ExtException(Object entity) {
		super();
		this.entity = entity;
	}

	public ExtException(String message, Object entity) {
		super(message);
		this.entity = entity;
	}

	public void setEntity(Object entity) {
		this.entity = entity;
	}

	public Object getEntity() {
		return entity;
	}
}
