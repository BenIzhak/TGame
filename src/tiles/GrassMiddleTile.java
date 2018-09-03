package tiles;

import mainGame.gfx.Assets;

public class GrassMiddleTile extends Tile{

	public GrassMiddleTile(int id) {
		super(Assets.grassMiddle, id);
	}

	
	public int getId(){
		return id;
	}

}
