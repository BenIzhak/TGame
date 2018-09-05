package mainGame;

import javax.swing.JOptionPane;

public class ErrorHandler {
	
	public static void srcBoardError() {
		String infoMessage = "World size is not matching the mapping.\n Try to rewrite the sourc board and restart the game.";
		String titleBar = "srcBoardError";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static void srcBoardLoadError() {
		String infoMessage = "Load board has failed.\n Make sure all the files exist.";
		String titleBar = "LoadBoardError";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static void noMonstersError() {
		String infoMessage = "Must be at least one monster in the map.";
		String titleBar = "noMonstersError";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static void MaxLevelError() {
		String infoMessage = "Well Done!\n You reached the highes level.";
		String titleBar = "GAME OVER";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	public static void DieError() {
		String infoMessage = "You lost all yours health points (HP)";
		String titleBar = "GAME OVER";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	
	public static void NoMoreMapError() {
		String infoMessage = "Well Done!\n You beat all the monsters!";
		String titleBar = "GAME OVER";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
		System.exit(0);
	}
	

}
