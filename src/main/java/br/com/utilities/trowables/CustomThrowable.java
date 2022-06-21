package br.com.utilities.trowables;

public class CustomThrowable extends Throwable {

	private static final long serialVersionUID = 7255743619571649827L;

	protected Object content;

	public CustomThrowable() {
		super();
	}

	public CustomThrowable(String message) {
		super(message);
		content = message;
	}

	public <C extends Object> CustomThrowable(C content) {
		super(contentToString(content));
		this.content = content;
	}

	public <C extends Object> CustomThrowable(String message, C content) {
		super(message);
		this.content = content;
	}

	public <C extends Object> void setContent(C content) {
		this.content = content;
	}

	@SuppressWarnings("unchecked")
	public <C extends Object> C getContent() {
		return (C) content;
	}

	protected static <C> String contentToString(C content) {
		return content != null ? (content instanceof String ? (String) content : content.toString()) : null;
	}
}
