package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {
	
	private boolean[] keys;
	public boolean up, down, left, right, runRight, runLeft, jump, attack;
	
	

	public KeyManager() {
		this.keys = new boolean[256];
	}
	
	public void tick(){
		up = keys[KeyEvent.VK_UP];
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		jump = keys[KeyEvent.VK_SPACE];
		runLeft = keys[KeyEvent.VK_LEFT] && keys[KeyEvent.VK_SHIFT];
		runRight = keys[KeyEvent.VK_RIGHT] && keys[KeyEvent.VK_SHIFT];
		attack = keys[KeyEvent.VK_CONTROL];
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ALT){
			// Fix Alt bug
			e.consume();
		}
		keys[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	

}
