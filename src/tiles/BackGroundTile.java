package tiles;

public class BackGroundTile extends Tile{

	public BackGroundTile(int id) {
		super(null, id);
	}
	
	@Override
	public boolean isSolid() {
		return false;
	}
	
	

}
