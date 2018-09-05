package mainGame.gfx;

import java.awt.image.BufferedImage;

public class Animation {
	
	private int speed, index;
	private long lastTime, timer;
	private boolean oneTime;
	private BufferedImage[] frames;
	
	public Animation(int speed, Boolean oneTime, BufferedImage[] frames) {
		this.speed = speed;
		this.frames = frames;
		this.index = 0;
		this.timer = 0;
		this.oneTime = oneTime;
		this.lastTime = System.currentTimeMillis();
	}
	
	public void tick(){
		if(oneTime && index > frames.length){
			return;
		}
		timer += System.currentTimeMillis() - lastTime;
		lastTime = System.currentTimeMillis();
		
		if(timer > speed){
			index++;
			timer = 0;
			if(index >= frames.length){
				index = 0;
			}
		}
	}
	
	public BufferedImage getCurrentFrame(){
		return frames[index];
	}
	
	public int getCurrentFrameNum() {
		return index;
	}
	
	public int getMaxFrameNum() {
		return frames.length;
	}
	
	

}
