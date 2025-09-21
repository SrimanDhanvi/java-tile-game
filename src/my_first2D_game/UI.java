package my_first2D_game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class UI extends Dialouges{

	GamePanel gp;
	Graphics g2;
	Font customFont_1, arial_20;
	BufferedImage killSymbol_Image, healthSymbol_Image, tenKill_Image, messageImg, Logo_game, TitleBackground, char1, char2, Heath_Bar;
	public boolean messageOn = false;
	public String message = "";
	int messageCounter = 0;
	String D1, D2;
	int dno = 0;
	Color textColor;
	public int commandNum = 0; 
	public int selectNum = 0;
	public int tileScreenState = 0; // 0: main menu , 1: character screen
	public int pauseScreenState = 0; // 0: pause screen , 1: settings
	
	public UI(GamePanel gp) {
		this.gp = gp;

        	arial_20 = new Font ("SansSerif" , Font.PLAIN, 10);
        	
            InputStream is = getClass().getResourceAsStream("/fonts/tt-supermolot-neue-trl.bd-it.ttf");
    			try {
					customFont_1 = Font.createFont(Font.TRUETYPE_FONT, is);
				} catch (FontFormatException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

        	
        	try {
        		killSymbol_Image = ImageIO.read(getClass().getResourceAsStream("/symbols/Kills_symbol.png"));
        		healthSymbol_Image = ImageIO.read(getClass().getResourceAsStream("/symbols/Health_Symbol.png"));
        		Logo_game = ImageIO.read(getClass().getResourceAsStream("/logo/Logo_game.png"));
        		char1 = ImageIO.read(getClass().getResourceAsStream("/player_images/Design2_static.png"));
        		char2 = ImageIO.read(getClass().getResourceAsStream("/player_images/Char2Static.png"));
        		
        		Heath_Bar = ImageIO.read(getClass().getResourceAsStream("/health_bar/HealthBar.png"));
        		
//        		TitleBackground = ImageIO.read(getClass().getResourceAsStream("/logo/Background_image.png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public int getXforCentredText(String text) {
		int lengthOfText = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
		int TextX = gp.screenWidth/2- lengthOfText/2;
		
		return TextX;
	}
	
	public void showMessage(int a) {
		
		messageOn = true;
		
		if (a == 1) {
			textColor = new Color(106, 240, 17, 255);
			message = Health_message;
			messageImg = healthSymbol_Image;
		}
	}
	
	public void update() {
		drawPlayerHealth();
	}
	
	public void draw(Graphics g2) {
		
		this.g2 = g2;
		
//		235, 84, 2, 255 - orange
//		106, 240, 17, 255 - green
		g2.setFont(customFont_1);
		
		//TITLE STATE
		if(gp.GameState == gp.titleState) {
		   drawTitleScreen();	
		}
		
		//PLAY STATE
		if(gp.GameState == gp.playState) {
			drawPlayerHealth();
			
			// TOP MESSAGES
			if(messageOn) {
				messageOnTop();
			}
			//PERFORMANCE DISPLAY
			if(gp.keyH.F3) {
				PerformanceDisplay();
			}	
			
			//DIALOUGE STATE
			if (gp.player.dsno == 1) {
				dialougeSet1();
			}
			
		}
		
		//PAUSE STATE
		if (gp.GameState == gp.pauseState) {
			drawPauseScreen();
			
			//PERFORMANCE DISPLAY
			if(gp.keyH.F3) {
				PerformanceDisplay();
			}	
		}

	}
	
	public void drawPlayerHealth() {
		
		
		g2.drawImage(Heath_Bar, 10, 20, 15, 300, null);
		
		g2.setColor(new Color(130, 27, 20));
		
		int barX = 15;
		int barY = 25;
		int barWidth = 5;
		int barHeight = 290;
		
	    // calculate health ratio
	    float healthPercent = (float) gp.player.life / gp.player.maxLife;

	    // height of the filled portion
	    int filledHeight = (int) (barHeight * healthPercent);
	    
		g2.fillRect(barX, barY + (barHeight - filledHeight), barWidth, filledHeight);	
		
		g2.setColor(new Color(255, 240, 200, 20));
		g2.fillRect(14, 24, 7, 292);
	}
	
	public void drawTitleScreen() {
//		g2.drawImage(TitleBackground, 0, 0, 768, 432, null);
		
		if (tileScreenState == 0) {
		g2.drawImage(Logo_game, 119, 40, 530, 94, null);
		g2.setColor(Color.white);
		g2.setFont(customFont_1.deriveFont(20f));
		g2.drawString("PLAY", getXforCentredText("PLAY"), 220);
		if (commandNum == 0) {
			g2.drawString(">", getXforCentredText("PLAY")- 40, 220);
		}
		
		g2.drawString("RESTART", getXforCentredText("RESTART"), 260);
		if (commandNum == 1) {
			g2.drawString(">", getXforCentredText("RESTART")- 40, 260);
		}
		
		g2.drawString("CHARACTER", getXforCentredText("CHARACTER"), 300);
		if (commandNum == 2) {
			g2.drawString(">", getXforCentredText("CHARACTER")- 40, 300);
		}
		
		g2.drawString("QUIT", getXforCentredText("QUIT"), 340);
		if (commandNum == 3) {
			g2.drawString(">", getXforCentredText("QUIT")- 40, 340);
		}
	}
		else if (tileScreenState == 1) {
			g2.setColor(Color.white);
			g2.setFont(customFont_1.deriveFont(20f));
			g2.drawString("<LeftArrow/A    SELECT CHARACTER    D/RightArrow>", getXforCentredText("<LeftArrow/A    SELECT CHARACTER    D/RightArrow>"), gp.screenHeight - 50);
			g2.setFont(customFont_1.deriveFont(10f));
			g2.drawString("ESC TO MAIN MENU", getXforCentredText("ESC TO MAIN MENU"), gp.screenHeight - 20);
			
			if (selectNum == 0) {
				g2.drawImage(char1, gp.screenWidth/2 - 96, 50, gp.tileSize*4, gp.tileSize*4, null);
			}
			if (selectNum == 1) {
				g2.drawImage(char2, gp.screenWidth/2 - 96, 50, gp.tileSize*4, gp.tileSize*4, null);
			}
		}
}
	
	public void drawPauseScreen() {
		
		if(pauseScreenState == 0) {
		
		g2.setColor(new Color(0, 0, 0, 100));
		g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
		g2.setFont(g2.getFont().deriveFont(30f));
		g2.setColor(Color.white);
		g2.drawString("Time Stilled", getXforCentredText("Time Stilled"), 110);
		
		g2.setColor(Color.white);
		g2.setFont(customFont_1.deriveFont(20f));
		g2.drawString("CONTINUE", getXforCentredText("CONTINUE"), 220);
		if (commandNum == 0) {
			g2.drawString(">", getXforCentredText("CONTINUE")- 40, 220);
		}
		
		g2.drawString("MAIN MENU", getXforCentredText("MAIN MENU"), 260);
		if (commandNum == 1) {
			g2.drawString(">", getXforCentredText("MAIN MENU")- 40, 260);
		}
		
		g2.drawString("SETTINGS", getXforCentredText("SETTINGS"), 300);
		if (commandNum == 2) {
			g2.drawString(">", getXforCentredText("SETTINGS")- 40, 300);
		}
	}
		else if (pauseScreenState == 1) {
			g2.setColor(new Color(0, 0, 0, 100));
			g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);
			g2.setFont(g2.getFont().deriveFont(30f));
			g2.setColor(Color.white);
			g2.drawString("Settings", getXforCentredText("Settings"), 80);
			
			//Settings
			
			
			
			//
			
			g2.setFont(customFont_1.deriveFont(10f));
			g2.drawString("ESC TO CLOSE SETTINGS", getXforCentredText("ESC TO CLOSE SETTINGS"), gp.screenHeight - 20);
		}
		
}
	
	public void messageOnTop() {
		
		g2.setFont(g2.getFont().deriveFont(10f));
		g2.setColor(textColor);
		g2.drawImage(messageImg, gp.screenWidth/2 - 24, 5, gp.tileSize, gp.tileSize, null);
		g2.drawString(message, getXforCentredText(message), 70);
		
		messageCounter++;
		
		if(messageCounter > 120) {
			messageCounter = 0;
			messageOn = false;
		}
	}
	
	public void PerformanceDisplay() {
		g2.setFont(g2.getFont().deriveFont(10f));
		g2.setColor(Color.white);
		g2.drawString("X, Y: " + gp.player.worldX + ", " + gp.player.worldY, 10, 330);
		g2.drawString("Draw Time: " + gp.passed, 10, 340);
	}
	
	public void dialougeBox() {
		g2.setColor(Color.white);
		g2.drawRect(10, gp.screenHeight-210, gp.screenWidth-20, 200);
		g2.setColor(new Color(87, 129, 145, 150));
		g2.fillRect(10, gp.screenHeight-210, gp.screenWidth-20, 200);
		g2.setFont(arial_20);
		g2.setColor(Color.white);
		g2.drawString("Press Enter", gp.screenWidth-80, gp.screenHeight-30);
	}
	
	public void dialougeSet1() {
		dialougeBox();

		g2.setFont(g2.getFont());
		g2.setColor(Color.white);

		if (dno == 0) {
			D1 = Dialouge1;
			D2 = emo1;
		}
		
		if (dno == 1) {
			D1 = Dialouge2;
			D2 = emo2;
		}
		
		if (dno == 2) {
			D1 = Dialouge3;
			D2 = emo3;
		}
		
		if (dno == 3) {
			D1 = emo4;
			D2 = Dialouge4;
		}
		
		if (dno == 4) {
			D1 = Dialouge5;
			D2 = emo5;
		}
		
		if (dno == 5) {
			D1 = Dialouge6;
			D2 = emo6;
		}
		
		if (dno == 6) {
			D1 = Dialouge7;
			D2 = "";
		}
		
		if (dno == 7) {
			D1 = emo8;
			D2 = Dialouge8;
		}
		
		if (dno == 8) {
			gp.player.dsno = 0;
			dno = 0;
		}
		
		g2.drawString(D1, 30, gp.screenHeight-180);
		g2.drawString(D2, 30, gp.screenHeight-160);
	}

}
	
