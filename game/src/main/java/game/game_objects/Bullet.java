package game.game_objects;

import game.ImageResource;

public class Bullet extends GameObject {

    private final BulletDestructor destructor;
    private final double DEG2RAD = Math.PI / 180;
    private final float g = -9.8f;
    private final double angel;
    private final float initialX;
    private final float initialY;
    private final float initialVelocity;
    private float time;
    private final float[] mesh;
    private Tank source;
//    private final Sprite[] sprite = new Sprite[16];


    public Bullet(float angel, float initialX, float initialY, float initialVelocity, Tank destructor, Tank tank) {
        this.angel = -angel * DEG2RAD;
        this.initialX = initialX;
        this.initialY = initialY;
        this.initialVelocity = initialVelocity;
        this.time = 0;
        this.destructor = destructor;
        this.setWidth(1f);
        this.setHeight(1f);
        mesh = Ground.getInstance().getMesh();
        setImageResource(new ImageResource("/bullet.png"));
        this.source = tank;
//        for(int i = 0; i<16;i++){
//            sprite[i] = new Sprite(getX(),getY(),"/"+(i+1)+".png");
//            sprite[i].setWidth(10);
//            sprite[i].setHeight(10);
//        }
    }

    private void shoot() {

        //float deltaTime = GameDisplay.getInstance().getDeltaTime();
        time += .018;

        float velX = (float) (initialVelocity * Math.cos(angel));
        float velY = (float) (initialVelocity * Math.sin(angel));

        float currVelo = velY + g * time;

        float x = velX * time + initialX;
        float y = (float) (currVelo * time + initialY + .5 * g * Math.pow(time, 2));

        setX(x);
        setY(y);

        draw();

        if (getX() < 0 || getX() > 100) {
            destructor.destroy();
            return;
        }

        if (getY() <= mesh[(int) getX()]) {
            bulletAnimation();
            Ground.getInstance().destroyGround(getX());
            destructor.checkDamage(getX());
            destructor.destroy();
        }
    }

    public void update() {
        shoot();
    }

    public void bulletAnimation() {
        Sprite[] sprite = new Sprite[16];
        for(int i = 0; i<16;i++){
            sprite[i] = new Sprite(getX(),getY(),"/"+(i+1)+".png");
            sprite[i].setWidth(10);
            sprite[i].setHeight(10);
        }
        try {
            for (int i = 0; i < sprite.length; i++) {
                sprite[i].draw();
                Thread.sleep(100);
            }
        }catch (InterruptedException exception){}

    }
}


