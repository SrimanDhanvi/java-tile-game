package npc;

import entity.Entity;
import my_first2D_game.GamePanel;

public class NPC_OldMan extends Entity{
	
	public NPC_OldMan(GamePanel gp) {
		super(gp);
		
		direction = "s";
		
		solidArea.x = 0;
		solidArea.y = 28;
		solidArea.width = 48;
		solidArea.height = 20;
		
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		
		getImage();
	}
	
	public void getImage() {
			getSpritImages = setUpSpriteSheet("/objects/Old_man_NPC.png",1 ,1);
			
			s1 = getSpritImages[0][0];
	}

}
