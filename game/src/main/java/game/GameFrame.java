package game;

import com.jogamp.opengl.awt.GLCanvas;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private static GameFrame instance;

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

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(canvas);

        getContentPane().add(panel);
        revalidate();
    }
}
