package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GameOver {
    private Btn gameOver, playBtn, scoreLabel, score, exiBtn;
    private BufferedImage background;

    private void initVar(int m_score) {
        
        try {
            background = ImageIO.read(getClass().getResourceAsStream("/image/background_image/menu/startMenu.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        gameOver = new Btn("GAME OVER", 35, 400, 380);
        scoreLabel = new Btn("SCORE", 25, 455, 430);

        String scoreText = Integer.toString(m_score);
        int x = (1008 - (36 * scoreText.length())) / 2;
        score = new Btn(scoreText, 60, x, 490);

        playBtn = new Btn("REPLAY", 16, 420, 550);
        exiBtn = new Btn("EXIT", 16, 530, 550);
    }

    public GameOver(int m_score) {
        this.initVar(m_score);
    }


    public int update(int mouseX, int mouseY) {
        if(415 <= mouseX && mouseX <= 485 && 530 <= mouseY && mouseY <= 555) return 1;
        else if(530 <= mouseX && mouseX <= 565 && 530 <= mouseY && mouseY <= 555) return 2;
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
        gameOver.draw(g2);
        scoreLabel.draw(g2);
        score.draw(g2);
        playBtn.draw(g2);
        exiBtn.draw(g2);

        g2.dispose();
    }
}