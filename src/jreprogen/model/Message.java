package jreprogen.model;

public class Message {

	private final String message;
	private final Exception exception;
	
	public Message(String message) {
		this(message, null);
	}
	
	public Message(Exception exception) {
		this("thrown Exception", exception);
	}
	
	public Message(String message, Exception exception) {
		this.message = message;
		this.exception = exception;
	}
	
	public String getMessage() {
		return message;
	}
	
	public Exception getException() {
		return exception;
	}
	
	@Override
	public String toString() {
		return message + (exception == null ? "" : "(" + exception.toString() + ")");
	}
}
