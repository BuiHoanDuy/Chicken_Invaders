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
		gp.updateMouseClick(e.getX(), e.getY());
		if (!gp.getIsIntersectEnermy()) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				gp.setIsRightClicked(true);
			}
		} else gp.setIsRightClicked(false);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (!gp.getIsIntersectEnermy()) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				gp.setIsShooting(true);
			}
		} else gp.setIsShooting(false);
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
		if (!gp.getIsIntersectEnermy())
			gp.setPlayerLocation(e.getX(), e.getY());

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (!gp.getIsIntersectEnermy())
			gp.setPlayerLocation(e.getX(), e.getY());
	}

}
