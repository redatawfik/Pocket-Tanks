package game;

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
    double ang = 70 * DEG2RAD;
    double initialVelo = 30;
    float g = -9.8f;
    float time = 0;


    public void draw() {
        myTank.getCanon().setImageResource(new ImageResource("/m1.png"));
        enemyTank.getCanon().setImageResource(new ImageResource("/md3.png"));
        myTank.setImageResource(new ImageResource("/tank.png"));
        enemyTank.setImageResource(new ImageResource("/tank.png"));
        myTank.draw();
        enemyTank.draw();
        myTank.getCanon().draw();
        enemyTank.getCanon().draw();

        time += .1;

        if (bullet != null) {


            bullet.setImageResource(new ImageResource("/bullet.png"));

       //     float x = (float) (initialX + (initialVelo * Math.cos(ang)));
            float initialX = bullet.getX();
            float x = initialX+1;
            bullet.setX(x+GameDisplay.getInstance().getDeltaTime());


//            double first = (x * Math.tan(ang));
//            double second = (-9.8 * Math.pow(x, 2)) /
//                    (2 * Math.pow(initialVelo, 2) * Math.pow(Math.cos(ang), 2));
//            float y = (float) (first + second);
//
//            System.out.println("X: " + x + "   Y: " + y);
//            bullet.setY(y+GameDisplay.getInstance().getDeltaTime());
//            // bullet.setX(bullet.getX() + GameDisplay.getInstance().getDeltaTime() * 10);
//            // bullet.setY(y + 1);
            bullet.draw();
           // if(bullet.getY()<0||bullet.getX()>100) bullet = null;

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
    }

    public void myCanonDown() {
        myTank.getCanon().setRotation(myTank.getCanon().getRotation() + .5f);
    }


    public void shoot() {
        bullet = new Bullet(myTank.getCanon().getX(), myTank.getCanon().getY());
    }
}
