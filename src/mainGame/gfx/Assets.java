package mainGame.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	public static BufferedImage[] btnStart;
	public static BufferedImage[] btnExit;
	public static BufferedImage leftCornerGrass, grassMiddle, rightCornerGrass, rightUpperGrass, midUpperGrass, leftUpperGrass,bg;
	public static BufferedImage tree;
	public static BufferedImage[] playerWalkLeft, playerWalkRight, playerIdleLeft, playerIdleRight,
	playerRunLeft, playerRunRight, playerDeadRight, playerDeadLeft;
	
	public static void init(){
		buttonsLoader();
		bg = ImageLoader.loadImage("/textures/bg_1.png");
		tree = ImageLoader.loadImage("/textures/tree_1.png");
		grassTileLoader();
		playerImageLoader();
	}

	private static void playerImageLoader() {
		playerWalkLeft = new BufferedImage[10];
		playerWalkRight = new BufferedImage[10];
		playerIdleLeft = new BufferedImage[1];
		playerIdleRight = new BufferedImage[1];
		playerRunLeft = new BufferedImage[10];
		playerRunRight = new BufferedImage[10];
		playerDeadRight = new BufferedImage[10];
		playerDeadLeft = new BufferedImage[10];
		
		for(int i = 0; i < 10; i++){
			playerWalkRight[i] = ImageLoader.loadImage("/textures/player/Walk_" + (i+1) + ".png");
			playerWalkLeft[i] = ImageLoader.loadImage("/textures/player/Walk_" + (i+11) + ".png");
		}
		for(int i = 0; i < 1; i++){
			playerIdleRight[i] = ImageLoader.loadImage("/textures/player/Idle_" + (i+1) + ".png");
			playerIdleLeft[i] = ImageLoader.loadImage("/textures/player/Idle_" + (i+11) + ".png");
		}
		for(int i = 0; i < 10; i++){
			playerRunLeft[i] = ImageLoader.loadImage("/textures/player/Run_" + (i+11) + ".png");
			playerRunRight[i] = ImageLoader.loadImage("/textures/player/Run_" + (i+1) + ".png");
		}
		for(int i = 0; i < 10; i++){
			playerDeadRight[i] = ImageLoader.loadImage("/textures/player/Dead " + "(" + (i+1) + ")" + ".png");
			playerDeadLeft[i] = ImageLoader.loadImage("/textures/player/Dead_left " + "(" + (i+1) + ")" + ".png");
		}
	}

	private static void grassTileLoader() {
		leftCornerGrass = ImageLoader.loadImage("/textures/grass_1.png");
		grassMiddle = ImageLoader.loadImage("/textures/grass_2.png");
		rightCornerGrass = ImageLoader.loadImage("/textures/grass_3.png");
		leftUpperGrass = ImageLoader.loadImage("/textures/grass_4.png");
		midUpperGrass = ImageLoader.loadImage("/textures/grass_5.png");
		rightUpperGrass = ImageLoader.loadImage("/textures/grass_6.png");
	}
	
	private static void buttonsLoader(){
		btnStart = new BufferedImage[2];
		btnExit = new BufferedImage[2];
		btnStart[0] = ImageLoader.loadImage("/textures/buttons/startButtonNotPressed.png");
		btnStart[1] = ImageLoader.loadImage("/textures/buttons/startButtonPressed.png");
		btnExit[0] = ImageLoader.loadImage("/textures/buttons/exitButtonNotPressed.png");
		btnExit[1] = ImageLoader.loadImage("/textures/buttons/exitButtonPressed.png");
	}

}
