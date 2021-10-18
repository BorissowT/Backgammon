package backgammon;

public class StoneImpl {

    private int id;
    //TODO change to enum
    private Color color;
    private PositionImpl position;

    StoneImpl(BGImpl GameObject){
        this.id = GameObject.getAllStones().size();
        // TODO change to enum
        if(this.id>15)
            //this.color = "WHITE";
            this.color = color.WHITE;
        else
            this.color = color.BLACK;
        GameObject.setStone(this);
    }

    public int getId() {
        return id;
    }

    public Color getColor() {
        return color;
    }

    public PositionImpl getPosition() {
        return position;
    }
}
