package backgammon.Exceptions;

public class StoneInBarException extends Exception{
	public StoneInBarException() { super(); }
	public StoneInBarException(String message) { super(message); }
	public StoneInBarException(String message, Throwable t) { super(message, t); }
}