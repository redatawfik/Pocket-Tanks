package game.networking;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import game.ControlPanel;
import game.GameFrame;
import game.Main;
import game.action.Action;
import game.action.EnemyAction;
import game.action.MyAction;
import game.game_objects.Ground;

import java.io.IOException;

public class Socket {
    private static Socket socket;
    private WebSocket websocket;

    private Socket() {
    }

    public static Socket getInstance() {
        if (socket == null) {
            socket = new Socket();
        }

        return socket;
    }

    public void connect() {
        try {
            websocket = new WebSocketFactory()
                    .createSocket("ws://localhost:8080")
                    .addListener(new WebSocketAdapter() {
                        @Override
                        public void onTextMessage(WebSocket ws, String message) {
                            receivedMessage(message);
                        }
                    })
                    .connect();

        } catch (WebSocketException | IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessage(String action) {
        websocket.sendText(action);
    }

    private void receivedMessage(String message) {
        if (message.equals(Action.SHOOT)) {
            EnemyAction.getInstance().shoot();
        } else if (message.equals(Action.MOVE_LEFT)) {
            EnemyAction.getInstance().moveLeft();
        } else if (message.equals(Action.MOVE_RIGHT)) {
            EnemyAction.getInstance().moveRight();
        } else if (message.equals(Action.CANON_UP)) {
            EnemyAction.getInstance().canonUp();
        } else if (message.equals(Action.CANON_DOWN)) {
            EnemyAction.getInstance().canonDown();
        } else if (message.equals(Action.POWER_UP)) {
            EnemyAction.getInstance().powerUp();
        } else if (message.equals(Action.POWER_DOWN)) {
            EnemyAction.getInstance().powerDown();
        } else if (message.equals(Action.CLOSE)) {
            MyAction.getInstance().endMatch();
        } else if (message.equals("left")) {
            Main.initializeHostGame();
        } else if (message.equals("right")) {
            Main.initializeGuestGame();
        } else if (message.contains("MAP")) {
            String[] arr = message.split(" ");
            int x = Integer.parseInt(arr[1]);
            float y = Float.parseFloat(arr[2]);
            Ground.getInstance().setYAtX(x, y);
        }
        else if (message.contains("USERNAME")){
            String name = message.substring(9);
            GameFrame.getInstance().setEnemayname(name);
            System.out.println(message);
        }
    }

    public void closeConnection() {
        websocket.sendClose();
    }
}
