package game;


import game.action.AbstractAction;
import game.action.ComputerAction;
import game.action.EnemyAction;
import game.action.MyAction;
import game.game_objects.GameObject;
import game.game_objects.Ground;
import game.game_objects.Tank;
import game.menu.GameMode;
import game.menu.LoadingView;
import game.networking.Socket;

import java.util.Timer;

public class World {

    private static World instance;
    private final Tank leftTank;
    private final Tank rightTank;
    private final Ground ground;
    private final GameObject arrow;
    private final ImageResource arrowImageResource;
    private Tank myTank;
    private Tank enemyTank;
    private boolean isMyTurn;
    private boolean leftTurn = true;
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
        arrow = new GameObject();
        arrowImageResource = new ImageResource("/arrow.png");
    }

    public static World getInstance() {
        if (instance == null)
            instance = new World();

        return instance;
    }

    public static void destroy() {
        Game.stopAnimation();

        AbstractAction.destroy();
        EnemyAction.destroy();
        MyAction.destroy();
        LoadingView.destroy();

        GameDisplay.destroy();
        Game.destroyGame();

        Ground.destroy();

        ComputerAction.destroy();

        if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
            Socket.getInstance().closeConnection();
        }

        instance = null;
    }

    public void checkEndOFGame() {
        if (myTank.getBulletCounter() == 0 && enemyTank.getBulletCounter() == 0) {
//            destroy();
//            GameFrame.getInstance().changeDisplayToMenu();
            new Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            GameFrame.getInstance().showResultView(leftTank.getScore() > rightTank.getScore() ?
                                    "Left tank won" : "Right tank won");
                        }
                    },
                    5000
            );
        }
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

        drawArrow();
    }

    private void drawArrow() {
        Tank tank;
        if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
            if (isMyTurn) {
                tank = getMyTank();
            } else {
                tank = getEnemyTank();
            }
        } else {
            tank = getTurnTank();
        }

        arrow.setX(tank.getX());
        arrow.setY(tank.getY() + 5);
        arrow.setImageResource(arrowImageResource);
        arrow.setWidth(3);
        arrow.setHeight(3);
        arrow.draw();
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

    public boolean isBulletExist() {
        return myTank.hasBullet() || enemyTank.hasBullet();
    }
}
