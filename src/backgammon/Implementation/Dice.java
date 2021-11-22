package backgammon.Implementation;

import java.util.HashMap;

public class Dice {
	private boolean ifDouble = false;
	private int totalPoints;
	private final int firstDice;
	private final int secondDice;

	Dice(int firstDice, int secondDice) {
		this.totalPoints = firstDice +secondDice;
		if(firstDice == secondDice){
			this.ifDouble = true;
			this.totalPoints = (firstDice +secondDice)*2;
		}
		this.firstDice = firstDice;
		this.secondDice = secondDice;
	}

	int getTotalPoints() {
		return totalPoints;
	}

	int getFirstDice() {
		return firstDice;
	}

	int getSecondDice() {
		return secondDice;
	}

	boolean ifDouble() {
		return ifDouble;
	}

	HashMap<String, Integer> getDiceDictionary(){
		HashMap<String, Integer> secureDiceDictionary = new HashMap<String, Integer>();
		secureDiceDictionary.put("total_points", this.getTotalPoints());
		secureDiceDictionary.put("first_dice", this.getFirstDice());
		secureDiceDictionary.put("second_dice", this.getSecondDice());
		secureDiceDictionary.put("if_double", this.ifDouble()? 1 : 0);
		return secureDiceDictionary;
	}

	void takeOffPoints(int pointsToTakeOff) {
		this.totalPoints=this.totalPoints-pointsToTakeOff;
	}
}
