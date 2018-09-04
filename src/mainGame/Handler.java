package mainGame;

import entities.creatures.Player;
import input.KeyManager;
import input.MouseManager;
import mainGame.gfx.GameCamera;
import worlds.World;

public class Handler {
	
	private Game game;
	private World world;
	private ErrorHandler errorHandler;


	public Handler(Game game) {
		this.game = game;
	}
	
	public int getWidth(){
		return game.getWidth();
	}
	
	public int getHeight(){
		return game.getHeight();
	}
	
	public KeyManager getKeyManager(){
		return game.getKeyManager();
	}
	
	public MouseManager getMouseManager(){
		return game.getMouseManager();
	}
	
	public GameCamera getGameCamera(){
		return game.getGameCamera();
	}
	
	public Display getDisplay(){
		return game.getDisplay();
	}

	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}


	public World getWorld() {
		return world;
	}


	public void setWorld(World world) {
		this.world = world;
	}
	
	public Player getPlayer(){
		return world.getEntityManager().getPlayer();
	}
	
	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

}
