package gui;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

        // Kiểm tra và tạo thư mục 'save' nếu chưa tồn tại
        File saveDir = new File("save");
        if (!saveDir.exists()) {
            saveDir.mkdirs();
        }

        // Kiểm tra và tạo tệp 'score.txt' nếu chưa tồn tại
        File scoreFile = new File(saveDir, "score.txt");
        if (!scoreFile.exists()) {
            try {
                scoreFile.createNewFile();
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(scoreFile))) {
                    writer.write("0"); // Ghi điểm ban đầu là 0
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(scoreFile))) {
            String scoreText;
            while ((scoreText = br.readLine()) != null) {
                // 36 = 26 + 10 với 26 là chiều rộng một chữ cái size 60, 10 là khoảng cách giữa 2 chữ
                // tính tọa độ x để căn giữa score text
                int x = (1008 - (36 * scoreText.length())) / 2;
                score = new Btn(scoreText, 60, x, 450);
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