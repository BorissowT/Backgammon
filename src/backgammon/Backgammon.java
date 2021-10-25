package backgammon;


public interface Backgammon  {

//TODO
// how to implement enum parameters?

    /***
     *
     * @return Color.BLACK or Color.WHITE representing the first player
     */
    Color start();

    /***
     *
     * @return Dice {"int:points","bool:if_double"}
     */
    Dice dice();

    /***
     *
     * @param stone id of wished stone to move
     * @param position id of position to move
     * @return 0 if ok. throws Exception.
     */
    boolean set(int stone, int position);

    /***
     * stop the game
     */
    void give_up();
}
