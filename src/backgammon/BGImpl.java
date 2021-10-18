package backgammon;

import java.util.ArrayList;

public class BGImpl implements Backgammon {
    private ArrayList<StoneImpl> allStones = new ArrayList<StoneImpl>();
    private ArrayList<PositionImpl> allPositions = new ArrayList<PositionImpl>();
    /**
     * start() - to dice the first player
     * set() - to place a stone
     * give_up() - to close the app
     */
     BGImpl(){
         for(int i=0;i<30;i++){
             allStones.add(new StoneImpl(this));
         }
         for(int i=0;i<=24;i++){
            PositionImpl position = new PositionImpl(this);
            replace_stones(position);
            allPositions.add(position);
         }
    }

    private void replace_stones(PositionImpl place){
         int place_id = place.getId();
         switch (place_id) {
            case 1:
                //2
                break;
            case 12:
                //5
                break;
            case 17:
                //3
                break;
            case 19:
               //5
                break;
            case 6:
                //TODO replace 5 black stones
                break;
            case 13:
                //TODO replace 5 black stones
                break;
            case 24:
                //TODO replace 2 black stones
                break;
         }
    }

    @Override
    public int start() {
        return 2;
    }

    @Override
    public int dice() {
        return 0;
    }

    @Override
    public int set(int stone, int position) {
        return 2;
    }

    @Override
    public void give_up() {

    }

    public void setStone(StoneImpl stone) {
        this.allStones.add(stone);
    }

    public void setPosition(PositionImpl position) {
         this.allPositions.add(position);
    }

    public ArrayList<StoneImpl> getAllStones() {
        return allStones;
    }

    public ArrayList<PositionImpl> getAllPositions() {
        return allPositions;
    }
}
