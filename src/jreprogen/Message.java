package jreprogen;

public class Message {

	public static Message message(String message) {
		return new Message(message);
	}
	
	public static Message message(Exception exception) {
		return new Message(exception);
	}
	
	public static Message message(String message, Exception exception) {
		return new Message(message, exception);
	}
	
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
