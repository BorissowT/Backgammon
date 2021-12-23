package backgammon;

import backgammon.Exceptions.*;
import backgammon.Implementation.BGImpl;
import backgammon.Implementation.BGImplDistributed;
import backgammon.Implementation.Color;
import backgammon.Implementation.tcp.TCPStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DistributedBackgammonUI {
	private PrintStream standardOut = System.out;
	private PrintStream standardError = System.err;
	public static final String EXIT = "EXIT";
	public static final String START = "START";
	public static final String DICE = "DICE";
	public static final String SET = "SET <stone id> <wished position>";
	private Backgammon BGobject = new BGImpl();;
	private Color activePlayer;
	boolean first_round_flag = false;

	public static final int PORTNUMBER = 7777;

	public static void main(String args[]) throws IOException, NotEnoughPointsException, WrongPositionException {
		BGImplDistributed distributedApp = new BGImplDistributed();
		TCPStream tcpStream;

		if(args.length > 0) {
			System.out.println("init as TCP client");
			tcpStream = new TCPStream(PORTNUMBER, false, "Client", distributedApp);
		} else {
			System.out.println("init as TCP server");
			tcpStream = new TCPStream(PORTNUMBER, true, "Server", distributedApp);
		}

		// try to establish connection
		tcpStream.start();

		// run it
		new DistributedBackgammonUI(distributedApp).commandLoop();

		// command loop ended - kill it under all circumstances
		tcpStream.kill();

		System.out.println("connection closed");
	}

	private final Backgammon app;

	private DistributedBackgammonUI(Backgammon app) throws NotEnoughPointsException, WrongPositionException {
		this.app = app;
	}

	private void changeActivePlayerInterface() {
		if(this.first_round_flag){
			first_round_flag = false;
			return;
		}
		if(this.activePlayer == Color.WHITE)
			this.activePlayer = Color.BLACK;
		else
			this.activePlayer = Color.WHITE;
	}

	private void setStoneInterface(String parameterString) throws InterruptedException, WrongDirectionException, StoneInBarException, NotEnoughPointsException, NotExistingStonePickedException, NotAllowedMethodException, WrongPositionException {
		StringTokenizer st = new StringTokenizer(parameterString);
		String stoneId = st.nextToken();
		String desiredPositionId = st.nextToken();

		System.out.println("move stone with id: "+stoneId+" to position with id: "+desiredPositionId);

		System.out.println("processing...");
		Thread.sleep(1500);
		System.out.println("done!");
		this.BGobject.set(Integer.parseInt(stoneId),Integer.parseInt(desiredPositionId));
	}

	private void stopApp() throws InterruptedException {
		this.BGobject.giveUp();
	}

	public void printUsage() {
		StringBuilder b = new StringBuilder();

		b.append("\n");
		b.append("\n");
		b.append("valid commands:");
		b.append("\n");
		b.append(START);
		b.append("..to start the game");
		b.append("\n");
		b.append(DICE);
		b.append("..to set a stone");
		b.append("\n");
		b.append(SET);
		b.append("..to dice");
		b.append("\n");
		b.append(EXIT);
		b.append("..to exit");

		this.standardOut.println(b.toString());
	}

	public void commandLoop() {
		boolean again = true;
		BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

		while(again) {
			this.printUsage();
			boolean rememberCommand = true;
			String cmdLineString = null;

			try {
				// read user input
				cmdLineString = userInput.readLine();

				// finish that loop if less than nothing came in
				if(cmdLineString == null) break;

				// trim whitespaces on both sides
				cmdLineString = cmdLineString.trim();

				// extract command
				int spaceIndex = cmdLineString.indexOf(' ');
				spaceIndex = spaceIndex != -1 ? spaceIndex : cmdLineString.length();

				// got command string
				String commandString = cmdLineString.substring(0, spaceIndex);

				// extract parameters string - can be empty
				String parameterString = cmdLineString.substring(spaceIndex);
				parameterString = parameterString.trim();

				// start command loop
				switch(commandString) {
					case START:
						this.activePlayer = BGobject.start();
						System.out.println("The game started!");
						System.out.println("THE FIRST PLAYER IS " + this.activePlayer);
						first_round_flag = true;
						this.app.start();
						break;
					case DICE:
						HashMap<String, Integer> diceList = this.BGobject.dice();
						changeActivePlayerInterface();
						System.out.println("PLAYER " + this.activePlayer + " diced.");
						System.out.println("The result: "
								+ diceList.get("total_points")
								+ " " + diceList.get("first_dice")
								+ " " + diceList.get("second_dice")
								+ " " + diceList.get("if_double"));
						break;
					case "SET":
						setStoneInterface(parameterString);
						break;
					case "q": // convenience
					case EXIT:
						this.stopApp();
						again = false; break; // end loop

					default: this.standardError.println("unknown command:" + cmdLineString);
						this.printUsage();
						rememberCommand = false;
						break;
				}

			}
			catch (NotAllowedMethodException e) {
				System.err.println("NOT ALLOWED METHOD: \n");
				System.err.println(e.getLocalizedMessage());
			}
			catch (NotEnoughPointsException e) {
				System.err.println("NOT ENOUGH POINTS TO MOVE THE STONE: \n");
				System.err.println(e.getLocalizedMessage());
			} catch (WrongPositionException e) {
				System.err.println("WRONG POSITION PICKED: \n");
				System.err.println(e.getLocalizedMessage());
			} catch (WrongDirectionException e) {
				System.err.println("THE STONE IS BEING MOVED IN WRONG DIRECTION: \n");
				System.err.println(e.getLocalizedMessage());
			} catch (StoneInBarException e) {
				e.printStackTrace();
			} catch (NotExistingStonePickedException e) {
				System.err.println("THE PICKED STONE DOESN'T EXIST: \n");
				System.err.println(e.getLocalizedMessage());
			} catch (IOException e) {
				System.err.println(e.getLocalizedMessage());
			} catch (InterruptedException e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
	}

}

