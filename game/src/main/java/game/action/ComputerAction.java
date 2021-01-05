package game.action;

import game.World;
import game.game_objects.Ground;

import java.util.Timer;

public class ComputerAction {
    private static ComputerAction instance;
    private final Difficulty difficulty = Difficulty.HARD;
    private float lowerAngel;
    private float maxHight;
    private int indexOfMax;
    private ComputerAction() {
    }

    public static ComputerAction getInstance() {
        if (instance == null) {
            instance = new ComputerAction();
        }
        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public void play() {

        new Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        shoot();
                    }
                },
                5000
        );
    }

    public void shoot() {
        float leftX = World.getInstance().getLeftTank().getX();
        float lefty = World.getInstance().getLeftTank().getY();
        float rightX = World.getInstance().getRightTank().getX();
        float righty = World.getInstance().getRightTank().getY();
        float range = rightX - leftX;
        maxHight = Ground.getInstance().getMaxHight();
        indexOfMax = Ground.getInstance().getIndexOfMax();
        lowerAngel = (float) Math.toDegrees(Math.atan((maxHight - righty) / (rightX - indexOfMax)));
        int random;
        switch (difficulty) {
            case EASY:
                random = (int) (Math.random() * 12 - 6);
                break;
            case NORMAL:
                random = (int) (Math.random() * 6 - 3);
                break;
            case HARD:
                random = (int) (Math.random() * 4 - 2);
                break;
            default:
                random = 0;
        }
        float angle = (float) (Math.random() * (90 - lowerAngel) + lowerAngel);
        float velocity = (float) Math.sqrt((range * 9.8) / Math.sin(Math.toRadians(2 * angle)));
        if (lefty > righty) velocity++;
        if (lefty < righty) velocity--;
        AbstractAction.getInstance().setPower((int) velocity + random);
        AbstractAction.getInstance().setAngle(180 + angle);
        AbstractAction.getInstance().shoot();
        System.out.println(velocity + "***" + range + "***" + angle + "***" + lowerAngel);
    }

    private enum Difficulty {EASY, NORMAL, HARD}
}
