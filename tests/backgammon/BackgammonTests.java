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
    public void allowed_positions_test(){
        Backgammon BGobject = new BGImpl();
        for (int position=0; position<=24;position++){
            for (int stone=0; stone<=29;stone++){
                Assert.assertEquals(1,BGobject.set(stone,position));
            }
        }
    }

    @Test
    public void position_1_stones_amount_test(){
        Backgammon BGobject = new BGImpl();
        //PositionImpl pos = BGobject.get;
        //Assert.assertEquals(2,pos.getStones().size());
    }

}
