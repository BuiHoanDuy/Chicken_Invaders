package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
    private int point;
    private int hp;
    private float attackCoolDown;
    private float attackCoolDownMax;
    private int ultiShoot;

    private void initVariable() {
        point = 0;
        hp = 100;
        ultiShoot = 0; // số lượng ulti

        try {
            image = ImageIO.read(getClass().getResourceAsStream("/image/player/ship.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Player(GamePanel gp, float x, float y, float speed) {
        super(gp, x, y, speed);
        this.initVariable();
    }
    

    public void updateWindowCollision() {

    }

    @Override
    public void update() {
        
    }

    @Override
    public void draw(Graphics2D g2) {
    	g2.drawImage(image, (int)x - gp.tileSize, (int)y, gp.tileSize * 2, gp.tileSize * 2, null);
    }
    
    public void setLocation(float x, float y) { // cài đặt tọa độ x, y
    	this.x = x;
    	this.y = y;
    }
    
    public float getX() {
    	return x;
    }
    
    public float getY() {
    	return y;
    }
}
