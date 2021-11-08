package backgammon;


import backgammon.Exceptions.NotAllowedMethodException;
import backgammon.Exceptions.NotEnoughPointsException;
import backgammon.Exceptions.WrongPositionException;
import backgammon.Exceptions.NotExistingStonePickedException;
import backgammon.Implementation.Color;
import backgammon.Implementation.Dice;

public interface Backgammon  {

//TODO
// how to implement enum parameters?

    /***
     *
     * @return Color.BLACK or Color.WHITE representing the active player
     */
    Color start() throws NotAllowedMethodException;

    /***
     *
     * @return Dice object in form {"int:points","bool:if_double","first_dice:int", "second_dice:int"}
     */
    Dice dice() throws NotAllowedMethodException;

    /***
     *
     * @param stone id of wished stone to move
     * @param position id of position to move
     * @return 0 if ok. throws Exception.
     */
    boolean set(int stone, int position) throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException;

    /***
     * stop the game
     */
    void give_up();
}
