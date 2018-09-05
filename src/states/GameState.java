package states;

import java.awt.Graphics;

import entities.creatures.Player;
import mainGame.Handler;
import worlds.WorldsManager;

public class GameState extends State {
	
	private WorldsManager worldsManager;
	
	public GameState(Handler handler){
		super(handler);
		Player player = new Player(handler, 0, 0);
		this.worldsManager = new WorldsManager(handler, player, 2);
		handler.setWorldsManger(this.worldsManager);
	}

	@Override
	public void tick() {
		worldsManager.tick();
		barsUpdate();	
	}

	@Override
	public void render(Graphics g) {
		if(!handler.getDisplay().getPanelVisibility()){
			// show panel
			handler.getDisplay().setPanelVisibility(true);
		}
		worldsManager.render(g);
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
	
	// GETTERS AND SETTERS
	public WorldsManager getWorldsManager() {
		return worldsManager;
	}

	public void setWorldsManager(WorldsManager worldsManager) {
		this.worldsManager = worldsManager;
	}
	
}
