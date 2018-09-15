package mainGame;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import input.KeyManager;
import input.MouseManager;
import mainGame.gfx.Assets;
import mainGame.gfx.GameCamera;
import states.GameState;
import states.MenuState;
import states.State;

public class Game implements Runnable {
	
	private Display display;
	private int width, height;
	public String title;
	
	private boolean running;
	private Thread thread;
	
	private BufferStrategy bs;
	private Graphics g;
	
	// States
	private State gameState;
	private State menuState;
	
	// Input
	private KeyManager keyManager;
	private MouseManager mouseManager;
	
	// Camera
	private GameCamera gameCamera;
	
	// Handler
	private Handler handler;

	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.running = false;
		this.keyManager = new KeyManager();
		this.mouseManager = new MouseManager();
	}
	
	// initialize the game components
	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);
		
		Assets.init();
		
		handler = new Handler(this);
		gameCamera = new GameCamera(handler, 0, 0);
		
		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		State.setState(menuState);
		
	}
	
	// update the game variables according to the user input
	private void tick(){
		keyManager.tick();
		if(State.getState() != null){
			State.getState().tick(); // Run the current state tick
		}
	}
	
	// Update the game animation according to the user input
	private void render(){
		bs = display.getCanvas().getBufferStrategy();
		if(bs == null){
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		g = bs.getDrawGraphics();
		// Clear screen
		g.clearRect(0, 0, width, height);
		// Draw Here!
		
		if(State.getState() != null){
			State.getState().render(g); // Run the current state render
		}
		
		// End Drawing!
		bs.show();
		g.dispose();
		
	}


	@Override
	public void run() {
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		while(running){
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			lastTime = now;
			if(delta >= 1){
				tick();
				render();
				delta--;
			}

		}
		
		stop();
		
	}

	public synchronized void start(){
		if(running){
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start(); // Call run method
	}
	
	public synchronized void stop(){
		if(!running){
			return;
		}
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// getters section
	public KeyManager getKeyManager() {
		return keyManager;
	}
	public GameCamera getGameCamera() {
		return gameCamera;
	}
	
	public MouseManager getMouseManager() {
		return mouseManager;
	}

	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
	public int getCanvasWidth() {
		return display.getWidth();
	}
	public int getCanvasHeight() {
		return display.getHeight();
	}
	public Display getDisplay() {
		return display;
	}
	public State getGameState() {
		return gameState;
	}
	
	


}
