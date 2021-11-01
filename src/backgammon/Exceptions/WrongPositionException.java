package backgammon.Exceptions;

public class WrongPositionException extends Exception{
	public WrongPositionException() { super(); }
	public WrongPositionException(String message) { super(message); }
	public WrongPositionException(String message, Throwable t) { super(message, t); }
}
