package game;

import com.jogamp.opengl.awt.GLCanvas;
import game.game_objects.Tank;

import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame implements LineListener {
    private static GameFrame instance;
    private ControlPanel controlPanel;

    private final String MENU_MUSIC_PATH = "/start-music.au";
    private final String GAME_MUSIC_PATH = "/back-music.au";

    private GameFrame() {
        SwingUtilities.invokeLater(
                () -> setVisible(true)
        );

        init();
    }

    private void init() {
        setTitle("Pocket Tanks");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(new Menu());

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

        JPanel panel = new JPanel(new GridLayout(2, 1));

        controlPanel = ControlPanel.getInstance();

        panel.add(canvas);
        panel.add(controlPanel);


        getContentPane().add(panel);

        updateControlPanel();

        revalidate();

        Sound.playBackgroundSound(GAME_MUSIC_PATH);
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
}
