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
	private int bulletCountLoop = 0; // dùng để deley đạn sấm sét, type 5.

	private int level = 1; // cấp đạn.

	public BulletList(GamePanel gp) {
		this.gp = gp;
		bulletList = new ArrayList<Bullet>();
	}

	public void createBulletList() { // tạo danh sách đạn
		if (gp.getIsShooting()) {
			if (momentType == 1) {
				gp.playSE(23);
				damage = 1;
				speed = 8;
				loadingTimeMax = 15;
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 20, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 40, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 5) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY() - 20, speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY() - 20, speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 1, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				}
			} else if (momentType == 2) {
				gp.playSE(24);
				damage = 2;
				speed = 8;
				loadingTimeMax = 15;
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY() - 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY() - 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 5) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY() - 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));

					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY() + 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY() + 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY() + 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY() + 30, speed, 2, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				}
			} else if (momentType == 3) {
				gp.playSE(25);
				damage = 1;
				speed = 6;
				loadingTimeMax = 15;
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), 0, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), 0, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 15, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), 0, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -15, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), -20, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), -15, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), -10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), 0, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 15, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 20, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				} else if (level == 5) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 20, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 15, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), 5, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), 0, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize()));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -5, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -10, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -15, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), -20, 3, damage,
							gp.getTileSize() / 3, gp.getTileSize() / 2));
				}
			} else if (momentType == 4) {
				gp.playSE(26);
				damage = 1;
				speed = 10;
				loadingTimeMax = 15;
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 41, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
				} else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 8, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY() - 30, speed, 41, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 32, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 8, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY(), speed, 41, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 32, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 8, gp.getPlayerY() - 50, speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY() - 50, speed, 41, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 32, gp.getPlayerY() - 50, speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 2));
				} else if (level == 5) {
					loadingTimeMax = 13;
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 10, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 3));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 0, gp.getPlayerY() - 20, speed, 41, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 3));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 10, gp.getPlayerY() - 50, speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 3));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 20, gp.getPlayerY() - 20, speed, 41, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 3));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 30, gp.getPlayerY(), speed, 4, damage,
							gp.getTileSize() / 2, gp.getTileSize() * 3));
				}
			} else if (momentType == 5) {
				gp.playSE(17);
				loadingTimeMax = 60;
				damage = 1;
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY() - gp.getTileSize() * 15, 0, 5,
							damage, gp.getTileSize() / 2, gp.getTileSize() * 15));
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY() - gp.getTileSize() * 15, 0, 51,
							damage, gp.getTileSize(), gp.getTileSize() * 15));
				} else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY() - gp.getTileSize() * 15, 0, 52,
							damage, gp.getTileSize(), gp.getTileSize() * 15));
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY() - gp.getTileSize() * 15, 0, 53,
							damage, gp.getTileSize(), gp.getTileSize() * 15));
				} else if (level == 5) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 20, gp.getPlayerY() - gp.getTileSize() * 15, 0, 54,
							damage, gp.getTileSize() * 2, gp.getTileSize() * 15));
				}
			}
		}
	}

	public void update() {

		// thời gian load đạn
		if (gp.getIsShooting()) {
			if (loadingTime >= loadingTimeMax) { // reset lại loading time
				loadingTime = 0;
				createBulletList();
			} else
				loadingTime++;
		} else {
			loadingTime = 15;
		}

		// check ulti
		if (gp.getIsRightClicked() == true && gp.getUltiShoot() > 0) {
			gp.playSE(27);
			loadingTimeMax = 0;
			damage = 20;
			bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY() - 50, 3, 6, damage, gp.getTileSize(),
					gp.getTileSize() * 6));

			for (int i = 0; i < 30; i++) {
				bulletList.add(new Bullet(this.gp, 35 * i, 800, 3, 61, damage, gp.getTileSize() / 2, gp.getTileSize() * 2));
			}
			gp.setIsRightClicked(false);
			gp.decreaseUltiShoot();
		}

		// di chuyển và thu hồi đạn khi lên khỏi màn hình
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).update();

			if (momentType == 5) {
				bulletCountLoop++;
				if (bulletCountLoop >= 20) {
					bulletList.remove(i);
					bulletCountLoop = 0;
				}
			} else {
				if (bulletList.get(i).y <= -200) {
					bulletList.remove(i);
				}
			}
		}

	}

	public void draw(Graphics2D g2) {
		int i = 0;
		try {
			while (i < bulletList.size()) {
				bulletList.get(i).draw(g2);
				if (bulletList.get(i).getIsIntersectEnemy()) {
					bulletList.remove(i);
				} else {
					i++;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public int getSize() {
		return bulletList.size();
	}

	public Bullet getBulletFromIndex(int index) { // lấy viên đạn vị trí thứ i
		return bulletList.get(index);
	}

	public void setMomentType(int i) {
		if (momentType == 5) { // khứ thằng này để tránh gặp bug nó không biến mất
			bulletList.clear();
		}
		if (momentType == i && level < 5 || i == 0 && level < 5) {
			level++;
		} else if (i == 0 && level >= 5) {
		} 
		else {
			momentType = i;
		}
	}
	
	public void decreaseLevel() {
		level -= 2;
		if(level <= 0) level = 1;
	}

	public int getLevel() {
		return level;
	}
}
