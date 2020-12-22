package game.action;

import game.Tank;
import game.World;

public class EnemyAction implements Action {

    private static EnemyAction instance;
    private final Tank tank;

    private EnemyAction() {
        tank = World.getInstance().getEnemyTank();
    }

    public static Action getInstance() {
        if (instance == null)
            instance = new EnemyAction();

        return instance;
    }

    @Override
    public void shoot() {
        tank.shoot();
    }

    @Override
    public void moveLeft() {
        tank.moveLeft();
    }

    @Override
    public void moveRight() {
        tank.moveRight();
    }

    @Override
    public void canonUp() {
        tank.canonUp();
    }

    @Override
    public void canonDown() {
        tank.canonDown();
    }
}
