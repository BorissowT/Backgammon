package backgammon.Exceptions;

public class BackgammonException extends Exception{
	public BackgammonException() { super(); }
	public BackgammonException(String message) { super(message); }
	public BackgammonException(String message, Throwable t) { super(message, t); }
}
