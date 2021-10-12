package backgammon;

import java.util.ArrayList;

public class StoneImpl {
    private static ArrayList<StoneImpl> allStones = new ArrayList<StoneImpl>();
    private int id;
    //TODO change to enum
    private String color;
    private PositionImpl position;

    StoneImpl(){
        this.id = allStones.size();
        // TODO change to enum
        if(allStones.size()>15)
            this.color = "WHITE";
        else
            this.color = "BLACK";
        allStones.add(this);
    }

    public int getId() {
        return id;
    }

    public static ArrayList<StoneImpl> getAllStones() {
        return allStones;
    }

    public String getColor() {
        return color;
    }

    public PositionImpl getPosition() {
        return position;
    }
}
