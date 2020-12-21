package game;

public class World {

    private static World instance;
    private Tank myTank, enemyTank;
    private ImageResource imageResource;
    private Ground ground;
    private int myMoveLeft;
    private int myMoveRight;

    private int xx = 15, yy = 10;
    private Bullet bullet;
    private float[] mesh;
    private boolean myTurn = true;

    private World() {
        ground = Ground.getInstance();
        mesh = ground.getMesh();
        myTank = new Tank(xx, yy, "m1.png");
        enemyTank = new Tank(xx + 65, yy, "m1.png");
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
        myTank.draw();
        enemyTank.draw();
        myTank.getCanon().draw();
        enemyTank.getCanon().draw();
        ground.draw();

        if (bullet != null) {
            float deltaTime = GameDisplay.getInstance().getDeltaTime();
            time += deltaTime * 30;
            float initX = bullet.getTank().getCanon().getX();
            float initY = bullet.getTank().getCanon().getY();

            float velX = (float) (initialVelo * Math.cos(ang));
            float velY = (float) (initialVelo * Math.sin(ang));

            float currVelo = velY + g * time;

            float x = velX * time + initX;
            float y = (float) (currVelo * time + initY + .5 * g * Math.pow(time, 2));
            bullet.setX(x);
            bullet.setY(y);

            bullet.setImageResource(new ImageResource("/bullet.png"));

            bullet.draw();

            mesh = Ground.getInstance().getMesh();
            if (bullet.getX() < 0 || bullet.getX() > 100) {
                bullet = null;
                isShooting = false;
                return;
            }

            if (bullet.getY() <= mesh[(int) bullet.getX()]) {
                destroyGround(bullet.getX());
                bullet = null;
                isShooting = false;
            }
        }
    }

    private void destroyGround(float x) {
        float[] min = new float[9];
        float nn = .2f;

        for (int i = 0; i < min.length; i++) {
            min[i] = nn;
            if (i < min.length / 2) {
                nn += .1;
            } else {
                nn -= .1;
            }
        }


        for (int i = (int) (x - 4), y = 0; i <= x + 4; i++, y++) {
            mesh[i] -= min[y];
        }
    }

    public void update() {
        if (myMoveLeft > 0) {
            getTurnTank().setX(getTurnTank().getX() - 0.04f);
            myMoveLeft--;
        } else if (myMoveRight > 0) {
            getTurnTank().setX(getTurnTank().getX() + 0.04f);
            myMoveRight--;
        }
    }

    public void startMoveLeft() {
        if (myMoveLeft != 0 || myMoveRight != 0) return;
        getTurnTank().setMoves(getTurnTank().getMoves() - 1);
        myMoveLeft = 150;
    }

    public void startMoveRight() {
        if (myMoveLeft != 0 || myMoveRight != 0) return;
        getTurnTank().setMoves(getTurnTank().getMoves() - 1);
        myMoveRight = 150;
    }

    public void myCanonUp() {
        getTurnTank().getCanon().setRotation(getTurnTank().getCanon().getRotation() - .5f);
    }

    public void myCanonDown() {
        getTurnTank().getCanon().setRotation(getTurnTank().getCanon().getRotation() + .5f);
    }

    public void shoot() {
        if (isShooting) return;
        time = 0;
        isShooting = true;
        ang = -getTurnTank().getCanon().getRotation() * DEG2RAD;
        bullet = new Bullet(getTurnTank());
        myTurn = !myTurn;
    }

    private Tank getTurnTank() {
        if (myTurn) return myTank;
        return enemyTank;
    }
}
