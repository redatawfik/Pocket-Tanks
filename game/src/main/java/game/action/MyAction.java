package game.action;

import game.Tank;
import game.World;
import game.websocket.Connection;

public class MyAction implements Action {

    private static MyAction instance;
    private final Tank tank;
    private final Connection connection;

    private MyAction() {
        tank = World.getInstance().getMyTank();
        connection = Connection.getInstance();
    }

    public static Action getInstance() {
        if (instance == null)
            instance = new MyAction();

        return instance;
    }

    @Override
    public void shoot() {
        tank.shoot();
        connection.sendMessage(Action.SHOOT);
    }

    @Override
    public void moveLeft() {
        tank.moveLeft();
        connection.sendMessage(Action.MOVE_LEFT);
    }

    @Override
    public void moveRight() {
        tank.moveRight();
        connection.sendMessage(Action.MOVE_RIGHT);
    }

    @Override
    public void canonUp() {
        tank.canonUp();
        connection.sendMessage(Action.CANON_UP);
    }

    @Override
    public void canonDown() {
        tank.canonDown();
        connection.sendMessage(Action.CANON_DOWN);
    }
}
