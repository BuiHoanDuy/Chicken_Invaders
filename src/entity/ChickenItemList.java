package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ChickenItemList {
    GamePanel gp;
    ArrayList<ChichkenItem> items;
    Random rand;
    int loadingTime;
    int loadingTimeMax;

    private void initVariable() {
        items = new ArrayList<>();
        rand = new Random();
        loadingTimeMax = 100;
        loadingTime = loadingTimeMax;
    }

    public ChickenItemList(GamePanel gp) {
        this.gp = gp;
        this.initVariable();
    }

    private boolean isDropItem() {
        int rate = rand.nextInt(2);
        if(rate == 1) return true;
        else return false;
    }

    private void spawnItems(float x, float y) {
        // int num = rand.nextInt(4) + 1;
        // for(int i = 0; i < num; ++i) {
            int type = rand.nextInt(8);
            items.add(new ChichkenItem(gp, x, y, 1, type));
        // }
    }

    public void update() {
        
        if (loadingTime >= loadingTimeMax) {
            loadingTime = 0;
            if(isDropItem()) {
                this.spawnItems(500, 200);
            }
		} else
			++loadingTime;

        for(int i = 0; i < items.size(); ++i) {
            items.get(i).update();
            // items.get(i).update(-0.1, 0, 10, 100);
            if(items.get(i).y >= 800) {
                items.remove(i);
            }
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < items.size(); ++i) {
            items.get(i).draw(g2);
        }
    }
}