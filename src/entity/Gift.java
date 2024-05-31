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
			g2.drawImage(starAnimation, (int) x - gp.getTileSize() / 2, (int) y, (int) (gp.getTileSize() / 1.5),
					(int) (gp.getTileSize() / 1.5), null);
		} else {
			g2.drawImage(image, (int) x - gp.getTileSize() / 2, (int) y, (int) (gp.getTileSize() / 1.5),
					(int) (gp.getTileSize() / 1.5), null);
		}
	}
	
	public Rectangle getGiftBound() {
		return new Rectangle((int) x + (gp.getTileSize() / 2), (int) y, (int) (gp.getTileSize() / 2),
				(int) (gp.getTileSize() / 2));
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