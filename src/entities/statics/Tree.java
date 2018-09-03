package entities.statics;

import java.awt.Graphics;

import mainGame.Handler;
import mainGame.gfx.Assets;
import tiles.Tile;

public class Tree extends StaticEntity {

	
	public Tree(Handler handler, float x, float y) {
		super(handler, x, y, (int) 2 * Tile.TILE_WIDTH , 2 * Tile.TILE_HEIGHT);
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.drawImage(Assets.tree, (int) (pX - handler.getGameCamera().getxOffset()), (int) (pY - handler.getGameCamera().getyOffset()), width, height, null);
		
	}

}
