package entities;

import java.awt.Graphics;
import java.util.ArrayList;

import entities.creatures.Creature;
import entities.creatures.Monster;
import entities.creatures.Player;
import mainGame.Handler;

public class EntityManager {
	
	private Handler handler;
	private Player player;
	private ArrayList<Entity> entities;
	
	
	public EntityManager(Handler handler, Player player) {
		this.handler = handler;
		this.player = player;
		this.entities = new ArrayList<>();
	}
	
	public void tick(){
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);
			if(e instanceof Creature){
				if(e.active){
					e.tick();
				}else{
					Monster monster = (Monster) e;
					if(!monster.isAliveOn){
						monster.setAlive();
						monster.setAliveOn(true);
					}
				}
			}else{
				e.tick();
			}
		}
		player.tick();
		
	}
	
	public void render(Graphics g){
		for(Entity e:entities){
			if(e instanceof Creature){
				if(e.active){
					e.render(g);
				}
			}else{
				e.render(g);
			}
		}
		player.render(g);
	}
	
	public void addEntity(Entity e){
		entities.add(e);
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

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public void setEntities(ArrayList<Entity> entities) {
		this.entities = entities;
	}
	
	
	

}
