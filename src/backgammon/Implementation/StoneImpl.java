package backgammon.Implementation;

public class StoneImpl {

    private int id;
    private Color color;
    private PositionImpl position;

    StoneImpl(BGImpl GameObject){
        this.id = GameObject.get_stones_number();
        if(this.id<=14)
            this.color = Color.WHITE;
        else
            this.color = Color.BLACK;
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
