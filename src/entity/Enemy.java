package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Enemy extends Entity {
	private float xDes, yDes, x0, y0;
	private int type;
	private int hp;
	private int width, height;
	private float t; // biến t để bỏ vào phương trình đường cong
	private boolean changePosition; // dùng dể dịch chuyển con gà cho nhìn đỡ đơ
	private ArrayList<BufferedImage> animation; // animiation
	private int indexToLoadAnimation; // dùng để load ảnh động.
	private int numOfFrame;
	private Random rand;
	private boolean isIntersectBullet; // kiểm tra xem có bị dính đạn hay chưa
	private int num = 3; // dùng để đổi hướng di chuyển cho con boss

	private void initVariable() {
		width = (int) (gp.getTileSize() * 1.5);
		height = (int) (gp.getTileSize() * 1.5);
		isIntersectBullet = false;
		changePosition = false;
		rand = new Random();
		indexToLoadAnimation = 0;
		animation = new ArrayList<BufferedImage>();
		switch (type) {
		case 0:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/0.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			width = (int) (gp.getTileSize() * 2);
			height = (int) (gp.getTileSize());
			numOfFrame = 3;
			hp = 15;
			break;
		case 1:
			width = (int) (gp.getTileSize() * 1.5);
			height = (int) (gp.getTileSize());
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/1.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			numOfFrame = 2;
			hp = 1;
			break;
		case 2:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 2;
			numOfFrame = 1;
			break;
		case 3:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/3.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 2;
			numOfFrame = 1;
			break;
		case 4:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/4.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 2;
			numOfFrame = 1;
			break;
		case 5:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/5.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 4;
			numOfFrame = 1;
			break;
		case 6:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/6.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 5;
			numOfFrame = 2;
			break;
		case 7:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/7.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 5;
			numOfFrame = 1;
			break;
		case 8:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/8.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			hp = 6;
			numOfFrame = 2;
			break;
		case 9:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/9.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			width = (int) (gp.getTileSize() * 4);
			height = (int) (gp.getTileSize() * 3);
			hp = 10;
			numOfFrame = 1;
			break;
		case 10:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/10.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			width = (int) (gp.getTileSize() * 4);
			height = (int) (gp.getTileSize() * 4);
			hp = 25;
			numOfFrame = 4;
			break;

		case 11:
			try {
				image = ImageIO.read(getClass().getResourceAsStream("/image/enemy/egg2.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			numOfFrame = 1;
			break;
		}

		loadAnimation();
	}

	public Enemy(GamePanel gp, float x, float y, float speed, int type, int hp) {
		super(gp, x, y, speed);
		this.type = type;
		this.hp = hp;
		this.initVariable();
	}

	public Enemy(GamePanel gp, float x, float y, float xDes, float yDes, float speed, int type) {
		super(gp, x, y, speed);
		this.type = type;
		this.xDes = xDes;
		this.yDes = yDes;
		this.x0 = x;
		y0 = y;
		this.initVariable();
	}

	public Enemy(GamePanel gp, float x, float y, float xDes, float yDes, float speed, int type, int hp, float widthTile,
			float heightTile) {
		super(gp, x, y, speed);
		this.type = type;
		this.xDes = xDes;
		this.yDes = yDes;
		this.x0 = x;
		y0 = y;
		this.hp = hp;
		this.initVariable();
		width = (int) (gp.getTileSize() * widthTile);
		height = (int) (gp.getTileSize() * heightTile);
	}

	public void changeDirection(int direction) {
		switch (direction) {
		case 1: // left
			x -= speed; 
			break;
		case 2: // up left
			x -= speed;
			y += speed;
			break;
		case 3: // down left
			x -= speed;
			y -= speed;
			break;
		case 4: // down
			y -= speed;
			break;
		case 5: // up
			y += speed;
			break;
		case 6: // right
			x += speed;
			break;
		case 7: // up right
			x += speed;
			y += speed;
			break;
		case 8: // down right
			x += speed;
			y -= speed;
			break;
		}
	}

	public void randomNum() {
		int a[] = {1,2,5,6,7}; // 2 mảng dùng để thay đổi hướng di chuyển của con boss
		int b[] = {1,3,4,6,8};
		if (y < 0) {
			num = a[rand.nextInt(5)];
		} else if (y > 500) {
			num = b[rand.nextInt(5)];
		}
		if(x>=900) {
			num = rand.nextInt(5) + 1;
		} else if (x<=0) {
			num = rand.nextInt(6) + 3;
		}
	}
	
	@Override
	public void update(int wave) {
		if (t <= 1 && changePosition == false) {
			t += 0.003;
			x = x0 + (xDes - x0) * t;
			y = y0 + (yDes - y0) * t;
			if (t == 1)
				changePosition = true;
		} else {
			if (wave == 11) {
				speed = 7;
				randomNum();
				changeDirection(num);

			} else {
				int num = 0;
				num = rand.nextInt(8);
				if (num == 0) {
					x--;
				} else if (num == 1) {
					x++;
				} else if (num == 2) {
					y--;
				} else if (num == 3) {
					y++;
				}
			}

		}
		upDateWhenIntersect();
	}

	@Override
	public void draw(Graphics2D g2) {
		if (indexToLoadAnimation / 10 >= animation.size()) {
			indexToLoadAnimation = 0;
		}
		g2.drawImage(animation.get(indexToLoadAnimation / 10), (int) x - gp.getTileSize(), (int) y, width, height, null);
		indexToLoadAnimation++;
	}

	private void loadAnimation() {
		for (int i = 0; i < numOfFrame; i++)
			animation.add(image.getSubimage(0, i * image.getHeight() / numOfFrame, image.getWidth(),
					image.getHeight() / numOfFrame));
	}

	public Rectangle getEnemyBound() {
		if (type == 11) {
			return new Rectangle((int) x+25, (int) y + 10, width - 50, height - 20);
		} else
		return new Rectangle((int) x-10, (int) y + 10, width - 20, height - 20);
	}

	public void setIsIntersectBullet() {
		isIntersectBullet = true;
	}

	public void upDateWhenIntersect() { // khi trúng đạn thì sẽ cập nhật lại HP,...
		if (isIntersectBullet == true) {
			hp -= gp.getBulletDamge();
			isIntersectBullet = false;
			isIntersectBullet = false;
		}
	}

	public int getHP() {
		return hp;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}
	
	public int getType() {
		return type;
	}
}
