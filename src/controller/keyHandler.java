package controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class keyHandler implements KeyListener {
	GamePanel gp;

	public keyHandler(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
			gp.pauseGame();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
