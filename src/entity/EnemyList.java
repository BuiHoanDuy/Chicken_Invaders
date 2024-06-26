package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class EnemyList {
	private GamePanel gp;
	
	private ArrayList<Enemy> enemyList;

	private int wave = 1; // màn chơi hiện tại
	private int waveSize; // số lượng con gà đã bị giết, nếu chưa giết thì sẽ render ra con gà khác để đủ
					// số lượng gà muốn xuất hiện thì thôi
	private int count = 0; // dùng để đếm số vòng lặp để render ra con gà sau số vòng lặp nhất định
	private int waveNum = 0; // dùng cho màn 4, số lượng đợt gà đã render ra
	private Random rand;


	private void initVar() {
		enemyList = new ArrayList<Enemy>();
		rand = new Random();
	}

	public EnemyList(GamePanel gp) {
		this.gp = gp;
		this.initVar();
	}

	public void draw(Graphics2D g2) {
		int i = 0;
		try {
			while (i < enemyList.size()) {
				enemyList.get(i).draw(g2);
				enemyList.get(i).update(wave);
				
				if (enemyList.get(i).getHP() <= 0) {
					// trả về tọa độ enemy vừa bị hạ gục
					Enemy temp = enemyList.get(i);
					// nếu enemy có type != 0 (tấm khiên) và 11 (quả trứng thiên thạch) thì thả vật phẩm
						gp.setLastPos(temp.getX(), temp.getY());
						gp.setIsSpawnItem();
						gp.setLastType(temp.getType()); 

					// xóa nó ra khỏi list
					enemyList.remove(i);
					switch(enemyList.get(i).getType()) {
						case 0:
						case 11:
							gp.playSE(20);
							break;
						case 1:
							gp.playSE(3);
							break;
						default:
							if (rand.nextInt(2) == 0) gp.playSE(4);
							else gp.playSE(5);
							break;
					}
					waveSize++;
					
				} 
				// vuợt qua khỏi màn hình thì remove
				if (enemyList.get(i).getT() >=1 && (wave == 1 || wave == 2 || wave == 3 || wave == 6 || wave == 10)) {
					enemyList.remove(i);
				}
				i++;
				
			}
		} catch (Exception e) { }
	}

	private void spawnEnemyBullet() {
		// random ra index của con gà bất kì trong list, con gà này sẽ thả trứng
		if(enemyList.size() > 0) {
			int index = rand.nextInt(enemyList.size());
			Enemy temp = enemyList.get(index);
			if(temp.getType() == 0 || temp.getType() == 11) return;
			gp.setChickenBulletPos(temp.getX(), temp.getY());
			gp.setCType(temp.getType());
			gp.setCWidth(temp.getWidth(), temp.getHeight());
			gp.setIsSpawnCB();
		}
	}

	public void update() {
		setWave();
		spawnEnemyBullet();
		count++;
	}

	public void setWave() {
		switch (wave) {
		case 1:
			if (waveSize < 70) {
				createWave1();
			} else {
				if (enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
				}
			}
			break;

		case 2:
			if (waveSize < 70) {
				createWave2();
			} else {
				if (enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
				}
			}
			break;
		case 3:
			if (waveSize < 5) {
				createWave3();
			} else {
				if (enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
				}
			}
			break;
		case 4:
			if (waveNum <= 2) {
				createWave4();
			} else if (enemyList.isEmpty() && waveNum > 2) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
			}
			break;
		case 5:
			count = 0;
			if (waveNum < 2) {
				createWave5();
			} else if (waveNum >= 2 && enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
			}
			break;
		case 6:
			if (waveSize <= 70) {
				createWave6();
			} else {
				if (enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
				}
			}
			break;
		case 7:
			if (waveNum <= 0) {
				createWave7();
			} else if (waveNum > 0 && enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
			}
			break;
		case 8:
			if (waveNum <= 0) {
				createWave8();
			} else if (waveNum > 0 && enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
			}
			break;
		case 9:
			if (waveNum <= 0) {
				createWave9();
			} else if (waveNum > 0 && enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
			}
			break;
		case 10:
			if (waveSize <= 200) {
				createWave10();
			} else {
				if (enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
				}
			}
			break;
		case 11:
			if (waveNum <= 0) {
				createWave11();
			} else if (enemyList.isEmpty()) {
					wave++;
					waveSize = 0;
					gp.changeWave();
					waveNum = 0;
			}
			break;
		}
	}

	// 4 hàng gà bay ra liên tục
	public void createWave1() {
		if (count % 30 == 0) {
			enemyList.add(new Enemy(this.gp, -50, 432, 1300, 240, 1, 1));
			enemyList.add(new Enemy(this.gp, -50, 288, 1300, 144, 1, 2));
			enemyList.add(new Enemy(this.gp, 1100, 432, -400, 240, 1, 2));
			enemyList.add(new Enemy(this.gp, 1100, 288, -400, 144, 1, 1));
		}
	}

	// 3 hàng gà bay ra liên tục
	public void createWave2() {
		if (count % 30 == 0) {
			enemyList.add(new Enemy(this.gp, -50, 144, 1300, 144, 1, 3));
			enemyList.add(new Enemy(this.gp, 1300, 288, -500, 288, 1, 4));
			enemyList.add(new Enemy(this.gp, -50, 432, 1300, 432, 1, 3));
		}
	}

	// UFO bay qua, giết đủ 5 tàu sẽ qua màn mới
	public void createWave3() {
		if (count % 500 == 0) {
			enemyList.add(new Enemy(this.gp, -300, 384, 1100, 384, 1, 9));
		}

		if (count % 200 == 0) {
			enemyList.add(new Enemy(this.gp, 1300, 192, -150, 192, 1, 9));
		}
	}

	// có 3 đợt gà, hạ từ trên xuống
	public void createWave4() {
		if (enemyList.isEmpty()) {
			waveNum++;
			for (int i = 0; i <= 5; i++) {
				enemyList.add(new Enemy(this.gp, 96 + (144 * i), -96, 96 + (144 * i), 332, 1, 5));
				enemyList.add(new Enemy(this.gp, 96 + (144 * i), -1, 96 + (144 * i), 428, 1, 5));
				enemyList.add(new Enemy(this.gp, 144 + (144 * i), -48, 144 + (144 * i), 380, 1, 8));
				enemyList.add(new Enemy(this.gp, 192 + (144 * i), -96, 192 + (144 * i), 332, 1, 5));
				enemyList.add(new Enemy(this.gp, 192 + (144 * i), -1, 192 + (144 * i), 428, 1, 5));
			}
		}
	}

	// có 2 đợt gà, hạ từ trên xuống, có khiên
	public void createWave5() {
		if (enemyList.isEmpty()) {
			waveNum++;
			for (int i = 0; i < 6; i++) {
				if (i % 2 == 0) {
					enemyList.add(new Enemy(this.gp, -20, 528, 120 + (144 * i), 428, 1, 0));
				} else {
					enemyList.add(new Enemy(this.gp, 1050, 528, 120 + (144 * i), 428, 1, 0));
				}
			}
			for (float i = 0; i <= 5; i += 1.5) {
				enemyList.add(new Enemy(this.gp, 120 + (144 * i), -96, 120 + (144 * i), 236, 1, 6));
				enemyList.add(new Enemy(this.gp, 120 + (144 * i), -1, 120 + (144 * i), 332, 1, 8));
				enemyList.add(new Enemy(this.gp, 168 + (144 * i), -48, 168 + (144 * i), 284, 1, 6));
				enemyList.add(new Enemy(this.gp, 216 + (144 * i), -96, 216 + (144 * i), 236, 1, 6));
				enemyList.add(new Enemy(this.gp, 216 + (144 * i), -1, 216 + (144 * i), 332, 1, 8));
			}
		}
	}

	// trứng thiên thạch rơi xuống, né tránh
	public void createWave6() {
		if (count % 50 == 0) {
			int randomNum = rand.nextInt(7);
			waveSize++;
			enemyList.add(new Enemy(this.gp, rand.nextInt(600) + 600, -200, -200, rand.nextInt(880), 4, 11,
					5 + randomNum * 3, (float) (1 + 0.5 * randomNum), (float) (1.5 + 0.5 * randomNum)));

			enemyList.add(new Enemy(this.gp, 1108, rand.nextInt(600) - 200, rand.nextInt(800) - 200, 780, 4, 11,
					5 + randomNum * 3, (float) (1 + 0.5 * randomNum), (float) (1.5 + 0.5 * randomNum)));
		}
	}

	// ra vị trí cố định
	public void createWave7() {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				enemyList.add(new Enemy(this.gp, -20, 480, 120 + (144 * i), 480, 1, 8));
				enemyList.add(new Enemy(this.gp, -20, 384, 120 + (144 * i), 384, 1, 8));
				enemyList.add(new Enemy(this.gp, -20, 288, 120 + (144 * i), 288, 1, 8));
				enemyList.add(new Enemy(this.gp, -20, 192, 120 + (144 * i), 192, 1, 8));
			} else {
				enemyList.add(new Enemy(this.gp, 1050, 480, 120 + (144 * i), 480, 1, 8));
				enemyList.add(new Enemy(this.gp, 1050, 384, 120 + (144 * i), 384, 1, 8));
				enemyList.add(new Enemy(this.gp, 1050, 288, 120 + (144 * i), 288, 1, 8));
				enemyList.add(new Enemy(this.gp, 1050, 192, 120 + (144 * i), 192, 1, 8));
			}
		}
		++waveNum;
	}

	// ra vị trí cố định
	public void createWave8() {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				enemyList.add(new Enemy(this.gp, -20, 480, 120 + (144 * i), 480, 1, 8));
				enemyList.add(new Enemy(this.gp, -20, 384, 120 + (144 * i), 384, 1, 7));
				enemyList.add(new Enemy(this.gp, -20, 288, 120 + (144 * i), 288, 1, 8));
				enemyList.add(new Enemy(this.gp, -20, 192, 120 + (144 * i), 192, 1, 7));
			} else {
				enemyList.add(new Enemy(this.gp, 1050, 480, 120 + (144 * i), 480, 1, 8));
				enemyList.add(new Enemy(this.gp, 1050, 384, 120 + (144 * i), 384, 1, 7));
				enemyList.add(new Enemy(this.gp, 1050, 288, 120 + (144 * i), 288, 1, 8));
				enemyList.add(new Enemy(this.gp, 1050, 192, 120 + (144 * i), 192, 1, 7));
			}
		}
		++waveNum;
	}

	// ra vị trí cố định
	public void createWave9() {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				enemyList.add(new Enemy(this.gp, -20, 480, 120 + (144 * i), 480, 1, 5));
				enemyList.add(new Enemy(this.gp, -20, 384, 120 + (144 * i), 384, 1, 6));
				enemyList.add(new Enemy(this.gp, -20, 288, 120 + (144 * i), 288, 1, 7));
				enemyList.add(new Enemy(this.gp, -20, 192, 120 + (144 * i), 192, 1, 8));
				enemyList.add(new Enemy(this.gp, -20, 48, 120 + (144 * i), 48, 1, 9));
			} else {
				enemyList.add(new Enemy(this.gp, 1050, 480, 120 + (144 * i), 480, 1, 5));
				enemyList.add(new Enemy(this.gp, 1050, 384, 120 + (144 * i), 384, 1, 6));
				enemyList.add(new Enemy(this.gp, 1050, 288, 120 + (144 * i), 288, 1, 7));
				enemyList.add(new Enemy(this.gp, 1050, 192, 120 + (144 * i), 192, 1, 8));
				enemyList.add(new Enemy(this.gp, 1050, 48, 120 + (144 * i), 48, 1, 9));
			}
		}
		++waveNum;
	}

	// giết đủ 200 con sẽ qua màn. gà sẽ bay tứ tung
	public void createWave10() {
		if (count % 30 == 0) {
			enemyList.add(new Enemy(this.gp, -200, 432, 1300, 240, 1, rand.nextInt(9) + 1));
			enemyList.add(new Enemy(this.gp, -200, 288, 1300, 144, 1, rand.nextInt(9) + 1));
			enemyList.add(new Enemy(this.gp, 1100, 432, -400, 240, 1, rand.nextInt(9) + 1));
			enemyList.add(new Enemy(this.gp, 1100, 288, -400, 144, 1, rand.nextInt(9) + 1));
			enemyList.add(new Enemy(this.gp, rand.nextInt(2000) - 500, -96, rand.nextInt(2000) - 500, 900, 1,
					rand.nextInt(9) + 1));
		}
	}

	// 2 con boss bay tứ tung
	public void createWave11() {
		enemyList.add(new Enemy(this.gp, -200, 432, 890, 240, 1, 10));
		enemyList.add(new Enemy(this.gp, 1100, 288, 10, 144, 1, 10));
		waveNum++;
	}

	public int getSize() {
		return enemyList.size();
	}

	public Enemy getEnemyFromIndex(int index) {
		return enemyList.get(index);
	}
	
	public int getWave() {
		return wave;
	}
}
