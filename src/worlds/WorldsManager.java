package worlds;

import java.awt.Graphics;
import java.io.File;

import entities.creatures.Player;
import mainGame.ErrorHandler;
import mainGame.Handler;
import mainGame.gfx.MusicPlayer;

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
		MusicPlayer.initJavaFx();
		this.currentWorld = new World(handler, player, "res/maps/world1.txt", "/music/speck_-_Ornery_Brunt_(New_Combination)_1.mp3"); // map to begin with
		this.maxWorldNum = new File("res/maps").listFiles().length;
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
		/*
		 * change the current world to next world available.
		 * if there is no more worlds pop a message.
		 */
		this.currentWorldNum++;
		if(this.currentWorldNum > this.maxWorldNum){
			ErrorHandler.NoMoreMapError();
		}
		this.currentWorld.stopMusic();
		this.currentWorld = new World(handler, player, "res/maps/world"+ this.currentWorldNum + ".txt", "/music/speck_-_Ornery_Brunt_(New_Combination)_1.mp3");
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
