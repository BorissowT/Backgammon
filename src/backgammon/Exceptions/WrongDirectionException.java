package backgammon.Exceptions;

public class WrongDirectionException extends Exception{
	public WrongDirectionException() { super(); }
	public WrongDirectionException(String message) { super(message); }
	public WrongDirectionException(String message, Throwable t) { super(message, t); }
}
