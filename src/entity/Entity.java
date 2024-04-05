package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.GamePanel;

public abstract class Entity {
	protected GamePanel gp;
    protected float x, y;         // tọa độ
    protected BufferedImage image;
    protected float speed;

    public Entity(GamePanel gp ,float x, float y, float speed) {
    	this.gp = gp;
        this.x = x;
        this.y = y;
        this.speed = speed;
        image = null;
    }

    public void draw(Graphics2D g2) {}
    public void update() {}
    public void update(float a, float b, float c, int steps) {}
}