package entity;

import java.awt.image.BufferedImage;

public abstract class Entity {
    private float x, y;         // tọa độ
    protected BufferedImage image;
    private float speed;

    public Entity(float x, float y, float speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        image = null;
    }

    public void update() {}
    public void draw() {}
}