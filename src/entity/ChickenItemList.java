	package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ChickenItemList {
	private GamePanel gp;
	private ArrayList<ChichkenItem> items;
	private Random rand;

    private void initVariable() {
        items = new ArrayList<>();
        rand = new Random();
    }

    public ChickenItemList(GamePanel gp) {
        this.gp = gp;
        this.initVariable();
    }

    private void spawnItems(float x, float y) {
            int type = rand.nextInt(7)+1;
            if (rand.nextInt(4) == 1) {
            	items.add(new ChichkenItem(gp, x, y, 4, type));            	
            }
    }

    public void update() {
        if(gp.getIsSpawnItem()) {
            spawnItems(gp.getLastX(), gp.getLastY());
           // gp.setIsSpawnItem(); // Goi trong effectList_Update
        }

        for(int i = 0; i < items.size(); ++i) {
            items.get(i).update();
            if(items.get(i).getCanRemove()) items.remove(i);
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < items.size(); ++i) {
            items.get(i).draw(g2);
        }
    }

    public int getSize() {
        return items.size();
    }

    public ChichkenItem getItemFromIndex(int index) {
        return items.get(index);
    }

    public void remove(int index) {
        items.remove(index);
    }
}