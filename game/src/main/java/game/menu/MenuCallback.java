package game.menu;

import game.GameFrame;
import game.websocket.Connection;

import java.awt.event.WindowEvent;

public class MenuCallback {
    private static MenuCallback instance;

    public static MenuCallback getInstance() {
        if(instance == null)
            instance = new MenuCallback();
        return instance;
    }

    public void startOnlineGame() {
        Connection.getInstance();
        GameFrame.getInstance().showLoadingSpinner();
    }

    public void startLocalGame() {
        System.out.println("Start local game");
    }

    public void exit() {
        GameFrame.getInstance().close();
    }
}
