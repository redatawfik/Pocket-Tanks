package game.action;

import game.GameFrame;
import game.Sound;
import game.World;
import game.menu.GameMode;

public class AbstractAction implements Action {

    private static AbstractAction instance;
    private final String SHOOTING_SOUND_PATH = "/bullet-sound.au";
    private final World world;

    private AbstractAction() {
        world = World.getInstance();
    }

    public static AbstractAction getInstance() {
        if (instance == null)
            instance = new AbstractAction();

        return instance;
    }

    public static void destroy() {
        instance = null;
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

    @Override
    public void shoot() {
        if (World.getInstance().isBulletExist()) return;

        world.getTurnTank().shoot();

        world.setLeftTurn(!world.isLeftTurn());

        Sound.playSound(SHOOTING_SOUND_PATH);

        if (!World.getInstance().isLeftTurn()
                && GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER) {
            ComputerAction.getInstance().play();
        }
    }

    public void setAngle(float angle) {
        world.getTurnTank().getCanon().setRotation(angle);
    }

    public void setPower(int power) {
        world.getTurnTank().setPower(power);
    }
}
