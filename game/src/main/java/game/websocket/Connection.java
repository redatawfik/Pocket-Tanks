package game.websocket;

import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;
import game.World;
import game.action.Action;
import game.action.EnemyAction;

import java.io.IOException;

public class Connection {
    private static Connection connection;
    private WebSocket websocket;

    private Connection() {
        connect();
    }

    private void connect() {
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
        //System.out.println(message);
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
        } else if(message.equals("left")) {
            World.getInstance().setMeLeft();
        }else if(message.equals("right")) {
            World.getInstance().setMeRight();
        }
    }

    public static Connection getInstance() {
        if (connection == null) {
            connection = new Connection();
        }

        return connection;
    }

}
