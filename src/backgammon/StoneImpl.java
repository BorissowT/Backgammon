package backgammon;

public class StoneImpl {

    private int id;
    private Color color;
    private PositionImpl position;

    StoneImpl(BGImpl GameObject){
        this.id = GameObject.getAllStones().size();
        if(this.id>15)
            this.color = color.WHITE;
        else
            this.color = color.BLACK;
    }

    public void setPosition(PositionImpl position) {
        this.position = position;
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
