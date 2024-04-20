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
            int type = rand.nextInt(8);
            items.add(new ChichkenItem(gp, x, y, 3, type));
    }

    public void update(float x, float y) {
        if (loadingTime >= loadingTimeMax) {
            if(isDropItem()) {
                this.spawnItems(x, y);
                loadingTime = 0;
            }
		} else
			++loadingTime;


        // if(isDropItem()) {
        //     this.spawnItems(x, y);
        // }

        for(int i = 0; i < items.size(); ++i) {
            items.get(i).update();
            if(items.get(i).y >= 500) {
                // while(items.get(i).splash() == false) {
                // }
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