package game;

public class Tank extends GameObject {
    private GameObject canon;
    private int moves = 5;

    public Tank(float x, float y, String canonImage) {
        this.canon = new GameObject(x + .5f, y - .1f, 5, 5, new ImageResource("/" + canonImage));
        this.setX(x);
        this.setY(Ground.getInstance().getMesh()[(int) x] + .88f);
        this.setImageResource(new ImageResource("/tank.png"));
    }

    @Override
    public void setX(float x) {
        if(x<0) x=0;
        if(x>100) x=100;
        super.setX(x);
        canon.setX(x + .5f);
        setY(Ground.getInstance().getMesh()[(int) x] + .88f);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        canon.setY(y + 0.3f);
    }


    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    @Override
    public void draw() {
        setY(Ground.getInstance().getMesh()[(int) getX()] + .88f);
        super.draw();
    }

    public GameObject getCanon() {
        return canon;
    }
}
