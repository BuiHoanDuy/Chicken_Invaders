package main;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.swing.JPanel;

import gui.*;
import controller.*;
import entity.Player;
import entity.GiftList;
import entity.EnemyList;
import entity.BulletList;
import entity.ChichkenItem;
import entity.ChickenBulletList;
import entity.ChickenItemList;


enum STAGE { START_MENU, HIGH_SCORE, SETTING, GAME_PLAY, GAME_PAUSE, GAME_OVER, GAME_END};

public class GamePanel extends JPanel implements Runnable {
	// screen setting
	private final int originalTileSize = 16; // 16x16
	private final int scale = 3;

	private  final int tileSize = originalTileSize * scale; // 48x48
	private final int maxScreenCol = 21;
	private final int maxScreenRow = 15;

	private final int screenWidth = tileSize * maxScreenCol; // 1008px
	private final int screenHeight = tileSize * maxScreenRow; // 720px

	private int fps;
	private int fpsIndex;
	private int[] fpsArr = {60, 90, 120};

	private Thread gameThread; // fps


	// gui
	private Boolean audio;
	private Boolean hiddenCursor;
	private StartMenu startMenu;
	private SettingMenu settingMenu;
	private PauseMenu pauseMenu;
	private HighScore highScore;
	private GameOver gameOver;
	private GameEnd gameEnd;
	private Sound sound;
	private GuiText guiText;
	private Background background;
	

	// controlers
	private mouseController Mouse;
	private keyHandler keyboard;


	// game stage
	private STAGE stage;

	// mouse position
	private int mouseX, mouseY;


	private int wave;
	private Boolean isChangeWave;

	private Random rand;

	// game variables
	private float xLastPos;			// lưu vị trí của con gà vừa mới chết
	private float yLastPos;
	private Boolean isSpawnItem;

	private int lastType;
	
	private float xPos;				// lưu vị trí của con gà còn sống
	private float yPos;
	private Boolean isSpawnCB;
	private int cType;
	private int cWidth;
	private int cHeight;

	private Boolean musicChanged;
	private Boolean victorySound;

	private boolean isShooting = false; // có đang nhấn chuột hay không ?
	private boolean isRightClicked = false; // có nhấn  chuột phải hay không?
	private int damage = 1;
	private int bulletType;

	private Boolean endGameSound;

	// game entity
	private Player player;
	private BulletList bulletList;
	private EnemyList enemyList;
	private GiftList giftList;
	private ChickenBulletList chickenBulletList;
	private ChickenItemList chickenItemList;
	private EffectList effectList;

	private void initVar() {
		mouseX = 0;
		mouseY = 0;
		// stage = STAGE.GAME_OVER;
		stage = STAGE.START_MENU;
		wave = 0;
		fpsIndex = 1;
		fps = fpsArr[fpsIndex];
		isChangeWave = true;
		bulletType = 2;
		audio = true;
		hiddenCursor = false;
		isSpawnItem = false;
		isSpawnCB = false;
		endGameSound = false;
		musicChanged = false;
		victorySound = false;
	}
	
	private void initControllers() {
		// init controllers
		Mouse = new mouseController(this);
		keyboard = new keyHandler(this);
	}


	// init game stage's menu
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
		startMenu = new StartMenu();
		sound = new Sound();
	}

	private void initPlayer() {
		player = new Player(this, 500, 570, 10);
	}

	private void initEntity() {
		// init game entity
		bulletList = new BulletList(this);
		giftList = new GiftList(this);
		enemyList = new EnemyList(this);
		chickenBulletList = new ChickenBulletList(this);
		chickenItemList = new ChickenItemList(this);
		
		// gameplay gui
		background = new Background(wave);
		// các giá trị mặc định khi vừa vào game
		guiText = new GuiText(3, 1, 3, 0);
		effectList = new EffectList(this);
	}

	public GamePanel() {
		this.initControllers();
		this.initVar();
		this.initGui();
		this.initPlayer();
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
			drawInterval = 1000000000 / fps; // 0,01666seconds
			nextDrawTime = System.nanoTime() + drawInterval;
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
	}


	private void updateGamePlay() {
		// update background if the wave is change
		if(isChangeWave) {
			wave = enemyList.getWave();
			background.update(wave);
			guiText.updateWave(wave);
			isChangeWave = false;
		}

		// update gui
		guiText.update(player.getHp(), bulletList.getLevel(), player.getUltiShoot(), player.getScore());

		// update game entity
		player.update();
		bulletList.update();
		enemyList.update();
		giftList.update();
		chickenBulletList.update();
		chickenItemList.update();
		effectList.update();

		// entity collision update
		try {
			checkBulletIntersectEnemy();	
			checkPlayerIntersectEnemy();
			checkPlayerIntersectGift();
			checkPlayerIntersectItem();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void updateCursor() {
		// update cursor status (display none | block)
		Cursor cursor;
		if(hiddenCursor) cursor = Toolkit.getDefaultToolkit().createCustomCursor(Toolkit.getDefaultToolkit().getImage(""), new java.awt.Point(), "cursor");
		else cursor = Cursor.getDefaultCursor();
		this.setCursor(cursor);
	}

	public void update() {
		// Put Update function here
		if(player.getHp() == 0) {
			stage = STAGE.GAME_OVER;
		}

		updateBgSound();
		updateCursor();
		if(stage == STAGE.GAME_PLAY) {
			updateGamePlay();
			if(wave > 11) {
				stage = STAGE.GAME_END;
			}
		}
	}

	private void updateBgSound() {
		if(stage == STAGE.GAME_OVER && !endGameSound) {
			playSE(21);
			endGameSound = !endGameSound;
		}

		if(wave > 5 && !musicChanged) {
			playMusic(1);
			musicChanged = !musicChanged;
		}

		if(stage == STAGE.GAME_END && !victorySound) {
			playMusic(22);
			victorySound = !victorySound;
		}
	}


	private void drawGamePlay(Graphics g) {
		// draw background
		background.draw(g);

		// draw game entity
		Graphics2D g2 = (Graphics2D) g;
		giftList.draw(g2);
		bulletList.draw(g2);
		enemyList.draw(g2);
		chickenBulletList.draw(g2);
		chickenItemList.draw(g2);
		effectList.draw(g2);
		player.draw(g2);
		guiText.draw(g);
		
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
			case GAME_OVER:
				updateScore();
				gameOver = new GameOver(player.getScore());
				gameOver.draw(g);
				hiddenCursor = false;
				break;
			case GAME_END:
				gameEnd = new GameEnd();
				gameEnd.draw(g);
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
				initPlayer();
				initEntity();
				wave = 0;
				isChangeWave = true;
				stage = STAGE.GAME_PLAY;
				player.ForceTheMouse();
				break;
			case 2:
				stage = STAGE.HIGH_SCORE;
				break;
			case 3:
				stage = STAGE.SETTING;
				break;
			case 4:
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
				stage = STAGE.START_MENU;
				break;
			case 2:
			case 3:
				audio = !audio;
				setMusic();
				break;
			case 4:
				if(fpsIndex > 0) fpsIndex--;
				else fpsIndex = fpsArr.length - 1;
				fps = fpsArr[fpsIndex];
				break;
			case 5:
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
				stage = STAGE.GAME_PLAY;
				hiddenCursor = true;
				player.ForceTheMouse();
				break;
			case 2:
				updateScore();
				stage = STAGE.START_MENU;
				break;
			case 3:
			case 4:
				audio = !audio;
				setMusic();
				break;
			case 5:
				if(fpsIndex > 0) fpsIndex--;
				else fpsIndex = fpsArr.length - 1;
				fps = fpsArr[fpsIndex];
				break;
			case 6:
				if(fpsIndex < fpsArr.length - 1) fpsIndex++;
				else fpsIndex = 0;
				fps = fpsArr[fpsIndex];
				break;
			default:
				break;
		}
	}

	private void gameOverMenu() {
		int option = gameOver.update(mouseX, mouseY);
		switch (option) {
			case 1:
				initPlayer();
				initEntity();
				wave = 0;
				isChangeWave = true;
				stage = STAGE.GAME_PLAY;
				player.ForceTheMouse();
				break;
			case 2:
				initPlayer();
				stage = STAGE.START_MENU;
				break;
			default:
				break;
		}
	}


	private void gameEndAction() {
		if(gameEnd.update(mouseX, mouseY)) {
			stage = STAGE.START_MENU;
		}
	}

	public void updateMouseClick(int x, int y) {
		// Xử lý sự kiện khi click chuột và lưu tọa độ
		mouseX = x;
		mouseY = y;
	
		// In ra tọa độ chuột
		System.out.println(mouseX + " " + mouseY);
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
			case GAME_OVER:
				gameOverMenu();
				break;
			case GAME_END:
			 	gameEndAction();
				break;
			default:
				break;
		}
	}

	public void playMusic(int i) {
		sound.stop();
		sound.setFile(true, i);
		sound.play(true);
		sound.loop();
	}

	public void setMusic() {
		if(audio) sound.on();
		else sound.off();
	}

	public void playSE(int i) { // sound effect
		sound.setFile(false, i);
		sound.play(false);
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
	
	public boolean getIsIntersectEnemy() { //kiểm tra xem có đang nổ hay không để không cho máy bay di chuyển.
		return player.getIsIntersectEnemy();
	}
	
	public void checkBulletIntersectEnemy() { // Kiểm tra xem đạn có chạm vào địch chưa
		for (int i = 0; i < bulletList.getSize(); i++) {
			for (int j = 0; j < enemyList.getSize(); j++) {
				if (bulletList.getSize() == 0 || enemyList.getSize() == 0) 
					return;
				if (bulletList.getBulletFromIndex(i).getBulletBound().intersects(enemyList.getEnemyFromIndex(j).getEnemyBound())) {
					bulletList.getBulletFromIndex(i).setIsIntersectEnemy(); // đạn chạm địch
					enemyList.getEnemyFromIndex(j).setIsIntersectBullet(); // địch chạm đạn
					switch(enemyList.getEnemyFromIndex(j).getType()) {
						case 0:
							playSE(18);
							break;
						case 11:
							playSE(19);
							break;
						default:
							playSE(rand.nextInt(4)+6);
							break;
					}
				}
			}
		}
	}
	
	public void checkPlayerIntersectEnemy() { // Kiểm tra xem máy bay có chạm vào địch chưa
		for (int i = 0; i < enemyList.getSize(); i++) {
			if (enemyList.getSize() == 0)
				break;
			if (player.getPlayerBound().intersects(enemyList.getEnemyFromIndex(i).getEnemyBound())) {
				// playSE(15);
				playSE(20);
				player.setPreStartPosition(); // cho máy bay về vị trí gần xuất phát
				player.setIsIntersectEnemy(); // máy bay chạm địch
				bulletList.decreaseLevel(); // giảm cấp đạn về 1
			}
		}

		for (int i = 0; i < chickenBulletList.getSize(); i++) {
			if (chickenBulletList.getSize() == 0)
				return;
			if (!chickenBulletList.getCBFromIndex(i).onTheGround() && player.getPlayerBound().intersects(chickenBulletList.getCBFromIndex(i).getCBBound())) {
				player.setPreStartPosition(); // cho máy bay về vị trí gần xuất phát
				player.setIsIntersectEnemy(); // máy bay chạm đạn của địch
				playSE(20);
				chickenBulletList.remove(i);
				bulletList.decreaseLevel(); // giảm cấp đạn về 1
			}
		}
	}

	public void checkPlayerIntersectGift() { // Kiểm tra xem máy bay có chạm vào quà chưa
		for (int i = 0; i < giftList.getSize(); i++) {
			if (giftList.getSize() == 0)
				return;
			if (player.getPlayerBound().intersects(giftList.getGiftFromIndex(i).getGiftBound())) {
				playSE(2);
				player.setIsIntersectGift(); // máy bay chạm quà
				giftList.getGiftFromIndex(i).upDateWhenIntersectPlayer();
				bulletList.setMomentType(giftList.getGiftFromIndex(i).getType());
			}
		}
	}

	public void checkPlayerIntersectItem() { // Kiểm tra xem máy bay có chạm vào item chưa
		for (int i = 0; i < chickenItemList.getSize(); i++) {
			if (chickenItemList.getSize() == 0)
				return;
			ChichkenItem item = chickenItemList.getItemFromIndex(i);
			if (player.getPlayerBound().intersects(item.getItemBound())) {
				playSE(2);
				player.upScore(item.getType()); // cộng điểm cho player với số điểm tương ứng với loại quà
				chickenItemList.remove(i);
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
	
	public void changeWave() {
		isChangeWave = true;
		System.out.println(wave);
	}

	public void pauseGame() {
		stage = STAGE.GAME_PAUSE;
	}
    
	public Boolean getAudio() {
		return audio;
	}

	public int getFps() {
		return fps;
	}

	public void setFps(int newFps) {
		fps = newFps;
	}

	public void setLastPos(float x, float y) {
		this.xLastPos = x;
		this.yLastPos = y;
	}

	public float getLastX() {
		return xLastPos;
	}

	public float getLastY() {
		return yLastPos;
	}

	public void setIsSpawnItem() {
		isSpawnItem = !isSpawnItem;
	}

	public Boolean getIsSpawnItem() {
		return isSpawnItem;
	}

	public void setChickenBulletPos(float x, float y) {
		this.xPos = x;
		this.yPos = y;
	}

	public float getXPos() {
		return xPos;
	}

	public float getYPos() {
		return yPos;
	}

	public void setIsSpawnCB() {
		isSpawnCB = !isSpawnCB;
	}

	public Boolean getIsSpawnCB() {
		return isSpawnCB;
	}

	public void setCType(int type) {
		cType = type;
	}

	public int getCType() {
		return cType;
	}

	public void setCWidth(int cWidth, int cHeight) {
		this.cWidth = cWidth;
		this.cHeight = cHeight;
	}

	public int getCWidth() {
		return cWidth;
	}

	public int getCHeight() {
		return cHeight;
	}
	
	public int getTileSize() {
		return tileSize;
	}

	public void setLastType(int lastType) {
		this.lastType = lastType;
	}
	
	public int getLastType() {
		return lastType;
	}
	
	private void updateScore() {
		int currentScore = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("save/score.txt"))) {
			String s;
            while ((s = br.readLine()) != null) {
				currentScore = Integer.parseInt(s);
			}
        } catch (IOException e) {
			e.printStackTrace();
        }

		int newScore = player.getScore();
		if(currentScore < newScore) {
			try (BufferedWriter bw = new BufferedWriter(new FileWriter("save/score.txt"))) {
				bw.write(Integer.toString(newScore));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}