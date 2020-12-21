package game;

import java.time.Duration;
import java.time.LocalTime;

public class World {

    private static World instance;
    private Tank myTank, enemyTank;
    private ImageResource imageResource;
    private int myMoveLeft;
    private int myMoveRight;

    private int xx = 15, yy = 10;
    private Bullet bullet;

    private World() {
        myTank = new Tank(xx, yy);
        enemyTank = new Tank(xx + 65, yy);
        imageResource = new ImageResource("/tank.png");
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

    public void updateMyTank(float x, float y) {
        myTank.setX(x);
        myTank.setY(y);
    }

    public void updateEnemyTank(float x, float y) {
        enemyTank.setX(x);
        enemyTank.setY(y);
    }


    final double DEG2RAD = Math.PI / 180;
    double ang = 20 * DEG2RAD;
    double initialVelo = 40;
    float g = -9.8f;
    float time;
    boolean isShooting = false;

    public void draw() {
        myTank.getCanon().setImageResource(new ImageResource("/m1.png"));
        enemyTank.getCanon().setImageResource(new ImageResource("/md3.png"));
        myTank.setImageResource(new ImageResource("/tank.png"));
        enemyTank.setImageResource(new ImageResource("/tank.png"));
        myTank.draw();
        enemyTank.draw();
        myTank.getCanon().draw();
        enemyTank.getCanon().draw();
        if (bullet != null) {
            float deltaTime = GameDisplay.getInstance().getDeltaTime();
            time += deltaTime * 1.5;
            float initX = myTank.getCanon().getX();
            float initY = myTank.getCanon().getY();

            float velX = (float) (initialVelo * Math.cos(ang));
            float velY = (float) (initialVelo * Math.sin(ang));

            float currVelo = velY + g * time;

            float x = velX * time + initX;
            float y = (float) (currVelo * time + initY + .5 * g * Math.pow(time, 2));
            bullet.setX(x);
            bullet.setY(y);

            bullet.setImageResource(new ImageResource("/bullet.png"));

            bullet.draw();

            if (bullet.getY() < 0 || bullet.getX() > 100) {
                bullet = null;
                isShooting = false;
            }
        }
    }

    public void update() {
        if (myMoveLeft > 0) {
            myTank.setX(myTank.getX() - 0.04f);
            myMoveLeft--;
        } else if (myMoveRight > 0) {
            myTank.setX(myTank.getX() + 0.04f);
            myMoveRight--;
        }
    }

    public void startMoveLeft() {
        if (myMoveLeft != 0 || myMoveRight != 0) return;
        myMoveLeft = 150;
    }

    public void startMoveRight() {
        if (myMoveLeft != 0 || myMoveRight != 0) return;
        myMoveRight = 150;
    }

    public void myCanonUp() {
        myTank.getCanon().setRotation(myTank.getCanon().getRotation() - .5f);
        System.out.println(myTank.getCanon().getRotation());
    }

    public void myCanonDown() {
        myTank.getCanon().setRotation(myTank.getCanon().getRotation() + .5f);
    }

    public void shoot() {
        if (isShooting) return;
        time = 0;
        isShooting = true;
        ang = -myTank.getCanon().getRotation() * DEG2RAD;
        bullet = new Bullet(myTank.getCanon().getX(), myTank.getCanon().getY());
    }
}
