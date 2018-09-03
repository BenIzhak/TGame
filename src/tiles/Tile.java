package tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Tile {
	
	// STATIC STUFF HERE
	public static Tile[] tiles = new Tile[256];
	public static Tile bg = new BackGroundTile(0);
	public static Tile grassLeftTile = new GrassLeftTile(1);
	public static Tile grassMiddleTile = new GrassMiddleTile(2);
	public static Tile grassRightTile = new GrassRightTile(3);
	public static Tile upperLeftTile = new UpperLeftGrass(4);
	public static Tile upperMidTile = new UpperMidGrass(5);
	public static Tile upperRightTile = new UpperRightGrass(6);

	
	
	public static final int TILE_WIDTH = 128, TILE_HEIGHT = 128;
	
	protected BufferedImage texture;
	protected int id;
	
	
	public Tile(BufferedImage texture, int id) {
		this.texture = texture;
		this.id = id;
		
		tiles[id] = this;
	}
	
	public void tick(){
		
	}
	
	public void render(Graphics g, int x, int y){
		g.drawImage(texture, x, y, TILE_WIDTH, TILE_HEIGHT, null);
	}
	
	public boolean isSolid(){
		return true;
	}

	public BufferedImage getTexture() {
		return texture;
	}

	public void setTexture(BufferedImage texture) {
		this.texture = texture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
	
	

}
