package game;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;
import java.awt.*;

public class Game extends JFrame {

    public static FPSAnimator animator = null;
    private static GLProfile profile;


    public Game() {
        //set the JFrame title
        super("Pocket Tanks");

        //kill the process when the JFrame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //we'll create our GLEventListener
        GameDisplay display = GameDisplay.getInstance();
        //display.addGameObject(Tank.getInstance());

        //Now we will create our GLCanvas
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        final GLCanvas glCanvas = new GLCanvas(capabilities);
        display.setCanvas(glCanvas);

        //GLCanvas glCanvas = new GLCanvas();
        glCanvas.addGLEventListener(display);
        glCanvas.addKeyListener(new KeyInput());

        //create the animator
        animator = new FPSAnimator(glCanvas, 400);

        getContentPane().add(glCanvas, BorderLayout.CENTER);
        setSize(500, 300);

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

    public void start() {

        SwingUtilities.invokeLater(
                () -> setVisible(true)
        );

        SwingUtilities.invokeLater(
                () -> animator.start()
        );
    }

    public static GLProfile getProfile() {
        return profile;
    }
}
