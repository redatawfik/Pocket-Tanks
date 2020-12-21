package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {

    private boolean shouldMove = false;

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {

        if(shouldMove && e.getKeyCode() == KeyEvent.VK_LEFT) {

            World.getInstance().startMoveLeft();

            shouldMove = false;
        } else if(shouldMove && e.getKeyCode() == KeyEvent.VK_RIGHT) {

            World.getInstance().startMoveRight();

            shouldMove = false;
        }

         if(e.getKeyCode() == KeyEvent.VK_UP) {

            World.getInstance().myCanonUp();

        }else if(e.getKeyCode() == KeyEvent.VK_DOWN) {

             World.getInstance().myCanonDown();

         }

        if(e.getKeyCode() == KeyEvent.VK_SPACE) {

            World.getInstance().shoot();

        }
    }

    public void keyReleased(KeyEvent e) {
        shouldMove = true;
    }
}
