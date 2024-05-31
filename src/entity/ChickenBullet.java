package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;

public class ChickenBullet extends Entity{
    private Boolean isOnTheGround;
    private int type;
    private int waitingTime;
    private Boolean canRemove;
    private int num;
    private int cWidth;
    private int cHeight;
    private int xPos;
    private int yPos;


    private double[] angles = {0, Math.PI / 4, Math.PI / 2, 3 * Math.PI / 4, Math.PI, 5 * Math.PI / 4, 3 * Math.PI / 2, 7 * Math.PI / 4};
    private int[] xPositions = new int[8];
    private int[] yPositions = new int[8];
    private int centerX, centerY;
    private int radius = 100;
    private int speed = 5;

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

    public ChickenBullet(GamePanel gp, float x, float y, int width, int height, float speed, int type) {
        super(gp, x, y, speed);
        this.type = type;
        this.cWidth = width;
        this.cHeight = height;
        isOnTheGround = false;
        waitingTime = 100;
        canRemove = false;
        num = 0;
        initTexture();



        centerX = (int) x;
        centerY = (int) y;

        for (int i = 0; i < 8; i++) {
            xPositions[i] = centerX;
            yPositions[i] = centerY;
        }
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        switch(type) {
            case 1:
            case 2:
                if(y < 680) {
                    y += speed;
                } else if (y >= 680 && !isOnTheGround) {
                    gp.playSE(13);
                    isOnTheGround = true;
                }
                else {
                    updateTexture();
                    isOnTheGround = true;
                    if(waitingTime <= 0) canRemove = true;
                    else --waitingTime;
                }
                break;
            case 0:
                for (int i = 0; i < 8; i++) {
                    xPositions[i] += (int) (speed * Math.cos(angles[i]));
                    yPositions[i] += (int) (speed * Math.sin(angles[i]));
                    if(-100 > xPositions[i] || xPositions[i] > 1008 || -100 > yPositions[i] || yPositions[i] > 730)
                        canRemove = true;
                }
                break;
            default:
                if(y < 725) y += speed;
                else canRemove = true;
                break;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        switch(type) {
            case 1:
                if(!isOnTheGround) 
                    g2.drawImage(image, (int) x + cWidth / 2, (int) y + cHeight, 25, 35, null);
                else 
                    g2.drawImage(image, (int) x + cWidth / 2, (int) y, 50, 20, null);
                break;
            case 2:
                if(!isOnTheGround) {
                    if(num >= 6) num = 0;
                    else ++num;
                    g2.drawImage(image.getSubimage(0, num * 23, image.getWidth(), 23), (int) x + cWidth / 2, (int) y + cHeight, 15, 25, null);
                } else g2.drawImage(image, (int) x - gp.getTileSize() / 2, (int) y, 40, 40, null);
                break;
            case 3:
                g2.drawImage(image, (int) x + cWidth / 2, (int) y + cHeight, 80, 85, null);
                break;
            case 4:
                g2.drawImage(image, (int) x + cWidth / 2, (int) y + cHeight, 5, 30, null);
                break;
            case 5:
                g2.drawImage(image, (int) x + cWidth / 2, (int) y + cHeight, 25, 35, null);
                break;
            case 0:
                for (int i = 0; i < 8; i++) {
                    g2.drawImage(image, xPositions[i] - image.getWidth() / 2, yPositions[i] - image.getHeight() / 2, 100, 100, null);
                }
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
            return new Rectangle((int) x + gp.getTileSize() / 2, (int) y - 10, 25, 35);
		else return new Rectangle((int) x + gp.getTileSize() / 2, (int) y - 10, 15, 25);
	}

    public Boolean onTheGround() {
        return isOnTheGround;
    }
}