package game;

import com.jogamp.opengl.awt.GLCanvas;
import game.game_objects.Tank;
import game.menu.LoadingView;
import game.menu.Menu;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class GameFrame extends JFrame implements LineListener {
    private static GameFrame instance;
    private ControlPanel controlPanel;

    private final String MENU_MUSIC_PATH = "/start-music.au";
    private final String GAME_MUSIC_PATH = "/back-music.au";
    private JPanel gamePanel;

    private GameFrame() {
        SwingUtilities.invokeLater(
                () -> setVisible(true)
        );

        init();
    }

    private void init() {
        setTitle("Pocket Tanks");

        //removeAll();

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(Menu.getInstance());

        setSize(800, 500);
        //center the JFrame on the screen
        centerWindow(this);

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

    public static GameFrame getInstance() {
        if (instance == null)
            instance = new GameFrame();
        return instance;
    }

    public void changeDisplayToGame(GLCanvas canvas) {
        getContentPane().removeAll();

        gamePanel = new JPanel(new BorderLayout());

        //BoxLayout layoutMgr = new BoxLayout(panel, BoxLayout.X_AXIS);
        //panel.setLayout(layoutMgr);

        controlPanel = ControlPanel.getInstance();

        //canvas.setPreferredSize(new Dimension(getWindowWidth(), getWindowHeight()));
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
      //  init();
    }


    public void showLoadingSpinner() {
        getContentPane().removeAll();

        getContentPane().add(LoadingView.getInstance());
        revalidate();
    }

    public void updateControlPanel() {
        Tank tank = World.getInstance().getMyTank();
        controlPanel.setPower(tank.getPower());
        controlPanel.setAngel(tank.getAngel());
        controlPanel.setNumOfMoves(tank.getMoves());
    }

    @Override
    public void update(LineEvent event) {

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
}
