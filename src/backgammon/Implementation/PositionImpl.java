package backgammon.Implementation;

import backgammon.Exceptions.NotEnoughPointsException;
import backgammon.Exceptions.WrongPositionException;

import java.util.ArrayList;

public class PositionImpl {

    private final int id;
    private ArrayList<StoneImpl> stones = new ArrayList<StoneImpl>();
    private Color color;

    PositionImpl(BGImpl GameObject){
        this.id = GameObject.getAllPositions().size();
        this.color = color.NONE;
    }

    public ArrayList<StoneImpl> getStones() {
        return stones;
    }

    public void setStone(StoneImpl new_stone) throws WrongPositionException, NotEnoughPointsException {
        //TODO
        // calculate color of the position
        if(this.getColor()!=new_stone.getColor() && this.getColor()!=Color.NONE)
            throw new WrongPositionException("Not allowed to move the stone");
        //TODO
        // change status of removed stone after removal of enemy stone
        // change position of moved stone
        this.stones.add(new_stone);
    }

    private void removeStone(StoneImpl stone){
        //TODO calculate color of the position
        this.stones.remove(stone);
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }
}
