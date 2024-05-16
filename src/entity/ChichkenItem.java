package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ChichkenItem extends Entity {
    int type;
    int point;
    double alpha;
    int waitingTime;
    boolean isSplashUp;
    boolean isSplashDown;
    private Boolean canRemove;
    private Boolean isOnTheGround;
    

    private void initVariable() {
        String path = "/image/enemy_item/chicken_item/" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        point = type;
        alpha = -0.1;
        waitingTime = 100;
        isSplashUp = false;
        isSplashDown = false;
        canRemove = false;
        isOnTheGround = false;
    }

    public ChichkenItem(GamePanel gp, float x, float y, float speed, int type) {
        super(gp, x, y, speed);
        this.type = type;
        this.initVariable();
    }

    private void splashUp() {
        if(y > 450) --y;
        else isSplashUp = true;
    }

    private void splashDown() {
        if(y < 490) ++y;
        else isSplashDown = true;
    }

    public boolean splash() {
        if(isSplashUp == false) {
            this.splashUp();
            return false;
        }
        
        if(isSplashDown == false) {
            this.splashDown();
            return false;
        }

        if(waitingTime > 0) {
            --waitingTime;
            return false;
        } else return true;
    }


    @Override
    public void update() {
        if(y < 640) {
            y += speed;
        } else {
            isOnTheGround = true;
            if(waitingTime <= 0) canRemove = true;
            else --waitingTime;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, 40, 40, null);
    }

    public Boolean getCanRemove() {
        return canRemove;
    }

    public Rectangle getItemBound() {
        return new Rectangle((int) x + gp.tileSize / 2, (int) y, 40, 40);
	}

    public int getType() {
        return type;
    }
}