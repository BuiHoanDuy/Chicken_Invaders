package entity;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player extends Entity {
	private int score;
	private int hp;
	private int ultiShoot; // số lượng ulti còn lại
	private boolean isIntersectEnemy; // có chạm địch hay không
	private boolean isIntersectItem;
	private boolean isIntersectGift;
	private BufferedImage bang; // nổ
	private BufferedImage shield;
	private Boolean isShielded = false;
	private int i = 0, j = 0;

	private void initVariable() {
		score = 0;
		hp = 3;
		ultiShoot = 3; // số lượng ulti
		isIntersectEnemy = false;
		isIntersectItem = false;
		isIntersectGift = false;
		try {
			bang = ImageIO.read(getClass().getResourceAsStream("/image/player/bang.png"));
			shield = ImageIO.read(getClass().getResourceAsStream("/image/player/protection.png"));
			image = ImageIO.read(getClass().getResourceAsStream("/image/player/ship.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Player(GamePanel gp, float x, float y, float speed) {
		super(gp, x, y, speed);
		this.initVariable();
	}

	@Override
	public void update() {
		if (isIntersectEnemy) {
			upDateWhenIntersectEnemy();
		}
	}

	@Override
	public void draw(Graphics2D g2) {
		BufferedImage animation = null;
		g2.drawImage(image, (int) x - gp.getTileSize(), (int) y, gp.getTileSize() * 2, gp.getTileSize() * 2, null);
		if (isIntersectEnemy) {
			
			if (j<5) j++;
			else {
				j=0; i++;
			}
			if (i>=5) i = 0;
			try {
				animation = bang.getSubimage(j * (64), i * (64), 64, 64);				
			} catch (Exception e) {
			}
			g2.drawImage(animation, (int) x - 50, (int) y - 20, 100, 100, null);
		}

		if(isShielded) {
			g2.drawImage(shield, (int) x - 50, (int) y - 20, 100, 100, null);
		}
	}

	public void setLocation(float x, float y) { // cài đặt tọa độ x, y
		if (x < 1008 && x > 0) {
			this.x = x;
		}
		if (y > 0 && y < 700) {
			this.y = y;
		}
	}

	public Rectangle getPlayerBound() {
		return new Rectangle((int) x + 20, (int) y, gp.getTileSize(), gp.getTileSize() - 20);
	}

	public void upDateWhenIntersectEnemy() {
		// hp-- viết ở setIsIntersectEnemy;
		moveToStartPosition();
		try {
            // Khởi tạo đối tượng Robot
            Robot robot = new Robot();

            // Di chuyển con trỏ chuột đến vị trí mong muốn (x, y)
            int x = (int) this.x + 255; // Tọa độ x
            int y = (int) this.y + 48; // Tọa độ y
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
	}
	
	public void ForceTheMouse() { //Di chuyển lại con trỏ khi nhấn nút conitnue hoặc play
		setPreStartPosition();
		
		do {
			moveToStartPosition();
		try {
            // Khởi tạo đối tượng Robot
            Robot robot = new Robot();

            // Di chuyển con trỏ chuột đến vị trí mong muốn (x, y)
            int x = (int) this.x + 255; // Tọa độ x
            int y = (int) this.y + 48; // Tọa độ y
            robot.mouseMove(x, y);
        } catch (AWTException e) {
            e.printStackTrace();
        }
		} while(x != 500 && y != 570);
		
	}
	
	public void setPreStartPosition() {
		x = 500; y = 630;
	}

	public void moveToStartPosition() { // di chuyển về vị trí xuất phát
		if (x > 500 && y > 570) {
			x--; y --;
		} else if (x < 500 && y > 570) {
			x ++; y--;
		} else if (x < 500 && y < 570) {
			x++; y++;
		} else if (x > 500 && y < 570 ) {
			x--; y++;
		} else if (x == 500 && y > 570) {
			y--;
		}else if (x == 500 && y < 570) {
			y++;
		}else if (x > 500 && y == 570) {
			x--;
		}else if (x < 500 && y == 570) {
			x++;
		}
		else isIntersectEnemy = false;
	}

	public void setIsIntersectEnemy() {
		if (!isIntersectEnemy)
		hp--;
		isIntersectEnemy = true;
	}

	public void setIsIntersectGift() {
		isIntersectGift = true;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void decreaseUltiShoot() {
		ultiShoot--;
	}

	public void increaseUltiShoot() {
		ultiShoot++;
	}

	public int getUltiShoot() {
		return ultiShoot;
	}

	public boolean getIsIntersectEnemy() {
		return isIntersectEnemy;
	}

	public int getHp() {
		return hp;
	}

	public int getScore() {
		return score;
	}

	public void upScore(int m_score) {
		score += m_score;
	}
}
