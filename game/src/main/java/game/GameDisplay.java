package game;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.texture.Texture;

import java.time.Duration;
import java.time.LocalTime;


public class GameDisplay implements GLEventListener {

    private static GameDisplay instance;

    private GL2 gl;
    private GLCanvas canvas;

    private LocalTime startTime;
    private long elapsedSeconds;
    private int elapsedNanos;
    int frames = 0;
    private int myMove = 0;

    private GameDisplay() {
    }

    public static GameDisplay getInstance() {
        if (instance == null)
            instance = new GameDisplay();

        return instance;
    }


    public void init(GLAutoDrawable glAutoDrawable) {
        startTime = LocalTime.now();
        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glClearColor(1f, 1f, 1f, 1f);

        gl.glEnable(GL.GL_TEXTURE_2D);  // Enable Texture Mapping
        gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE_MINUS_SRC_ALPHA);

        gl.glEnable(GL2.GL_TEXTURE_2D);
        //gl.glEnable(GL2.GL_BLEND);

        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);

//        gl.glEnable(GL2.GL_TEXTURE_2D);
//        gl.glEnable(GL2.GL_BLEND);
//        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
//
//
//        //gl.glViewport(0, 0, 500, 300);
//
//        gl.glMatrixMode(GL2.GL_PROJECTION);
//        gl.glLoadIdentity();
//
//        gl.glOrtho(-10, 10, -10, 10, -1, 1);
    }


    @Override
    public void display(GLAutoDrawable glAutoDrawable) {
        float deltaTime = getDeltaTime();
        //System.out.println(deltaTime);
        gl = glAutoDrawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);

        gl.glLoadIdentity();

        gl.glEnable(GL2.GL_BLEND);


        World.getInstance().update();

        World.getInstance().draw();

        gl.glDisable(GL.GL_BLEND);

//        World.getInstance().draw();
//        for (GameObject gameObject : gameObjects) {
//            gameObject.draw(this);
//        }

        //   drawImage(new ImageResource("/me.png"), 0, 0, 10, 10);

        gl.glEnd();
    }

    public float getDeltaTime() {
        Duration timeSinceStart = Duration.between(startTime, LocalTime.now());
        int deltaTime = timeSinceStart.minusSeconds(elapsedSeconds).getNano() - elapsedNanos;
        if (timeSinceStart.getSeconds() > elapsedSeconds) {
            //System.out.println(frames + " " + elapsedSeconds);
            frames = 0;
            elapsedSeconds = timeSinceStart.getSeconds();
        } else {
            frames++;
        }
        elapsedNanos = elapsedNanos + deltaTime;
       // System.out.println(elapsedNanos + " - " + deltaTime);
        return deltaTime < 0 ? 0 : deltaTime / 1000000000f;
    }

    public void drawImage(ImageResource image, float x, float y, float width, float height, float rotation) {
        Texture texture = image.getTexture();

        if (texture != null) {
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
        }


        gl.glTranslatef(x, y, 0);
            gl.glRotated(-rotation, 0, 0, 1);

        //gl.glColor4f(1, 0, 0, .6f);

        gl.glColor4f(1, 1, 1, 1);
        gl.glBegin(GL2.GL_QUADS);

        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(-width / 2, -height / 2);

        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(+width / 2, -height / 2);

        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(+width / 2, +height / 2);

        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(-width / 2, +height / 2);

        gl.glEnd();
        gl.glFlush();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);

         gl.glRotated(rotation, 0, 0, 1);
        gl.glTranslatef(-x, -y, 0);
    }


    public void reshape(GLAutoDrawable glAutoDrawable, int i, int i1, int i2, int i3) {

        GL2 gl = glAutoDrawable.getGL().getGL2();

        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        gl.glOrtho(0, 100, 0, 40, -1, 1);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
    }

    public void dispose(GLAutoDrawable glAutoDrawable) {

    }

    public void setCanvas(GLCanvas glCanvas) {
        this.canvas = glCanvas;
    }


    public void setMyMove(int i) {
        myMove = i;
    }
}