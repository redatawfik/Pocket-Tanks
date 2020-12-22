package game.action;

public interface Action {
    String SHOOT = "shoot";
    String MOVE_LEFT = "move-left";
    String MOVE_RIGHT = "move-right";
    String CANON_UP = "canon-up";
    String CANON_DOWN = "canon-down";

    void shoot();

    void moveLeft();

    void moveRight();

    void canonUp();

    void canonDown();
}
