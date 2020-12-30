package game.menu;

import game.GameFrame;
import game.Main;
import game.networking.Socket;


public class MenuCallback {
    private static MenuCallback instance;

    public static MenuCallback getInstance() {
        if(instance == null)
            instance = new MenuCallback();
        return instance;
    }

    public void startOnlineGame() {
        GameFrame.getInstance().setGameMode(GameMode.ONLINE);
        Socket.getInstance();
        GameFrame.getInstance().showLoadingSpinner();
    }

    public void startLocalGame() {
        GameFrame.getInstance().setGameMode(GameMode.OFFLINE_MULTIPLAYER);
        Main.initializeHostGame();
        System.out.println("Start local game");
    }

    public void exit() {
        GameFrame.getInstance().close();
    }
}
