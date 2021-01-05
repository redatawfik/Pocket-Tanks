package game.game_objects;


import game.GameDisplay;
import game.networking.Socket;

import java.util.Random;

public class Ground {
    private static Ground instance;

    private final float[] mesh;
    private int finishedCells;
    private float maxHight;
    private int indexOfMax;

    private Ground() {
        this.mesh = new float[110];
    }

    public static Ground getInstance() {
        if (instance == null)
            instance = new Ground();

        return instance;
    }

    public static void destroy() {
        instance = null;
    }

    public void generateMap() {

        for (int i = 0; i < mesh.length; i++) {
            float sd = 4f;
            float yy = 0;
            Random random = new Random();

            if (i < 50) {
                yy = (float) (30 / (1 + Math.pow(Math.E, -(i * .2 - 5)))) + 10;
            } else {
                yy = (float) (-30 / (1 + Math.pow(Math.E, -(i * .2 - 15)))) + 50;
            }

            mesh[i] = yy;

            for (int j = 1; j < mesh.length - 1; j++) {
                float ff = (float) (random.nextGaussian() * sd + mesh[j]);

                if (i % 5 == 0) {
                    mesh[j] = ff;
                } else {
                    mesh[j] = (mesh[j - 1] + mesh[j + 1]) / 2;
                }
            }
            if (mesh[i] >= maxHight) {
                maxHight = mesh[i];
                indexOfMax = i + 1;
            }
        }
    }

    public float[] getMesh() {
        return mesh;
    }

    public void draw() {
        GameDisplay.getInstance().drawGround(mesh);
    }

    public void setYAtX(int x, float y) {
        mesh[x] = y;
        finishedCells++;
    }

    public void sendMapToGuest() {
        for (int i = 0; i < mesh.length; i++) {
            Socket.getInstance().sendMessage("MAP " + i + " " + mesh[i]);
        }
    }

    public boolean finishBuildingMap() {
        return finishedCells == mesh.length;
    }

    public int getSetMesh() {
        return finishedCells;
    }

    public void destroyGround(float x) {
        float[] min = new float[9];
        float nn = .2f;

        for (int i = 0; i < min.length; i++) {
            min[i] = nn;
            if (i < min.length / 2) {
                nn += .1;
            } else {
                nn -= .1;
            }
        }

        for (int i = (int) (x - 4), y = 0; i <= x + 4; i++, y++) {
            if (i < 0) continue;
            mesh[i] -= min[y];
        }
    }

    public float getMaxHight() {
        return maxHight;
    }

    public int getIndexOfMax() {
        return indexOfMax;
    }
}
