package backgammon.Implementation;

public class Dice {
	boolean if_double = false;

	public boolean isIf_double() {
		return if_double;
	}

	private int total_points;
	private int first_dice;
	private int second_dice;

	public Dice(int first_dice, int second_dice) {
		this.total_points = first_dice+second_dice;
		if(first_dice == second_dice){
			this.if_double = true;
			this.total_points = first_dice+second_dice*2;
		}
		this.first_dice = first_dice;
		this.second_dice = second_dice;
	}

	public Dice getPoints() {
		return this;
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

}
