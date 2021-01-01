package game;


import game.action.AbstractAction;
import game.action.EnemyAction;
import game.action.MyAction;
import game.game_objects.GameObject;
import game.game_objects.Ground;
import game.game_objects.Tank;
import game.menu.LoadingView;

public class World {

    private static World instance;
    private final Tank leftTank;
    private final Tank rightTank;
    private Tank myTank;
    private Tank enemyTank;
    private final Ground ground;
    private boolean isMyTurn;
    private boolean leftTurn = true;
    private boolean shouldPlaynow = false;
    // Bullet animations
    private int bulletAnimationIndex = -1;
    private boolean shouldAnimateBullet;
    private ImageResource[] bulletAnimations;
    private float bulletX, bulletY;

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


    public static void destroy() {
        AbstractAction.getInstance().destroy();
        EnemyAction.getInstance().destroy();
        MyAction.getInstance().destroy();
        LoadingView.getInstance().destroy();

        Game.getInstance().destroyGame();
        GameDisplay.getInstance().destroy();

        Ground.getInstance().destroy();

        instance = null;
    }

    public void checkEndOFGame() {
        if (myTank.getBulletCounter() == 0 && enemyTank.getBulletCounter() == 0)
            MyAction.getInstance().endMatch();
    }

    public boolean getShouldPlayNow() {
        return shouldPlaynow;
    }

    public void setShouldPlayNow(boolean spn) {
        this.shouldPlaynow = spn;
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
        if (shouldAnimateBullet) {
            drawBulletAnimation();
        }
    }

    public void update() {
        myTank.update();
        enemyTank.update();
        GameFrame.getInstance().updateControlPanel();
        if (shouldAnimateBullet) {
            if (bulletAnimationIndex < 15) {
                bulletAnimationIndex++;
            } else {
                shouldAnimateBullet = false;
                bulletAnimationIndex = -1;
            }
        }
    }

    public void setMeLeft() {
        myTank = leftTank;
        enemyTank = rightTank;
        isMyTurn = true;
        shouldPlaynow = true;
    }

    public void setMeRight() {
        myTank = rightTank;
        enemyTank = leftTank;
        isMyTurn = false;
        shouldPlaynow = false;
    }

    public boolean isMyTurn() {

        // return (shouldPlaynow&&isMyTurn);
        return (isMyTurn);
    }

    public void setMyTurn(boolean myTurn) {
        isMyTurn = myTurn;
    }

    public boolean isRightPosition() {
        return myTank == rightTank;
    }

    public Tank getRightTank() {
        return rightTank;
    }

    public Tank getLeftTank() {
        return leftTank;
    }

    public Tank getMyEnemy(Tank tank) {
        if (myTank == tank) {


            return enemyTank;

        } else {

            return myTank;
        }
    }

    public boolean isLeftTurn() {
        return leftTurn;
    }

    public void setLeftTurn(boolean turn) {
        leftTurn = turn;
    }

    public Tank getTurnTank() {
        if (leftTurn)
            return leftTank;

        return rightTank;
    }

    public void startBulletAnimation(float x, float y, ImageResource[] resources) {
        bulletX = x;
        bulletY = y;
        bulletAnimations = resources;
        shouldAnimateBullet = true;
    }

    private void drawBulletAnimation() {
        new GameObject(bulletX, bulletY, 5, 5, bulletAnimations[bulletAnimationIndex], 0).draw();
    }
}
