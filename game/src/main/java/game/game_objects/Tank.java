package game.game_objects;


import game.ImageResource;
import game.World;

public class Tank extends GameObject implements BulletDestructor {

    private final GameObject canon;
    private Bullet bullet;
    private int bulletCounter = 10;

    public int getBulletCounter() {
        return bulletCounter;
    }

    public void setBulletCounter(int bulletCounter) {
        this.bulletCounter = bulletCounter;
    }

    private int moves = 5;
    private int leftMoves;
    private int rightMoves;
    private int power = 40;
    private int score;

    public Tank(float x, float y, String canonImage, float angel) {
        this.canon = new GameObject(x + .5f, y - .1f, 5, 5,
                new ImageResource("/" + canonImage), angel);
        this.setX(x);
        this.setY(Ground.getInstance().getMesh()[(int) x] + .88f);
        this.setImageResource(new ImageResource("/tank.png"));
    }

    @Override
    public void setX(float x) {
        if (x < 0) x = 0;
        if (x > 100) x = 100;
        super.setX(x);
        canon.setX(x + .5f);
        setY(Ground.getInstance().getMesh()[(int) x] + .88f);
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        canon.setY(y + 0.3f);
    }


    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    @Override
    public void draw() {
        // Draw tank
        setY(Ground.getInstance().getMesh()[(int) getX()] + .88f);
        super.draw();

        // Draw Canon
        canon.draw();
    }

    public GameObject getCanon() {
        return canon;
    }

    public void update() {
        if (leftMoves > 0) {
            setX(getX() - 0.04f);
            leftMoves--;
        } else if (rightMoves > 0) {
            setX(getX() + 0.04f);
            rightMoves--;
        }

        if (bullet != null) {
            bullet.update();
        }

        int startX = (int) (getX() - getWidth());
        int endX = (int) (getX() + getWidth());

        float[] mesh = Ground.getInstance().getMesh();

        float startY = mesh[startX];
        float endY = mesh[endX];

        float angel = (float) Math.atan((endY - startY) / (endX - startX));
        setRotation((float) Math.toDegrees(-angel));
    }

    public void shoot() {
        bullet = new Bullet(canon.getRotation(), getX(), getY(), power, this, this);
        bulletCounter--;
        World.getInstance().checkEndOFGame();

    }

    public void moveLeft() {
        if(moves <= 0)return;
        if (leftMoves != 0 || rightMoves != 0) return;
        leftMoves = 150;
    }

    public void moveRight() {
        if(moves <= 0)return;
        if (leftMoves != 0 || rightMoves != 0) return;
        rightMoves = 150;
        moves--;
    }

    public void canonUp() {
        float delta = -.5f;
        if ((World.getInstance().isMyTurn() && World.getInstance().isRightPosition()) ||
                (!World.getInstance().isMyTurn() && !World.getInstance().isRightPosition()))
            delta *= -1;
        getCanon().setRotation(canon.getRotation() + delta);
    }

    public void canonDown() {
        float delta = .5f;
        if ((World.getInstance().isMyTurn() && World.getInstance().isRightPosition()) ||
                (!World.getInstance().isMyTurn() && !World.getInstance().isRightPosition()))
            delta *= -1;

        getCanon().setRotation(canon.getRotation() + delta);
    }

    @Override
    public void destroy() {
        bullet = null;
    }

    @Override
    public void checkDamage(float bulletPosition) {
        Tank tank1 = World.getInstance().getEnemyTank();
        Tank tank2 = World.getInstance().getMyTank();

        if(bulletPosition >= tank1.getX() - tank1.getWidth() && bulletPosition <= tank1.getX() + tank1.getWidth()) {
            World.getInstance().getMyEnemy(tank1).scoreUp();
        }

        if(bulletPosition >= tank2.getX() - tank2.getWidth() && bulletPosition <= tank2.getX() + tank2.getWidth()) {
            World.getInstance().getMyEnemy(tank2).scoreUp();
        }
    }

    private void scoreUp() {
        score += 5;
        System.out.println(score);
    }

    public int getPower() {
        return power;
    }

    public int getAngel() {
        return (int) canon.getRotation();
    }

    public void powerUp() {
        power++;
    }

    public void powerDown() {
        power--;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
