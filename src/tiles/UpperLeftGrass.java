package tiles;

import mainGame.gfx.Assets;

public class UpperLeftGrass extends Tile {
	
	public UpperLeftGrass(int id) {
		super(Assets.leftUpperGrass, id);
	}
	
	
	public int getId(){
		return id;
	}

}
