package gui;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Effect {
	private float x, y;
	private ArrayList<BufferedImage> smoke; // hieu ung khoi
	private int indextoLoadSmokeAnimation;
	private boolean isEndOfEffect; // kiem tra co ket thuc hieu ung khoi chua

	private void initVariable() {
		smoke = new ArrayList<BufferedImage>();
		isEndOfEffect = false;
		indextoLoadSmokeAnimation = 0;
		
		BufferedImage smokeImg = null;
		try {
			smokeImg = ImageIO.read(getClass().getResourceAsStream("/image/gui/smoke.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				smoke.add(smokeImg.getSubimage(j * 205, i * 132, 205, 132));
			}
		}
	}

	public Effect(float x, float y, int type) {
		this.x = x;
		this.y = y;
		this.initVariable();
	}

	public void update() {
		
	}

	public void draw(Graphics2D g2) {
		if ((indextoLoadSmokeAnimation / 5) >= 8) {
			isEndOfEffect = true;
		} else {
			g2.drawImage(smoke.get(indextoLoadSmokeAnimation/5), (int) x - 48, (int) y+10, 80, 80, null);
			indextoLoadSmokeAnimation++;
		}
	}
	
	public boolean getIsEndOfEffect() {
		return isEndOfEffect;
	}
}
