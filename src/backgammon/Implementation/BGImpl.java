package backgammon.Implementation;

import backgammon.Backgammon;
import backgammon.Exceptions.*;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BGImpl implements Backgammon {
    private HashMap<Integer, StoneImpl> allStones =  new HashMap<Integer, StoneImpl>();
    private HashMap<Integer, PositionImpl> allPositions = new HashMap<Integer, PositionImpl>();
    private Color active_player = Color.NONE;
    private Dice points;

    public BGImpl() throws WrongPositionException, NotEnoughPointsException {
         for(int i=0;i<30;i++){
             this.setStone(new StoneImpl(this));
         }
         for(int i=0;i<=26;i++){
            PositionImpl position = new PositionImpl(this);
            replaceStones(position);
            this.setPosition(position);
         }
    }

    private void setStone(StoneImpl stone) {
        this.allStones.put(stone.getId(), stone);
    }

    private void replaceStones(PositionImpl position) throws WrongPositionException, NotEnoughPointsException {
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
        //TODO ask: wie wäre es am besten hier
        int start = ThreadLocalRandom.current().nextInt(1, 1 + 1);
        try{
            switch (start) {
                case 1 -> {
                    this.active_player = Color.WHITE;
                    return Color.WHITE;
                }
                case 2 -> {
                    this.active_player = Color.BLACK;
                    return Color.BLACK;
            }
        }
        } catch (Exception e) {
            return Color.NONE;
        }
        return Color.NONE;
    }

    @Override
    public HashMap<String, Integer> dice() throws NotAllowedMethodException {
        if(this.active_player == Color.NONE)
            throw new NotAllowedMethodException("diced before start");
        int first_dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        int second_dice = ThreadLocalRandom.current().nextInt(1, 6 + 1);
        changeActivePlayer();
        this.points = new Dice(first_dice, second_dice);
        return this.points.get_dice_dictionary();
    }

    private void changeActivePlayer() {
        if(this.active_player == Color.WHITE)
            this.active_player = Color.BLACK;
        this.active_player = Color.WHITE;
    }

    @Override
    public boolean set(int stone, int position) throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, WrongDirectionException {
        validateStone(stone);
        validatePosition(position);
        validatePoints(stone, position);
        checkIfNotWrongDirection(stone, position);
        //check_if_any_stone_in_bar();
        //set_stone(stone, position);<- <-check_if_there_one_enemy_stone_on_the_position();
        //check_if_won();
        return true;
    }

    private void checkIfNotWrongDirection(int stone_id, int position_id) throws WrongDirectionException {
        StoneImpl Stone = this.allStones.get(stone_id);
        PositionImpl Position = this.allPositions.get(position_id);

        if(this.active_player == Color.BLACK && Stone.getPositionId() < Position.getId()){
            throw new WrongDirectionException(String.format("the stone %d is moving in wrong direction. (old position: %d new position: %d)",Stone.getId(),Stone.getPositionId(), Position.getId()));
        }
        if(this.active_player == Color.WHITE && Stone.getPositionId() > Position.getId()){
            throw new WrongDirectionException(String.format("the stone %d is moving in wrong direction. (old position: %d new position: %d)",Stone.getId(),Stone.getPositionId(), Position.getId()));
        }
    }

    private void validatePoints(int stone_id, int position_id) throws NotEnoughPointsException {
        StoneImpl Stone = this.allStones.get(stone_id);
        PositionImpl Position = this.allPositions.get(position_id);

        if(Math.abs(Stone.getPositionId() - Position.getId()) == this.getTotalPoints())
            return;
        if (Math.abs(Stone.getPositionId() - Position.getId()) == this.getFirstDicePoints())
            return;
        if (Math.abs(Stone.getPositionId() - Position.getId()) == this.getSecondDicePoints())
            return;
        throw new NotEnoughPointsException("Not enough points");
    }


    private void validatePosition(int position_id) throws WrongPositionException {
       validateIfPositionIdInRange(position_id);
       validateIfNextPositionNotEnemy(position_id);
    }

    private void validateIfNextPositionNotEnemy(int position_id) throws WrongPositionException {
        PositionImpl Position = this.allPositions.get(position_id);
        if(Position.getColor() == this.active_player)
            return;
        if(Position.getColor() == Color.NONE)
            return;
        throw new WrongPositionException("Enemy position.");
    }

    private void validateIfPositionIdInRange(int position) throws WrongPositionException {
        if(position > 26 || position < 0)
            throw new WrongPositionException("Position doesn't exist");
    }

    private void validateStone(int stone) throws NotExistingStonePickedException {
        if (this.active_player == Color.WHITE){
            if(stone < 0 || stone >14)
                throw new NotExistingStonePickedException("Stone isn't allowed");
        }
        if (this.active_player == Color.BLACK){
            if(stone < 15 || stone >29)
                throw new NotExistingStonePickedException("Stone isn't allowed");
        }
    }

    @Override
    public void giveUp() {
    }

    public void setPosition(PositionImpl position) {
         this.allPositions.put(position.getId(), position);
    }

    private HashMap<Integer, StoneImpl> getAllStones() {
        return this.allStones;
    }

    public int get_stones_number(){
        return this.allStones.size();
    }

    public HashMap<Integer, PositionImpl> getAllPositions() {
        return allPositions;
    }

    public int getTotalPoints() {
        return points.getTotal_points();
    }
    public int getFirstDicePoints() {
        return points.getFirst_dice();
    }
    public int getSecondDicePoints(){ return points.getSecond_dice(); };
    public boolean get_if_double(){return points.If_double();}

    public Color getActive_player() {
        if (active_player == Color.BLACK)
            return Color.BLACK;
        return Color.WHITE;
    }
}
