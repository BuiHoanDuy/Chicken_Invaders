package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class GiftList {
	GamePanel gp;
	ArrayList<Gift> gifts;
	Random rand;
	int type; // Loại của item
	int loadingTime;
	int loadingTimeMax; // quãng thời gian tối thiểu để 2 hộp quà xuất hiện

	private void initVariable() {
		gifts = new ArrayList<>();
		rand = new Random();
		loadingTimeMax = 500;
		loadingTime = loadingTimeMax;
	}

	public GiftList(GamePanel gp) {
		this.gp = gp;
		this.initVariable();
	}

	private boolean isDropItem() {
		// tính tỉ lệ có 30% sẽ rơi hộp quà
		int num = rand.nextInt(5) + 1;
		if (num == 1)
			return true;
		else
			return false;
	}

	private void spawnGifts() {
		// thả rơi các hộp quà theo số lượng đã được tính toán
//		for (int i = 0; i < numsOfGift; ++i) {
			int num = rand.nextInt(16);
			// random loại của hộp quà sẽ rơi
			// tỉ lệ rơi ra ngôi sao may mắn sẽ bằng 1/3 tỉ lệ rơi ra các vật phẩm còn lại
			gifts.add(new Gift(gp, rand.nextInt(980) + 30, 0, 1, (int)Math.ceil(num / 3)));
//		}
	}

	public void update() {
		if (loadingTime >= loadingTimeMax) {
			loadingTime = 0;
            if(isDropItem()) {
            	this.spawnGifts();
            	loadingTimeMax = rand.nextInt(201) + 500;
//			int num = rand.nextInt(6) + 1;
//			if (1 <= num && num <= 3)
//				this.spawnGifts(1);
//			else if (num == 4 || num == 5)
//				this.spawnGifts(2);
//			else
//				this.spawnGifts(3);
            }
		} else
			++loadingTime;

		for (int i = 0; i < gifts.size(); i++) {
			gifts.get(i).update();
			if (gifts.get(i).y >= 800) {
				gifts.remove(i);
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < gifts.size(); i++) {
			gifts.get(i).draw(g2);
		}
	}
}