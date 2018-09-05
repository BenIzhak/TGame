package mainGame;

import entities.creatures.Player;
import input.KeyManager;
import input.MouseManager;
import mainGame.gfx.GameCamera;
import worlds.World;
import worlds.WorldsManager;

public class Handler {
	
	private Game game;
	private WorldsManager worldsManger;
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
	
	public WorldsManager getWorldsManger() {
		return worldsManger;
	}

	public void setWorldsManger(WorldsManager worldsManger) {
		this.worldsManger = worldsManger;
	}

	public World getWorld() {
		return worldsManger.getWorld();
	}


	public void setWorld(World world) {
		this.worldsManger.setWorld(world);
	}
	
	public Player getPlayer(){
		return worldsManger.getPlayer();
	}
	
	public ErrorHandler getErrorHandler() {
		return errorHandler;
	}

	public void setErrorHandler(ErrorHandler errorHandler) {
		this.errorHandler = errorHandler;
	}

}
