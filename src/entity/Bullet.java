package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Bullet extends Entity {
	private int type;
	private int damage;
	
	private void initVariable() {
		String path = "/image/bullet/" + type + ".png";

		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Bullet(GamePanel gp, float x, float y, float speed, int type, int damage) {
		super(gp, x, y, speed);
		this.type = type;
		this.damage = damage;
		this.initVariable();
	}

	@Override
	public void update() {
		y -= speed;
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, gp.tileSize / 3, gp.tileSize/2, null);
	}

}
