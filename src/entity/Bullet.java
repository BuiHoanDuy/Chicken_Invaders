package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Bullet extends Entity {
	private int type;
	private int damage;
	private int width, height; // cài đặt kích cỡ của viên đạn
	private boolean isIntersectEnermy; // kiểm tra xem có chạm vào địch hay chưa
	
	private void initVariable() {
		isIntersectEnermy = false; setDamage();
		
		setType();
		String path = "/image/bullet/" + type + ".png";

		try {
			image = ImageIO.read(getClass().getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Bullet(GamePanel gp, float x, float y, float speed, int type, int damage, int width, int height) {
		super(gp, x, y, speed);
		this.type = type;
		this.damage = damage;
		this.width = width; 
		this.height = height;
		this.initVariable();
	}

	@Override
	public void update() {
		if (type != 3) {
			y -= speed;			
		}
		else {
			if (speed >= 0) {
				x = (float) (x + Math.abs((y*Math.tan(speed)/x))) ;				
			}
			else if (speed < 0) {
				x = (float) (x - Math.abs((y*Math.tan(speed)/x))) ;
			}
			y -= 6;
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) x - gp.tileSize/2, (int) y, width, height, null);
	}
	
	public Rectangle getBulletBound() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	
	public void setIsIntersectEnermy () {
		isIntersectEnermy = true;
	}
	
	public boolean getIsIntersectEnermy () {
		return isIntersectEnermy;
	}
	
	public void setDamage() {
		gp.setBulletDamge(damage);
	}
	
	public void setType() {
		gp.setBulletType(type);
	}
	
	public int getType() {
		return type;
	}
}
