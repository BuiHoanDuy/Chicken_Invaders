package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Entity {
    private int point;
    private int hp;
    private float attackCoolDown;
    private float attackCoolDownMax;
    private int ultiShoot;

    private void initVariable() {
        point = 0;
        hp = 100;
        ultiShoot = 0;

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/resource/player/ship.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Player(float x, float y, float speed) {
        super(x, y, speed);
        this.initVariable();
    }

    public void updateWindowCollision() {

    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.update();
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        super.draw();
    }
}
