package entities.creatures;

import java.awt.Color;
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
	private Animation animDeadRight;
	private Animation animDeadLeft;

	private float spawnX;
	private long attackSpeed = 10; // Every "attackSpeed" frames try to attack.
									// 60 mean try to attack every second.
	private long attackTimeCounter; // when this var is equal to attackSpeed try
									// to attack.

	public Monster(Handler handler, float pX, float pY, int width, int height) {
		super(handler, pX, pY, width, height);
		this.spawnX = pX;
		bounds.x = 36;
		bounds.y = 20;
		bounds.width = width - 60;
		bounds.height = height - 37;
		animationInit();
		randMoveSpeed();
	}

	@Override
	public void tick() {
		// Animation
		animWalkRight.tick();
		animWalkLeft.tick();
		if (health <= 0) {
			animDeadLeft.tick();
			animDeadRight.tick();
		}
		die();
		// Movement
		if (health > 0) {
			attack();
			autoMove();
			move();
		}
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimFrame(), (int) (pX - handler.getGameCamera().getxOffset()),
				(int) (pY - handler.getGameCamera().getyOffset()), width, height, null);
		if(health < 100 && health > 0){
			// show the health indicator only if the monster has been attacked 
			healthIndicator(g);	
		}
	}

	// Animation section
	public void healthIndicator(Graphics g){
		 g.setColor(Color.red);
		 g.fillRect((int) (pX + bounds.x - handler.getGameCamera().getxOffset()),
		 (int) (pY - 17 + bounds.y - handler.getGameCamera().getyOffset()),
		 (health * 100)/DEFAULT_HEALTH, 5);
	}
	
	@Override
	protected void animationInit() {
		animWalkRight = new Animation(150, false, Assets.playerWalkRight);
		animWalkLeft = new Animation(150, false, Assets.playerWalkLeft);
		animDeadRight = new Animation(100, true, Assets.playerDeadRight);
		animDeadLeft = new Animation(100, true, Assets.playerDeadLeft);
	}

	protected BufferedImage getCurrentAnimFrame() {
		if (xMove < 0 && health > 0) {
			return animWalkLeft.getCurrentFrame();
		} else if (xMove > 0 && health > 0) {
			return animWalkRight.getCurrentFrame();
		} else {
			if (side == false) {
				return animDeadRight.getCurrentFrame();
			} else {
				return animDeadLeft.getCurrentFrame();
			}
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
		if (((int) speed % 2) == 0) {
			side = true;
		}
	}

	// Attack section
	private void attack() {
		if (attackTimeCounter == attackSpeed) {
			if (Math.abs(handler.getPlayer().getpX() + handler.getPlayer().getWidth() / 2 - (pX + getWidth() / 2)) < 30
					&& Math.abs(handler.getPlayer().getpY() - pY) < 20) {
				handler.getPlayer().hurt(attack);
			}
			attackTimeCounter = 0;
		} else {
			attackTimeCounter++;
		}

	}

	@Override
	public void die() {
		if (health <= 0) {
			// set active = false only after the animation is finished.
			int maxFrame = animDeadLeft.getMaxFrameNum();
			if (animDeadLeft.getCurrentFrameNum() == maxFrame - 1) {
				handler.getPlayer().raiseExp(100);
				this.active = false;
			} else if (animDeadRight.getCurrentFrameNum() == maxFrame - 1) {
				handler.getPlayer().raiseExp(100);
				this.active = false;
			}
		}

	}

}
