package tiles;

import mainGame.gfx.Assets;

public class GrassRightTile extends Tile{

	public GrassRightTile(int id) {
		super(Assets.rightCornerGrass, id);
	}
	
	
	public int getId(){
		return id;
	}
	

}
