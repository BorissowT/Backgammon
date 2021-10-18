package backgammon;

import org.junit.Assert;
import org.junit.Test;

public class BackgammonTests {

    public boolean if_start_dice_in_range(int result){
        if (result == 0 || result == 1)
            return true;
        return false;
    }

    private boolean if_game_dice_in_range(int result) {
        if (result>=2&&result<=12)
            return true;
        return false;
    }

    @Test
    public void dice_test(){
        Backgammon BGobject = new BGImpl();
        for (int i=0; i<10;i++){
            Assert.assertTrue(if_game_dice_in_range(BGobject.dice()));
        }
    }

    @Test
    public void start_first_player_dice_test(){
        Backgammon BGobject = new BGImpl();
        for (int i=0; i<10;i++){
            Assert.assertTrue(if_start_dice_in_range(BGobject.start()));
        }
    }

    @Test
    public void allowed_positions_test() {
        Backgammon BGobject = new BGImpl();
        for (int position=0; position<=24;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertEquals(1,BGobject.set(stone,position));
            }
        }
    }

    @Test()
    public void move_to_not_allowed_positions_test_1(){
        Backgammon BGobject = new BGImpl();
        for (int position=24; position<=50;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertEquals(0,BGobject.set(stone,position));
            }
        }
    }

    @Test
    public void move_to_not_allowed_positions_test_2(){
        Backgammon BGobject = new BGImpl();
        for (int position=-50; position<=-1;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertEquals(0,BGobject.set(stone,position));
            }
        }
    }

    @Test
    public void move_to_enemy_position(){
        Backgammon BGobject = new BGImpl();
        int player = BGobject.start();
        int points = BGobject.dice();
        if(player == 0){
            if(points >= 5){
                Assert.assertEquals(0,BGobject.set(28,19));
            }
        }
        else if(player == 1){
            if(points >= 5){
                Assert.assertEquals(0,BGobject.set(0,6));
            }
        }
    }


}
