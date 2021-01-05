package game.menu;

import game.GameFrame;
import game.Main;
import game.networking.Site;
import game.networking.Socket;


public class MenuCallback {
    private static MenuCallback instance;

    public static MenuCallback getInstance() {
        if (instance == null)
            instance = new MenuCallback();
        return instance;
    }

    public void startOnlineGame() {
        if (Site.isLoggedIn()) {
            GameFrame.getInstance().setGameMode(GameMode.ONLINE);
            Socket.getInstance().connect();
            GameFrame.getInstance().showLoadingSpinner();
        } else {
            LoginFrame.getInstance();
        }
    }

    public void startLocalGame() {
        GameFrame.getInstance().setGameMode(GameMode.OFFLINE_MULTIPLAYER);
        Main.initializeLocalMultiPlayerGame();
    }

    public void exit() {
        GameFrame.getInstance().close();
    }

    public void startComputerGame() {
        GameFrame.getInstance().setGameMode(GameMode.OFFLINE_COMPUTER);
        Main.initializeVersusComputerGame();
    }
}
