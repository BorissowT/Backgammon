package backgammon.Implementation;

import java.util.HashMap;

public class Dice {
	private boolean if_double = false;
	private int total_points;
	private int first_dice;
	private int second_dice;

	public Dice(int first_dice, int second_dice) {
		this.total_points = first_dice+second_dice;
		if(first_dice == second_dice){
			this.if_double = true;
			this.total_points = (first_dice+second_dice)*2;
		}
		this.first_dice = first_dice;
		this.second_dice = second_dice;
	}

	public int getTotal_points() {
		return total_points;
	}

	public int getFirst_dice() {
		return first_dice;
	}

	public int getSecond_dice() {
		return second_dice;
	}

	public boolean If_double() {
		return if_double;
	}

	public HashMap<String, Integer> get_dice_dictionary(){
		HashMap<String, Integer> secure_dice_dictionary = new HashMap<String, Integer>();
		secure_dice_dictionary.put("total_points", this.getTotal_points());
		secure_dice_dictionary.put("first_dice", this.getFirst_dice());
		secure_dice_dictionary.put("second_dice", this.getSecond_dice());
		secure_dice_dictionary.put("if_double", this.If_double()? 1 : 0);
		return secure_dice_dictionary;
	}
}
