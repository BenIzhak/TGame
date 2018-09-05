package worlds;

import java.awt.Graphics;

import entities.creatures.Player;
import mainGame.Handler;

public class WorldsManager {
	
	private Handler handler;
	private Player player;
	private World currentWorld;
	private int currentWorldNum;
	private int maxWorldNum;
	
	
	public WorldsManager(Handler handler, Player player ,int maxWorldNum) {
		this.handler = handler;
		this.maxWorldNum = maxWorldNum;
		this.currentWorldNum = 1;
		this.player = player;
		this.currentWorld = new World(handler, player, "res/worlds/world1.txt"); //Choose map to begin with
	}
	
	
	public void tick(){
		this.currentWorld.tick();
		if(this.currentWorld.getEntityManager().getNumOfActiveEntities() == 0){
			changeToNextWorld();
		}
	}


	public void render(Graphics g){
		this.currentWorld.render(g);
	}
	
	
	private void changeToNextWorld() {
		this.currentWorldNum++;
		this.currentWorld = new World(handler, player, "res/worlds/world"+ this.currentWorldNum + ".txt");
	}
	
	

	public World getWorld() {
		return currentWorld;
	}


	public void setWorld(World currentWorld) {
		this.currentWorld = currentWorld;
	}


	public Player getPlayer() {
		return player;
	}
	
	
}
