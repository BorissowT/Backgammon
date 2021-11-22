package backgammon.Implementation;

public class StoneImpl {

    private final int id;
    private final Color color;
    private PositionImpl position;

    StoneImpl(BGImpl GameObject){
        this.id = GameObject.getStonesNumber();
        if(this.id<=14)
            this.color = Color.WHITE;
        else
            this.color = Color.BLACK;
    }

    void setPosition(PositionImpl position) {
        if(this.position!=null)
            this.position.removeStone(this);
        this.position = position;
    }

    int getId() {
        return id;
    }

    Color getColor() {
        return color;
    }

    int getPositionId() {
        return position.getId();
    }
}
