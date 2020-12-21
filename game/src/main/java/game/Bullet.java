package game;

public class Bullet extends GameObject {

    private float initialX, initialY, velX, velY;

    public Bullet(float x, float y) {
        this.setX(x);
        this.setY(y);
        this.setWidth(2);
        this.setHeight(2);
        initialX = x;
        initialY = y;
    }

    public float getVelX() {
        return velX;
    }

    public void setVelX(float velX) {
        this.velX = velX;
    }

    public float getVelY() {
        return velY;
    }

    public void setVelY(float velY) {
        this.velY = velY;
    }
}
