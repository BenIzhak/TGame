package states;

import java.awt.Graphics;

import entities.creatures.Player;
import mainGame.Handler;
import worlds.World;

public class GameState extends State {
	
	private World world;
	
	public GameState(Handler handler){
		super(handler);
		Player player = new Player(handler, 0, 0);
		world = new World(handler, player, "res/worlds/world1.txt"); //Choose map
		handler.setWorld(world);
	}

	@Override
	public void tick() {
		world.tick();
		barsUpdate();
		
	}

	@Override
	public void render(Graphics g) {
		world.render(g);
	}
	
	private void barsUpdate(){
		int LEVEL = handler.getWorld().getEntityManager().getPlayer().getLevel();
		int SP = (handler.getWorld().getEntityManager().getPlayer().getStanima() * 100) / Player.PLAYER_STANIMA;
		int HP = (handler.getWorld().getEntityManager().getPlayer().getHealth() * 100) / Player.DEFAULT_HEALTH;
		int EXP = (handler.getWorld().getEntityManager().getPlayer().getExp() * 100) / Player.EXP_FOR_LEVEL[LEVEL-1];
		handler.getDisplay().getStanimaBar().setValue(SP);
		handler.getDisplay().getHealthBar().setValue(HP);
		handler.getDisplay().getExpBar().setValue(EXP);
		handler.getDisplay().getLblLevel().setText(LEVEL + "");
	}

}