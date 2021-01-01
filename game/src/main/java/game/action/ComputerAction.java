package game.action;

import java.util.Random;
import java.util.Timer;

public class ComputerAction {
    private static ComputerAction instance;

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
                        // your code here
                        Random random = new Random();
                        AbstractAction.getInstance().setPower(random.nextInt(70) + 40);
                        AbstractAction.getInstance().setAngle(-(random.nextInt(20) + 100));
                        AbstractAction.getInstance().shoot();
                    }
                },
                5000
        );
    }
}
