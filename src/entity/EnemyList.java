package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class EnemyList {
	GamePanel gp;
	
	ArrayList<Enemy> enermyList;
	private int wave = 1; // màn chơi hiện tại
	private int waveSize; // số lượng con gà đã bị giết, nếu chưa giết thì sẽ render ra con gà khác để đủ
					// số lượng gà muốn xuất hiện thì thôi
	private int count = 0; // dùng để đếm số vòng lặp để render ra con gà sau số vòng lặp nhất định
	private int waveNum = 0; // dùng cho màn 4, số lượng đợt gà đã render ra
	private Random rand = new Random();

	public EnemyList(GamePanel gp) {
		this.gp = gp;
		enermyList = new ArrayList<Enemy>();
	}

	public void draw(Graphics2D g2) {
		int i = 0;
		try {
			while (i < enermyList.size()) {
				enermyList.get(i).draw(g2);
				enermyList.get(i).update(wave);
				if (enermyList.get(i).getHP() <= 0) {
					enermyList.remove(i);

					waveSize++;
				} else
					i++;
			}
		} catch (Exception e) {
		}
	}

	public void update() {
		setWave();

		count++;
	}

	public void setWave() {
		if (enermyList.isEmpty()) {
			waveSize = 0;
		}
		switch (wave) {
		case 1:
			if (waveSize < 70) {
				createWave1();
			} else {
				if (count % 1000 == 0) {
					enermyList.clear();
					wave++;
				}
			}
			break;

		case 2:
			if (waveSize < 70) {
				createWave2();
			} else {
				if (count % 1500 == 0) {
					enermyList.clear();
					wave++;
				}
			}
			break;
		case 3:
			if (waveSize < 5) {
				createWave3();
			} else {
				if (count % 1500 == 0) {
					enermyList.clear();
					wave++;
				}
			}
			break;
		case 4:
			if (waveNum <= 0) {
				createWave4();
			} else if (enermyList.isEmpty()) {
				wave++;
				waveNum = 0;
			}
			break;
		case 5:
			count = 0;
			if (waveNum <= 1) {
				createWave5();
				waveNum++;
			} else if (waveNum > 1 && enermyList.isEmpty()) {
				wave++;
				waveNum = 0;
			}
			break;
		case 6:
			if (waveSize <= 70) {
				createWave6();
			} else {
				if (count % 1500 == 0) {
					enermyList.clear();
					wave++;
				}
			}
			break;
		case 7:
			if (waveNum <= 0) {
				createWave7();
			} else if (waveNum > 0 && enermyList.isEmpty()) {
				wave++;
			}
			break;
		case 8:
			if (waveNum <= 0) {
				createWave8();
			} else if (waveNum > 0 && enermyList.isEmpty()) {
				++wave;
			}
			break;
		case 9:
			if (waveNum <= 0) {
				createWave9();
			} else if (waveNum > 0 && enermyList.isEmpty()) {
				wave++;
			}
			break;
		case 10:
			if (waveSize <= 200) {
				createWave10();
			} else if (waveNum > 0 && enermyList.isEmpty()) {
				wave++;
			}
			break;
		case 11:
			if (waveNum <= 0) {
				createWave11();
			} else if (waveNum > 0 && enermyList.isEmpty()) {
				wave++;
			}
			break;
		}
	}

	// 4 hàng gà bay ra liên tục
	public void createWave1() {
		if (count % 30 == 0) {
			enermyList.add(new Enemy(this.gp, -50, 432, 1300, 240, 1, 1));
			enermyList.add(new Enemy(this.gp, -50, 288, 1300, 144, 1, 2));
			enermyList.add(new Enemy(this.gp, 1100, 432, -400, 240, 1, 2));
			enermyList.add(new Enemy(this.gp, 1100, 288, -400, 144, 1, 1));
		}
	}

	// 3 hàng gà bay ra liên tục
	public void createWave2() {
		if (count % 30 == 0) {
			enermyList.add(new Enemy(this.gp, -50, 144, 1300, 144, 1, 3));
			enermyList.add(new Enemy(this.gp, 1300, 288, -50, 288, 1, 4));
			enermyList.add(new Enemy(this.gp, -50, 432, 1300, 432, 1, 3));
		}
	}

	// UFO bay qua, giết đủ 5 tàu sẽ qua màn mới
	public void createWave3() {
		if (count % 500 == 0) {
			enermyList.add(new Enemy(this.gp, -300, 384, 1300, 384, 1, 9));
		}

		if (count % 200 == 0) {
			enermyList.add(new Enemy(this.gp, 1300, 192, -1000, 192, 1, 9));
		}
	}

	// có 3 đợt gà, hạ từ trên xuống
	public void createWave4() {
		if (count % 800 == 0) {
			waveNum++;
			for (int i = 0; i <= 5; i++) {
				enermyList.add(new Enemy(this.gp, 96 + (144 * i), -96, 96 + (144 * i), 432, 1, 5));
				enermyList.add(new Enemy(this.gp, 96 + (144 * i), -1, 96 + (144 * i), 528, 1, 5));
				enermyList.add(new Enemy(this.gp, 144 + (144 * i), -48, 144 + (144 * i), 480, 1, 8));
				enermyList.add(new Enemy(this.gp, 192 + (144 * i), -96, 192 + (144 * i), 432, 1, 5));
				enermyList.add(new Enemy(this.gp, 192 + (144 * i), -1, 192 + (144 * i), 528, 1, 5));
			}
		}
	}

	// có 2 đợt gà, hạ từ trên xuống, có khiên
	public void createWave5() {
		if (count % 1600 == 0) {
			for (int i = 0; i < 6; i++) {
				if (i % 2 == 0) {
					enermyList.add(new Enemy(this.gp, -20, 528, 120 + (144 * i), 528, 1, 0));
				} else {
					enermyList.add(new Enemy(this.gp, 1050, 528, 120 + (144 * i), 528, 1, 0));
				}
			}
			for (float i = 0; i <= 5; i += 1.5) {
				enermyList.add(new Enemy(this.gp, 120 + (144 * i), -96, 120 + (144 * i), 336, 1, 6));
				enermyList.add(new Enemy(this.gp, 120 + (144 * i), -1, 120 + (144 * i), 432, 1, 8));
				enermyList.add(new Enemy(this.gp, 168 + (144 * i), -48, 168 + (144 * i), 384, 1, 6));
				enermyList.add(new Enemy(this.gp, 216 + (144 * i), -96, 216 + (144 * i), 336, 1, 6));
				enermyList.add(new Enemy(this.gp, 216 + (144 * i), -1, 216 + (144 * i), 432, 1, 8));
			}
		}
	}

	// trứng thiên thạch rơi xuống, né tránh
	public void createWave6() {
		if (count % 50 == 0) {
			int randomNum = rand.nextInt(7);
			waveSize++;
			enermyList.add(new Enemy(this.gp, rand.nextInt(600) + 600, -200, -200, rand.nextInt(880), 4, 11,
					5 + randomNum * 3, (float) (1 + 0.5 * randomNum), (float) (1.5 + 0.5 * randomNum)));

			enermyList.add(new Enemy(this.gp, 1108, rand.nextInt(600) - 200, rand.nextInt(800) - 200, 780, 4, 11,
					5 + randomNum * 3, (float) (1 + 0.5 * randomNum), (float) (1.5 + 0.5 * randomNum)));
		}
	}

	// ra vị trí cố định
	public void createWave7() {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				enermyList.add(new Enemy(this.gp, -20, 480, 120 + (144 * i), 480, 1, 8));
				enermyList.add(new Enemy(this.gp, -20, 384, 120 + (144 * i), 384, 1, 8));
				enermyList.add(new Enemy(this.gp, -20, 288, 120 + (144 * i), 288, 1, 8));
				enermyList.add(new Enemy(this.gp, -20, 192, 120 + (144 * i), 192, 1, 8));
			} else {
				enermyList.add(new Enemy(this.gp, 1050, 480, 120 + (144 * i), 480, 1, 8));
				enermyList.add(new Enemy(this.gp, 1050, 384, 120 + (144 * i), 384, 1, 8));
				enermyList.add(new Enemy(this.gp, 1050, 288, 120 + (144 * i), 288, 1, 8));
				enermyList.add(new Enemy(this.gp, 1050, 192, 120 + (144 * i), 192, 1, 8));
			}
		}
		++waveNum;
	}

	// ra vị trí cố định
	public void createWave8() {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				enermyList.add(new Enemy(this.gp, -20, 480, 120 + (144 * i), 480, 1, 8));
				enermyList.add(new Enemy(this.gp, -20, 384, 120 + (144 * i), 384, 1, 7));
				enermyList.add(new Enemy(this.gp, -20, 288, 120 + (144 * i), 288, 1, 8));
				enermyList.add(new Enemy(this.gp, -20, 192, 120 + (144 * i), 192, 1, 7));
			} else {
				enermyList.add(new Enemy(this.gp, 1050, 480, 120 + (144 * i), 480, 1, 8));
				enermyList.add(new Enemy(this.gp, 1050, 384, 120 + (144 * i), 384, 1, 7));
				enermyList.add(new Enemy(this.gp, 1050, 288, 120 + (144 * i), 288, 1, 8));
				enermyList.add(new Enemy(this.gp, 1050, 192, 120 + (144 * i), 192, 1, 7));
			}
		}
		++waveNum;
	}

	// ra vị trí cố định
	public void createWave9() {
		for (int i = 0; i < 6; i++) {
			if (i % 2 == 0) {
				enermyList.add(new Enemy(this.gp, -20, 480, 120 + (144 * i), 480, 1, 5));
				enermyList.add(new Enemy(this.gp, -20, 384, 120 + (144 * i), 384, 1, 6));
				enermyList.add(new Enemy(this.gp, -20, 288, 120 + (144 * i), 288, 1, 7));
				enermyList.add(new Enemy(this.gp, -20, 192, 120 + (144 * i), 192, 1, 8));
				enermyList.add(new Enemy(this.gp, -20, 48, 120 + (144 * i), 48, 1, 9));
			} else {
				enermyList.add(new Enemy(this.gp, 1050, 480, 120 + (144 * i), 480, 1, 5));
				enermyList.add(new Enemy(this.gp, 1050, 384, 120 + (144 * i), 384, 1, 6));
				enermyList.add(new Enemy(this.gp, 1050, 288, 120 + (144 * i), 288, 1, 7));
				enermyList.add(new Enemy(this.gp, 1050, 192, 120 + (144 * i), 192, 1, 8));
				enermyList.add(new Enemy(this.gp, 1050, 48, 120 + (144 * i), 48, 1, 9));
			}
		}
		++waveNum;
	}

	public void createWave10() {
		if (count % 30 == 0) {
			enermyList.add(new Enemy(this.gp, -200, 432, 1300, 240, 1, rand.nextInt(9) + 1));
			enermyList.add(new Enemy(this.gp, -200, 288, 1300, 144, 1, rand.nextInt(9) + 1));
			enermyList.add(new Enemy(this.gp, 1100, 432, -400, 240, 1, rand.nextInt(9) + 1));
			enermyList.add(new Enemy(this.gp, 1100, 288, -400, 144, 1, rand.nextInt(9) + 1));
			enermyList.add(new Enemy(this.gp, rand.nextInt(2000) - 500, -96, rand.nextInt(2000) - 500, 900, 1,
					rand.nextInt(9) + 1));
		}
	}

	public void createWave11() {
		waveNum++;
		enermyList.add(new Enemy(this.gp, -200, 432, 900, 240, 1, 10));
		enermyList.add(new Enemy(this.gp, 1100, 288, 100, 144, 1, 10));
	}

	public int getSize() {
		return enermyList.size();
	}

	public Enemy getEnermyFromIndex(int index) {
		return enermyList.get(index);
	}
}
