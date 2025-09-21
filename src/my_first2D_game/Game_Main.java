package my_first2D_game;

import javax.swing.JFrame;


public class Game_Main {

	public static void main(String[] args) {
		
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);
		window.setTitle("The Last Call");
		
		// Connecting the GamePenel class.-----
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		//-------------------------------------
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		
		gamePanel.setUpGame();
		// Game Loop --------------------
		gamePanel.startGameThread();
		// ------------------------------
		
		// Justice for practice

	}

}
