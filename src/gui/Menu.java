package gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import main.GamePanel;

public class Menu {
    private int index;
    private int y, lineHeight;


    private static String[][] title = {
        {"PLAY", "HIGH SCORE", "SETTING", "EXIT"},      // start menu
        {"AUDIO: ", "FPS: ", "EXIT"},                   // setting menu
        {"CONTINUE", "AUDIO: ", "FPS: ", "EXIT"},       // pause menu
        {"HIGH SCORE", "EXIT"}                          // high score menu
    };

    private static int[][] x = {
        {471, 420, 448, 475},           // start menu
        {432, 456, 475},                // setting menu
        {437, 432, 456, 475},           // pause menu
        {420, 475},                     // high score
    };

    private static int[][] dirX = {
        {400, 392},
        {585, 392},
        {400, 480},
        {585, 480}
    };

    private ArrayList<Btn> btn;
    private ArrayList<BufferedImage> dirBtn;
    private BufferedImage background;

    private void initVar(String name) {
        String s = "/image/background_image/menu/" + name +".png";
        try {
            background = ImageIO.read(getClass().getResourceAsStream(s));
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        switch (name) {
            case "GAME_START":
                index = 0;
                y = 375;
                lineHeight = 80;
                break;
            case "SETTING":
                index = 1;
                y = 410;
                lineHeight = 90;
                break;
            case "GAME_PAUSE":
                index = 2;
                y = 320;
                lineHeight = 90;
                break;
            case "HIGH_SCORE":
                index = 3;
                y = 375;
                lineHeight = 70;
                break;
            default:
                break;
            }

        btn = new ArrayList<>();
       for(int i = 0; i < title[index].length; ++i) {
            btn.add(new Btn(title[index][i], 25, x[index][i], y));
            y += lineHeight;
        }

        // if(index == 1 || index == 2) {
        //     dirBtn = new ArrayList<>();
        //     for(int i = 0; i < 4; ++i) {
        //         BufferedImage temp = null;
        //         String path = null;
        //         if(i % 2 != 0) path = "prevIcon";
        //         else path = "nextIcon";
        //         try {
        //             temp = ImageIO.read(getClass().getResourceAsStream("/image/gui/" + path + ".png"));
        //         } catch (IOException e) {
        //             // TODO Auto-generated catch block
        //             e.printStackTrace();
        //         }
                
        //         dirBtn.add(temp);
        //     }
        // }
    }

    public void changeStage(String name) {
        initVar(name);
    }
    

    public Menu(String name) {
        this.initVar(name);
    }


    public int update(int mouseX, int mouseY) {
        for(int i = 0; i < 4; ++i) {
            if(btn.get(i).getBounds().contains(mouseX, mouseY)) {
                return i + 1;
            }
        }
        return -1;
    }

    public void draw(Graphics g) {
        // Vẽ background
        if (background != null) {
            g.drawImage(background, 0, 0, null);
        } else {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, 1008, 720);
        }

        int i = 0;
        for(BufferedImage x : dirBtn) {
            g.drawImage(x, dirX[i][0], dirX[i][1], null);
            ++i;
        }

        Graphics2D g2 = (Graphics2D) g;
        for(Btn x : btn) {
            x.draw(g2);
        }

        g2.dispose();
    }
}
