package entities.creatures;

import java.awt.image.BufferedImage;

import entities.Entity;
import mainGame.Handler;
import tiles.Tile;

public abstract class Creature extends Entity {

	public static final int DEFAULT_HEALTH = 100, DEFAULT_ATTACK = 5;
	public static final float DEFAULT_PLAYER_SPEED = 2.7f;
	public static final float DEFAULT_MIN_SPEED = 2.1f;
	public static final float DEFAULT_MAX_SPEED = 3.0f;
	public static final float DEFAULT_GRAVITY = 5.0f;

	// Creature size
	public static final int DEFAULT_CREATURE_WIDTH = 128;
	public static final int DEFAULT_CREATURE_HEIGHT = 128;

	protected int health, attack;
	protected float speed, gravity;

	protected float xMove, yMove; // Tell us how much we have to move the player
	protected boolean side; // False means the last move was to the right

	public Creature(Handler handler, float pX, float pY, int width, int height) {
		super(handler, pX, pY, width, height);
		this.health = DEFAULT_HEALTH;
		this.attack = DEFAULT_ATTACK;
		this.gravity = DEFAULT_GRAVITY;
		this.speed = DEFAULT_PLAYER_SPEED;
		this.xMove = 0;
		this.yMove = 0;
		this.side = false;
	}

	public void move() {
		moveX();
		moveY();
	}

	public void moveX() {
		if (xMove > 0) { // Move right
			int tx = (int) (pX + xMove + bounds.x + bounds.width) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) ((pY + bounds.y) / (Tile.TILE_HEIGHT)))
					&& !collisionWithTile(tx, (int) ((pY + bounds.y + bounds.height/10) / Tile.TILE_HEIGHT))
					&& (pX + bounds.x + bounds.width + xMove) < handler.getWorld().getWidth() * Tile.TILE_WIDTH) {
				// upper corner right and bottom corner right
				// The last term keeps us into the map
				pX += xMove;
				side = false;
			} else {// There is a collision
				pX = tx * Tile.TILE_WIDTH - bounds.x - bounds.width - 1;
				side = true;
			}
		} else if (xMove < 0) { // Move left
			int tx = (int) (pX + xMove + bounds.x) / Tile.TILE_WIDTH;

			if (!collisionWithTile(tx, (int) (pY + bounds.y) / Tile.TILE_HEIGHT)
					&& !collisionWithTile(tx, (int) (pY + bounds.y + bounds.height) / Tile.TILE_HEIGHT)
					&& (pX + bounds.x) > 0) {
				// upper corner left and bottom corner left
				// The last term keeps us into the map
				pX += xMove;
				side = true;
			}else{
				side  = false;
			}
		}
	}

	public void moveY() {
		if (yMove < 0) { // Up
			int ty = (int) (pY + yMove + bounds.y) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((int) (pX + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (pX + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				pY += (yMove);
			}
		} else if (yMove > 0) { // Down
			int ty = (int) (pY + yMove + bounds.y + bounds.height) / Tile.TILE_HEIGHT;

			if (!collisionWithTile((int) (pX + bounds.x) / Tile.TILE_WIDTH, ty)
					&& !collisionWithTile((int) (pX + bounds.x + bounds.width) / Tile.TILE_WIDTH, ty)) {
				pY += (yMove);
			} else {// There is a collision
				pY = ty * Tile.TILE_HEIGHT - bounds.y - bounds.height - 1;
			}
		}

	}

	public boolean groundDetictor() {
		// check if there is a solid tile (d pixels) under the creature
		int d = 3;
		if (collisionWithTile((int) (pX + bounds.x + 1) / Tile.TILE_WIDTH,
				(int) (pY + bounds.y + bounds.height + d) / Tile.TILE_HEIGHT)
				&& collisionWithTile((int) (pX + bounds.x + bounds.width - 1) / Tile.TILE_WIDTH,
						(int) (pY + bounds.y + bounds.height + d) / Tile.TILE_HEIGHT)) {
			return true;
		} else {
			return false;
		}

	}

	protected boolean collisionWithTile(int x, int y) {
		//x and y are in Tile terms
		return handler.getWorld().getTile(x, y).isSolid();
															
	}

	public void hurt(int damage) {
		health -= damage;
		if (health <= 0) {
			die();
		}
	}

	public abstract void die();

	protected abstract void animationInit();

	protected abstract BufferedImage getCurrentAnimFrame();

	// GETTERS AND SETTERS

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getxMove() {
		return xMove;
	}

	public void setxMove(float xMove) {
		this.xMove = xMove;
	}

	public float getyMove() {
		return yMove;
	}

	public void setyMove(float yMove) {
		this.yMove = yMove;
	}

}
