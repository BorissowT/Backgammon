package backgammon;

public class BGImpl implements Backgammon {
    /**
     * start() - to dice the first player
     * set() - to place a stone
     * give_up() - to close the app
     */
     BGImpl(){
         for(int i=0;i<30;i++){
             all_stones[i] = new StoneImpl();
         }
         for(int i=0;i<=24;i++){
            PositionImpl position = new PositionImpl();
            replace_stones(position);
            all_positions[i] = position;
         }
    }



    private void replace_stones(PositionImpl place){
         int place_id = place.getId();
         switch (place_id) {
            case 1:
                //TODO replace 2 white stones
                break;
            case 12:
                //TODO replace 5 white stones
                break;
            case 17:
                //TODO replace 2 white stones
                break;
            case 19:
                //TODO replace 2 white stones
                break;
            case 6:
                //TODO replace 5 black stones
                break;
            case 13:
                //TODO replace 5 black stones
                break;
            case 24:
                //TODO replace 2 black stones
                break;
         }
    }

    @Override
    public int start() {
        return 2;
    }

    @Override
    public int dice() {
        return 0;
    }

    @Override
    public int set(int stone, int position) {
        return 2;
    }

    @Override
    public void give_up() {

    }
}
