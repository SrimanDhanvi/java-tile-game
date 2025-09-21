package my_first2D_game;

import mobs.Mob_Torroid;
import npc.NPC_OldMan;
import object.OBJ_health;

public class AssetSetter {

	GamePanel gp;
	
	int timer;
	int spawnRange = 1000;
	
	public AssetSetter(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObj() {
		
   		gp.obj[0] = new OBJ_health(gp);
   		gp.obj[0].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		gp.obj[0].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
		
	}
	
	public void objRespawn(int objectNum, boolean condition) {
	       if (gp.obj[objectNum] == null&&condition) {
	    	   
	   		gp.obj[objectNum] = new OBJ_health(gp);
	   		gp.obj[objectNum].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
	   		gp.obj[objectNum].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
	   		}
	          
	          timer++;
	          
	          if(timer>9000) {
	          gp.obj[objectNum].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
	   		gp.obj[objectNum].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
	   		timer = 0;
	   	}
	}
	
	public void setNPC() {
		
		gp.npc[0] = new NPC_OldMan(gp);
		gp.npc[0].worldX = 0;
		gp.npc[0].worldY = 100;
	}
	
	public void setMob() {
		gp.monster[0] = new Mob_Torroid(gp);
   		gp.monster[0].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		gp.monster[0].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		
		gp.monster[1] = new Mob_Torroid(gp);
   		gp.monster[1].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		gp.monster[1].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		
		gp.monster[2] = new Mob_Torroid(gp);
   		gp.monster[2].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		gp.monster[2].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		
		gp.monster[3] = new Mob_Torroid(gp);
   		gp.monster[3].worldX = gp.player.worldX + (int)(Math.random() * 2 * spawnRange - spawnRange);
   		gp.monster[3].worldY = gp.player.worldY + (int)(Math.random() * 2 * spawnRange - spawnRange);
	}

}
