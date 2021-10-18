package backgammon;

public interface Backgammon  {

//TODO
// how to implement enum parameters?

    public enum active_player {
        BLACK,
        WHITE,
        NONE,
    }



    int start();
    int dice();
    int set(int stone, int position);
    void give_up();
}
