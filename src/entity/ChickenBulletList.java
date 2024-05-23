package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ChickenBulletList {
    private GamePanel gp;
	private ArrayList<ChickenBullet> bullets;
	private Random rand;


    public ChickenBulletList() {}

    public ChickenBulletList(GamePanel gp) {
        this.gp = gp;
        bullets = new ArrayList<>();
        rand = new Random();
    }

    private Boolean isSpawnBullet() {
        return (rand.nextInt(25) == 0);
    }

    
    public void update() {
        if(isSpawnBullet() && gp.getIsSpawnCB()) {
            // System.out.println("Spawn egg");
            bullets.add(new ChickenBullet(gp, gp.getXPos(), gp.getYPos(), 4, rand.nextInt(2) + 1));
            gp.playSE(12);
            gp.setIsSpawnCB();
        }

        for(int i = 0; i < bullets.size(); ++i) {
            bullets.get(i).update();
            if(bullets.get(i).getCanRemove()) bullets.remove(i);
        }
    }

    public void draw(Graphics2D g2) {
        for(int i = 0; i < bullets.size(); ++i) {
            bullets.get(i).draw(g2);
        }
    }

    public int getSize() {
        return bullets.size();
    }

    public ChickenBullet getCBFromIndex(int index) {
		return bullets.get(index);
	}

    public void remove(int index) {
        bullets.remove(index);
    }
}
