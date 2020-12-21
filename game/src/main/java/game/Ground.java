package game;


import java.util.Arrays;

public class Ground {
    private static Ground instance;

    private float[] mesh;

    private Ground() {
        this.mesh = new float[110];
        generateMesh();
    }

    public static Ground getInstance() {
        if (instance == null)
            instance = new Ground();
        return instance;
    }


    private void generateMesh() {

        Arrays.fill(mesh, 20.0f);
        /*
        float STEP_MAX = 1f;
        float STEP_CHANGE = 1;
        float MAX_HEIGHT = 40;

        float height = (float) ((Math.random() * MAX_HEIGHT / 3) + 2 * MAX_HEIGHT / 3);
        float slope = (float) ((Math.random() * STEP_MAX) * 2 - STEP_MAX);

        for (int i = 0; i < mesh.length; i++) {
            height += slope;
            slope += (Math.random() * STEP_CHANGE) * 2 - STEP_CHANGE;

            if (slope > STEP_MAX) slope = STEP_MAX;
            if (slope < -STEP_MAX) slope = -STEP_MAX;

            if (height > 0.75 * MAX_HEIGHT) {
                height = (float) (0.75 * MAX_HEIGHT);
                slope *= -1;
            }

            if (height > 0.4 * MAX_HEIGHT) {
                height = (float) (0.4 * MAX_HEIGHT);
                slope *= -1;
            }

            mesh[i] = height;
        }

         */
    }

    public float[] getMesh() {
        return mesh;
    }


    public void draw() {
        GameDisplay.getInstance().drawGround(mesh);
    }
}
