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

    public static FPSAnimator animator = null;
    private static Game instance;
    private static GLProfile profile;

    private Game(GLCapabilities capabilities, GameDisplay display) {
        super(capabilities);

        addGLEventListener(display);
        addKeyListener(new KeyInput());

        //create the animator
        animator = new FPSAnimator(this, 60);
    }

    public static Game getInstance() {
        if (instance == null) {
            profile = GLProfile.get(GLProfile.GL2);
            GLCapabilities capabilities = new GLCapabilities(profile);
            instance = new Game(capabilities, GameDisplay.getInstance());
        }

        return instance;
    }

    public static GLProfile getProfile() {
        return profile;
    }

    public static void destroyGame() {
        instance = null;
    }

    public static void stopAnimation() {
        animator.stop();
    }

    public void start() {
        SwingUtilities.invokeLater(
                () -> animator.start()
        );
    }
}
