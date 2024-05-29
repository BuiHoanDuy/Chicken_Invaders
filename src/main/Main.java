package main;

import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

public static void main(String[] args) {
			
		JFrame window = new JFrame();
		window.setTitle("Chicken Invaders - CDUniverse");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Image icon = Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/image/gui/gameIcon.png"));
        window.setIconImage(icon);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}