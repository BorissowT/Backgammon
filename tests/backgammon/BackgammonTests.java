package backgammon;

import org.junit.Assert;
import org.junit.Test;

public class BackgammonTests {

    public boolean if_start_dice_in_range(int result){
        if (result == 0 || result == 1)
            return true;
        return false;
    }

    //TODO dice test

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
            for (int stone=0; stone<=16;stone++){
                Assert.assertEquals(1,BGobject.set(stone,position));
            }
        }
    }

    @Test
    public void test(){
        Backgammon BGobject = new BGImpl();
        PositionImpl pos = BGobject.all_positions[0];
        StoneImpl st1 = new StoneImpl();
        StoneImpl st2 = new StoneImpl();
        pos.setStone(st1);
        pos.setStone(st2);
        pos.getStones().remove(st2);
        System.out.println(pos.getStones());
    }

}
