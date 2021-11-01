
package backgammon.Exceptions;

public class NotEnoughPointsException extends Exception{
	public NotEnoughPointsException() { super(); }
	public NotEnoughPointsException(String message) { super(message); }
	public NotEnoughPointsException(String message, Throwable t) { super(message, t); }
}