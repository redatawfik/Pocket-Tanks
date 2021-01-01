package game.action;

import game.World;
import game.game_objects.Tank;

public class EnemyAction implements Action {

    private static EnemyAction instance;
    private final Tank tank;

    private EnemyAction() {
        tank = World.getInstance().getEnemyTank();
    }

    public static EnemyAction getInstance() {
        if (instance == null)
            instance = new EnemyAction();

        return instance;
    }

    @Override
    public void shoot() {
        tank.shoot();
        World.getInstance().setShouldPlayNow(false);
        World.getInstance().setMyTurn(true);
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

    @Override
    public void powerDown() {
        tank.powerDown();
    }

    @Override
    public void powerUp() {
        tank.powerUp();
    }

    public void destroy() {
        instance = null;
    }
}
