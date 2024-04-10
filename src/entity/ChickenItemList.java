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
        int rate = rand.nextInt(3);
        if(rate == 0) return false;
        else return true;
    }

    private void spawnItems(float x, float y) {
        // int num = rand.nextInt(3) + 1;
        // for(int i = 0; i < num; ++i) {
            int type = rand.nextInt(8);
            items.add(new ChichkenItem(gp, x, y, 1, type));
        // }
    }

    public void update() {
        
        if (loadingTime >= loadingTimeMax) {
            loadingTime = 0;
            // if(isDropItem()) {
                this.spawnItems(500, 200);
            // }
		} else
			++loadingTime;

        for(int i = 0; i < items.size(); ++i) {
            items.get(i).update(i % 2 == 0);
            if(items.get(i).y >= 500) {
                while(items.get(i).splash() == false) {
                }
                items.remove(i);
                // if(items.get(i).splash() == true) {
                //     items.remove(i);
                // }
            }
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < items.size(); ++i) {
            items.get(i).draw(g2);
        }
    }
}