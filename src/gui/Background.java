package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Background {
    private BufferedImage background;

    public Background(int wave) {
        this.setPath(wave);
    }

    private void setPath(int wave) {
        if(wave >= 11) wave = 4;
        else wave = (wave - 1) / 2;
        String path = "/image/background_image/background/" + wave + ".png";

        try {
            background = ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update(int wave) {
        this.setPath(wave);
    }

    public void draw(Graphics g) {
        if (background != null) {
            g.drawImage(background, 0, 0, null);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1008, 720);
        }
    }
}
