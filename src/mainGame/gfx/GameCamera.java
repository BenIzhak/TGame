package mainGame.gfx;

import entities.Entity;
import mainGame.Handler;
import tiles.Tile;

public class GameCamera {
	
	private float xOffset, yOffset;
	private Handler handler;

	public GameCamera(Handler handler, float xOffset, float yOffset) {
		this.handler = handler;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public void move(float xAmt, float yAmt){
		/*
		 * move the game camera
		 */
		xOffset += xAmt;
		yOffset += yAmt;
		checkBlankSpace();
	}
	
	public void centerOnEntity(Entity e){
		/*
		 * sets the xOffset and yOffset so the Entity e will be in the center
		 */
		xOffset = e.getpX() - handler.getWidth() / 2 + e.getWidth() / 2;
		yOffset = e.getpY() - handler.getHeight() / 2 + e.getHeight() / 2;
		checkBlankSpace();
	} 
	
	public void checkBlankSpace(){	
		/*
		 * keep the camera into the world properties 
		 */
		if(xOffset < 0){
			xOffset = 0;
		}else if(xOffset > handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth()){
			xOffset = handler.getWorld().getWidth() * Tile.TILE_WIDTH - handler.getWidth();
		}
		if(yOffset < 0){
			yOffset = 0;
		}else if(yOffset > handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getGame().getCanvasHeight()){
			yOffset = handler.getWorld().getHeight() * Tile.TILE_HEIGHT - handler.getGame().getCanvasHeight();
		}
		
	}
	
	
	// GETTERS AND SETTERS
	public float getxOffset() {
		return xOffset;
	}

	public void setxOffset(float xOffset) {
		this.xOffset = xOffset;
	}

	public float getyOffset() {
		return yOffset;
	}

	public void setyOffset(float yOffset) {
		this.yOffset = yOffset;
	}
	
	
	
	

}
