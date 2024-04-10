package controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class mouseController implements MouseMotionListener, MouseListener {
	private GamePanel gp;

	public mouseController(GamePanel gp) {
		this.gp = gp;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			gp.setIsRightClicked(true);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON1) {
			gp.setIsShooting(true);
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		gp.setIsShooting(false); 
		gp.setIsRightClicked(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		gp.setPlayerLocation(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		gp.setPlayerLocation(e.getX(), e.getY());
	}

}
