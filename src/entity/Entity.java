package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import my_first2D_game.GamePanel;

public class Entity {
	
	// MAIN
	GamePanel gp;
	public int worldX, worldY ;
	public int speed;
	public int diaSpeed;
	
	public String name;
	
	public BufferedImage s1, s2, up1, up2, down1, down2, left1, left2, right1, right2;
	public BufferedImage[][] getSpritImages;
//	public BufferedImage image;
	
	public String direction = "s";
	public String directionDia;
	
	public int spriteCounter = 0;
	public int spriteCounter2 = 0;
	public int spriteNum = 1;
	public int spriteNum2 = 1;
	
	// COLLISION RELATED
	public boolean collision = false;
	public boolean collisionOn = false;
    
	public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
	public int solidAreaDefaultX, solidAreaDefaultY;


	// MINOR VARIABLES
	int objectIndex;
	int npcIndex;
	int mobIndex;
	public int dsno = 0;
	public int characterNum = 0;
	public String imgPath;
	public String arrayImgPath[] = new String[10];
	public BufferedImage spriteSheet;
	public int actionLockCounter = 0;
//	public int objectCounter = 0;
	
	// LIFE
	public int maxLife;
	public int life;
	
	
    
    public Entity(GamePanel gp) {
    	this.gp = gp;
    }
    
    
    // OBJECT, NPC AND MOBS
    public void setAction() {};
    public void update() {
    	
    	setAction();
    	
    	collisionOn = false;
    	gp.cChecker.checkTile(this);	
    	gp.cChecker.checkObject(this,false);
    	gp.cChecker.checkEntity(this, gp.npc);
    	gp.cChecker.checkEntity(this, gp.monster);
    	gp.cChecker.checkPlayer(this);
    	
		if (collisionOn == false) {
			
			switch(direction) {
			case "up":
				worldY -= speed;
				break;
			case "down":
				worldY += speed;
			    break;
			case"left":
				worldX -= speed;
				break;
			case"right":
				worldX += speed;
			   break;
			}
		} 
		spriteCounter2++;
		
		if (spriteCounter2 > 12) {
			if (spriteNum2 == 1) {
				spriteNum2 = 2;
			} else if (spriteNum2 == 2) {
			spriteNum2 = 1;
			}
			spriteCounter2 = 0;
		}
	}

    	public void draw(Graphics2D g2) {
    		
    		BufferedImage image = null;
    		
    		int screenX = (int)(worldX - gp.player.worldX + gp.player.screenX) ;
    		int screenY = (int)(worldY - gp.player.worldY + gp.player.screenY) ;
    		
    		if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
    			worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
    			worldY + gp.tileSize > gp.player.worldY - gp.player.screenY && 
    			worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
    			
    			switch(direction) {
    			case "s":
    			    if(s2 == null) {
    					image = s1;
    			    } else {
    				    if (spriteNum2 == 1) {
    					    image = s1;
    				     }
    				    if (spriteNum2 == 2) {
    					    image = s2;
    				}
    			}
    			break;
    			case "up":	
    				if(up2 == null) {
    					image = up1;
    				} else {
    					if (spriteNum2 == 1) {
    						image = up1;
    					}
    					if (spriteNum2 == 2) {
    						image = up2;
    					}
    				}
    			break;
    			case "down":
    				if(down2 == null) {
    					image = down1;
    				} else {
    					if (spriteNum2 == 1) {
    						image = down1;
    					}
    					if (spriteNum2 == 2) {
    						image = down2;
    					}
    				}
    			break;
    			case "left":
    				if(left2 == null) {
    					image = left1;
    				} else {
    					if (spriteNum2 == 1) {
    						image = left1;
    					}
    					if (spriteNum2 == 2) {
    						image = left2;
    					}
    				}
    			break;
    			case "right":
    				if(right2 == null) {
    					image = right1;
    				} else {
    					if (spriteNum2 == 1) {
    						image = right1;
    					}
    					if (spriteNum2 == 2) {
    						image = right2;
    					}
    				}
    			break;
    			}
    		}
    			
    			g2.setColor(new Color(0, 0, 0, 100));
    			g2.fillOval(screenX+12, screenY+40, 20, 20);
    			g2.drawImage(image , screenX, screenY, gp.tileSize, gp.tileSize, null);
    	}
    	
    // -----------------------------------------------------------------------------------
    	
    	
    
    public void characterNo() {
     	arrayImgPath[0] = "/player_images/Spritesheet_Player4_Char1.png";
     	arrayImgPath[1] = "/player_images/Spritesheet_Player01_Char2.png";
    	
     	imgPath = arrayImgPath[characterNum];
    }
    
    public BufferedImage[][] setUpSpriteSheet(String path, int x, int y) {
		try {
			
			spriteSheet = ImageIO.read(getClass().getResourceAsStream(path));

		} catch (IOException e) {
			e.printStackTrace();
		}
		
		BufferedImage[][] allDirections = new BufferedImage[y][x]; // 4 directions, 4 frames each
		for (int dir = 0; dir < allDirections.length; dir++) {
		    for (int i = 0; i < allDirections[0].length; i++) {
		        allDirections[dir][i] = spriteSheet.getSubimage(i * 48, dir * 48, 48, 48);
		    }
		}
		return allDirections;
    }
}
