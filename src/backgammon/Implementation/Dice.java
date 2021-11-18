package backgammon.Implementation;

import java.util.HashMap;

public class Dice {
	private boolean ifDouble = false;
	private int totalPoints;
	private int firstDice; //TODO ask: was ist final?
	private int secondDice;

	public Dice(int firstDice, int secondDice) {
		this.totalPoints = firstDice +secondDice;
		if(firstDice == secondDice){
			this.ifDouble = true;
			this.totalPoints = (firstDice +secondDice)*2;
		}
		this.firstDice = firstDice;
		this.secondDice = secondDice;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public int getFirstDice() {
		return firstDice;
	}

	public int getSecondDice() {
		return secondDice;
	}

	public boolean ifDouble() {
		return ifDouble;
	}

	public HashMap<String, Integer> getDiceDictionary(){
		HashMap<String, Integer> secureDiceDictionary = new HashMap<String, Integer>();
		secureDiceDictionary.put("total_points", this.getTotalPoints());
		secureDiceDictionary.put("first_dice", this.getFirstDice());
		secureDiceDictionary.put("second_dice", this.getSecondDice());
		secureDiceDictionary.put("if_double", this.ifDouble()? 1 : 0);
		return secureDiceDictionary;
	}
}
