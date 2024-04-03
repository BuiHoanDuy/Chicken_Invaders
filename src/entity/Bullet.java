package entity;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bullet extends Entity {
    private int type;
    private int damage;

    private void initVariable() {
        String path = "/resource/bullet/" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Bullet(float x, float y, float speed, int type, int damage) {
        super(x, y, speed);
        this.type = type;
        this.damage = damage;
        this.initVariable();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void draw() {
        // TODO Auto-generated method stub
        
    }

}
