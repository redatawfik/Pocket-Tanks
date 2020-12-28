package game;


import game.game_objects.Ground;
import game.game_objects.Tank;

public class World {

    private static World instance;
    private final Tank leftTank;
    private final Tank rightTank;
    private Tank myTank;
    private Tank enemyTank;
    private final Ground ground;
    private boolean isMyTurn;

    private World() {
        ground = Ground.getInstance();
        int tX = 15;
        int tY = 10;
        leftTank = new Tank(tX, tY, "m1.png", -45);
        rightTank = new Tank(tX + 65, tY, "m1.png", -135);
    }

    public static World getInstance() {
        if (instance == null)
            instance = new World();

        return instance;
    }

    public Tank getMyTank() {
        return myTank;
    }

    public Tank getEnemyTank() {
        return enemyTank;
    }

    public void draw() {
        myTank.draw();
        enemyTank.draw();
        ground.draw();
    }

    public void update() {
        myTank.update();
        enemyTank.update();
    }

    public void setMeLeft() {
        myTank = leftTank;
        enemyTank = rightTank;
        isMyTurn = true;
    }

    public void setMeRight() {
        myTank = rightTank;
        enemyTank = leftTank;
        isMyTurn = false;
    }

    public boolean isMyTurn() {
        return isMyTurn;
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public boolean isRightPosition() {
        return myTank == rightTank;
    }
}
