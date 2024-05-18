package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ChichkenItem extends Entity {
   private int type;
   private int waitingTime;
   private Boolean canRemove;
    

    private void initVariable() {
        String path = "/image/enemy_item/chicken_item/" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        waitingTime = 100;
        canRemove = false;
    }

    public ChichkenItem(GamePanel gp, float x, float y, float speed, int type) {
        super(gp, x, y, speed);
        this.type = type;
        this.initVariable();
    }

    @Override
    public void update() {
        if(y < 640) {
            y += speed;
        } else {
            if(waitingTime <= 0) canRemove = true;
            else --waitingTime;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) x - gp.getTileSize()/2, (int) y, 40, 40, null);
    }

    public Boolean getCanRemove() {
        return canRemove;
    }

    public Rectangle getItemBound() {
        return new Rectangle((int) x + gp.getTileSize() / 2, (int) y, 40, 40);
	}

    public int getType() {
        return type;
    }
}