package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import main.GamePanel;

public class PauseMenu {
    private GamePanel gp;
    private Btn continueBtn, audioBtn, fpsBtn, exiBtn;
    private BufferedImage prevBtn, nextBtn;
    private BufferedImage background;

    private void initVar() {
        
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/image/background_image/menu/pauseMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        continueBtn = new Btn("CONTINUE", 25, 437, 320);

        String audioString = "AUDIO: " + (gp.getAudio() == true ? "ON" : "OFF");
        audioBtn = new Btn(audioString, 25, 432, 410);

        String fpsString = "FPS: " + gp.getFps();
        fpsBtn = new Btn(fpsString, 25, 456, 500);

        exiBtn = new Btn("EXIT", 25, 475, 592);

        try {
            prevBtn = ImageIO.read(getClass().getResourceAsStream("/image/gui/prevIcon.png"));
            nextBtn = ImageIO.read(getClass().getResourceAsStream("/image/gui/nextIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public PauseMenu(GamePanel gp) {
        this.gp = gp;
        this.initVar();
    }


    public int update(int mouseX, int mouseY) {
        if(430 <= mouseX && mouseX <= 580 && 290 <= mouseY && mouseY <= 330) return 1;
        else if(430 <= mouseX && mouseX <= 565 && 385 <= mouseY && mouseY <= 415) return 2;
        else if(450 <= mouseX && mouseX <= 555 && 470 <= mouseY && mouseY <= 510) return 3;
        else if(465 <= mouseX && mouseX <= 540 && 560 <= mouseY && mouseY <= 600) return 4;
        else if(400 <= mouseX && mouseX <= 412 && 394 <= mouseY && mouseY <= 412) return 5;
        else if(583 <= mouseX && mouseX <= 595 && 390 <= mouseY && mouseY <= 412) return 6;
        else if(400 <= mouseX && mouseX <= 412 && 480 <= mouseY && mouseY <= 500) return 7;
        else if(583 <= mouseX && mouseX <= 595 && 480 <= mouseY && mouseY <= 500) return 8;
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

        g.drawImage(prevBtn, 400, 392, 10, 20, null);
        g.drawImage(nextBtn, 585, 392, 10, 20, null);

        g.drawImage(prevBtn, 400, 480, 10, 20, null);
        g.drawImage(nextBtn, 585, 480, 10, 20, null);

        Graphics2D g2 = (Graphics2D) g;
        continueBtn.draw(g2);
        audioBtn.draw(g2);
        fpsBtn.draw(g2);
        exiBtn.draw(g2);

        g2.dispose();
    }
}