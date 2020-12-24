package game;

import game.game_objects.Ground;

import java.util.Timer;
import java.util.TimerTask;

public class Main {

    public static void main(String[] args) {
        GameFrame.getInstance();
    }

    public static void initializeGuestGame() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Ground.getInstance().finishBuildingMap()) {
                    World.getInstance().setMeRight();

                    Game game = Game.getInstance();

                    GameFrame.getInstance().changeDisplayToGame(game);
                    game.start();
                    timer.cancel();
                }
            }
        }, 0, 1000);
    }

    public static void initializeHostGame() {
        Ground.getInstance().generateMap();
        Ground.getInstance().sendMapToGuest();

        World.getInstance().setMeLeft();

        Game game = Game.getInstance();

        GameFrame.getInstance().changeDisplayToGame(game);
        game.start();
    }
}
