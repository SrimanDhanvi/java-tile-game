package my_first2D_game;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import lighting.light;
import tile.ChunkManager;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
		
		// Screen Settings
		
	private static final long serialVersionUID = 1L;
	
		final int originalTileSize = 16; // 16x16
		final int scale = 3;
		
		public final int tileSize = originalTileSize * scale; // 48x48
		public final int maxScreenCol = 16;
		public final int maxScreenRow = 9;
		public int screenWidth = maxScreenCol * tileSize; //  768pixels
		public int screenHeight = maxScreenRow * tileSize; //  432px

		int FPS = 60;
		public long passed;
		
		// GAME STATE
		public int GameState;
		public int titleState = 0;
		public int playState = 1;
		public int pauseState = 2;
		
//		public Scale sc = new Scale(this);
		
		//SYSTME
		public TileManager tileM = new TileManager(this);
		Keyhandler keyH = new Keyhandler(this);
		Thread gameThread;
		Sound se = new Sound();
		Sound music = new Sound();
		Sound wind = new Sound();
		public ChunkManager chunkManger = new ChunkManager(this);	
		public CollisionChecker cChecker = new CollisionChecker(this);
		public AssetSetter aSetter = new AssetSetter(this);
		public UI ui = new UI(this);
		
		// ENTITY AND OBJECT
		public Player player = new Player (this, keyH);
		public Entity obj[] = new Entity[10];
		public Entity npc[] = new Entity[10];
		public Entity monster[] = new Entity[10];
		ArrayList<Entity> entityList = new ArrayList<>();
		
		//LIGHT
		public light li = new light(this);
		
		public GamePanel() {
			
			this.setPreferredSize(new Dimension(screenWidth , screenHeight));
			this.setBackground(Color.DARK_GRAY);
			this.setDoubleBuffered(true);
			this.addKeyListener(keyH);
			this.setFocusable(true);
		}
		
		public void setUpGame() {
			aSetter.setObj();
			aSetter.setNPC();
			aSetter.setMob();
//			playMusic();
//			playWind();
			GameState = titleState;
		}
		
		public void startGameThread() {
			gameThread = new Thread(this);
			gameThread.start();
		}
		
		@Override
		public void run() {
			
			double drawInterval = 1000000000/FPS; // 0.01666 seconds
			double newxDrawTime = System.nanoTime() + drawInterval;
			
			while(gameThread != null) {
			   
				update(); // update
				repaint(); // draw

				try {
					
					double remainingTime = newxDrawTime - System.nanoTime() ;
					remainingTime /= 1000000;
					
					  if (remainingTime < 0) {
						remainingTime = 0;
					  }
					
					Thread.sleep((long) remainingTime);
					
					newxDrawTime += drawInterval; 
					
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
		
		public void update() {
			
			if (GameState == playState) {
			//PLAYER
			player.update();
			//MONSTER
			for(int i=0;i < monster.length; i++) {
				if(monster[i] != null) {
					monster[i].update();
				}
				
			//OBJECT
				// remove the true and replace it with no. of kills condition.
			aSetter.objRespawn(0, true);
			}
			
			ui.update();
			
			}
			
		}
			
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			BufferedImage gameScreen = new BufferedImage(screenWidth, screenHeight, BufferedImage.TYPE_INT_ARGB);
			Graphics2D g2 = gameScreen.createGraphics();
			
			// DEBUG
			long drawStart = 0;
			if (keyH.F3 == true) {
			drawStart = System.nanoTime();
			}
			//
			
			// TITLE SCREEN
			if(GameState == titleState) {
				ui.draw(g2);
			} // OTHERS
			else {
			
			//TILE
			    tileM.draw(g2);

			//LIGHT
			    li.draw(g2);
			
			//ADD ENTITIES TO THE LIST
			    entityList.add(player);
			    
			    for(int i=0; i<npc.length; i++) {
			    	if (npc[i] != null) {
			    		entityList.add(npc[i]);
			    	}
			    }
			    
			    for(int i=0; i<obj.length;i++) {
			    	if (obj[i] != null) {
			    		entityList.add(obj[i]);
			    	}
			    }
			    
			    for(int i=0; i<monster.length;i++) {
			    	if (monster[i] != null) {
			    		entityList.add(monster[i]);
			    	}
			    }
			
			    
			//SORT
			    Collections.sort(entityList, new Comparator<Entity>() {
			    	
			    	public int compare(Entity e1, Entity e2) {
			    		
			    		int result = Integer.compare(e1.worldY, e2.worldY);
			    		return result;
			    	}
			    });
			    
			//DRAW ENTITIES
			    for(int i=0;i<entityList.size();i++) {
			    	entityList.get(i).draw(g2);
			    }
			    
			//EMPTY ENTITYLIST
			    for(int i=0;i<entityList.size();i++) {
			    	entityList.remove(i);
			    }
			// UI
			    ui.draw(g2);
			
			}
			//
			
			// DEBUG
			if(keyH.F3 == true) {
			long drawEnd = System.nanoTime();
			passed = drawEnd - drawStart;
			}
			//
			
			
			double resize = Math.min((double)getWidth() / screenWidth, (double)getHeight() / screenHeight);
			int newWidth = (int)(screenWidth * resize);
			int newHeight = (int)(screenHeight * resize);
			int xOffset = (getWidth() - newWidth) / 2;
			int yOffset = (getHeight() - newHeight) / 2;
			
			Graphics2D g2Internal  = (Graphics2D) g;
			g2Internal.drawImage(gameScreen, xOffset, yOffset, newWidth, newHeight, null);
			
			g2Internal.dispose();
		}
		
		public void playMusic() {
			
			music.BGMloop((int)((Math.random()*300) + 60000));
		}
		
		public void stopMusic() {
			
			music.stop();
		}
		
		public void playWind() {
			se.setFile(4);
			se.loop();
		}
		
        public void stopSE() {
			
			se.stop();
		}
		
		public void playSE(int i) {
			
			se.setFile(i);
			se.play();
		}
}







