package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
	private int point;
	private int hp;
	private float attackCoolDown;
	private float attackCoolDownMax;
	private int ultiShoot; // số lượng ulti còn lại
	private boolean isIntersectEnermy; // có chạm địch hay không
	private boolean isIntersectGift;
	
	private void initVariable() {
		point = 0;
		hp = 100;
		ultiShoot = 3; // số lượng ulti
		isIntersectEnermy = false;
		isIntersectGift = false;
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/image/player/ship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Player(GamePanel gp, float x, float y, float speed) {
		super(gp, x, y, speed);
		this.initVariable();
	}

	@Override
	public void update() {
		if (isIntersectEnermy) {
			upDateWhenIntersectEnemy();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) x - gp.tileSize, (int) y, gp.tileSize * 2, gp.tileSize * 2, null);
	}

	public void setLocation(float x, float y) { // cài đặt tọa độ x, y
		if (x < 1008 && x > 0) {
			this.x = x;
		}
		if (y > 0 && y < 700) {
			this.y = y;
		}
	}
	
	public Rectangle getPlayerBound() {
		return new Rectangle((int) x+20, (int) y, gp.tileSize , gp.tileSize-20);
	}

	public void upDateWhenIntersectEnemy () {
		hp --;
		setLocation( 500, 570);
		isIntersectEnermy = false;
	}
	
	public void setIsIntersectEnermy() {
		isIntersectEnermy = true;
	}
	
	public void setIsIntersectGift() {
		isIntersectGift = true;
	}
	
	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void decreaseUltiShoot() {
		ultiShoot--;
	}

	public void increaseUltiShoot() {
		ultiShoot++;
	}

	public int getUltiShoot() {
		return ultiShoot;
	}
}
