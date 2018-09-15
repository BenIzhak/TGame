package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.creatures.Creature;
import entities.creatures.Player;
import entities.statics.StaticEntity;
import mainGame.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Creature> creatures;
	private ArrayList<StaticEntity> StaticEntities;
	private int numOfActiveEntities;

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		this.creatures = new ArrayList<>();
		this.StaticEntities = new ArrayList<>();
	}

	public void tick() {
		/*
		 * call the tick method of the active entities if entity
		 * is not currently active remove it from the list.  
		 */
		for (int i = 0; i < creatures.size(); i++) {
			Entity e = creatures.get(i);
			if (e.active) {
				e.tick();
			}else{
				creatures.remove(e);
			}
		}
		for (Entity e : StaticEntities) {
			e.tick();
		}
		player.tick();
		this.numOfActiveEntities = creatures.size();

	}

	public void render(Graphics g) {
		/*
		 * render static entities first so they will appear in the background.
		 */
		for (Entity e : StaticEntities) {
			e.render(g);
		}
		for (Entity e : creatures) {
			if (e.active) {
				e.render(g);
			}
		}
		player.render(g);
	}

	public void addEntity(Entity e) {
		/*
		 * add new entity to the relevant list.
		 */
		if (e instanceof Creature) {
			creatures.add((Creature) e);
		} else {
			StaticEntities.add((StaticEntity) e);
		}

	}

	// GETTERS AND SETTERS

	public Handler getHandler() {
		return handler;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public ArrayList<Creature> getCreatures() {
		return creatures;
	}

	public ArrayList<StaticEntity> getStaticEntities() {
		return StaticEntities;
	}

	public int getNumOfActiveEntities() {
		return numOfActiveEntities;
	}
	

}
