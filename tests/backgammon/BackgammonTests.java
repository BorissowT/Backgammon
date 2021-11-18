package backgammon;

import backgammon.Exceptions.*;
import backgammon.Implementation.BGImpl;
import backgammon.Implementation.Color;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class BackgammonTests {

    //TODO tests for stones in bar

    private boolean ifStartDiceIsBlackOrWhite(Color result){
        if (result == Color.BLACK)
            return true;
        return result == Color.WHITE;
    }

    private boolean ifGameDiceInRange(int result) {
        return result >= 2 && result <= 24;
    }

    private Backgammon makeGameInstance() throws WrongPositionException, NotEnoughPointsException {
        return new BGImpl();
    }

    @Test
    public void diceTest() throws WrongPositionException, NotEnoughPointsException, NotAllowedMethodException {
        Backgammon BGobject = makeGameInstance();
        BGobject.start();
        for (int i=0; i<10;i++){
            Assert.assertTrue(ifGameDiceInRange(BGobject.dice().get("total_points"))) ;
        }
    }

    @Test
    public void startFirstPlayerDiceTest() throws WrongPositionException, NotEnoughPointsException, NotAllowedMethodException {
        Backgammon BGobject = makeGameInstance();
        for (int i=0; i<10;i++){
            Assert.assertTrue(ifStartDiceIsBlackOrWhite(BGobject.start()));
        }
    }


    @Test(expected = WrongDirectionException.class)
    public void moveStoneInWrongDirection() throws WrongPositionException, NotEnoughPointsException, NotExistingStonePickedException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        Color player = BGobject.start();
        for(int i=0; i<=100;  i++){
            HashMap<String, Integer> points = BGobject.dice();
            while(points.get("total_points") != 5){
                points = BGobject.dice();
            }
            if(player == Color.BLACK){
                Assert.assertTrue(BGobject.set(23,18));
            }
            if(player == Color.WHITE){
                Assert.assertTrue(BGobject.set(6,7));
            }
        }
    }

    @Test(expected = WrongPositionException.class)
    public void moveToNotAllowedPositionsTest2() throws WrongPositionException, NotEnoughPointsException, NotExistingStonePickedException, WrongDirectionException, NotAllowedMethodException {
        Backgammon BGobject = makeGameInstance();
        BGobject.start();
        for (int position=-50; position<=-1;position++){
            for (int stone=0; stone<=29;stone++){
                BGobject.dice();
                Assert.assertTrue(BGobject.set(stone,position));
            }
        }
    }

    @Test(expected = WrongPositionException.class)
    public void moveToEnemyPositionTest() throws WrongPositionException, NotEnoughPointsException, NotExistingStonePickedException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        Color player = BGobject.start();
        HashMap<String, Integer> points = BGobject.dice();
        while(points.get("total_points") != 5){
            points = BGobject.dice();
        }
        moveToEnemyPosition(BGobject, player);
    }

    @Test
    public void checkDoubleDiceTest() throws WrongPositionException, NotEnoughPointsException, NotAllowedMethodException {
        Backgammon BGobject = makeGameInstance();
        BGobject.start();
        for (int i=0; i<=100;i++){
            HashMap<String, Integer> points = BGobject.dice();
            if(points.get("if_double")==1){
                //System.out.println(points.getFirst_dice()+" "+points.getSecond_dice()+" "+points.getTotal_points());
                int double_sum = (points.get("second_dice") + points.get("first_dice"))*2;
                Assert.assertEquals(double_sum, (int)points.get("total_points"));
            }
        }
    }

    public void moveToEnemyPosition(Backgammon BGobject, Color player) throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, WrongDirectionException, NotAllowedMethodException {
        if(player == Color.BLACK){
            Assert.assertTrue(BGobject.set(28,19));
        }
        else if(player == Color.WHITE){
            Assert.assertTrue(BGobject.set(0,6));
        }
    }

    @Test(expected = NotEnoughPointsException.class)
    public void notEnoughPointsTest() throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        Color player = BGobject.start();
        HashMap<String, Integer> points = BGobject.dice();
        while(points.get("total_points") >= 6){
            points = BGobject.dice();
        }
        if(player == Color.BLACK){
            BGobject.set(28,18);
        }
        if(player == Color.WHITE){
            BGobject.set(0,7);
        }
    }

    @Test(expected = NotExistingStonePickedException.class)
    public void notExistingStonePickedTest() throws NotExistingStonePickedException, NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        Color player = BGobject.start();
        BGobject.dice();
        for (int i=0; i<=100;i++){
            int stone = ThreadLocalRandom.current().nextInt(-1000, -1);
            if(player == Color.BLACK)
                BGobject.set(stone,23);
            if(player == Color.WHITE)
                BGobject.set(stone,1);
        }

    }

    @Test(expected = NotExistingStonePickedException.class)
    public void notExistingStonePickedTest2() throws NotExistingStonePickedException, NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        Color player = BGobject.start();
        BGobject.dice();
        for (int i=0; i<=100;i++){
            int stone = ThreadLocalRandom.current().nextInt(30, 1000);
            if(player == Color.BLACK)
                BGobject.set(stone,23);
            if(player == Color.WHITE)
                BGobject.set(stone,1);
        }
    }

    @Test(expected = NotAllowedMethodException.class)
    public void diceBeforeStartTest() throws NotEnoughPointsException, WrongPositionException, NotAllowedMethodException {
        Backgammon BGobject = makeGameInstance();
        BGobject.dice();
    }

    @Test(expected = NotAllowedMethodException.class)
    public void setBeforeDiceTest() throws NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, NotExistingStonePickedException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        Color player = BGobject.start();
        if(player == Color.BLACK)
            BGobject.set(28,23);
        if(player == Color.WHITE)
            BGobject.set(1,1);
    }

    @Test(expected = NotAllowedMethodException.class)
    public void setBeforeStartTest() throws NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, NotExistingStonePickedException, WrongDirectionException {
        Backgammon BGobject = makeGameInstance();
        BGobject.set(28,23);
    }

}
