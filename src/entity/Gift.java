package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Gift extends Entity {
	private int type;
	private int countLoop = 0; // load ảnh, đếm số vòng lặp
	private int i = 0, j = 0;
	private BufferedImage starAnimation = null;
	private boolean isIntersect = false;

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
	// image.
	// }

	// @Override
	// protected void paintComponent(Graphics g) {
	// super.paintComponent(g);
	// Graphics2D g2d = (Graphics2D) g.create();

	// // Get image dimensions
	// int imageWidth = image.getWidth();
	// int imageHeight = image.getHeight();

	// // Calculate the center of the image
	// int centerX = imageWidth / 2;
	// int centerY = imageHeight / 2;

	// // Create an AffineTransform for rotation
	// AffineTransform transform = new AffineTransform();

	// // Rotate the image around its center (you can change the angle as needed)
	// transform.rotate(Math.toRadians(45), centerX, centerY);

	// // Apply the transformation to the Graphics2D object
	// g2d.drawImage(image, transform, null);

	// g2d.dispose();
	// }

	@Override
	public void update() {
		// TODO Auto-generated method stub
		y += speed;
	}

	@Override
	public void draw(Graphics2D g2) {
		if (j >= 5) {
			i++;
			j = 0;
		}
		if (i > 1) {
			i = 0;
		}
		countLoop++;
		if (type == 0) {
			if (countLoop == 3) {
				countLoop = 0;
				j++;
				starAnimation = image.getSubimage(j * (110 + 36), i * (111 + 52), 110, 111);
			}
			g2.drawImage(starAnimation, (int) x - gp.tileSize / 2, (int) y, (int) (gp.tileSize / 1.5),
					(int) (gp.tileSize / 1.5), null);
		} else {
			g2.drawImage(image, (int) x - gp.tileSize / 2, (int) y, (int) (gp.tileSize / 1.5),
					(int) (gp.tileSize / 1.5), null);
		}
	}
	
	public Rectangle getGiftBound() {
		return new Rectangle((int) x + (gp.tileSize / 2), (int) y, (int) (gp.tileSize / 2),
				(int) (gp.tileSize / 2));
	}
	
	public void upDateWhenIntersectPlayer () {
		isIntersect = true;
	}
	
	public boolean getIsIntersect() {
		return isIntersect;
	}
	
	public int getType () {
		return type;
	}
}