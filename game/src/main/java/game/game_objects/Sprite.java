package game.game_objects;

import game.ImageResource;

public class Sprite extends GameObject{
    private float x ;
    private float y;

    public Sprite(float x, float y, String SpritPath){
        this.x = x;
        this.y = y;
        setImageResource(new ImageResource(SpritPath));
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

}
