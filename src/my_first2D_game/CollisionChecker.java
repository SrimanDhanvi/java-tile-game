package my_first2D_game;

import entity.Entity;

public class CollisionChecker {
	
	GamePanel gp;
	
	int entityLeftWorldX;
	int entityRightWorldX;
	int entityTopWorldY;
	int entityBottomWorldY;
	
	int entityLeftCol;
	int entityRightCol;
	int entityTopRow;
	int entityBottomRow;
	
	int tileNum1, tileNum2;
	
	int index;
	
	public CollisionChecker(GamePanel gp) {
		this.gp = gp;
	}
	
	public void checkTile(Entity entity) {
		
		entityLeftWorldX = entity.worldX + entity.solidArea.x;
		entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
		entityTopWorldY = entity.worldY + entity.solidArea.y;
		entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;
		
		entityLeftCol = Math.floorDiv(entityLeftWorldX, gp.tileSize);
		entityRightCol = Math.floorDiv(entityRightWorldX, gp.tileSize);
		entityTopRow =  Math.floorDiv(entityTopWorldY, gp.tileSize);
		entityBottomRow = Math.floorDiv(entityBottomWorldY, gp.tileSize);
		
		switch (entity.direction) {
		case "up":
			entityTopRow = Math.floorDiv(entityTopWorldY - entity.speed,gp.tileSize);
			tileNum1 = gp.chunkManger.getTileID(entityLeftCol, entityTopRow);
			tileNum2 = gp.chunkManger.getTileID(entityRightCol, entityTopRow);
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "down":
			entityBottomRow = Math.floorDiv(entityBottomWorldY + entity.speed, gp.tileSize);
			tileNum1 = gp.chunkManger.getTileID(entityLeftCol, entityBottomRow);
			tileNum2 = gp.chunkManger.getTileID(entityRightCol, entityBottomRow);
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "left":
			entityLeftCol = Math.floorDiv(entityLeftWorldX - entity.speed, gp.tileSize);
			tileNum1 = gp.chunkManger.getTileID(entityLeftCol, entityTopRow);
			tileNum2 = gp.chunkManger.getTileID(entityLeftCol, entityBottomRow);
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		case "right":
			entityRightCol = Math.floorDiv(entityRightWorldX + entity.speed, gp.tileSize);
			tileNum1 = gp.chunkManger.getTileID(entityRightCol, entityTopRow);
			tileNum2 = gp.chunkManger.getTileID(entityRightCol, entityBottomRow);
			if (gp.tileM.tile[tileNum1].collision == true || gp.tileM.tile[tileNum2].collision == true) {
				entity.collisionOn = true;
			}
			break;
		}
		
	}
	
	public int checkObject(Entity entity, boolean player) {
		
		index = 999;
		
		for(int i=0;i < gp.obj.length;i++) {
			if(gp.obj[i] != null) {
				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get abject's solid area position
				gp.obj[i].solidArea.x = gp.obj[i].worldX + gp.obj[i].solidArea.x;
				gp.obj[i].solidArea.y = gp.obj[i].worldY + gp.obj[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(gp.obj[i].solidArea)) {
					if (gp.obj[i].collision == true) {
						entity.collisionOn = true;
					}
					if (player == true) {
						index = i;
					}
				}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				gp.obj[i].solidArea.x = gp.obj[i].solidAreaDefaultX;
				gp.obj[i].solidArea.y = gp.obj[i].solidAreaDefaultY;
			}
		}
		
		return index;
	}
	// NPC or MONSTER
    public int checkEntity(Entity entity, Entity[] target) {
		index = 999;
		
		for(int i=0;i < target.length;i++) {
			if(target[i] != null) {
				//Get entity's solid area position
				entity.solidArea.x = entity.worldX + entity.solidArea.x;
				entity.solidArea.y = entity.worldY + entity.solidArea.y;
				
				// Get abject's solid area position
				target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
				target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;
				
				switch(entity.direction) {
				case "up":
					entity.solidArea.y -= entity.speed;	
					break;
				case "down":
					entity.solidArea.y += entity.speed;
					break;
				case "right":
					entity.solidArea.x += entity.speed;
					break;
				case "left":
					entity.solidArea.x -= entity.speed;
					break;
				}
				
				if(entity.solidArea.intersects(target[i].solidArea)) {
					if(target[i] != entity) {
					entity.collisionOn = true;
					index = i;
					}
			}
				
				entity.solidArea.x = entity.solidAreaDefaultX;
				entity.solidArea.y = entity.solidAreaDefaultY;
				
				target[i].solidArea.x = target[i].solidAreaDefaultX;
				target[i].solidArea.y = target[i].solidAreaDefaultY;
			}
		}
		
		return index;
    }
    
    public void checkPlayer(Entity entity) {
		//Get entity's solid area position
		entity.solidArea.x = entity.worldX + entity.solidArea.x;
		entity.solidArea.y = entity.worldY + entity.solidArea.y;
		
		// Get abject's solid area position
		gp.player.solidArea.x = gp.player.worldX + gp.player.solidArea.x;
		gp.player.solidArea.y = gp.player.worldY + gp.player.solidArea.y;
		
		switch(entity.direction) {
		case "up":
			entity.solidArea.y -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
					entity.collisionOn = true;
			}
			break;
		case "down":
			entity.solidArea.y += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
				}
			}
			break;
		case "right":
			entity.solidArea.x += entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
				}
			}
			break;
		case "left":
			entity.solidArea.x -= entity.speed;
			if(entity.solidArea.intersects(gp.player.solidArea)) {
				if(entity.solidArea.intersects(gp.player.solidArea)) {
						entity.collisionOn = true;
				}
			}
			break;
		}
		entity.solidArea.x = entity.solidAreaDefaultX;
		entity.solidArea.y = entity.solidAreaDefaultY;
		
		gp.player.solidArea.x = gp.player.solidAreaDefaultX;
		gp.player.solidArea.y = gp.player.solidAreaDefaultY;
    }

}
