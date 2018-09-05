package states;

import java.awt.Graphics;

import UI.ClickListener;
import UI.UIImageButton;
import UI.UIManager;
import mainGame.Handler;
import mainGame.gfx.Assets;

public class MenuState extends State {
	
	private UIManager uiManager;
	
	public MenuState(Handler handler){
		super(handler);
		handler.getDisplay().setPanelVisibility(false);
		this.uiManager = new UIManager(handler);
		handler.getMouseManager().setUiManager(uiManager);
		
		// Create new buttons
		createButtons();
	}

	@Override
	public void tick() {
		uiManager.tick();
	}

	@Override
	public void render(Graphics g) {
		uiManager.render(g);
	}
	
	private void createButtons(){
		uiManager.addObject(new UIImageButton(390, 200, 500, 100, Assets.btnStart, new ClickListener() {
			
			@Override
			public void onClick() {
				handler.getMouseManager().setUiManager(null);
				State.setState(handler.getGame().getGameState());
				
			}
		})); 
		
		uiManager.addObject(new UIImageButton(490, 400, 300, 90, Assets.btnExit, new ClickListener() {
			
			@Override
			public void onClick() {
				System.exit(0);
			}
		}));
	}

}
