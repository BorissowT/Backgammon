package backgammon.Implementation;

import backgammon.Exceptions.*;
import backgammon.Implementation.tcp.TCPStream;
import backgammon.Implementation.tcp.TCPStreamCreatedListener;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

public class BGImplDistributed extends BGImpl implements ReadThreadListener, TCPStreamCreatedListener {

	private InputStream is;
	private OutputStream os;

	public BGImplDistributed() throws WrongPositionException, NotEnoughPointsException {
	}
	////////////////////////////////////////////////////////////////////////////
	//                          implement distribution                        //
	////////////////////////////////////////////////////////////////////////////
	@Override
	public Color start() { return this.start(true); }

	private Color start(boolean localCall) {
		if(this.connected()) {
			// in any case local increment
			Color return_val = super.start(); // decorator calls super method..

			// ... and does something else
			if (localCall) {
				// tell other side
				if (return_val == Color.WHITE){
					byte[] messageByte = {ReadThreadListener.START_RESULT_MESSAGE, (byte) 1};
					this.sendMessage(messageByte);
				}
				else if (return_val == Color.BLACK){
					byte[] messageByte = {ReadThreadListener.START_RESULT_MESSAGE, (byte) 2};
					this.sendMessage(messageByte);
				}

			}
			return return_val;
		}
		return Color.NONE;
	}

	@Override
	public HashMap<String, Integer> dice() throws NotAllowedMethodException {
		return this.dice(true);
	}

	private HashMap<String, Integer> dice(boolean localCall) throws NotAllowedMethodException {
		HashMap<String, Integer> diceHash = null;
		if(this.connected()) {
			// in any case local increment
			diceHash = super.dice(); // decorator calls super method..
			int total_points = diceHash.get("total_points");
			int first_dice = diceHash.get("first_dice");
			int second_dice = diceHash.get("second_dice");
			int if_double = diceHash.get("if_double");
			// ... and does something else
			if (localCall) {
				byte[] messageByte = {
						ReadThreadListener.DICE_RESULT_MESSAGE,
						(byte) total_points,
						(byte) first_dice,
						(byte) second_dice,
						(byte) if_double
				};
				this.sendMessage(messageByte);
			}
		}
		return diceHash;
	}

	@Override
	public boolean set(int stoneId, int positionId) throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, WrongDirectionException, NotAllowedMethodException, StoneInBarException {
		return this.set(true, stoneId, positionId);
	}

	@Override
	public void giveUp() throws InterruptedException {
		this.giveUp(true);
	}

	private void giveUp(boolean localCall) throws InterruptedException {
		if(this.connected()) {
			// in any case local increment
			super.giveUp();
			// ... and does something else
			if (localCall) {
				byte[] messageByte = {
						ReadThreadListener.GIVE_UP_MESSAGE,
				};
				this.sendMessage(messageByte);
			}
		}
	}

	private boolean set(boolean localCall, int stoneId, int positionId) throws WrongDirectionException, StoneInBarException, NotEnoughPointsException, NotExistingStonePickedException, NotAllowedMethodException, WrongPositionException {
		boolean result = false;
		if(this.connected()) {
			// in any case local increment
			 result = super.set(stoneId, positionId); // decorator calls super method...
			// ... and does something else
			if (localCall) {
				byte[] messageByte = {
						ReadThreadListener.SET_RESULT_MESSAGE,
						(byte) stoneId,
						(byte) positionId,
						(byte) (result?1:0)
				};
				this.sendMessage(messageByte);
			}
		}
		return result;
	}

	private boolean connected() {
		synchronized(this) {
			if (this.is != null && this.os != null) return true;

			// else
			System.out.println("no yet connected");
			return false;
		}
	}

	private void sendMessage(byte[] messageByte) {
		try {
			this.os.write(messageByte);
		} catch (IOException e) {
			System.err.println("fatal: cannot send message: " + e.getLocalizedMessage());
		}
	}

	/////////////////////////////////////////////////////////////////////////////
	//                             ReadThreadListener                          //
	/////////////////////////////////////////////////////////////////////////////

	@Override
	public void recognizedMessage(byte[] messageByte) {
		byte command = messageByte[0];
		//System.out.println(command);
		switch(command) {
			case START_RESULT_MESSAGE:
				System.out.println("start message received");
				System.out.println(messageByte[1]); //COLOR of actual player
				break;
			case DICE_RESULT_MESSAGE:
				System.out.println(messageByte[1]); //total_points
				System.out.println(messageByte[2]); //first_dice
				System.out.println(messageByte[3]); //second_dice
				System.out.println(messageByte[4]); //if_double
				System.out.println("dice message received");
				break;
			case SET_RESULT_MESSAGE:
				System.out.println("set message received");
				System.out.println(messageByte[1]); //stoneId
				System.out.println(messageByte[2]); //positionId
				System.out.println(messageByte[3]); //resultStatus
				break;
			case GIVE_UP_MESSAGE:
				System.out.println("give up message received");
				break;
		}
	}

	@Override
	public void connectionClosed() {
		this.is = null; this.os = null;
	}

	@Override
	public void streamCreated(TCPStream channel) {
		try {
			synchronized(this) {
				this.is = channel.getInputStream();
				this.os = channel.getOutputStream();
			}
			// run ReadThread
			new ReadThread(this.is, this).start();
		} catch (IOException e) {
			System.err.println("fatal: " + e.getLocalizedMessage());
			System.exit(1);
		}
	}

}
