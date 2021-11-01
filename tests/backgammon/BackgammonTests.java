package backgammon;

import backgammon.Exceptions.WrongPositionException;
import backgammon.Implementation.BGImpl;
import backgammon.Implementation.Color;
import backgammon.Implementation.Dice;
import org.junit.Assert;
import org.junit.Test;

public class BackgammonTests {

    private boolean if_start_dice_is_black_or_white(Color result){
        if (result == Color.BLACK)
            return true;
        if (result == Color.WHITE)
            return true;
        return false;
    }

    private boolean if_game_dice_in_range(int result) {
        if (result>=2&&result<=24)
            return true;
        return false;
    }


    private Backgammon make_game_instance() throws WrongPositionException {
        return new BGImpl();
    }

    @Test
    public void dice_test() throws WrongPositionException {
        Backgammon BGobject = make_game_instance();
        for (int i=0; i<10;i++){
            Assert.assertTrue(if_game_dice_in_range(BGobject.dice().getTotal_points())) ;
        }
    }

    @Test
    public void start_first_player_dice_test() throws WrongPositionException {
        Backgammon BGobject = make_game_instance();
        for (int i=0; i<10;i++){
            Assert.assertTrue(if_start_dice_is_black_or_white(BGobject.start()));
        }
    }


    @Test(expected = WrongPositionException.class)
    public void move_to_not_allowed_positions_test_1() throws WrongPositionException {
        Backgammon BGobject = make_game_instance();
        for (int position=24; position<=50;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertTrue(BGobject.set(stone,position));
            }
        }
    }

    @Test
    public void move_to_not_allowed_positions_test_2() throws WrongPositionException {
        Backgammon BGobject = make_game_instance();
        for (int position=-50; position<=-1;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertTrue(BGobject.set(stone,position));
            }
        }
    }

    @Test(expected = WrongPositionException.class)
    public void move_to_position() throws WrongPositionException {
        Backgammon BGobject = make_game_instance();
        Color player = BGobject.start();
        Dice points = BGobject.dice();
        if(points.getTotal_points() >= 5){
            move_to_enemy_position(BGobject, player);
        }
    }

    @Test
    public void check_double_dice() throws WrongPositionException {
        Backgammon BGobject = make_game_instance();
        for (int i=0; i<=100;i++){
            Dice points = BGobject.dice();
            if(points.If_double()){
                //System.out.println(points.getFirst_dice()+" "+points.getSecond_dice()+" "+points.getTotal_points());
                int double_sum = (points.getSecond_dice() + points.getFirst_dice())*2;
                Assert.assertEquals(double_sum, points.getTotal_points());
            }

        }
    }

    public void move_to_enemy_position(Backgammon BGobject, Color player){
        if(player == Color.BLACK){
            Assert.assertTrue(BGobject.set(28,19));
        }
        else if(player == Color.WHITE){
            Assert.assertTrue(BGobject.set(0,6));
        }
    }

}
