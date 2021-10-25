package backgammon;

public class Dice {
	boolean if_double = false;
	int points;

	public Dice(int first_dice, int second_dice) {
		this.points = first_dice+second_dice;
		if(first_dice == second_dice){
			this.if_double = true;
			this.points = first_dice+second_dice*2;
		}

	}
}
