package game;

import game.websocket.Connection;

public class MenuCallback {
    private static MenuCallback instance;

    public static MenuCallback getInstance() {
        if(instance == null)
            instance = new MenuCallback();
        return instance;
    }

    public void startOnlineGame() {
        Connection.getInstance();
    }
}
