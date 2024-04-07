package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class ChichkenItem extends Entity {
    int type;
    int point;
    double alpha;
    

    private void initVariable() {
        String path = "/image/enemy_item/chicken_item/" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public ChichkenItem(GamePanel gp, float x, float y, float speed, int type) {
        super(gp, x, y, speed);
        this.type = type;
        this.point = type;
        alpha = -0.1;
        this.initVariable();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        x += 1;
        y += alpha * x * x + 100 * x;
        // alpha -= 0.1;
    }

    @Override
    public void update(double a, double b, double c, int steps) {
        // vẽ đồ thị đường cong cho vật thể
        // TODO Auto-generated method stub
        for(int i = 1; i <= steps; ++i) {
            // x += i;
            // y = a * x * x + b * x + c;
            float newX = x + 1; // Đơn giản chỉ là di chuyển sang phải
            float newY = (float)(a * newX * newX + b * newX + c);
            x = newX;
            y = newY;
        }
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
		g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, gp.tileSize, gp.tileSize, null);
    }
}
