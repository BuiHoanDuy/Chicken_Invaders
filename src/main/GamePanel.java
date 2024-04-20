package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import controller.keyHandler;
import controller.mouseController;
import entity.BulletList;
import entity.Enermy;
import entity.EnermyList;
import entity.Player;
import gui.Sound;

public class GamePanel extends JPanel implements Runnable {
	// screen setting
	final int originalTileSize = 16; // 16x16
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 21;
	final int maxScreenRow = 15;

	final int screenWidth = tileSize * maxScreenCol; // 1008px
	final int screenHeight = tileSize * maxScreenRow; // 720px

	int fps = 90;

	Thread gameThread; // fps
	keyHandler keyH = new keyHandler();
	mouseController Mouse = new mouseController(this);
	Sound sound = new Sound();

	boolean isShooting = false; // có đang nhấn chuột hay không ?
	boolean isRightClicked = false; // có nhấn  chuột phải hay không?
	int damage = 1;
	int bulletType;

	Player player = new Player(this, 500, 570, 10);
	BulletList bulletList = new BulletList(this);
	EnermyList enermyList = new EnermyList(this);

	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);

		this.addMouseListener(Mouse);
		this.addMouseMotionListener(Mouse);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		playMusic(0);
	}

	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {

		// --------- set FPS ------------
		double drawInterval = 1000000000 / fps; // 0,01666seconds
		double nextDrawTime = System.nanoTime() + drawInterval;
		while (gameThread != null) {
			// update:
			update();

			// draw:
			repaint();

			try {
				double remainingTime = nextDrawTime - System.nanoTime();
				remainingTime = remainingTime / 1000000;

				if (remainingTime < 0) {
					remainingTime = 0;
				}

				Thread.sleep((long) remainingTime);

				nextDrawTime += drawInterval;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// -----------------------------

	}

	public void update() {
		// Put Update function here
		player.update();
		bulletList.update();
		enermyList.update();

		try {
			checkBulletIntersectEnermy();			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public void paintComponent(Graphics g) {
		// Put Draw Function here
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		drawBackground(g2);
		player.draw(g2);
		bulletList.draw(g2);
		enermyList.draw(g2);

		g2.dispose();
	}

	public void playMusic(int i) {
//		sound.setFile(i);
//		sound.play();
//		sound.loop();
	}

	public void stopMusic() {
//		sound.stop();
	}

	public void playSE(int i) { // sound effect
//		sound.setFile(i);
//		sound.play();
	}

	public void drawBackground(Graphics2D g2) {
		BufferedImage background = null;
		try {
			background = ImageIO.read(getClass().getResourceAsStream("/image/background_image/background/5.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		g2.drawImage(background, 0, 0, screenWidth, screenHeight, null);

	}

	public void setPlayerLocation(float x, float y) { // cài đặt tọa độ máy bay
		player.setLocation(x, y);
	}

	public float getPlayerX() {
		return player.getX();
	}

	public float getPlayerY() {
		return player.getY();
	}

	public void setIsShooting(boolean status) {
		isShooting = status;
	}

	public boolean getIsRightClicked() {
		return isRightClicked;
	}

	public void setIsRightClicked(boolean status) {
		isRightClicked = status;
	}

	public boolean getIsShooting() {
		return isShooting;
	}
	
	public void checkBulletIntersectEnermy() { // Kiểm tra xem đạn có chạm vào địch chưa
		for (int i = 0; i < bulletList.getSize(); i++) {
			for (int j = 0; j < enermyList.getSize(); j++) {
				if (bulletList.getSize() == 0 || enermyList.getSize() == 0)
					return;
				if (bulletList.getBulletFromIndex(i).getBulletBound().intersects(enermyList.getEnermyFromIndex(j).getEnermyBound())) {
					bulletList.getBulletFromIndex(i).setIsIntersectEnermy(); // đạn chạm địch
					enermyList.getEnermyFromIndex(j).setIsIntersectBullet(); // địch chạm đạn
				}
			}
		}
	}

	public int getBulletDamge() {
		return damage;
	}

	public void setBulletDamge(int damage) { // dùng trong hàm bullet.initVariable
		this.damage = damage;
	}

	public void setBulletType(int type) { // dùng trong hàm bullet.initVariable
		this.bulletType = type;
	}
	
	public void decreaseUltiShoot () { // Giảm số lượng ulti
    	player.decreaseUltiShoot();
    }
    
    public void increaseUltiShoot() { // tăng số lượng ulti
    	player.increaseUltiShoot();
    }
    
    public int getUltiShoot() { // lấy số lượng ulti
    	return player.getUltiShoot();
    }
}