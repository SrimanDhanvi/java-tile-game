package mobs;

import java.util.Random;

import entity.Entity;
import my_first2D_game.GamePanel;

public class Mob_Torroid extends Entity{

	public Mob_Torroid(GamePanel gp) {
		super(gp);
		
		direction = "s";
		speed = 1;
		maxLife = 4;
		life = maxLife;
		
		solidArea.x = 11;
		solidArea.y = 11;
		solidArea.width = 24;
		solidArea.height = 23;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
		getSpritImages = setUpSpriteSheet("/mob_images/toriod_spriteSheet.png",2 ,1);
		
		s1 = getSpritImages[0][0];
		s2 = getSpritImages[0][1];
		up1 = getSpritImages[0][1];
		down1 = getSpritImages[0][1];
		left1 = getSpritImages[0][1];
		right1 =  getSpritImages[0][1];
	}
	
	public void setAction() {
		
		actionLockCounter++;
		
		if(actionLockCounter == 120) {
		
		Random randMove = new Random();
		int i = randMove.nextInt(100)+1;	
		if(i<=20) {
			direction = "s";
		}
		if (i>20 && i<= 40) {
			direction = "up";
		}
		if(i>40 && i<=60) {
			direction = "down";
		}
		if (i>60 && i<=80) {
			direction = "left";
		} 
		if(i>80 && i<= 100) {
			direction = "right";
		}
		actionLockCounter = 0;
		}

	}

}
