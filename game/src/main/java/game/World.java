package game;


public class World {

    private static World instance;
    private final Tank myTank;
    private final Tank enemyTank;
    private final Ground ground;

    private World() {
        ground = Ground.getInstance();
        int tX = 15;
        int tY = 10;
        myTank = new Tank(tX, tY, "m1.png", -45);
        enemyTank = new Tank(tX + 65, tY, "m1.png", -135);
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
}
