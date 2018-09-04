package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import entities.Entity;
import entities.statics.StaticEntity;
import mainGame.ErrorHandler;
import mainGame.Handler;
import mainGame.gfx.Animation;
import mainGame.gfx.Assets;
import tiles.Tile;

public class Player extends Creature {

	public static final int PLAYER_STANIMA = 500;
	public static final int[] EXP_FOR_LEVEL = { 200, 250, 300 };

	private int level, exp, stanima;
	private float runSpeed;

	// Animation
	private Animation animWalkRight;
	private Animation animWalkLeft;
	private Animation animIdleRight;
	private Animation animIdleLeft;
	private Animation animRunRight;
	private Animation animRunLeft;
	private Animation animDeadRight;

	public Player(Handler handler, float pX, float pY) {
		// Down here you change the player size
		super(handler, pX, pY, (int) (DEFAULT_CREATURE_WIDTH), (int) (DEFAULT_CREATURE_HEIGHT * 1.25));
		bounds.x = 36;
		bounds.y = 20;
		bounds.width = width - 60;
		bounds.height = height - 37;
		this.level = 1;
		this.runSpeed = (float) (DEFAULT_PLAYER_SPEED * 1.5);
		this.stanima = PLAYER_STANIMA;
		this.exp = 0;

		// Animation
		animationInit();
	}

	@Override
	public void tick() {
		// Animation
		animWalkRight.tick();
		animWalkLeft.tick();
		animRunRight.tick();
		animRunLeft.tick();
		// Movement
		getInput();
		stanimaRecover();
		move();
		handler.getGameCamera().centerOnEntity(this); // Center camera on the											// player
		// level update
		levelUp();
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(getCurrentAnimFrame(), (int) (pX - handler.getGameCamera().getxOffset()),
				(int) (pY - handler.getGameCamera().getyOffset()), width, height, null);
		// g.setColor(Color.red);
		// g.fillRect((int) (pX + bounds.x -
		// handler.getGameCamera().getxOffset()),
		// (int) (pY + bounds.y - handler.getGameCamera().getyOffset()),
		// bounds.width, bounds.height);
	}

	// Animation section
	protected void animationInit() {
		animWalkRight = new Animation(150, Assets.playerWalkRight);
		animWalkLeft = new Animation(150, Assets.playerWalkLeft);
		animIdleRight = new Animation(1000, Assets.playerIdleRight);
		animIdleLeft = new Animation(1000, Assets.playerIdleLeft);
		animRunRight = new Animation(150, Assets.playerRunRight);
		animRunLeft = new Animation(150, Assets.playerRunLeft);
		animDeadRight = new Animation(200, Assets.playerDeadRight);
	}

	protected BufferedImage getCurrentAnimFrame() {
		if (xMove < 0) {
			if (xMove == -runSpeed) {
				return animRunLeft.getCurrentFrame();
			} else {
				return animWalkLeft.getCurrentFrame();
			}
		} else if (xMove > 0) {
			if (xMove == runSpeed) {
				return animRunRight.getCurrentFrame();
			} else {
				return animWalkRight.getCurrentFrame();
			}
		} else {
			if (xMove == 0 && side == true) {
				return animIdleLeft.getCurrentFrame();
			} else {
				return animIdleRight.getCurrentFrame();
			}
		}
	}

	// Input section
	private void getInput() {
		xMove = 0;
		yMove = gravity;
		if (handler.getKeyManager().left) {
			xMove = -speed;
		}
		if (handler.getKeyManager().right) {
			xMove = +speed;
		}
		if (handler.getKeyManager().runLeft) {
			if (stanima > 0) {
				// Player able to run
				xMove = -runSpeed;
				stanima--;
			} else {
				xMove = -speed;
			}
		}
		if (handler.getKeyManager().runRight) {
			if (stanima > 0) {
				// Player able to run
				xMove = +runSpeed;
				stanima--;
			} else {
				xMove = +speed;
			}
		}
		if (handler.getKeyManager().attack) {
			attack();
		}
		if (handler.getKeyManager().jump) {
			teleport();
		}
	}

	// Others
	private void attack() {
		ArrayList<Creature> entities = handler.getWorld().getEntityManager().getCreatures();
		for (int i = 0; i < entities.size(); i++) {
			Creature e = entities.get(i);
			if (e.isActive()) {
				if (Math.abs(e.getpX() + handler.getPlayer().getWidth() / 2 - (pX + getWidth() / 2)) < 20
						&& Math.abs(e.getpY() - pY) < 20) {
					e.hurt(attack);
				}
			}
		}

	}

	private void stanimaRecover() {
		if (stanima > PLAYER_STANIMA) {
			stanima = PLAYER_STANIMA;
		}
		if (stanima < PLAYER_STANIMA && !(handler.getKeyManager().runLeft || handler.getKeyManager().runRight)) {
			stanima += 1;
		}
	}

	@Override
	public void die() {
		// TODO
	}

	public void raiseExp(int exp) {
		this.exp += exp;
	}

	private void levelUp() {

		if (this.exp >= EXP_FOR_LEVEL[level - 1]) {
			exp = 0;
			level++;
			setHealth(DEFAULT_HEALTH);
			if (level == EXP_FOR_LEVEL.length + 1) {
				ErrorHandler.MaxLevelError();
			}
		}
	}

	private void teleport() {
		Random rand = new Random();
		ArrayList<StaticEntity> entities = handler.getWorld().getEntityManager().getStaticEntities();
		if (entities.size() <= 1) {
			// if we have one or zero entities do nothing
			return;
		}
		for (int i = 0; i < entities.size(); i++) {
			StaticEntity e = entities.get(i);
			if (Math.abs((e.getpY() + Tile.TILE_HEIGHT) - this.pY) < 20
					&& Math.abs((e.getpX() + (e.getWidth() / 3)) - this.pX) < 60) {
				// we are in the range of an entity
				int newEntityIndex = rand.nextInt(entities.size());
				while (newEntityIndex == i) {
					// teleport to another entity
					newEntityIndex = rand.nextInt(entities.size());
				}
				StaticEntity randomEntity = entities.get(newEntityIndex);
				float newPx = randomEntity.getpX() + (e.getWidth() / 3);
				float newPy = randomEntity.getpY();
				this.pX = newPx;
				this.pY = newPy;
				return;
			}

		}
	}

	// GETTER AND SETTERS
	public int getLevel() {
		return level;
	}

	public int getStanima() {
		return stanima;
	}

	public int getExp() {
		return exp;
	}

}
