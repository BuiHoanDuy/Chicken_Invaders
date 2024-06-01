package gui;

import java.awt.*;

public class Btn {
    private Font font;
    private String name;
    float x, y;

    public Btn() { font = null; name = null; x = 0; y = 0;}

    public Btn(String name, int size, float x, float y) {
        font =  new Font("Inter", Font.BOLD, size);
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public void setName(String newName) {
        name = newName;
    }

    public void draw(Graphics2D g2) {
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.drawString(name, x, y);
    }
}