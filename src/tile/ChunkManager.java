package tile;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import my_first2D_game.GamePanel;

public class ChunkManager extends Patterns{
	
	GamePanel gp;
	HashMap<Point, Chunk> chunkMap = new HashMap<>();
	HashMap<HashMap <Integer, Point>, Rectangle> discontinuedStructures = new HashMap<>();
	

	public boolean placingStructures = true;
	
	private int startX, startY;
	
	
	public ChunkManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public Chunk getChunk(int chunkX, int chunkY) {
		Point key = new Point(chunkX, chunkY);
		
		Chunk chunk = new Chunk();
		
		long seed = (chunkX*341873128712L) ^ (chunkY*132897987541L);
		Random rand = new Random(seed);
		
		for (int y = 0;y < Chunk.size;y++) {
			for (int x = 0;x < Chunk.size;x++) {
				chunk.tiles[x][y] = tileWeights[rand.nextInt(tileWeights.length)];
			}
		}
		
		if (!chunkMap.containsKey(key)) {
			Chunk newChunk = generateChunk(chunk, rand, key);
			chunkMap.put(key, newChunk);
		}
		return chunkMap.get(key);
	}
	
	
	private void placeStructure(int[][] chunkTiles, int[][] pattern, Random rand, 
		List<Rectangle> previousStructureAreas, Point point) {
		
		findCoordinatesStructure(rand, pattern, previousStructureAreas);
		
		if (placingStructures) {
		for (int y=0;y<pattern.length;y++) {
			for (int x=0;x<pattern[0].length;x++) {
				int px = startX + x;
				int py = startY + y;
			if (px>=0 && py>=0 && px < Chunk.size && py < Chunk.size) {
				chunkTiles[px][py] = pattern [y][x];
			}
		}
	}
		saveOverflow(startX, startY, pattern[0].length, pattern.length, pattern, point);
		
		previousStructureAreas.add(new Rectangle(startX, startY, pattern[0].length, pattern.length));
		}
		
}
	
	public void findCoordinatesStructure(Random rand, int[][] pattern, List<Rectangle> previousStructureAreas) {
		
		int searchX = 0;
		int searchY = 0;
		
		// Initialize
	    if (previousStructureAreas.isEmpty()) {
	        startX = rand.nextInt(Chunk.size - 4) + 2;
	        startY = rand.nextInt(Chunk.size - 4) + 2;
	        placingStructures = true;
	        return;
	    }
		
		for (int attempt = 0; attempt < 100; attempt++) {
			searchX = rand.nextInt(Chunk.size-4) + 2;
			searchY = rand.nextInt(Chunk.size-4) + 2;
			
			Rectangle structureArea = new Rectangle(0, 0, 0, 0);
			structureArea.x = searchX;
			structureArea.y = searchY;
			structureArea.width = pattern[0].length;
			structureArea.height = pattern.length;
			
			boolean overlap = false;
			
			for (Rectangle placed : previousStructureAreas) {
			if (structureArea.intersects(placed)) {
				overlap = true;
				break;
			} 
			}
			
			if(!overlap) {
				startX = searchX;
				startY = searchY;
				placingStructures = true;
				return;
			} 
		}
		
		placingStructures = false;
		
//		System.out.println("Trying to place structure at (" + searchX + ", " + searchY + ")");
//		System.out.println("Total previous structures: " + previousStructureAreas.size());

	}
	
	public void saveOverflow (int startX, int startY, int width, int height, int[][] pattern,  Point point) {
		
		if (width>(Chunk.size-startX)||height>(Chunk.size-startY)) {
		int raminingWidth = width - Chunk.size + startX;
		int raminingHeight = height - Chunk.size + startY;
		
		int contineX;
		int continueY;
		int contineWidth;
		int continueHeight;
		int condi;
		
		// structure is out of boundary at width
		if (raminingWidth <= 0) {
			contineX = startX;
			continueY = 0;
			contineWidth = width;
			continueHeight = raminingHeight;
			condi = 0;
		}
		// structure is out of boundary at height
		else if (raminingHeight <= 0) {
			contineX = 0;
			continueY = startY;
			contineWidth = raminingWidth;
			continueHeight = height;
			condi = 1;
		} else {
			return;
		}
		
//		System.out.println("Structure overflowed");
		
		Rectangle rectCache = new Rectangle(contineX, continueY, contineWidth, continueHeight);
		
		HashMap <Integer, Point> condStru = new HashMap<>();
		condStru.put(condi, point);
		discontinuedStructures.put(condStru, rectCache);
		}
	}
	
	int[] tileWeights = {
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
			1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,
			13,13,
			14,14,
			21,
			22,22,
			23,
			24
	};
	
	int timer;
	
	
	private Chunk generateChunk(Chunk chunk, Random rand, Point point) {

		List<Rectangle> previousStructureAreas = new ArrayList<>();
		List<Rectangle> disStruCache = new ArrayList<>();
		
		// add disc struc to LocalStructureAreas
		if(!discontinuedStructures.isEmpty()) {
			
			int condi1st = 0;
			int condi2nd = 1;
			
			for (HashMap<Integer, Point> condStru: discontinuedStructures.keySet()) {
				if(condStru.containsKey(condi1st)) {
					Rectangle rectCache = discontinuedStructures.get(condStru);
					if (rectCache != null) {
					int x = condStru.get(condi1st).x;
					int y = condStru.get(condi1st).y;
					if((++x) == point.x && y == point.y) {
//						System.out.println("Discontinued structure added");
					disStruCache.add(rectCache);
					}
					}
				}
				if(condStru.containsKey(condi2nd)) {
					Rectangle rectCache = discontinuedStructures.get(condStru);
					if (rectCache != null) {
					int x = condStru.get(condi2nd).x;
					int y = condStru.get(condi2nd).y;
					if(x == point.x && (++y) == point.y) {
//						System.out.println("Discontinued structure added");
					disStruCache.add(rectCache);
					}
					}
				}
			}
		
		previousStructureAreas.addAll(disStruCache);
		}
		
		disStruCache.clear();
		discontinuedStructures.clear();
		
		timer--;
		
		if (rand.nextInt(100)<50) {
			placeStructure(chunk.tiles,cageDesign1, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<20) {
			placeStructure(chunk.tiles,pipePattern1, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
		    placeStructure(chunk.tiles,boxWall, rand, previousStructureAreas, point);
		}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,boxWall1, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,maze, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,boxWall2, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,boxWall3, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,boxWall4, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,boxWall5, rand, previousStructureAreas, point);
			}
		if (rand.nextInt(100)<99) {
			placeStructure(chunk.tiles,doubleRoom, rand, previousStructureAreas, point);
			}
		

		if (rand.nextInt(100)<10&&timer<=0) {
			placeStructure(chunk.tiles,boxWall_light, rand, previousStructureAreas, point);
			timer = 20;
		}

		if (rand.nextInt(100)<10&&timer<=0) {
			placeStructure(chunk.tiles,boxWall_light2, rand, previousStructureAreas, point);
			timer = 60;
		}
		if (rand.nextInt(100)<10&&timer<=0) {
			placeStructure(chunk.tiles,boxWall_light3, rand, previousStructureAreas, point);
			timer = 120;
		}
			
//			for (int y = 0;y<20;y++) {
//				for (int x = 0;x<20;x++) {
//					System.out.println(chunk.tiles[x][y]);
//				}
//			}
		
		return chunk;
		
	}
	
	public int getTileID(int worldTileX, int worldTileY) {
		int chunkX = Math.floorDiv(worldTileX, Chunk.size);
		int chunkY = Math.floorDiv(worldTileY, Chunk.size);
		int tileX = Math.floorMod(worldTileX, Chunk.size);
		int tileY = Math.floorMod(worldTileY, Chunk.size);
		
		
		Chunk chunk = getChunk(chunkX, chunkY);
		return chunk.tiles[tileX][tileY];
	}
	
}
