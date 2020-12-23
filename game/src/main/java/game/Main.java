package game;

import game.websocket.Connection;

public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        game.start();

        Connection.getInstance();

//        Menu menu = new Menu();
    }
}
