package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ChickenItemList {
    GamePanel gp;
    ArrayList<ChichkenItem> items;
    Random rand;

    private void initVariable() {
        items = new ArrayList<>();
        rand = new Random();
    }

    public ChickenItemList(GamePanel gp) {
        this.gp = gp;
        this.initVariable();
    }

    private void spawnItems(int num) {
        for(int i = 0; i < num; ++i) {
            int type = rand.nextInt(8);
            items.add(new ChichkenItem(gp, 200, 200, 2, type));
        }
    }

    public void update() {
//        int rate = rand.nextInt(2);
//        if(rate == 1) {
//            int num = rand.nextInt(4) + 1;
            this.spawnItems(1);
//        }
        
        for(int i = 0; i < items.size(); ++i) {
        	float a = rand.nextFloat(3) + 1;
        	float b = rand.nextFloat(3) + 1;
        	float c = rand.nextFloat(3) + 1;
        	items.get(i).update(a, b, c, 2);
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < items.size(); ++i) {
            items.get(i).draw(g2);
        }
    }
}