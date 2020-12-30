package game.action;

import game.Sound;
import game.World;

public class AbstractAction implements Action {

    private static AbstractAction instance;

    private World world;
    private final String SHOOTING_SOUND_PATH = "/bullet-sound.au";

    private AbstractAction() {
        world = World.getInstance();
    }

    public static AbstractAction getInstance() {
        if (instance == null)
            instance = new AbstractAction();

        return instance;
    }

    @Override
    public void shoot() {
        world.getTurnTank().shoot();

        world.setLeftTurn(!world.isLeftTurn());
        Sound.playSound(SHOOTING_SOUND_PATH);
    }

    @Override
    public void moveLeft() {
        world.getTurnTank().moveLeft();
    }

    @Override
    public void moveRight() {
        world.getTurnTank().moveRight();
    }

    @Override
    public void canonUp() {
        world.getTurnTank().canonUp();
    }

    @Override
    public void canonDown() {
        world.getTurnTank().canonDown();
    }

    @Override
    public void powerDown() {
        world.getTurnTank().powerDown();
    }

    @Override
    public void powerUp() {
        world.getTurnTank().powerUp();
    }

    public void destroy() {
        instance = null;
    }
}
