package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HighScore {
    private Btn label, score, exiBtn;
    private BufferedImage background;

    private void initVar() {
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/image/background_image/menu/startMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        label = new Btn("HIGH SCORE", 25, 420, 380);

        try (BufferedReader br = new BufferedReader(new FileReader("/save/score.txt"))) {
            String scoreText;
            while ((scoreText = br.readLine()) != null) {
                score = new Btn(scoreText, 60, 482, 450);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        exiBtn = new Btn("EXIT", 25, 475, 592);
    }

    public HighScore() {
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
        label.draw(g2);
        score.draw(g2);
        exiBtn.draw(g2);

        g2.dispose();
    }
}