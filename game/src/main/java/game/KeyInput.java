package game;

import game.action.AbstractAction;
import game.action.MyAction;
import game.menu.GameMode;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private boolean shouldMove = true;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (shouldMove && e.getKeyCode() == KeyEvent.VK_A) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().moveLeft();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().moveLeft();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().moveLeft();
            }

            shouldMove = false;
        } else if (shouldMove && e.getKeyCode() == KeyEvent.VK_D) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().moveRight();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().moveRight();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().moveRight();
            }
            shouldMove = false;
        }

        if (shouldMove && e.getKeyCode() == KeyEvent.VK_LEFT) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().powerDown();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().powerDown();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().powerDown();
            }
        } else if (shouldMove && e.getKeyCode() == KeyEvent.VK_RIGHT) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().powerUp();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().powerUp();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().powerUp();
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().canonUp();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().canonUp();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().canonUp();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().canonDown();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().canonDown();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().canonDown();
            }

        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
                MyAction.getInstance().shoot();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER) {
                AbstractAction.getInstance().shoot();
            } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER && World.getInstance().isLeftTurn()) {
                AbstractAction.getInstance().shoot();
            }
        }
    }

    public void keyReleased(KeyEvent e) {
        shouldMove = true;
    }
}
