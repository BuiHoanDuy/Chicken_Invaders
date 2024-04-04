package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class BulletList {
	private GamePanel gp;
	private ArrayList<Bullet> bulletList; // danh sách các viên đạn đang có trên game
	private int momentType = 1; // loại đạn hiện tại máy bay đang sử dụng, mặc định ban đầu là 1;
	private float speed = 8; // tốc độ dùng để cài đặt cho đạn, thay đổi theo cấp độ / loại đạn. Mặc định là
								// 8.
	private int damage = 1; // dame dùng để cài đặt cho đạn, thay đổi theo cấp độ / loại đạn. Mặc định là 1.

	private int loadingTime = 0; // tốc độ ra đạn, sau mỗi vòng lặp thì tăng lên 1
	private int loadingTimeMax = 15; // loadingTime == loadingTimeMax, loadingTime = 0;
	
	private int level = 1; //cấp đạn. 

	public BulletList(GamePanel gp) {
		this.gp = gp;
		bulletList = new ArrayList<Bullet>();
	}

	public void createBulletList() { // tạo danh sách đạn
		if (gp.getIsShooting()) {
			if (momentType ==1 ) {
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));	
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
				}  else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
				} else if (level == 5) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage));
				}
			}
			else if (momentType == 2) {
				bulletList.add(new Bullet(this.gp, gp.getPlayerX()+25, gp.getPlayerY(), speed, 2, damage));				
			}
		}
	}

	public void update() {
		if (loadingTime >= loadingTimeMax) { // reset lại loading time
			loadingTime = 0;
			createBulletList();
		} else
			loadingTime++;

		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).update();
			if (bulletList.get(i).y <= 0) {
				bulletList.remove(i);
			}
		}

	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).draw(g2);
		}
	}

}
