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

    private boolean if_start_dice_is_black_or_white(Color result){
        if (result == Color.BLACK)
            return true;
        return result == Color.WHITE;
    }

    private boolean if_game_dice_in_range(int result) {
        return result >= 2 && result <= 24;
    }

    private Backgammon make_game_instance() throws WrongPositionException, NotEnoughPointsException {
        return new BGImpl();
    }

    @Test
    public void dice_test() throws WrongPositionException, NotEnoughPointsException, NotAllowedMethodException {
        Backgammon BGobject = make_game_instance();
        BGobject.start();
        for (int i=0; i<10;i++){
            Assert.assertTrue(if_game_dice_in_range(BGobject.dice().get("total_points"))) ;
        }
    }

    @Test
    public void start_first_player_dice_test() throws WrongPositionException, NotEnoughPointsException, NotAllowedMethodException {
        Backgammon BGobject = make_game_instance();
        for (int i=0; i<10;i++){
            Assert.assertTrue(if_start_dice_is_black_or_white(BGobject.start()));
        }
    }


    @Test(expected = WrongDirectionException.class)
    public void move_stone_in_wrong_direction() throws WrongPositionException, NotEnoughPointsException, NotExistingStonePickedException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
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
    public void move_to_not_allowed_positions_test_2() throws WrongPositionException, NotEnoughPointsException, NotExistingStonePickedException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
        for (int position=-50; position<=-1;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertTrue(BGobject.set(stone,position));
            }
        }
    }

    @Test(expected = WrongPositionException.class)
    public void move_to_enemy_position_test() throws WrongPositionException, NotEnoughPointsException, NotExistingStonePickedException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
        Color player = BGobject.start();
        HashMap<String, Integer> points = BGobject.dice();
        while(points.get("total_points") != 5){
            points = BGobject.dice();
        }
        move_to_enemy_position(BGobject, player);
    }

    @Test
    public void check_double_dice_test() throws WrongPositionException, NotEnoughPointsException, NotAllowedMethodException {
        Backgammon BGobject = make_game_instance();
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

    public void move_to_enemy_position(Backgammon BGobject, Color player) throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, WrongDirectionException {
        if(player == Color.BLACK){
            Assert.assertTrue(BGobject.set(28,19));
        }
        else if(player == Color.WHITE){
            Assert.assertTrue(BGobject.set(0,6));
        }
    }

    @Test(expected = NotEnoughPointsException.class)
    public void not_enough_points_test() throws NotEnoughPointsException, WrongPositionException, NotExistingStonePickedException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
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
    public void not_existing_stone_picked_test() throws NotExistingStonePickedException, NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
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
    public void not_existing_stone_picked_test2() throws NotExistingStonePickedException, NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
        Color player = BGobject.start();
        BGobject.dice();
        for (int i=0; i<=100;i++){
            int stone = ThreadLocalRandom.current().nextInt(1, 1000);
            if(player == Color.BLACK)
                BGobject.set(stone,23);
            if(player == Color.WHITE)
                BGobject.set(stone,1);
        }
    }

    @Test(expected = NotAllowedMethodException.class)
    public void dice_before_start_test() throws NotEnoughPointsException, WrongPositionException, NotAllowedMethodException {
        Backgammon BGobject = make_game_instance();
        BGobject.dice();
    }

    @Test(expected = NotAllowedMethodException.class)
    public void set_before_dice_test() throws NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, NotExistingStonePickedException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
        Color player = BGobject.start();
        if(player == Color.BLACK)
            BGobject.set(28,23);
        if(player == Color.WHITE)
            BGobject.set(1,1);
    }

    @Test(expected = NotAllowedMethodException.class)
    public void set_before_start_and_dice_test() throws NotEnoughPointsException, WrongPositionException, NotAllowedMethodException, NotExistingStonePickedException, WrongDirectionException {
        Backgammon BGobject = make_game_instance();
        BGobject.set(28,23);
    }

}
