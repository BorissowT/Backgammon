package backgammon;

public class BGImpl implements Backgammon {
    /**
     * start() - to dice the first player
     * set() - to place a stone
     * give_up() - to close the app
     */
     BGImpl(){
        for(int i=0;i<=24;i++){
            all_positions[i] = new PositionImpl();
        }
        for(int i=0;i<30;i++){
             all_stones[i] = new StoneImpl();
         }

    }

    @Override
    public int start() {
        return 0;
    }

    @Override
    public int set(int stone, int position) {
        return 0;
    }

    @Override
    public void give_up() {

    }
}
