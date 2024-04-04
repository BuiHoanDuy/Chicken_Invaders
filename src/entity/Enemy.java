package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class Enemy extends Entity {
    private int type;
    private int hp;

    private void initVariable() {
        switch(type) {
            case 0:
                hp = 15;
            case 1:
                hp = 1;
                break;
            case 2:
                hp = 5;
                
                break;
            case 3:
                hp = 10;
                
                break;
            case 4:
                hp = 20;
                
                break;
            case 5:
                hp = 50;

                break;
            case 6:
                hp = 70;

                break;
            case 7:
                hp = 100;

                break;
        }
    }

    public Enemy(GamePanel gp, Float x, float y, float speed, int type, int hp) {
        super(gp, x, y, speed);
        this.type = type;
        this.hp = hp;
        this.initVariable();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub
    }
}
