package mainGame;

import javax.swing.JOptionPane;

public class ErrorHandler {
	
	public static void srcBoardError() {
		String infoMessage = "World size is not matching the mapping.\n Try to rewrite the sourc board and restart the game.";
		String titleBar = "srcBoardError";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static void MaxLevelError() {
		String infoMessage = "Well Done!\n You reached the highes level.";
		String titleBar = "GAME OVER";
		JOptionPane.showMessageDialog(null, infoMessage, "TGame: " + titleBar, JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	

}
