package backgammon;

public interface Backgammon  {

//TODO
// how to implement enum parameters?

    public enum active_player {
        BLACK,
        WHITE,
        NONE,
    }

    PositionImpl[] all_positions = new PositionImpl[25];
    StoneImpl[] all_stones = new StoneImpl[30];

    int start();
    int dice();
    int set(int stone, int position);
    void give_up();
}
