package backgammon.Exceptions;

public class WrongStonePickedException extends Exception{
	public WrongStonePickedException() { super(); }
	public WrongStonePickedException(String message) { super(message); }
	public WrongStonePickedException(String message, Throwable t) { super(message, t); }
}
