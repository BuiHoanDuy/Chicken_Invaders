package main;

import java.awt.Cursor;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Main {

public static void main(String[] args) {
			
		JFrame window = new JFrame();
		window.setTitle("Chicken Invaders - CDUniverse");
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// ẩn con trỏ
		Cursor hiddenCursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new java.awt.Point(), "hiddenCursor");
		window.setCursor(hiddenCursor);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
	}
}