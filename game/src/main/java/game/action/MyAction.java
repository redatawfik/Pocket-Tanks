package game.action;

import game.World;
import game.game_objects.Tank;
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
        if (!World.getInstance().isMyTurn()) return;

        tank.shoot();

        try {
            connection.sendMessage(Action.SHOOT);
        } catch (Error ignored) {
        }

        World.getInstance().setMyTurn(false);
    }

    @Override
    public void moveLeft() {
        if(!World.getInstance().isMyTurn()) return;

        tank.moveLeft();
        connection.sendMessage(Action.MOVE_LEFT);
    }

    @Override
    public void moveRight() {
        if(!World.getInstance().isMyTurn()) return;

        tank.moveRight();
        connection.sendMessage(Action.MOVE_RIGHT);
    }

    @Override
    public void canonUp() {
        if(!World.getInstance().isMyTurn()) return;

        tank.canonUp();
        connection.sendMessage(Action.CANON_UP);
    }

    @Override
    public void canonDown() {
        if(!World.getInstance().isMyTurn()) return;

        tank.canonDown();
        connection.sendMessage(Action.CANON_DOWN);
    }
}
