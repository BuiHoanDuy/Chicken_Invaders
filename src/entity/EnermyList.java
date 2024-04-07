package entity;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import main.GamePanel;

public class EnermyList {
	GamePanel gp;
	ArrayList<Enermy> enermyList;

	public EnermyList(GamePanel gp) {
		this.gp = gp;
		enermyList = new ArrayList<Enermy>();
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < enermyList.size(); i++) {
			enermyList.get(i).draw(g2);
			enermyList.get(i).update();
			if (enermyList.get(i).getHP() <= 0) {
				enermyList.remove(i);
			}
		}
	}

	public void update() {
		setWave();
	}

	public void setWave() {
		if (enermyList.isEmpty()) {
			createWave1();
		}
	}

	

	public void createWave1() {
		enermyList.add(new Enermy(this.gp, -1, 720, 96, 96, 1, 3, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 192, 96, 1, 3, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 144, 144, 1, 3, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 96, 192, 1, 3, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 192, 192, 1, 3, 1));

		enermyList.add(new Enermy(this.gp, -1, -10, 384, 96, 1, 5, 1));
		enermyList.add(new Enermy(this.gp, -1, -10, 480, 96, 1, 5, 1));
		enermyList.add(new Enermy(this.gp, -1, -10, 432, 144, 1, 5, 1));
		enermyList.add(new Enermy(this.gp, -1, -10, 384, 192, 1, 5, 1));
		enermyList.add(new Enermy(this.gp, -1, -10, 480, 192, 1, 5, 1));

		enermyList.add(new Enermy(this.gp, 1010, 720, 634, 96, 1, 6, 1));
		enermyList.add(new Enermy(this.gp, 1010, 720, 720, 96, 1, 6, 1));
		enermyList.add(new Enermy(this.gp, 1010, 720, 672, 144, 1, 6, 1));
		enermyList.add(new Enermy(this.gp, 1010, 720, 634, 192, 1, 6, 1));
		enermyList.add(new Enermy(this.gp, 1010, 720, 720, 192, 1, 6, 1));
		
		enermyList.add(new Enermy(this.gp, -1, 720, 864, 96, 1, 4, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 960, 96, 1, 4, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 912, 144, 1, 4, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 864, 192, 1, 4, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 960, 192, 1, 4, 1));
		
		enermyList.add(new Enermy(this.gp, -1, 720, 864, 336, 1, 1, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 960, 336, 1, 1, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 912, 384, 1, 1, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 864, 432, 1, 1, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 960, 432, 1, 1, 1));

		enermyList.add(new Enermy(this.gp, -1, 720, 96, 336, 1, 7, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 192, 336, 1, 7, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 144, 384, 1, 7, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 96, 432, 1, 7, 1));
		enermyList.add(new Enermy(this.gp, -1, 720, 192, 432, 1, 7, 1));
		
	}
	
	public int getSize() {
		return enermyList.size();
	}
	
	public Enermy getEnermyFromIndex(int index) {
		return enermyList.get(index);
	}
}
