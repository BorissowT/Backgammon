package backgammon.Exceptions;

public class NotExistingStonePickedException extends Exception{
	public NotExistingStonePickedException() { super(); }
	public NotExistingStonePickedException(String message) { super(message); }
	public NotExistingStonePickedException(String message, Throwable t) { super(message, t); }
}
