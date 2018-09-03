package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import mainGame.Handler;
import mainGame.gfx.Animation;
import mainGame.gfx.Assets;
import tiles.Tile;

public class Monster extends Creature {

	// Animation
	private Animation animWalkRight;
	private Animation animWalkLeft;

	private float spawnX;
	private long attackSpeed = 10; // Every "attackSpeed" frames try to attack.
									// 60 mean try to attack every second.
	private long attackTimeCounter; // when this var is equal to attackSpeed try
									// to attack.
	private long respawnTime = 4000;
	public boolean isAliveOn; // Check if the setAlive method already called

	public Monster(Handler handler, float pX, float pY, int width, int height) {
		super(handler, pX, pY, width, height);
		this.spawnX = pX;
		bounds.x = 36;
		bounds.y = 20;
		bounds.width = width - 60;
		bounds.height = height - 37;
		isAliveOn = false;
		animationInit();
		randMoveSpeed();
	}

	@Override
	public void tick() {
		// Animation
		animWalkRight.tick();
		animWalkLeft.tick();
		// Movement
		if (active) {
			attack();
			autoMove();
			move();

		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimFrame(), (int) (pX - handler.getGameCamera().getxOffset()),
				(int) (pY - handler.getGameCamera().getyOffset()), width, height, null);
	}

	// Animation section
	@Override
	protected void animationInit() {
		animWalkRight = new Animation(150, Assets.playerWalkRight);
		animWalkLeft = new Animation(150, Assets.playerWalkLeft);
	}

	protected BufferedImage getCurrentAnimFrame() {
		if (xMove < 0) {
			return animWalkLeft.getCurrentFrame();
		} else {
			return animWalkRight.getCurrentFrame();
		}
	}

	// Movement section
	private void autoMove() {
		sideChange(Tile.TILE_WIDTH, Tile.TILE_WIDTH);
		yMove = gravity;
		if (side == false) {
			xMove = +speed;
		} else {
			xMove = -speed;
		}
	}

	private void sideChange(float maxLeft, float maxRight) {
		// change the side that the monster move to
		// maxLeft and maxRight are the boundaries from the spawn
		if (pX <= spawnX - maxLeft && groundDetictor()) {
			side = false;
		} else if (pX >= spawnX + maxRight && groundDetictor()) {
			side = true;
		}
	}

	private void randMoveSpeed() {
		Random rnd = new Random();
		speed = DEFAULT_MIN_SPEED + rnd.nextFloat() * (DEFAULT_MAX_SPEED - DEFAULT_MIN_SPEED);
		if(((int) speed % 2) == 0){
			side = true;
		}
	}
	

	// Attack section
	private void attack() {
		if (attackTimeCounter == attackSpeed) {
			if (Math.abs(handler.getPlayer().getpX() + handler.getPlayer().getWidth() / 2 - (pX + getWidth() / 2)) < 20
					&& Math.abs(handler.getPlayer().getpY() - pY) < 20) {
				handler.getPlayer().hurt(attack);
			}
			attackTimeCounter = 0;
		} else {
			attackTimeCounter++;
		}

	}

	// Is alive section
	@Override
	public void die() {
		handler.getPlayer().raiseExp(100);
		this.active = false;
	}

	public boolean isAliveOn() {
		return isAliveOn;
	}

	public void setAliveOn(boolean isAliveOn) {
		this.isAliveOn = isAliveOn;
	}

	public void setAlive() {
		Thread aliveDelay = new Thread(new delayAlive());
		aliveDelay.start();
		health = DEFAULT_HEALTH;
		setActive(true);
		isAliveOn = false;
	}

	private class delayAlive implements Runnable {
		@Override
		public void run() {
			try {
				Thread.sleep(respawnTime);
			} catch (Exception e) {
				e.printStackTrace();
				new Thread(this).start();
				System.exit(0);

			}

		}

	}

}
