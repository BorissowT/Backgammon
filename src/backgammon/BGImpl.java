package backgammon;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BGImpl implements Backgammon {
    private ArrayList<StoneImpl> allStones = new ArrayList<StoneImpl>();
    private ArrayList<PositionImpl> allPositions = new ArrayList<PositionImpl>();
    private Color active_player;

    private int points;

     BGImpl(){
         for(int i=0;i<30;i++){
             this.setStone(new StoneImpl(this));
         }
         for(int i=0;i<=24;i++){
            PositionImpl position = new PositionImpl(this);
            replace_stones(position);
            this.setPosition(position);
         }
    }

    private void replace_stones(PositionImpl place){
         int place_id = place.getId();
         switch (place_id) {
            case 1:
                place.setStone(this.getAllStones().get(0));
                place.setStone(this.getAllStones().get(1));
                break;
            case 12:
                place.setStone(this.getAllStones().get(2));
                place.setStone(this.getAllStones().get(3));
                place.setStone(this.getAllStones().get(4));
                place.setStone(this.getAllStones().get(5));
                place.setStone(this.getAllStones().get(6));
                break;
            case 17:
                place.setStone(this.getAllStones().get(7));
                place.setStone(this.getAllStones().get(8));
                place.setStone(this.getAllStones().get(9));
                break;
            case 19:
                place.setStone(this.getAllStones().get(10));
                place.setStone(this.getAllStones().get(11));
                place.setStone(this.getAllStones().get(12));
                place.setStone(this.getAllStones().get(13));
                place.setStone(this.getAllStones().get(14));
                break;
            case 6:
                place.setStone(this.getAllStones().get(15));
                place.setStone(this.getAllStones().get(16));
                place.setStone(this.getAllStones().get(17));
                place.setStone(this.getAllStones().get(18));
                place.setStone(this.getAllStones().get(19));
                break;
            case 8:
                place.setStone(this.getAllStones().get(20));
                place.setStone(this.getAllStones().get(21));
                place.setStone(this.getAllStones().get(22));
                break;
            case 13:
                place.setStone(this.getAllStones().get(23));
                place.setStone(this.getAllStones().get(24));
                place.setStone(this.getAllStones().get(25));
                place.setStone(this.getAllStones().get(26));
                place.setStone(this.getAllStones().get(27));
                break;
            case 24:
                place.setStone(this.getAllStones().get(28));
                place.setStone(this.getAllStones().get(29));
                break;
         }
    }

    @Override
    public Color start() {
        return Color.NONE;
    }

    @Override
    public Dice dice() {

        int first_dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int second_dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        Dice result = new Dice(first_dice, second_dice);
         return result;
    }

    @Override
    public boolean set(int stone, int position){
        return true;
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
