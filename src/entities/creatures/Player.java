package entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import entities.statics.StaticEntity;
import mainGame.ErrorHandler;
import mainGame.Handler;
import mainGame.gfx.Animation;
import mainGame.gfx.Assets;
import tiles.Tile;

public class Player extends Creature {

	public static final int PLAYER_STANIMA = 500;
	public static final int[] EXP_FOR_LEVEL = { 300, 480, 700, 800 };

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
	private Animation animDeadLeft;

	public Player(Handler handler, float pX, float pY) {
		// Down here you change the player size
		super(handler, pX, pY, (int) (DEFAULT_CREATURE_WIDTH), (int) (DEFAULT_CREATURE_HEIGHT * 1.25));
		super.attack = DEFAULT_PLAYER_ATTACK;
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
		if (health <= 0) {
			// we want to tick the death animation only after the player dies.
			animDeadLeft.tick();
			animDeadRight.tick();
			die();
		}
		// Movement
		getInput();
		staminaRecover();
		move();
		// Center camera on the
		handler.getGameCamera().centerOnEntity(this); 											// player
		// Level update
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
		/*
		 * initialize the animation  objects
		 */
		animWalkRight = new Animation(150, false, Assets.playerWalkRight);
		animWalkLeft = new Animation(150, false, Assets.playerWalkLeft);
		animIdleRight = new Animation(1000, false, Assets.playerIdleRight);
		animIdleLeft = new Animation(1000, false, Assets.playerIdleLeft);
		animRunRight = new Animation(150, false, Assets.playerRunRight);
		animRunLeft = new Animation(150, false, Assets.playerRunLeft);
		animDeadRight = new Animation(100, true, Assets.playerDeadRight);
		animDeadLeft = new Animation(100, true, Assets.playerDeadLeft);
	}

	protected BufferedImage getCurrentAnimFrame() {
		/*
		 * return the current from to show according to the monster state
		 */
		if (xMove < 0 && health > 0) {
			if (xMove == -runSpeed) {
				return animRunLeft.getCurrentFrame();
			} else {
				return animWalkLeft.getCurrentFrame();
			}
		} else if (xMove > 0 && health >0) {
			if (xMove == runSpeed) {
				return animRunRight.getCurrentFrame();
			} else {
				return animWalkRight.getCurrentFrame();
			}
		} else if(health > 0) {
			if (xMove == 0 && side == true) {
				return animIdleLeft.getCurrentFrame();
			} else {
				return animIdleRight.getCurrentFrame();
			}
		}else{
			if (side == false) {
				return animDeadRight.getCurrentFrame();
			} else {
				return animDeadLeft.getCurrentFrame();
			}
		}
	}

	// Input section
	private void getInput() {
		/*
		 * get input from the user and call the appropriate method.
		 */
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
		if (handler.getKeyManager().teleport) {
			teleport();
		}
	}

	// Others
	private void attack() {
		/*
		 * check if there are monsters in the surrounding and attack them.
		 */
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

	private void staminaRecover() {
		/*
		 * raise the stamina points if the player doesn't currently run.
		 */
		if (stanima > PLAYER_STANIMA) {
			stanima = PLAYER_STANIMA;
		}
		if (stanima < PLAYER_STANIMA && !(handler.getKeyManager().runLeft || handler.getKeyManager().runRight)) {
			stanima += 1;
		}
	}

	@Override
	public void die() {
		/*
		 * set active to false only after the animation is finished.
		 * after the player is dead pop the message.
		 */
		if (health <= 0) {
			int maxFrame = animDeadLeft.getMaxFrameNum();
			if (animDeadLeft.getCurrentFrameNum() == maxFrame - 1) {
				this.active = false;
			} else if (animDeadRight.getCurrentFrameNum() == maxFrame - 1) {
				this.active = false;
			}
		}
		if(this.active == false){
			ErrorHandler.DieError();
		}

	}

	public void raiseExp(int exp) {
		/*
		 * raise the player's experience points 
		 */
		this.exp += exp;
	}

	private void levelUp() {
		/*
		 * when the player level up set the exp to zero and the health to it's max value.
		 * check that the player doesn't reached the maximum level available. 
		 */
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
		/*
		 * responsible for the teleport operation.
		 * check if there is a static entity nearby and randomly teleport the player
		 * to another static entity. if there is one (or zero) such entities do nothing.
		 */
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
