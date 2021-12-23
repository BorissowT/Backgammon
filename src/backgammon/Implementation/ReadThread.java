package backgammon.Implementation;

import java.io.IOException;
import java.io.InputStream;

public class ReadThread extends Thread{
	private final ReadThreadListener listener;
	private final InputStream is;

	final byte START_RESULT_MESSAGE = 10; // arg: 1 if white 2 if black
	final byte DICE_RESULT_MESSAGE = 11;
	final byte SET_RESULT_MESSAGE = 12;
	final byte GIVE_UP_MESSAGE = -1;

	ReadThread(InputStream is, ReadThreadListener listener) {
		this.is = is;
		this.listener = listener;
	}

	public void run() {
		try {
			while(true) {
				int readInt = this.is.read();
				recogniseTheArgument(readInt);
			}
		} catch (IOException e) {
			System.err.println("connection broken: " + e.getLocalizedMessage());
			this.listener.connectionClosed();
		}
	}

	private void recogniseTheArgument(int readInt) throws IOException {
		byte command = (byte) readInt;
		readInt = this.is.read();
		byte [] messageByte = new byte[5];
		messageByte[0] = command;
		switch (command){
			case START_RESULT_MESSAGE:
				byte color = (byte) readInt;
				messageByte[1] = color;
				break;
			case DICE_RESULT_MESSAGE:
				byte total_points = (byte) readInt; messageByte[1] = total_points;
				readInt = this.is.read();
				byte first_dice = (byte) readInt; messageByte[2] = first_dice;
				readInt = this.is.read();
				byte second_dice = (byte) readInt; messageByte[3] = second_dice;
				readInt = this.is.read();
				byte if_double = (byte) readInt; messageByte[4] = if_double;
				break;
			case SET_RESULT_MESSAGE:
				byte stoneId = (byte) readInt; messageByte[1] = stoneId;
				readInt = this.is.read();
				byte positionId = (byte) readInt; messageByte[2] = positionId;
				readInt = this.is.read();
				byte resultStatus = (byte) readInt; messageByte[3] = resultStatus;
				break;
			case GIVE_UP_MESSAGE:
				break;
			default:
				throw new IllegalStateException("Unexpected value: " + readInt);
		}
		this.listener.recognizedMessage(messageByte);
	}
}
