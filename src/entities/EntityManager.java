package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.creatures.Creature;
import entities.creatures.Monster;
import entities.creatures.Player;
import entities.statics.StaticEntity;
import mainGame.Handler;

public class EntityManager {

	private Handler handler;
	private Player player;
	private ArrayList<Creature> Creatures;
	private ArrayList<StaticEntity> StaticEntities;

	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		this.Creatures = new ArrayList<>();
		this.StaticEntities = new ArrayList<>();
	}

	public void tick() {
		for (Entity e: Creatures) {
			if (e.active) {
				e.tick();
			}
		}
		for (Entity e : StaticEntities) {
			e.tick();
		}
		player.tick();

	}

	public void render(Graphics g) {
		// render static entities first
		for (Entity e : StaticEntities) {
			e.render(g);
		}
		for (Entity e : Creatures) {
			if (e.active) {
				e.render(g);
			}
		}
		player.render(g);
	}

	public void addEntity(Entity e) {
		if (e instanceof Creature) {
			Creatures.add((Creature) e);
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
		return Creatures;
	}

	public ArrayList<StaticEntity> getStaticEntities() {
		return StaticEntities;
	}

}
