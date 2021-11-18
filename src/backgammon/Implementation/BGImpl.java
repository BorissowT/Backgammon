package backgammon.Implementation;

import backgammon.Backgammon;
import backgammon.Exceptions.*;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BGImpl implements Backgammon {
    private HashMap<Integer, StoneImpl> allStones =  new HashMap<Integer, StoneImpl>();
    private HashMap<Integer, PositionImpl> allPositions = new HashMap<Integer, PositionImpl>();
    //private HashMap<Integer, StoneImpl> barBLACK = new HashMap<Integer, StoneImpl>();
    //private HashMap<Integer, StoneImpl> barWHITE = new HashMap<Integer, StoneImpl>();
    private Color active_player = Color.NONE;
    private Dice points = null;

    public BGImpl() throws WrongPositionException, NotEnoughPointsException {
         for(int i=0;i<30;i++){
             this.setStone(new StoneImpl(this));
         }
         for(int i=0;i<=27;i++){
            PositionImpl position = new PositionImpl(this);
            replaceStartStones(position);
            this.setPosition(position);
         }
    }

    private void setStone(StoneImpl stone) {
        this.allStones.put(stone.getId(), stone);
    }

    private void replaceStartStones(PositionImpl position) throws WrongPositionException, NotEnoughPointsException {
         int place_id = position.getId();
         switch (place_id) {
            case 1:
                setStoneAndPositionLowLevel(this.getAllStones().get(0),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(1),position);;
            case 12:
                setStoneAndPositionLowLevel(this.getAllStones().get(2),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(3),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(4),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(5),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(6),position);
                break;
            case 17:
                //position.setStone(this.getAllStones().get(7));
                setStoneAndPositionLowLevel(this.getAllStones().get(7),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(8),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(9),position);
                break;
            case 19:
                setStoneAndPositionLowLevel(this.getAllStones().get(10),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(11),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(12),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(13),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(14),position);
                break;
            case 6:
                setStoneAndPositionLowLevel(this.getAllStones().get(15),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(16),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(17),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(18),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(19),position);
                break;
            case 8:
                setStoneAndPositionLowLevel(this.getAllStones().get(20),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(21),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(22),position);
                break;
            case 13:
                setStoneAndPositionLowLevel(this.getAllStones().get(23),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(24),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(25),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(26),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(27),position);
                break;
            case 24:
                setStoneAndPositionLowLevel(this.getAllStones().get(28),position);
                setStoneAndPositionLowLevel(this.getAllStones().get(29),position);
                break;
         }
    }

    @Override
    public Color start() {
        //TODO ask: wie wäre es am besten hier
        int start = ThreadLocalRandom.current().nextInt(1, 3);
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
        return this.points.getDiceDictionary();
    }

    private void changeActivePlayer() {
        if(this.points==null)
            return;
        if(this.active_player == Color.WHITE)
            this.active_player = Color.BLACK;
        else this.active_player = Color.WHITE;
    }

    @Override
    public boolean set(int stoneId, int positionId) throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, WrongDirectionException, NotAllowedMethodException, StoneInBarException {
        validateIfGameStarted();
        validateIfDiced();
        validateStone(stoneId);
        validatePosition(positionId);
        validatePoints(stoneId, positionId);
        checkIfNotWrongDirection(stoneId, positionId);
        checkIfAnyStoneInBar();
        setStoneInPosition(stoneId, positionId);
        //check_if_won();
        return true;
    }

    private void setStoneInPosition(int stoneId, int positionId) throws NotEnoughPointsException, WrongPositionException {
        StoneImpl Stone = this.allStones.get(stoneId);
        PositionImpl Position = this.allPositions.get(positionId);

        checkIfThereOneEnemyStoneOnThePosition(Position);
        takeOffPoints(Stone,Position);
        setStoneAndPositionLowLevel(Stone,Position);

    }

    private void takeOffPoints(StoneImpl Stone, PositionImpl Position) {
        int pointsToTakeOff = Math.abs(Stone.getPositionId() - Position.getId());
        this.points.takeOffPoints(pointsToTakeOff);
    }

    private void setStoneAndPositionLowLevel(StoneImpl Stone, PositionImpl desiredPosition) throws NotEnoughPointsException, WrongPositionException {
        desiredPosition.setStone(Stone);
        Stone.setPosition(desiredPosition);
    }

    private void checkIfThereOneEnemyStoneOnThePosition( PositionImpl Position) throws NotEnoughPointsException, WrongPositionException {
        if(Position.getStones().size() == 1){
            if(this.active_player == Color.WHITE)
                placeStoneToBar(Position.getStones().get(0), this.allPositions.get(25));
            if(this.active_player == Color.BLACK)
                placeStoneToBar(Position.getStones().get(0), this.allPositions.get(0));
        }
    }

    private void placeStoneToBar(StoneImpl Stone, PositionImpl Bar) throws NotEnoughPointsException, WrongPositionException {
        setStoneAndPositionLowLevel(Stone, Bar);
    }

    private void checkIfAnyStoneInBar() throws StoneInBarException {
        if(this.active_player == Color.BLACK){
            if(this.allPositions.get(25).getStones().size()>0)
                throw new StoneInBarException("You have to move first stone from the bar");
        }
        if(this.active_player == Color.WHITE){
            if(this.allPositions.get(0).getStones().size()>0)
                throw new StoneInBarException("You have to move first stone from the bar");
        }
    }

    private void validateIfDiced() throws NotAllowedMethodException {
        if(this.points == null)
            throw new NotAllowedMethodException("Dice before set");
    }

    private void validateIfGameStarted() throws NotAllowedMethodException {
        if(this.active_player == Color.NONE)
            throw new NotAllowedMethodException("The method start() hasn't been called before");
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

        if(Math.abs(Stone.getPositionId() - Position.getId()) == this.getTotalPoints() && Math.abs(Stone.getPositionId() - Position.getId())<=this.getTotalPoints())
            return;
        if (Math.abs(Stone.getPositionId() - Position.getId()) == this.getFirstDicePoints() && Math.abs(Stone.getPositionId() - Position.getId())<=this.getTotalPoints())
            return;
        if (Math.abs(Stone.getPositionId() - Position.getId()) == this.getSecondDicePoints() && Math.abs(Stone.getPositionId() - Position.getId())<=this.getTotalPoints())
            return;
        if (this.getIfDouble()){
            if (Math.abs(Stone.getPositionId() - Position.getId()) == this.getTotalPoints()/2 && Math.abs(Stone.getPositionId() - Position.getId())<=this.getTotalPoints())
                return;
        }
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

    public int getStonesNumber(){
        return this.allStones.size();
    }

    public HashMap<Integer, PositionImpl> getAllPositions() {
        return allPositions;
    }

    public int getTotalPoints() {
        return points.getTotalPoints();
    }
    public int getFirstDicePoints() {
        return points.getFirstDice();
    }
    public int getSecondDicePoints(){ return points.getSecondDice(); };
    public boolean getIfDouble(){return points.ifDouble();}

    public Color getActivePlayer() {
        if (active_player == Color.BLACK)
            return Color.BLACK;
        return Color.WHITE;
    }
}
