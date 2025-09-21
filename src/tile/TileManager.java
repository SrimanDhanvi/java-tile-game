package tile;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import my_first2D_game.GamePanel;

public class TileManager extends Tile{

	GamePanel gp;
	public Tile[] tile;
	
	public TileManager(GamePanel gp) {
		this.gp = gp;
		tile = new Tile[28];
		getTileImage();
	}
	
	public void getTileImage() {
		
	try {
			
		spTile = ImageIO.read(getClass().getResourceAsStream("/tiles/SpriteSheet_tiles.png"));

		} catch (IOException e) {
			e.printStackTrace();
		}
	
	BufferedImage[][] allTile = new BufferedImage[7][4]; // 7 directions, 4 frames each
	for (int dir = 0; dir < allTile.length; dir++) {
	    for (int i = 0; i < allTile[0].length; i++) {
	        allTile[dir][i] = spTile.getSubimage(i * 48, dir * 48, 48, 48);
	    }
	}
		
			
		tile[0] = new Tile();	
		tile[0].image = allTile[0][0];//0
		
		tile[1] = new Tile();
		tile[1].image = allTile[0][1];//1
		
		tile[2] = new Tile();
		tile[2].image = allTile[0][2];//2
		
		tile[3] = new Tile();
		tile[3].image = allTile[0][3];//3
		tile[3].collision = true;
		
		tile[4] = new Tile();
		tile[4].image = allTile[1][0];//4
		tile[4].collision = true;
		
		tile[5] = new Tile();
		tile[5].image = allTile[1][1];//5
		
		
		tile[6] = new Tile();
		tile[6].image = allTile[1][2];//6
		
		tile[7] = new Tile();
		tile[7].image = allTile[1][3];//7
		
		tile[8] = new Tile();
		tile[8].image = allTile[2][0];//8
		tile[8].collision = true;
		
		tile[9] = new Tile();
		tile[9].image = allTile[2][1];//9
		
		tile[10] = new Tile();
		tile[10].image = allTile[2][2];//10
		tile[10].collision = true;
		
		tile[11] = new Tile();
		tile[11].image = allTile[2][3];//11 ---------- Add here other tile
		
		tile[12] = new Tile();
		tile[12].image = allTile[3][0];//12
		
		tile[13] = new Tile();
		tile[13].image = allTile[3][1];//13
		
		tile[14] = new Tile();
		tile[14].image = allTile[3][2];//14
		
		tile[15] = new Tile();
		tile[15].image = allTile[3][3];//15
		tile[15].collision = true;
		
		tile[16] = new Tile();
		tile[16].image = allTile[4][0];//16
		tile[16].collision = true;
		
		tile[17] = new Tile();
		tile[17].image = allTile[4][1];//17
		tile[17].collision = true;
		
		tile[18] = new Tile();
		tile[18].image = allTile[4][2];//18
		tile[18].collision = true;
		
		tile[19] = new Tile();
		tile[19].image = allTile[4][3];//19
		tile[19].collision = true;
		
		tile[20] = new Tile();
		tile[20].image = allTile[5][0];//20
		tile[20].collision = true;
		
		tile[21] = new Tile();
		tile[21].image = allTile[5][1];//21
		
		tile[22] = new Tile();
		tile[22].image = allTile[5][2];//22
		
		tile[23] = new Tile();
		tile[23].image = allTile[5][3];//23
		
		tile[24] = new Tile();
		tile[24].image = allTile[6][0];//24
		
		tile[25] = new Tile();
		tile[25].image = allTile[6][1];//25
		tile[25].collision = true;
		
		tile[26] = new Tile();
		tile[26].image = allTile[6][2];//26
		tile[26].collision = true;
		
		tile[27] = new Tile();
		tile[27].image = allTile[6][3];//27
		tile[27].collision = true;
		
	}
		
	
	
	public void draw(Graphics2D g2) {
		
		colorCode = 0;
		lightTileX.clear();
		lightTileY.clear();
		
		startTileX = (gp.player.worldX / gp.tileSize) - (gp.maxScreenCol / 2) - buffer;
		endTileX = (gp.player.worldX / gp.tileSize) + (gp.maxScreenCol / 2) + buffer;
		
		startTileY = (gp.player.worldY / gp.tileSize) - (gp.maxScreenRow / 2) - buffer;
		endTileY = (gp.player.worldY / gp.tileSize) + (gp.maxScreenRow / 2) + buffer;
				
	    for (int worldTileX = startTileX;worldTileX <= endTileX;worldTileX++) {
	    	for (int worldTileY = startTileY;worldTileY <= endTileY;worldTileY++) {
	    		
	    		worldX = worldTileX*gp.tileSize;
	    		worldY = worldTileY*gp.tileSize;
	    		
	    		screenX = worldX - gp.player.worldX + gp.player.screenX ;
	    		screenY = worldY - gp.player.worldY + gp.player.screenY ;
	    		
	    		tileNum = gp.chunkManger.getTileID(worldTileX, worldTileY);
	    		
	    		switch (tileNum) {
	    		case 25:
	    			colorCode = 1;
	    			lightTileX.add(screenX);
	    			lightTileY.add(screenY);
	    		break;
	    		}
	   
	    		g2.drawImage(tile[tileNum].image , screenX, screenY, gp.tileSize, gp.tileSize, null);
	    		
	    	}
	    }
	}

	
}
