package backgammon.Exceptions;

public class NotAllowedMethodException extends Exception{
	public NotAllowedMethodException() { super(); }
	public NotAllowedMethodException(String message) { super(message); }
	public NotAllowedMethodException(String message, Throwable t) { super(message, t); }
}
