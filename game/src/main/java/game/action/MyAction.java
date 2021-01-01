package game.action;

import game.GameFrame;
import game.Sound;
import game.World;
import game.game_objects.Tank;
import game.networking.Site;
import game.networking.Socket;

public class MyAction implements Action {

    private static MyAction instance;
    private final Tank tank;
    private final Socket socket;

    private final String SHOOTING_SOUND_PATH = "/bullet-sound.au";

    private MyAction() {
        tank = World.getInstance().getMyTank();
        socket = Socket.getInstance();
    }

    public static MyAction getInstance() {
        if (instance == null)
            instance = new MyAction();

        return instance;
    }

    @Override
    public void shoot() {
        World.getInstance().setShouldPlayNow(false);
        if (!World.getInstance().isMyTurn()) return;

        tank.shoot();
        Sound.playSound(SHOOTING_SOUND_PATH);

        try {
            socket.sendMessage(Action.SHOOT);
        } catch (Error ignored) {
        }
        World.getInstance().setShouldPlayNow(false);
        World.getInstance().setMyTurn(false);
    }

    @Override
    public void moveLeft() {
        if(!World.getInstance().isMyTurn()) return;

        tank.moveLeft();
        socket.sendMessage(Action.MOVE_LEFT);
        GameFrame.getInstance().updateControlPanel();
    }

    @Override
    public void moveRight() {
        if(!World.getInstance().isMyTurn()) return;

        tank.moveRight();
        socket.sendMessage(Action.MOVE_RIGHT);
        GameFrame.getInstance().updateControlPanel();
    }

    @Override
    public void canonUp() {
        if(!World.getInstance().isMyTurn()) return;

        tank.canonUp();
        socket.sendMessage(Action.CANON_UP);
        GameFrame.getInstance().updateControlPanel();
    }

    @Override
    public void canonDown() {
        if (!World.getInstance().isMyTurn()) return;

        tank.canonDown();
        socket.sendMessage(Action.CANON_DOWN);
        GameFrame.getInstance().updateControlPanel();
    }

    @Override
    public void powerUp() {
        if(!World.getInstance().isMyTurn()) return;

        tank.powerUp();
        socket.sendMessage(Action.POWER_UP);
        GameFrame.getInstance().updateControlPanel();
    }



    @Override
    public void powerDown() {
        if(!World.getInstance().isMyTurn()) return;

        tank.powerDown();
        socket.sendMessage(Action.POWER_DOWN);
        GameFrame.getInstance().updateControlPanel();
    }

    public void endMatch() {
        Tank tank1 = World.getInstance().getMyTank();
        Tank tank2 = World.getInstance().getEnemyTank();

        String text = "{\"user1\":\"test\",\"user2\":\"testt\",\"score1\":\"" + tank1.getScore() + "\",\"score2\":\"" + tank2.getScore() + "\"}";
        Site.sendResult(text);

        World.destroy();

        GameFrame.getInstance().changeDisplayToMenu();
    }

    public void destroy() {
        instance = null;
    }
}
