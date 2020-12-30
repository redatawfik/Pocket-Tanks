package game;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

public class Game extends GLCanvas {

    private static Game instance;
    private Image backgroundImage;

    public static FPSAnimator animator = null;
    private static GLProfile profile;

    public static Game getInstance() {
        if (instance == null) {
            profile = GLProfile.get(GLProfile.GL2);
            GLCapabilities capabilities = new GLCapabilities(profile);
            instance = new Game(capabilities, GameDisplay.getInstance());
        }

        return instance;
    }

    private Game(GLCapabilities capabilities, GameDisplay display) {
        super(capabilities);

        addGLEventListener(display);
        addKeyListener(new KeyInput());

        try {
            backgroundImage = ImageIO.read(new File(getClass().getResource("/menu_background.jpg").toURI()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }

        //create the animator
        animator = new FPSAnimator(this, 60);
    }

    public void start() {
        SwingUtilities.invokeLater(
                () -> animator.start()
        );
    }

    public static GLProfile getProfile() {
        return profile;
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        //  graphics.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    public void destroyGame() {
        instance = null;
    }
}
