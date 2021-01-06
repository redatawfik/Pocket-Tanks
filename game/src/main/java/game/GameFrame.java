package game;

import com.jogamp.opengl.awt.GLCanvas;
import game.game_objects.Tank;
import game.menu.GameMode;
import game.menu.LoadingView;
import game.menu.Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame {
    private static GameFrame instance;
    private final String MENU_MUSIC_PATH = "/start-music.au";
    private final String GAME_MUSIC_PATH = "/back-music.au";
    private ControlPanel controlPanel;
    private JPanel gamePanel;
    private GameMode gameMode;

    private GameFrame() {
        SwingUtilities.invokeLater(
                () -> setVisible(true)
        );

        init();
    }

    public static GameFrame getInstance() {
        if (instance == null)
            instance = new GameFrame();
        return instance;
    }

    private void init() {
        setTitle("Pocket Tanks");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(Menu.getInstance());

        //setExtendedState(JFrame.MAXIMIZED_BOTH);

        setSize(800, 500);
        //center the JFrame on the screen
        centerWindow(this);


        //setResizable(false);

        Sound.playBackgroundSound(MENU_MUSIC_PATH);
    }

    public void centerWindow(Component frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = frame.getSize();
        if (frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        if (frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        frame.setLocation(
                (screenSize.width - frameSize.width) >> 1,
                (screenSize.height - frameSize.height) >> 1
        );
    }

    public void changeDisplayToGame(GLCanvas canvas) {
        getContentPane().removeAll();

        gamePanel = new JPanel(new BorderLayout());

        controlPanel = ControlPanel.getInstance();

        gamePanel.add(canvas);
        gamePanel.add(controlPanel, BorderLayout.SOUTH);

        getContentPane().add(gamePanel);

        updateControlPanel();

        revalidate();

        Sound.playBackgroundSound(GAME_MUSIC_PATH);
    }

    public void changeDisplayToMenu() {
        World.destroy();

        getContentPane().removeAll();

        getContentPane().add(Menu.getInstance());

        revalidate();
        repaint();
        pack();

        Sound.playBackgroundSound(MENU_MUSIC_PATH);
    }


    public void showLoadingSpinner() {
        getContentPane().removeAll();

        getContentPane().add(LoadingView.getInstance());
        revalidate();
    }

    public void updateControlPanel() {
        Tank tank = null;
        int angle = 0;


        if (GameFrame.getInstance().getGameMode() == GameMode.ONLINE) {
            tank = World.getInstance().getMyTank();
            angle = tank.getAngle();
            if (World.getInstance().isRightPosition()) {
                angle *= -1;
                angle = 180 - angle;
            } else {
                angle *= -1;
            }
        } else if (GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_MULTIPLAYER ||
                GameFrame.getInstance().getGameMode() == GameMode.OFFLINE_COMPUTER) {
            tank = World.getInstance().getTurnTank();
            angle = tank.getAngle();
            if (!World.getInstance().isLeftTurn()) {
                angle *= -1;
                angle = 180 - angle;
            } else {
                angle *= -1;
            }
        }


        controlPanel.setPower(tank.getPower());
        controlPanel.setAngle(angle);
        controlPanel.setNumOfMoves(tank.getMoves());
    }

    public void showResultView(String result) {
        getContentPane().removeAll();

        getContentPane().add(new ResultView(result));
        revalidate();
    }

    public void close() {
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    public int getWindowWidth() {
        return getWidth();
    }

    public int getWindowHeight() {
        return getHeight();
    }


    public int getCurrWidth() {
        return getWindowWidth();
    }

    public int getCurrHeight() {
        return getWindowHeight();
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }
}

