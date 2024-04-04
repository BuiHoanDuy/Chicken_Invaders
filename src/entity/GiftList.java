package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class GiftList {
    GamePanel gp;
    ArrayList<Gift> gifts;
    Random rand;
    int loadingTime;
    int loadingTimeMax;             // quãng thời gian tối thiểu để 2 hộp quà xuất hiện

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
        if(num == 1) return true;
        else return false;
    }

    private void spawnGifts() {
        // random loại của hộp quà sẽ rơi
        int num = rand.nextInt(16);
        // tỉ lệ rơi ra ngôi sao may mắn sẽ bằng 1/3 tỉ lệ rơi ra các vật phẩm còn lại
        gifts.add(new Gift(gp, rand.nextInt(980) + 30, 0, 4, (int)Math.ceil(num / 3)));
    }

    public void update() {
        if(loadingTime >= loadingTimeMax) {
            loadingTime = 0;
            if(isDropItem()) {
                this.spawnGifts();
                loadingTimeMax = rand.nextInt(201) + 500;
            }
        } else ++loadingTime;

        // xóa các hộp quà đã trôi ra khỏi màn hình
        for(int i = 0; i < gifts.size(); ++i) {
            gifts.get(i).update();
            if(gifts.get(i).y >= 720) {
                gifts.remove(i);
            }
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < gifts.size(); ++i) {
            gifts.get(i).draw(g2);
        }
    }
}