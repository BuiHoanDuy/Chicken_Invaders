package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class BulletList {
	private GamePanel gp;
	private ArrayList<Bullet> bulletList; // danh sách các viên đạn đang có trên game
	private int momentType = 4; // loại đạn hiện tại máy bay đang sử dụng, mặc định ban đầu là 1;
	private float speed = 8; // tốc độ dùng để cài đặt cho đạn, thay đổi theo cấp độ / loại đạn. Mặc định là
								// 8.
	private int damage = 1; // dame dùng để cài đặt cho đạn, thay đổi theo cấp độ / loại đạn. Mặc định là 1.

	private int loadingTime = 0; // tốc độ ra đạn, sau mỗi vòng lặp thì tăng lên 1
	private int loadingTimeMax = 15; // loadingTime == loadingTimeMax, loadingTime = 0;
	int bulletCountLoop = 0; // dùng để deley đạn sấm sét, type 5.

	private int level = 2; // cấp đạn.

	public BulletList(GamePanel gp) {
		this.gp = gp;
		bulletList = new ArrayList<Bullet>();
	}

	public void createBulletList() { // tạo danh sách đạn
		if (gp.getIsShooting()) {
			if (momentType == 1) {
				damage = 1;
				speed = 8;
				loadingTimeMax = 15;
				if (level == 1) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
				} else if (level == 2) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
				} else if (level == 3) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 20, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 40, gp.getPlayerY(), speed, 1, damage, gp.tileSize/3, gp.tileSize/2));	
				} else if (level == 4) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 1, damage, gp.tileSize/3, gp.tileSize/2));	
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 1, damage, gp.tileSize/3, gp.tileSize/2));	
				} else if (level == 5) {
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY() - 20, speed, 1, damage, gp.tileSize/3, gp.tileSize/2));	
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY() - 20, speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 1, damage, gp.tileSize/3, gp.tileSize/2));	
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 1, damage, gp.tileSize / 3, gp.tileSize / 2));
					bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 1, damage, gp.tileSize/3, gp.tileSize/2));	
				}
			} else if (momentType == 2) {
				damage = 2;
				speed = 8;
				loadingTimeMax = 15;
					if (level == 1) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
					} else if (level == 2) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
					} else if (level == 3) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY() - 30, speed, 2, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
					} else if (level == 4) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY() - 30, speed, 2, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
					} else if (level == 5) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY(), speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY() - 30, speed, 2, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY(), speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
						
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 15, gp.getPlayerY() + 30, speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 5, gp.getPlayerY() + 30, speed, 2, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY() + 30, speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 45, gp.getPlayerY() + 30, speed, 2, damage, gp.tileSize/3, gp.tileSize/2));	
					}
				}
			else if (momentType == 3) {
				damage = 1;
				speed = 6;
				loadingTimeMax = 15;
					if (level == 1) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 17, gp.getPlayerY(), 0, 3, damage, gp.tileSize / 3, gp.tileSize));
					} else if (level == 2) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -10, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 0, 3, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 10, 3, damage, gp.tileSize/3, gp.tileSize/2));	
					} else if (level == 3) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -20, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -10, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 0, 3, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 10, 3, damage, gp.tileSize/3, gp.tileSize/2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 20, 3, damage, gp.tileSize/3, gp.tileSize/2));
					} else if (level == 4) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), -20, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), -17, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), -10, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 0, 3, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 10, 3, damage, gp.tileSize/3, gp.tileSize/2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 17, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 20, 3, damage, gp.tileSize/3, gp.tileSize/2));
					} else if (level == 5) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -140, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -130, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -110, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY(), -90, 3, damage, gp.tileSize / 3, gp.tileSize / 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 15, gp.getPlayerY(), 0, 3, damage, gp.tileSize / 3, gp.tileSize));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 90, 3, damage, gp.tileSize/3, gp.tileSize/2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 110, 3, damage, gp.tileSize/3, gp.tileSize/2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 130, 3, damage, gp.tileSize/3, gp.tileSize/2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 35, gp.getPlayerY(), 140, 3, damage, gp.tileSize/3, gp.tileSize/2));
					}
				}
			else if (momentType == 4) {
				damage = 1;
				speed = 10;
				loadingTimeMax = 15;
					if (level == 1) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY(), speed, 4, damage, gp.tileSize / 2, gp.tileSize*2));
					} else if (level == 2) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 5, gp.getPlayerY(), speed, 4, damage, gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 25, gp.getPlayerY(), speed, 41, damage, gp.tileSize / 2, gp.tileSize * 2));
					} else if (level == 3) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 8, gp.getPlayerY(), speed, 4, damage, gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY() - 30, speed, 41, damage, gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 32, gp.getPlayerY(), speed, 4, damage,  gp.tileSize / 2, gp.tileSize * 2));	
					} else if (level == 4) {
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 8, gp.getPlayerY(), speed, 4, damage,  gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY() , speed, 41, damage, gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 32, gp.getPlayerY(), speed, 4, damage, gp.tileSize / 2, gp.tileSize * 2));	
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 8, gp.getPlayerY() -50, speed, 4, damage,  gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 13, gp.getPlayerY() - 50, speed, 41, damage, gp.tileSize / 2, gp.tileSize * 2));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 32, gp.getPlayerY() - 50, speed, 4, damage, gp.tileSize / 2, gp.tileSize * 2));	
					} else if (level == 5) {
						loadingTimeMax = 13;
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() -10, gp.getPlayerY(), speed, 4, damage,  gp.tileSize / 2, gp.tileSize * 3));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX()+ 0, gp.getPlayerY()-20, speed, 41, damage, gp.tileSize / 2, gp.tileSize * 3));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 10, gp.getPlayerY()-50, speed, 4, damage,  gp.tileSize / 2, gp.tileSize * 3));
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 20, gp.getPlayerY()-20, speed, 41, damage,  gp.tileSize / 2, gp.tileSize * 3));	
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() + 30, gp.getPlayerY(), speed, 4, damage,  gp.tileSize / 2, gp.tileSize * 3));		
					}
				}
			else if (momentType == 5) {
				loadingTimeMax = 60;
					if (level == 1) {
						damage = 1;
						bulletList.add(new Bullet(this.gp, gp.getPlayerX()+13 , gp.getPlayerY()-gp.tileSize*15, 0, 5, damage, gp.tileSize/2, gp.tileSize*15));
					} else if (level == 2) {
						damage = 3;
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY()-gp.tileSize*15, 0, 51, damage, gp.tileSize, gp.tileSize*15));
					} else if (level == 3) {
						damage = 5;
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY()-gp.tileSize*15, 0, 52, damage, gp.tileSize, gp.tileSize*15));
					} else if (level == 4) {
						damage = 7;
						bulletList.add(new Bullet(this.gp, gp.getPlayerX(), gp.getPlayerY()-gp.tileSize*15, 0, 53, damage, gp.tileSize, gp.tileSize*15));
					} else if (level == 5) {
						damage = 10;
						bulletList.add(new Bullet(this.gp, gp.getPlayerX() - 20 , gp.getPlayerY()-gp.tileSize*15, 0, 54, damage, gp.tileSize*2, gp.tileSize*15));
					}
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
			
			if (momentType == 5){
				bulletCountLoop ++;
				if (bulletCountLoop >= 20) {
					bulletList.remove(i);
					bulletCountLoop = 0;
				}
			} else {
				if (bulletList.get(i).y <= -50) {
					bulletList.remove(i);
				} 
			}
		}

	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < bulletList.size(); i++) {
			bulletList.get(i).draw(g2);
		}
	}

}
