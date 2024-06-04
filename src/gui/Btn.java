package gui;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Btn {
    private String name;
    private Font font;
    private float x, y;

    public Btn() { name = null; font = null; x = 0; y = 0;}

    public Btn(String name, int size, float x, float y) {
        this.name = name;
        this.font = new Font("Inter", Font.BOLD, size);
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

    public Rectangle getBounds() {
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        
        g2.setFont(font);
        
        FontMetrics metrics = g2.getFontMetrics();
        
        int textWidth = metrics.stringWidth(name);
        int textHeight = metrics.getHeight();
        
        Rectangle bounds = new Rectangle((int)x, (int)(y - metrics.getAscent()), textWidth, textHeight);
        
        g2.dispose();
        
        return bounds;
    }
}