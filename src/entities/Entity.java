package entities;

import java.awt.Graphics;
import java.awt.Rectangle;

import mainGame.Handler;

public abstract class Entity {
	
	protected float pX, pY; // the current position of the entity
	protected int width, height; // Creature width and height
	protected Handler handler;
	protected Rectangle bounds;
	protected boolean active;
	

	public Entity(Handler handler, float pX, float pY, int width, int height) {
		this.handler = handler;
		this.pX = pX;
		this.pY = pY;
		this.width = width;
		this.height = height;
		this.active = true;
		
		bounds = new Rectangle(0,0, width, height); // Create default bounds for collision
		
	}
	
	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	
	// GETTERS AND SETTER

	public float getpX() {
		return pX;
	}

	public void setpX(float pX) {
		this.pX = pX;
	}

	public float getpY() {
		return pY;
	}

	public void setpY(float pY) {
		this.pY = pY;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	

}
