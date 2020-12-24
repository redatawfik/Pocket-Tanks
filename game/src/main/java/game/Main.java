package game;

import game.game_objects.Ground;
import game.websocket.Connection;

import java.util.Timer;
import java.util.TimerTask;

public class Main {


    public static void main(String[] args) {
        //Game game = new Game();
        //game.start();

        Connection.getInstance();

//        Menu menu = new Menu();
    }

    public static void initializeGuestGame() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (Ground.getInstance().finishBuildingMap()) {
                    World.getInstance().setMeRight();

                    Game game = new Game();
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

        Game game = new Game();
        game.start();
    }
}
