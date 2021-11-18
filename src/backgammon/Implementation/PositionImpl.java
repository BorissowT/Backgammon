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

    public void setStone(StoneImpl newStone) throws WrongPositionException, NotEnoughPointsException {
        //TODO

        validateColor(newStone);
        //TODO
        // change status of removed stone after removal of enemy stone
        newStone.setPosition(this);
        this.stones.add(newStone);
        calculateColorOfThePosition();
    }

    private void calculateColorOfThePosition() {
       if(this.stones.size()>1){
           this.color = this.stones.get(0).getColor();
       }
    }

    private void validateColor(StoneImpl newStone) throws WrongPositionException {
        if(this.getColor()!=newStone.getColor() && this.getColor()!=Color.NONE)
            throw new WrongPositionException("Not allowed to move the stone");
    }

    public void removeStone(StoneImpl stone){
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
