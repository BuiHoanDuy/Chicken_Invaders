package gui;

import java.io.IOException;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class GuiText {
    private BufferedImage hp;
    private BufferedImage bulletLevel;
    private BufferedImage untiShoot;
    private BufferedImage score;
    private BufferedImage guiBg;

    private int hpNum;
    private int bulletNum;
    private int rocketNum;
    private int scoreNum;

    private Btn rockeBtn;
    private Btn scoreBtn;

    private BufferedImage chapterScreen;
    private Graphics2D chapterGraphics;


    private void initVar(int m_hp, int m_bullet, int m_rocket, int m_score) {
        this.hpNum = m_hp;
        this.bulletNum = m_bullet;
        this.rocketNum = m_rocket;
        this.scoreNum = m_score;
    }

    private void initTexture() {
        String hpPath = "/image/gui/heart_" + hpNum + ".png";
        String bulletString = "/image/gui/bulletLevel.jpg";
        String rocketString = "/image/gui/rocket.png";
        String scoreString = "/image/gui/score.png";

        try {
            hp = ImageIO.read(getClass().getResourceAsStream(hpPath));
            bulletLevel = ImageIO.read(getClass().getResourceAsStream(bulletString));
            untiShoot = ImageIO.read(getClass().getResourceAsStream(rocketString));
            score = ImageIO.read(getClass().getResourceAsStream(scoreString));
            guiBg  = new BufferedImage(280, 55, BufferedImage.TYPE_INT_ARGB);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initChapterText(int wave) {
        chapterScreen = new BufferedImage(1008, 720, BufferedImage.TYPE_INT_RGB);
        chapterGraphics = chapterScreen.createGraphics();
        chapterGraphics.setColor(Color.BLACK);
        chapterGraphics.fillRect(0, 0, 1008, 720);
        chapterGraphics.setColor(Color.WHITE);
        chapterGraphics.setFont(new Font("Arial", Font.BOLD, 48));
        String text = "Wave " + wave;
        int textWidth = chapterGraphics.getFontMetrics().stringWidth(text);
        int textHeight = chapterGraphics.getFontMetrics().getHeight();
        chapterGraphics.drawString(text, 400 - textWidth / 2, 300 - textHeight / 2);
    }

    public void drawChapterText(Graphics g, int wave) {
        if (chapterScreen != null) {
            g.drawImage(chapterScreen, 0, 0, null);
            try {
                Thread.sleep(1000); // Đợi 1 giây
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            chapterScreen = null; // Xóa màn hình đen và dòng chữ
        }
    }

    public GuiText() { this.initVar(0, 0, 0, 0); hp = null; bulletLevel = null; untiShoot = null; score = null; rockeBtn = null; scoreBtn = null; guiBg = null;}

    public GuiText (int hpNum, int bulletNum, int untiShoot, int m_score) {
        this.initVar(hpNum, bulletNum, untiShoot, m_score);
        this.initTexture();
        rockeBtn = new Btn("", 20, 180, 675);
        scoreBtn = new Btn("", 20, 250, 675);
    }

    public void update(int hpNum, int bulletNum, int untiShoot, int m_score) {
        this.initVar(hpNum, bulletNum, untiShoot, m_score);
        this.initTexture();
        rockeBtn.setName(Integer.toString(rocketNum));
        scoreBtn.setName(Integer.toString(scoreNum));
    }

    public void draw(Graphics g) {
        // vẽ background gui
        Graphics2D g2 = (Graphics2D) g;

        Color bgColor = new Color(208, 212, 218, 40);
        g2.setColor(bgColor);
        g2.fillRect(20, 635, guiBg.getWidth(), guiBg.getHeight());
        g2.drawRoundRect(20, 635, guiBg.getWidth(), guiBg.getHeight(), 10, 10); // Draw rounded rectangle border
        

        // vẽ icon gui
        g.drawImage(hp, 40, 645, 35, 40, null);
        g.drawImage(bulletLevel.getSubimage((bulletNum-1)*107, 0, 107, bulletLevel.getHeight()), 95, 645, 30, 35, null);
        g.drawImage(untiShoot, 140, 650, 30, 30, null);
        rockeBtn.draw(g2);
        g.drawImage(score, 210, 650, 30, 30, null);
        scoreBtn.draw(g2);

        g2.dispose();
    }
}