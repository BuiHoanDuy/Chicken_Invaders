package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Gift extends Entity {
    private int type;
    
    private void initVariable() {
        String path = "/resource/image/gitf/" + type + ".png";
        try {
            image = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Gift(GamePanel gp, float x, float y, float speed, int type) {
        super(gp, x, y, speed);
        this.type = type;
        this.initVariable();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        super.y += speed;
    }    
    
    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
        g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, gp.tileSize / 2, gp.tileSize/2, null);
    }
}
