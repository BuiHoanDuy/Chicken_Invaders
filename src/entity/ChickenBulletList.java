package entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

import main.GamePanel;

public class ChickenBulletList {
    private GamePanel gp;
	private ArrayList<ChickenBullet> bullets;
	private Random rand;
    private int type;
    private int soundIndex;


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
            int cType = gp.getCType();
            switch(cType) {
                case 1:
                    type = rand.nextInt(2) + 1;
                    soundIndex = 12;
                    break;
                case 2:
                case 3:
                case 4:
                case 7:
                case 9:
                    type = rand.nextInt(3) + 1;
                    soundIndex = 12;
                    break;
                case 5:
                case 6:
                    type = 4;
                    soundIndex = 23;
                    break;
                case 8:
                    type = 5;
                    soundIndex = 12;
                    break;
                case 10 :
                    type = rand.nextInt(2);
                    soundIndex = 24;
                    break;
                default:
                    break;
            }
            bullets.add(new ChickenBullet(gp, gp.getXPos(), gp.getYPos(), gp.getCWidth(), gp.getCHeight(), 4, type));
            gp.playSE(soundIndex);
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
