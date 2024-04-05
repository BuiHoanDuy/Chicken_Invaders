package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ChichkenItem extends Entity {
    int point;

    private void initVariable(int type) {
        String path = "/image/enemy_item/chicken_item" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        this.point = type;
    }

    public ChichkenItem(GamePanel gp, float x, float y, float speed, int type) {
        super(gp, x, y, speed);
        this.initVariable(type);;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        y += speed;
    }

    @Override
    public void update(float a, float b, float c, int steps) {
        // vẽ đồ thị đường cong cho vật thể
        for(int i = 1; i <= steps; ++i) {
//            x += i;
//            y = a * x * x + b * x + c;
        	float newX = x + i;
        	float newY = a * newX * newX + b * newX + c;
        	x = newX;
        	y = newY;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
		g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, gp.tileSize / 2, gp.tileSize/2, null);
    }
}