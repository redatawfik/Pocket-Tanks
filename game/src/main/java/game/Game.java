package game;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

import javax.swing.*;

public class Game extends GLCanvas {

    private static Game instance;

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
}
