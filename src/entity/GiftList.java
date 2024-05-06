package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class GiftList {
	private GamePanel gp;
	private ArrayList<Gift> gifts;
	private Random rand;
	private int loadingTime;
	private int loadingTimeMax; // quãng thời gian tối thiểu để 2 hộp quà xuất hiện

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
		// tính tỉ lệ có 20% sẽ rơi hộp quà
		return (rand.nextInt(5) + 1 == 1);
	}

	private void spawnGifts() {
		// random loại của hộp quà sẽ rơi
		int num = rand.nextInt(16);
		// tỉ lệ rơi ra ngôi sao may mắn sẽ bằng 1/3 tỉ lệ rơi ra các vật phẩm còn lại
		gifts.add(new Gift(gp, rand.nextInt(980) + 30, -100, 1, (int) Math.ceil(num / 3)));
	}

	public void update() {
		// thả rơi hộp quà và reset thời tgian rơi hộp quà tiếp theo
		if (loadingTime >= loadingTimeMax) {
			if (isDropItem()) {
				this.spawnGifts();
				loadingTime = 0;
				loadingTimeMax = rand.nextInt(201) + 500;
			}
		} else
			++loadingTime;

		// thả rơi các hộp quà và xóa khi hộp quà rơi ra khỏi màn hình
		for (int i = 0; i < gifts.size(); i++) {
			gifts.get(i).update();
			if (gifts.get(i).y >= 800 || gifts.get(i).getIsIntersect()) {
				gifts.remove(i);
			}
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < gifts.size(); i++) {
			gifts.get(i).draw(g2);
		}
	}
	
	public int getSize() {
		return gifts.size();
	}
	
	public Gift getGiftFromIndex(int i) {
		return gifts.get(i);
	}
}