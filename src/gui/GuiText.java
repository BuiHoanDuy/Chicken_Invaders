package gui;

import java.io.IOException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GuiText {
    private BufferedImage hp;
    private BufferedImage bulletLevel;
    private BufferedImage untiShoot;
    private BufferedImage score;
    private final int guiBgWidth = 280;
    private final int guiBgHeight = 55;

    private int hpNum;
    private int bulletNum;
    private int rocketNum;
    private int scoreNum;

    private Btn rockeBtn;
    private Btn scoreBtn;

    private int currentWave;
    private final int progressWidth = 50;
    private final int progressBarWidth = 500;
    private final int progressBarHeight = 5;
    private BufferedImage boss;
    private BufferedImage sword;

    private void initVar(int m_hp, int m_bullet, int m_rocket, int m_score) {
        this.hpNum = m_hp;
        this.bulletNum = m_bullet;
        this.rocketNum = m_rocket;
        this.scoreNum = m_score;
    }

    private void initTexture() {
        String hpPath = "/image/gui/heart_" + hpNum + ".png";

        try {
            hp = ImageIO.read(getClass().getResourceAsStream(hpPath));
            bulletLevel = ImageIO.read(getClass().getResourceAsStream("/image/gui/bulletLevel.jpg"));
            untiShoot = ImageIO.read(getClass().getResourceAsStream("/image/gui/rocket.png"));
            score = ImageIO.read(getClass().getResourceAsStream("/image/gui/score.png"));
            sword = ImageIO.read(getClass().getResourceAsStream("/image/gui/swordIcon.png"));
            boss = ImageIO.read(getClass().getResourceAsStream("/image/gui/bossIcon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GuiText() { this.initVar(0, 0, 0, 0); hp = null; bulletLevel = null; untiShoot = null; score = null; rockeBtn = null; scoreBtn = null; sword = null;}

    public GuiText (int hpNum, int bulletNum, int untiShoot, int m_score) {
        this.initVar(hpNum, bulletNum, untiShoot, m_score);
        this.initTexture();
        rockeBtn = new Btn("", 20, 180, 675);
        scoreBtn = new Btn("", 20, 250, 675);
    }

    public void updateWave(int m_wave) {
        currentWave = m_wave - 1;
    }

    public void update(int hpNum, int bulletNum, int untiShoot, int m_score) {
        this.initVar(hpNum, bulletNum, untiShoot, m_score);
        this.initTexture();
        rockeBtn.setName(Integer.toString(rocketNum));
        scoreBtn.setName(Integer.toString(scoreNum));
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        
        // vẽ background gui
        Color bgColor = new Color(208, 212, 218, 40);
        g2.setColor(bgColor);
        g2.fillRect(20, 635, guiBgWidth, guiBgHeight);
        g2.drawRoundRect(20, 635, guiBgWidth, guiBgHeight, 10, 10); // Draw rounded rectangle border
        
        // vẽ icon gui
        g.drawImage(hp, 40, 645, 35, 40, null);
        g.drawImage(bulletLevel.getSubimage((bulletNum-1)*107, 0, 107, bulletLevel.getHeight()), 95, 645, 30, 35, null);
        g.drawImage(untiShoot, 140, 650, 30, 30, null);
        rockeBtn.draw(g2);
        g.drawImage(score, 210, 650, 30, 30, null);
        scoreBtn.draw(g2);
        
        // vẽ thanh progress
        g2.setColor(new Color(92, 255, 252, 128));
        g2.fillRect(254, 20, currentWave * progressWidth, progressBarHeight);
        g2.drawRoundRect(254, 20, progressBarWidth, progressBarHeight, 5, 5);
        g.drawImage(sword, 242 + (currentWave * progressWidth), 10, 25, 25, null);
        if(currentWave < 10) 
            g.drawImage(boss, 737, 10, 35, 30, null);
        else g.drawImage(boss, 730, 5, 45, 40, null);

        g2.dispose();
    }
}