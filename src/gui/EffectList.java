package gui;

import java.awt.Graphics2D;
import java.util.ArrayList;
import main.GamePanel;

public class EffectList {
	private GamePanel gp;
	private ArrayList<Effect> effects = new ArrayList<Effect>();
	private int type;

	public EffectList(GamePanel gp) {
		this.gp = gp;
		initVariable();
	}

	private void initVariable() {
		effects = new ArrayList<>();
	}

	public void update() {
		if (gp.getIsSpawnItem()) {
			effects.add(new Effect((int) gp.getLastX()-15, (int) gp.getLastY()-25, type));
			gp.setIsSpawnItem();
		}
	}

	public void draw(Graphics2D g2) {
		int i = 0;
		while (i < effects.size()) {
			effects.get(i).draw(g2);
			
			if (effects.get(i).getIsEndOfEffect()) {
				effects.remove(i);
			} else {
				i++;				
			}
		}
	}
}
