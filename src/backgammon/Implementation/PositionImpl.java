package backgammon.Implementation;

import backgammon.Exceptions.NotEnoughPointsException;
import backgammon.Exceptions.WrongPositionException;

import java.util.ArrayList;

public class PositionImpl {

    private final int id;
    private final ArrayList<StoneImpl> stones = new ArrayList<StoneImpl>();
    private Color color;

    PositionImpl(BGImpl GameObject){
        this.id = GameObject.getAllPositions().size();
        this.color = color.NONE;
    }

    ArrayList<StoneImpl> getStones() {
        return stones;
    }

    void setStone(StoneImpl newStone) throws WrongPositionException, NotEnoughPointsException {
        validateColor(newStone);
        this.stones.add(newStone);
        calculateColorOfThePosition();
    }

    void calculateColorOfThePosition() {
       if(this.stones.size()>1){
           this.color = this.stones.get(0).getColor();
       }
        if(this.stones.size()==0){
            this.color = Color.NONE;
        }
    }

    void validateColor(StoneImpl newStone) throws WrongPositionException {
        if(this.getColor()!=newStone.getColor() && this.getColor()!=Color.NONE)
            throw new WrongPositionException("Not allowed to move the stone");
    }

    void removeStone(StoneImpl stone){
        this.stones.remove(stone);
        this.calculateColorOfThePosition();
    }

    int getId() {
        return id;
    }

    Color getColor() {
        return color;
    }

}
