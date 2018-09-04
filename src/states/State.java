package states;

import java.awt.Graphics;

import mainGame.Game;
import mainGame.Handler;

public abstract class State {
	
	protected Handler handler;
	
	protected static State currentState = null;
	
		
	public State(Handler handler) {
		this.handler = handler;
	}

	public abstract void tick();
	
	public abstract void render(Graphics g);
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}

}
