package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ChickenBullet extends Entity{
    private Boolean isOnTheGround;
    private int type;
    private int waitingTime;
    private Boolean canRemove;
    private int num;

    private void initTexture() {
        String path = "/image/enemy_item/chicken_bullet/" + type +".png";
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void updateTexture() {
        String path = "/image/enemy_item/chicken_bullet/" + type + "_0" +".png";
        try {
            image = ImageIO.read(getClass().getResource(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ChickenBullet(GamePanel gp, float x, float y, float speed, int type) {
        super(gp, x, y, speed);
        this.type = type;
        isOnTheGround = false;
        waitingTime = 100;
        canRemove = false;
        num = 0;
        initTexture();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        if(y < 640) {
            y += speed;
        } else {
            updateTexture();
            isOnTheGround = true;
            if(waitingTime <= 0) canRemove = true;
            else --waitingTime;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        switch(type) {
            case 1:
                if(!isOnTheGround) 
                    g2.drawImage(image, (int) x - gp.tileSize / 2, (int) y, 25, 35, null);
                else 
                    g2.drawImage(image, (int) x - gp.tileSize / 2, (int) y, 50, 20, null);
                break;
            case 2:
                if(!isOnTheGround) {
                    if(num >= 6) num = 0;
                    else ++num;
                    g2.drawImage(image.getSubimage(0, num * 23, image.getWidth(), 23), (int)x, (int)y, 15, 25, null);
                } else g2.drawImage(image, (int) x - gp.tileSize / 2, (int) y, 40, 40, null);
                break;
            default:
                break;
        }
    }

    public Boolean getCanRemove() {
        return canRemove;
    }

    public Rectangle getCBBound() {
		// return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
        if(type == 1)
            return new Rectangle((int) x + gp.tileSize / 2, (int) y - 10, 25, 35);
		else return new Rectangle((int) x + gp.tileSize / 2, (int) y - 10, 15, 25);
	}

    public Boolean onTheGround() {
        return isOnTheGround;
    }
}