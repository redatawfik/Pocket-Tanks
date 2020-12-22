package game.game_objects;

import game.GameDisplay;
import game.ImageResource;

public class GameObject {
    private float x = 1, y = 1, width = 5, height = 5, rotation = 0;
    private ImageResource imageResource;

    public GameObject() {
    }

    public GameObject(float x, float y, float width, float height, ImageResource imageResource, float rotation) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.imageResource = imageResource;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public ImageResource getImageResource() {
        return imageResource;
    }

    public void setImageResource(ImageResource imageResource) {
        this.imageResource = imageResource;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void draw() {
        GameDisplay.getInstance().drawImage(imageResource, getX(), getY(), getWidth(), getHeight(), rotation);
    }
}
