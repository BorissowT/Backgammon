package backgammon;

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

    public void setStone(StoneImpl stone) {
        //TODO
        // calculate color of the position
        // throw error if not possible to replace a stone
        // change status of removed stone after removal of enemy stone
        // change position of moved stone
        this.stones.add(stone);
    }

    private void removeStone(StoneImpl stone){
        this.stones.remove(stone);
    }

    public int getId() {
        return id;
    }
}
