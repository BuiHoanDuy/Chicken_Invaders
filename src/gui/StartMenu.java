package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartMenu {
    private Btn playBtn, highScoreBtn, settingBtn, exiBtn;
    private BufferedImage background;

    private void initVar() {
        
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/image/background_image/menu/startMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        playBtn = new Btn("PLAY", 25, 471, 375);
        highScoreBtn = new Btn("HIGH SCORE", 25, 420, 455);
        settingBtn = new Btn("SETTING", 25, 448, 535);
        exiBtn = new Btn("EXIT", 25, 475, 615);
    }

    public StartMenu() {
        this.initVar();
    }


    public int update(int mouseX, int mouseY) {
        if(465 <= mouseX && mouseX <= 550 && 350 <= mouseY && mouseY <= 385) return 1;
        else if(415 <= mouseX && mouseX <= 590 && 425 <= mouseY && mouseY <= 465) return 2;
        else if(435 <= mouseX && mouseX <= 565 && 510 <= mouseY && mouseY <= 540) return 3;
        else if(460 <= mouseX && mouseX <= 535 && 590 <= mouseY && mouseY <= 620) return 4;
        else return -1;
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
        playBtn.draw(g2);
        highScoreBtn.draw(g2);
        settingBtn.draw(g2);
        exiBtn.draw(g2);

        g2.dispose();
    }
}