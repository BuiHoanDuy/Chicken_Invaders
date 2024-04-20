package entity;

import java.awt.Graphics2D;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Gift extends Entity {
    private int type;
    
    private void initVariable() {
        String path = "/image/gift/" + type + ".png";
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

    

    // private void rotate() {
    //     image.
    // }


    // @Override
    // protected void paintComponent(Graphics g) {
    //     super.paintComponent(g);
    //     Graphics2D g2d = (Graphics2D) g.create();
        
    //     // Get image dimensions
    //     int imageWidth = image.getWidth();
    //     int imageHeight = image.getHeight();
        
    //     // Calculate the center of the image
    //     int centerX = imageWidth / 2;
    //     int centerY = imageHeight / 2;
        
    //     // Create an AffineTransform for rotation
    //     AffineTransform transform = new AffineTransform();
        
    //     // Rotate the image around its center (you can change the angle as needed)
    //     transform.rotate(Math.toRadians(45), centerX, centerY);
        
    //     // Apply the transformation to the Graphics2D object
    //     g2d.drawImage(image, transform, null);
        
    //     g2d.dispose();
    // }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        y += speed;
    }    
    
    @Override
    public void draw(Graphics2D g2) {
    	g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, gp.tileSize/2, gp.tileSize/2, null);
    }
}