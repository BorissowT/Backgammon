package backgammon.Implementation;

import backgammon.Backgammon;
import backgammon.Exceptions.NotEnoughPointsException;
import backgammon.Exceptions.WrongPositionException;
import backgammon.Exceptions.WrongStonePickedException;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class BGImpl implements Backgammon {
    private ArrayList<StoneImpl> allStones = new ArrayList<StoneImpl>();
    private ArrayList<PositionImpl> allPositions = new ArrayList<PositionImpl>();
    private Color active_player;
    private Dice points;

    public BGImpl() throws WrongPositionException {
         for(int i=0;i<30;i++){
             this.setStone(new StoneImpl(this));
         }
         for(int i=0;i<=26;i++){
            PositionImpl position = new PositionImpl(this);
            replace_stones(position);
            this.setPosition(position);
         }
    }

    private void replace_stones(PositionImpl position) throws WrongPositionException {
         int place_id = position.getId();
         switch (place_id) {
            case 1:
                position.setStone(this.getAllStones().get(0));
                position.setStone(this.getAllStones().get(1));
                break;
            case 12:
                position.setStone(this.getAllStones().get(2));
                position.setStone(this.getAllStones().get(3));
                position.setStone(this.getAllStones().get(4));
                position.setStone(this.getAllStones().get(5));
                position.setStone(this.getAllStones().get(6));
                break;
            case 17:
                position.setStone(this.getAllStones().get(7));
                position.setStone(this.getAllStones().get(8));
                position.setStone(this.getAllStones().get(9));
                break;
            case 19:
                position.setStone(this.getAllStones().get(10));
                position.setStone(this.getAllStones().get(11));
                position.setStone(this.getAllStones().get(12));
                position.setStone(this.getAllStones().get(13));
                position.setStone(this.getAllStones().get(14));
                break;
            case 6:
                position.setStone(this.getAllStones().get(15));
                position.setStone(this.getAllStones().get(16));
                position.setStone(this.getAllStones().get(17));
                position.setStone(this.getAllStones().get(18));
                position.setStone(this.getAllStones().get(19));
                break;
            case 8:
                position.setStone(this.getAllStones().get(20));
                position.setStone(this.getAllStones().get(21));
                position.setStone(this.getAllStones().get(22));
                break;
            case 13:
                position.setStone(this.getAllStones().get(23));
                position.setStone(this.getAllStones().get(24));
                position.setStone(this.getAllStones().get(25));
                position.setStone(this.getAllStones().get(26));
                position.setStone(this.getAllStones().get(27));
                break;
            case 24:
                position.setStone(this.getAllStones().get(28));
                position.setStone(this.getAllStones().get(29));
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
    public boolean set(int stone, int position) throws NotEnoughPointsException, WrongPositionException, WrongStonePickedException {

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


    public Dice getPoints() {
        return points;
    }

    public Color getActive_player() {
        return active_player;
    }
}
