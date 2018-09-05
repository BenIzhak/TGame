package worlds;

import java.awt.Graphics;

import entities.EntityManager;
import entities.creatures.*;
import entities.statics.Tree;
import mainGame.ErrorHandler;
import mainGame.Handler;
import mainGame.gfx.Assets;
import tiles.Tile;
import utils.Utils;

public class World {
	
	private Handler handler;
	private int width, height; // Tiles number!!! NOT pixels.
	private int spawnX, spawnY; // As read from the world file in terms of tiles number.
	final private int spawnYElignment = 17;
	private int[][] tiles; // [x][y]
	// Entities
	private EntityManager entityManager;
	
	public World(Handler handler, Player player ,String path){
		this.handler = handler;
		// Create new Entity manager and Player
		entityManager = new EntityManager(handler, player);
		// Map load
		loadWorld(path);
		// Player spawn
		entityManager.getPlayer().setpX(spawnX * Tile.TILE_WIDTH);
		entityManager.getPlayer().setpY(spawnY * Tile.TILE_HEIGHT - spawnYElignment);
	}
	
	public void tick(){
		entityManager.tick();
	}
	
	public void render(Graphics g){
		int xStart = (int) Math.max(0, handler.getGameCamera().getxOffset() / Tile.TILE_WIDTH); // The left most tile that the user can see (the number is tile not pixel)
		int xEnd = (int) Math.min(width, (handler.getGameCamera().getxOffset() + handler.getWidth()) / Tile.TILE_WIDTH + 1); // The right most tile that the user can see (the number is tile not pixel)
		int yStart = (int) Math.max(0, handler.getGameCamera().getyOffset() / Tile.TILE_HEIGHT);
		int yEnd = (int) Math.min(height, (handler.getGameCamera().getyOffset() + handler.getHeight()) / Tile.TILE_HEIGHT + 1);
		g.drawImage(Assets.bg, xStart - 10, yStart - 10, handler.getWidth() + 11, handler.getHeight() + 11,null); // Set world background
		for(int y = yStart; y < yEnd;y++){
			for(int x = xStart;x < xEnd;x++){
				getTile(x, y).render(g,(int) (x * Tile.TILE_WIDTH - handler.getGameCamera().getxOffset()),
						(int) (y * Tile.TILE_HEIGHT - handler.getGameCamera().getyOffset()));
			}
		}
		// Entities
		entityManager.render(g);
		
	}
	
	public Tile getTile(int x, int y){
/*		find the tile in the x and y position - NOT PIXELS */
		if(x < 0 || y < 0 || x >= width || y >= height){
			return Tile.bg;
		}
		Tile t = Tile.tiles[tiles[x][y]];
		if(t == null){
			return Tile.bg; // Set transparent tile default if tile not found by it's id
		}
		return t;
	}
	

	private void loadWorld(String path) {
		int numOfMonsters = 0;
		String file = Utils.loadFileAsString(path);
		String[] tokens = file.split("\\s+");
		width = Utils.parseInt(tokens[0]);
		height = Utils.parseInt(tokens[1]);
		spawnX = Utils.parseInt(tokens[2]);
		spawnY = Utils.parseInt(tokens[3]);
		if((width * height) != (tokens.length-4)){
			ErrorHandler.srcBoardError();
		}
		tiles = new int[width][height];
		for(int y = 0; y < height;y++){
			for(int x = 0;x < width;x++){
				int currentTile = Utils.parseInt(tokens[((x + y * width) + 4)]);
				if(currentTile == 9){
					// Add new monster to the map if the currentTile is 9
					numOfMonsters++;
					entityManager.addEntity(new Monster(handler, x * Tile.TILE_WIDTH, y * Tile.TILE_HEIGHT - spawnYElignment, (int) (Creature.DEFAULT_CREATURE_WIDTH), (int) (Creature.DEFAULT_CREATURE_HEIGHT * 1.25)));
				}
				if(currentTile == 8){
					entityManager.addEntity(new Tree(handler, (x-1) * Tile.TILE_WIDTH, (y-1) * Tile.TILE_HEIGHT));
				}
				tiles[x][y] = currentTile;
			}
		}
		if(numOfMonsters == 0){
			ErrorHandler.noMonstersError();
		}
		
	}
	

	public int getWidth() {
		//Tiles NOT pixels
		return width;
	}

	public int getHeight() {
		//Tiles NOT pixels
		return height;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}
	

}
