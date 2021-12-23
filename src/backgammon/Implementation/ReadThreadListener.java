package backgammon.Implementation;

public interface ReadThreadListener {
	byte START_RESULT_MESSAGE = 10; // arg: 1 if white 2 if black
	byte DICE_RESULT_MESSAGE = 11;
	byte SET_RESULT_MESSAGE = 12;
	byte GIVE_UP_MESSAGE = 99;

	/**
	 * Tell listener message received
	 * @param message
	 */
	void recognizedMessage(byte[]  message);

	/**
	 * Tell listener: connection closed
	 */
	void connectionClosed();
}
