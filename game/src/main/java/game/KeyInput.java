package game;

import game.action.MyAction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private boolean shouldMove = true;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if (shouldMove && e.getKeyCode() == KeyEvent.VK_LEFT) {
            MyAction.getInstance().moveLeft();
            shouldMove = false;
        } else if (shouldMove && e.getKeyCode() == KeyEvent.VK_RIGHT) {
            MyAction.getInstance().moveRight();
            shouldMove = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_UP) {
            MyAction.getInstance().canonUp();

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            MyAction.getInstance().canonDown();

        }

        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            MyAction.getInstance().shoot();
        }
    }

    public void keyReleased(KeyEvent e) {
        shouldMove = true;
    }
}
