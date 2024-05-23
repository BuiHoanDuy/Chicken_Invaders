package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Bullet extends Entity {
	private int type;
	private int damage;
	private int width, height; // cài đặt kích cỡ của viên đạn
	private boolean isIntersectEnemy; // kiểm tra xem có chạm vào địch hay chưa
	
	private void initVariable() {
		isIntersectEnemy = false; setDamage();
		
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
		else { // Định hướng riêng cho đạn loại 3
			// Định nghĩa góc bay (theta) và vận tốc ban đầu (speed)
			double theta = Math.toRadians(speed); // truyền speed ở bên BulletList. speed chỉ mang tính chất set góc

			// Tính toán các thành phần vận tốc theo trục x và y
			float angle = (float) (speed * Math.sin(theta));

			// Điều chỉnh vị trí đạn
			if (speed > 0) {
			    x -= angle;
			} else if (speed < 0){
			    x += angle;
			}
			y -= 6; // Di chuyển đạn lên trên theo trục y với vận tốc cố định
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		g2.drawImage(image, (int) x - gp.getTileSize()/2, (int) y, width, height, null);
	}
	
	public Rectangle getBulletBound() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	
	public void setIsIntersectEnemy () {
		if (type != 5 && type != 51 && type != 52 && type != 53 && type != 54) {
			isIntersectEnemy = true;			
		}
	}
	
	
	public boolean getIsIntersectEnemy () {
		return isIntersectEnemy;
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
