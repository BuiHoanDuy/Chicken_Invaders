package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import gui.*;
import controller.*;
import entity.Player;
import entity.GiftList;
import entity.EnemyList;
import entity.BulletList;
import entity.ChickenItemList;


enum STAGE { START_MENU, HIGH_SCORE, SETTING, GAME_PLAY, GAME_PAUSE, GAME_OVER, GAME_END};

public class GamePanel extends JPanel implements Runnable {
	// screen setting
	final int originalTileSize = 16; // 16x16
	final int scale = 3;

	public final int tileSize = originalTileSize * scale; // 48x48
	final int maxScreenCol = 21;
	final int maxScreenRow = 15;

	final int screenWidth = tileSize * maxScreenCol; // 1008px
	final int screenHeight = tileSize * maxScreenRow; // 720px

	private int fps;
	private int fpsIndex;
	private int[] fpsArr = {60, 90, 120};

	Thread gameThread; // fps


	// gui
	private Boolean audio;
	private Boolean hiddenCursor;
	private StartMenu startMenu;
	private SettingMenu settingMenu;
	private PauseMenu pauseMenu;
	private HighScore highScore;
	Sound sound;


	// controlers
	private mouseController Mouse;
	private keyHandler keyboard;


	// game stage
	private STAGE stage;

	// mouse position
	private int mouseX, mouseY;


	private int wave;
	private Boolean isChangeWave;
	private Background background;


	boolean isShooting = false; // có đang nhấn chuột hay không ?
	boolean isRightClicked = false; // có nhấn  chuột phải hay không?
	int damage = 1;
	int bulletType;

	// game entity

	private int score;
	private Player player;
	private BulletList bulletList;
	private EnemyList enemyList;
	private GiftList giftList;
	// private ChickenItemList items;

	private void initVar() {
		mouseX = 0;
		mouseY = 0;
		stage = STAGE.START_MENU;
		wave = 1;
		fpsIndex = 1;
		fps = fpsArr[fpsIndex];
		isChangeWave = false;
		score = 0;
		bulletType = 2;
		audio = true;
		hiddenCursor = false;
	}
	
	private void initControllers() {
		Mouse = new mouseController(this);
		keyboard = new keyHandler(this);
	}

	private void initSetting() {
		settingMenu = new SettingMenu(this);
	}

	private void initPause() {
		pauseMenu = new PauseMenu(this);
	}

	private void initHighScore() {
		highScore = new HighScore();
	}
	

	private void initGui() {
		startMenu = new StartMenu(this);
		background = new Background(wave);
	}

	private void initEntity() {
		player = new Player(this, 500, 570, 10);
		bulletList = new BulletList(this);
		giftList = new GiftList(this);
		enemyList = new EnemyList(this);
	}

	public GamePanel() {
		this.initControllers();
		this.initVar();
		this.initGui();
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);

		this.addMouseListener(Mouse);
		this.addMouseMotionListener(Mouse);
		this.addKeyListener(keyboard);
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


	private void updateGamePlay() {
		if(isChangeWave) {
			background.update(wave);
			isChangeWave = false;
		}
		player.update();
		bulletList.update();
		enemyList.update();

		try {
			checkBulletIntersectEnermy();	
			checkPlayerIntersectEnermy();
			checkPlayerIntersectGift();
		} catch (Exception e) {
			// TODO: handle exception
		}
		 giftList.update();
//		items.update(500, 200);
	}

	private void updateCursor() {
		Cursor cursor;
		if(hiddenCursor) cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new java.awt.Point(), "cursor");
		else cursor = Cursor.getDefaultCursor();
		this.setCursor(cursor);
	}

	public void update() {
		// Put Update function here
		updateCursor();
		if(stage == STAGE.GAME_PLAY)
			updateGamePlay();
	}


	private void drawGamePlay(Graphics g) {
		// draw background
		background.draw(g);

		// draw game entity
		Graphics2D g2 = (Graphics2D) g;
		player.draw(g2);
		bulletList.draw(g2);
		enemyList.draw(g2);
		giftList.draw(g2);

		g2.dispose();
	}

	public void paintComponent(Graphics g) {
		// Put Draw Function here
		super.paintComponent(g);
		switch (stage) {
			case START_MENU:
				startMenu.draw(g);
				hiddenCursor = false;
				break;
			case HIGH_SCORE:
				initHighScore();
				highScore.draw(g);
				hiddenCursor = false;
				break;
			case SETTING:
				initSetting();
				settingMenu.draw(g);
				hiddenCursor = false;
				break;
			case GAME_PLAY:
				drawGamePlay(g);
				hiddenCursor = true;
				break;
			case GAME_PAUSE:
				initPause();
				pauseMenu.draw(g);
				hiddenCursor = false;
				break;
			default:
				break;
		}
	}

	private void startMenuAction() {
		int option = startMenu.update(mouseX, mouseY);
		switch (option) {
			case 1:
				System.out.println("play");
				initEntity();
				stage = STAGE.GAME_PLAY;
				break;
			case 2:
				System.out.println("high score");
				stage = STAGE.HIGH_SCORE;
				break;
			case 3:
				System.out.println("setting");
				stage = STAGE.SETTING;
				break;
			case 4:
				System.out.println("exit");
				System.exit(0);
				break;
			default:
				break;
		}
	}


	private void settingMenuAction() {
		int option = settingMenu.update(mouseX, mouseY);
		switch (option) {
			case 1:
				System.out.println("continue");
				break;
			case 2:
				System.out.println("audio");
				break;
			case 3:
				System.out.println("fps");
				break;
			case 4:
				System.out.println("return");
				stage = STAGE.START_MENU;
				break;
			case 5:
			case 6:
				System.out.println("audio change");
				setAudio();
				break;
			case 7:
				System.out.println("fps change");
				if(fpsIndex > 0) fpsIndex--;
				else fpsIndex = fpsArr.length - 1;
				fps = fpsArr[fpsIndex];
				break;
				case 8:
				System.out.println("fps change");
				if(fpsIndex < fpsArr.length - 1) fpsIndex++;
				else fpsIndex = 0;
				fps = fpsArr[fpsIndex];
				break;
			default:
				break;
		}
	}


	private void highScoreAction() {
		if(highScore.update(mouseX, mouseY)) {
			stage = STAGE.START_MENU;
		}
	}

	private void pauseMenuAction() {
		int option = pauseMenu.update(mouseX, mouseY);
		switch (option) {
			case 1:
				System.out.println("continue");
				stage = STAGE.GAME_PLAY;
				hiddenCursor = true;
				break;
			case 2:
				System.out.println("audio");
				break;
			case 3:
				System.out.println("fps");
				break;
			case 4:
				System.out.println("return");
				stage = STAGE.START_MENU;
				break;
			case 5:
			case 6:
				System.out.println("audio change");
				setAudio();
				break;
			case 7:
				System.out.println("fps change");
				if(fpsIndex > 0) fpsIndex--;
				else fpsIndex = fpsArr.length - 1;
				fps = fpsArr[fpsIndex];
				break;
				case 8:
				System.out.println("fps change");
				if(fpsIndex < fpsArr.length - 1) fpsIndex++;
				else fpsIndex = 0;
				fps = fpsArr[fpsIndex];
				break;
			default:
				break;
		}
	}


	public void updateMouseClick(int x, int y) {
		// Xử lý sự kiện khi click chuột và lưu tọa độ
		mouseX = x;
		mouseY = y;
	
		// Cập nhật trạng thái khi click chuột
	
		// In ra tọa độ chuột
		System.out.println("Mouse clicked at: " + mouseX + ", " + mouseY);
		switch(stage) {
			case START_MENU:
				startMenuAction();
				break;
			case HIGH_SCORE:
				highScoreAction();
				break;
			case SETTING:
				settingMenuAction();
				break;
			case GAME_PAUSE:
				pauseMenuAction();
				break;
		}
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
	
	public boolean getIsIntersectEnermy() { //kiểm tra xem có đang nổ hay không để không cho máy bay di chuyển.
		return player.getIsIntersectEnermy();
	}
	
	public void checkBulletIntersectEnermy() { // Kiểm tra xem đạn có chạm vào địch chưa
		for (int i = 0; i < bulletList.getSize(); i++) {
			for (int j = 0; j < enemyList.getSize(); j++) {
				if (bulletList.getSize() == 0 || enemyList.getSize() == 0)
					return;
				if (bulletList.getBulletFromIndex(i).getBulletBound().intersects(enemyList.getEnermyFromIndex(j).getEnermyBound())) {
					bulletList.getBulletFromIndex(i).setIsIntersectEnermy(); // đạn chạm địch
					enemyList.getEnermyFromIndex(j).setIsIntersectBullet(); // địch chạm đạn
				}
			}
		}
	}
	
	public void checkPlayerIntersectEnermy() { // Kiểm tra xem máy bay có chạm vào địch chưa
			for (int j = 0; j < enemyList.getSize(); j++) {
				if (enemyList.getSize() == 0)
					return;
				if (player.getPlayerBound().intersects(enemyList.getEnermyFromIndex(j).getEnermyBound())) {
					player.setIsIntersectEnermy(); // máy bay chạm địch
					bulletList.decreaseLevel(); // giảm cấp đạn về 1
				}
		}
	}

	public void checkPlayerIntersectGift() { // Kiểm tra xem máy bay có chạm vào quà chưa
		for (int j = 0; j < giftList.getSize(); j++) {
			if (giftList.getSize() == 0)
				return;
			if (player.getPlayerBound().intersects(giftList.getGiftFromIndex(j).getGiftBound())) {
				player.setIsIntersectGift(); // máy bay chạm quà
				giftList.getGiftFromIndex(j).upDateWhenIntersectPlayer();
				bulletList.setMomentType(giftList.getGiftFromIndex(j).getType());
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

	public void pauseGame() {
		stage = STAGE.GAME_PAUSE;
	}
    
	public Boolean getAudio() {
		return audio;
	}

	public void setAudio() {
		audio = !audio;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int newFps) {
		fps = newFps;
	}
    
}