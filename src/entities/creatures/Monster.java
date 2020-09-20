package entities.creatures;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import mainGame.Handler;
import mainGame.gfx.Animation;
import mainGame.gfx.Assets;

import static tiles.Tile.TILE_WIDTH;

public class Monster extends Creature {

	// Animation
	private Animation animWalkRight;
	private Animation animWalkLeft;

	//private float spawnX;
	private long attackSpeed = 10; // Every "attackSpeed" frames try to attack.
									// 60 mean try to attack every second.
	private long attackTimeCounter; // when this var is equal to attackSpeed try
									// to attack.
	private boolean moveToTarget; // True means the creature move toward the player, otherwise move randomly
	private static final Random random = new Random();

	public Monster(Handler handler, float pX, float pY, int width, int height, boolean moveToTarget) {
		super(handler, pX, pY, width, height);
		//this.spawnX = pX;
		bounds.x = 36;
		bounds.y = 20;
		bounds.width = width - 60;
		bounds.height = height - 37;
		this.moveToTarget = moveToTarget;
		animationInit();
		randMoveSpeed();
	}

	@Override
	public void tick() {
		// Animation
		animWalkRight.tick();
		animWalkLeft.tick();
		// Movement
		if (health > 0) {
			attack();
			applyMovement();
			move();
		}else{
			die();
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
		/*
		 * update and show the health indicator
		 */
		 g.setColor(Color.red);
		 g.fillRect((int) (pX + bounds.x - handler.getGameCamera().getxOffset()),
		 (int) (pY - 15 + bounds.y - handler.getGameCamera().getyOffset()),
		 (health * 100)/DEFAULT_HEALTH, 5);
	}
	
	@Override
	protected void animationInit() {
		/*
		 * initialize the animation  objects
		 */
		animWalkRight = new Animation(150, false, Assets.zombieWalkRight);
		animWalkLeft = new Animation(150, false, Assets.zombieWalkLeft);
	}

	protected BufferedImage getCurrentAnimFrame() {
		/*
		 * return the current from to show according to the monster state
		 */
		if (xMove < 0 && health > 0) {
			return animWalkLeft.getCurrentFrame();
		} else {
			// (xMove > 0 && health > 0)
			return animWalkRight.getCurrentFrame();
		}
	}

	private void applyMovement(){
		if (moveToTarget){
			autoMove();
		}else{
			autoMoveRnd();
		}
	}

	// Movement section
	private void autoMoveRnd() {
		/*
		 * responsible for the monster automatic movement.
		 */
		sideChange();
		yMove = gravity;
		if (!side) {
			xMove = +speed;
		} else {
			xMove = -speed;
		}
	}

	private void autoMove() {
		/*
		 * responsible for the monster automatic movement.
		 */
		float distanceFromPlayerX = (handler.getPlayer().getpX() + handler.getPlayer().getWidth() / 2 - (pX + getWidth() / 2));
		boolean sameAltitude = Math.abs(handler.getPlayer().getpY() - pY) < 20;
		yMove = gravity;
		if (sameAltitude && Math.abs(distanceFromPlayerX) < TILE_WIDTH * 2 && groundDetector()){
			// In the same altitude and in far at most 2 tiles from player
			if (distanceFromPlayerX > TILE_WIDTH){
				side = false;
				xMove = +speed;
			}else if (distanceFromPlayerX < -TILE_WIDTH){
				side = true;
				xMove = -speed;
			}else{
				if (side){
					xMove = -speed;
				}else{
					xMove = +speed;
				}
			}
		}else {
//			if (side && groundDetector()){
//				xMove = -speed;
//			}else if (!side && groundDetector()){
//				xMove = +speed;
//			}else{
//				side = !side;
//				if (side){
//					xMove = -speed;
//				}else {
//					xMove = +speed;
//				}
//			}
			autoMoveRnd();
		}
	}

	private void sideChange() {
		/*
		 * change the side that the monster move to.
		 * maxLeft and maxRight are the boundaries from the spawn position.
		 */

		boolean true25 = random.nextInt(180) == 0;
		if (true25 && groundDetector()) {
			side = !side;
		} else if (!groundDetector()) {
			side = !side;
		}
	}

	private void randMoveSpeed() {
		/*
		 * choose randomly the move speed for each monster.
		 * the speed is greater than the DEFAULT_MIN_SPEED and
		 * smaller than the DEFAULT_MAX_SPEED. 
		 */
		Random rnd = new Random();
		speed = DEFAULT_MIN_SPEED + rnd.nextFloat() * (DEFAULT_MAX_SPEED - DEFAULT_MIN_SPEED);
		if (((int) speed % 2) == 0) {
			side = true;
		}
	}

	// Others
	private void attack() {
		/*
		 * check if the player in the monster surrounding and attack him.
		 * we want to attack the player every amount of time and not every 
		 * frame (it's too fast to attack every frame) so we  use here the 
		 * the attackTimeCounter.  
		 */
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
		/*
		 * when the monster die raise the player's experience points
		 * and deactivate the player.
		 */
		handler.getPlayer().raiseExp(100);
		this.active = false;
	}

}
