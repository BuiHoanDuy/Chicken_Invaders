package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameEnd {
    private Btn exiBtn;
    private BufferedImage background;

    private void initVar() {
        
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/image/background_image/menu/GAME_END.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        exiBtn = new Btn("EXIT", 25, 475, 592);
    }

    public GameEnd() {
        this.initVar();
    }


    public Boolean update(int mouseX, int mouseY) {
        return (465 <= mouseX && mouseX <= 565 && 540 <= mouseY && mouseY <= 600);
    }

    public void draw(Graphics g) {
        // Vẽ background
        if (background != null) {
            g.drawImage(background, 0, 0, null);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1008, 720);
        }

        Graphics2D g2 = (Graphics2D) g;
        exiBtn.draw(g2);

        g2.dispose();
    }
}