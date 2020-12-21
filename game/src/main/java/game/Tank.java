package game;

public class Tank extends GameObject {
    private GameObject canon;

    public Tank(float x, float y) {
        this.canon = new GameObject(x + .5f, y-.1f, 5, 5, new ImageResource("/m1.png"));
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        canon.setX(x + .5f);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        canon.setY(y+0.3f);
    }

    public GameObject getCanon() {
        return canon;
    }
}
