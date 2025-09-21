package entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import my_first2D_game.GamePanel;
import my_first2D_game.Keyhandler;

public class Player extends Entity{
	
	
	Keyhandler keyH;
	
	public final int screenX;
	public final int screenY;
	
	public Player(GamePanel gp, Keyhandler keyH) {
		
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth/2 - gp.tileSize/2;
		screenY = gp.screenHeight/2 - gp.tileSize/2;
		
		solidArea = new Rectangle(8 ,32 ,32 ,16);
		solidArea.x=8;
		solidArea.y=32;
		solidArea.width=32;
		solidArea.height=16;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		setDefaultValues();
		getplayerImage();
	}
	
	public void setDefaultValues() {
		worldX = 0*gp.tileSize;
		worldY = 0*gp.tileSize;
		speed = 3;
		direction = "s"; // default
		
//		PLAYER STATUS
		maxLife = 145;
		life = maxLife;
	}
	
	public void getplayerImage() {
	
		characterNo();
		
		getSpritImages = setUpSpriteSheet(imgPath,4 ,5);
		
		
		s1 = getSpritImages[0][0];
		s2 = getSpritImages[3][3];
		up1 = getSpritImages[0][3];
		up2 = getSpritImages[1][0];
		down1 = getSpritImages[0][1];
		down2 = getSpritImages[0][2];
		left1 = getSpritImages[1][3];
		left2 = getSpritImages[2][0];
		right1 = getSpritImages[1][1];
		right2 = getSpritImages[1][2];
		
		
	}
	
	public void update() {
		
		direction = "s";
		directionDia = "";
		
		if (keyH.upPressed == true || keyH.downPressed == true 
				||keyH.leftPressed == true ||keyH.rightPressed == true) {
		
		
		if (keyH.upPressed == true && keyH.leftPressed == true) {
			direction = "up";
			directionDia = "left" ;
		}
		else if (keyH.upPressed == true && keyH.rightPressed == true) {
			direction = "up";
			directionDia = "right" ;
		}
		else if (keyH.downPressed == true && keyH.rightPressed == true) {
			direction = "down";
			directionDia = "right" ;
		}
		else if (keyH.downPressed == true && keyH.leftPressed == true) {
			direction = "down";
			directionDia = "left" ;
		}
		else if (keyH.upPressed == true) {
			direction = "up";
		}
		else if (keyH.downPressed == true) {
			direction = "down";
		}
		else if (keyH.leftPressed == true) {
			direction = "left";
	    }
		else if (keyH.rightPressed == true) {
			direction = "right";
	    } 
		
		diaSpeed = (int)(speed/Math.sqrt(2));
		
		//Check tile collision
		collisionOn = false;
		gp.cChecker.checkTile(this);
		
		//Check object collision
		objectIndex = gp.cChecker.checkObject(this, true);
		pickUpObject(objectIndex);
		
		//Check NPC collision
		npcIndex = gp.cChecker.checkEntity(this, gp.npc);
		interactNPC(npcIndex);
		
		//Check monster collision
		mobIndex = gp.cChecker.checkEntity(this, gp.monster);
		contactMonster(mobIndex);
		
		
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
			
			
			switch(directionDia) {
			case "up":
				worldY -= diaSpeed;
				break;
			case "down":
				worldY += diaSpeed;
			    break;
			case"left":
				worldX -= diaSpeed;
				break;
			case"right":
				worldX += diaSpeed;
			   break;
			}
		} 
		
	}
		spriteCounter++;
		
		if (spriteCounter > 20) {
			if (spriteNum == 1) {
				spriteNum = 2;
			} else if (spriteNum == 2) {
			spriteNum = 1;
			}
			spriteCounter = 0;
		}
		
//		System.out.println(worldX);
//		System.out.println(worldY);
}

	public void pickUpObject(int i) {
		
		// different cases for different objects touched by the player
		if(i != 999) {
			switch(i) {
			case 0:
				gp.playSE(2);
				gp.obj[0] = null;
				gp.ui.showMessage(1);
				break;
			}
		}
	}
	
	public void interactNPC(int i) {
		
		if(i != 999) {
			switch(i) {
		    case 0:
			    if(dsno == 0) {
				dsno = 1;
//			    System.out.println("rectangle:" + gp.obj[1].solidArea.x);
			    }
			    break;
		    }
		}
	}
	
	public void contactMonster(int i) {
		
		if(i != 999) {
			switch(i) {
		    case 0:
		    	life -= 1;
			    break;
		    }
		}
	}
	
	public void draw(Graphics2D g2) {
		
		
//		{
//        Point2D center = new Point2D.Float(screenX+24, screenY+41);
//        float radius = 25;
//        float[] dist = {0.5f, 1.0f};  // From center to edge
//        Color[] colors = { new Color(0, 0, 0, 0), new Color(0, 255, 255, 100)};  // Bright center to transparent
//
//        RadialGradientPaint rgp = new RadialGradientPaint(center, radius, dist, colors);
//
//        g2.setPaint(rgp);
//        g2.fillOval(screenX-2, screenY+16, 50, 50);
//		}
		
		g2.setColor(new Color(0, 0, 0, 100));
		g2.fillOval(screenX+3, screenY+22, 40, 40);
        
		
		BufferedImage image = null;
		
		switch(direction) {
		case "s":
			if (spriteNum == 1) {
				image = s1;
			}
			if (spriteNum == 2) {
				image = s2;
			}
		break;
		case "up":			
			if (spriteNum == 1) {
				image = up1;
			}
			if (spriteNum == 2) {
				image = up2;
			}
		break;
		case "down":
			if (spriteNum == 1) {
				image = down1;
			}
			if (spriteNum == 2) {
				image = down2;
			}
		break;
		case "left":
			if (spriteNum == 1) {
				image = left1;
			}
			if (spriteNum == 2) {
				image = left2;
			}
		break;
		case "right":
			if (spriteNum == 1) {
				image = right1;
			}
			if (spriteNum == 2) {
				image = right2;
			}
		break;
		}
		
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
		
	}

}
