package tile;

public class Chunk {
	public static final int size = 20;
	public int[][] tiles = new int[size][size];
	
	public Chunk() {
		for (int y = 0;y<size;y++) {
			for (int x = 0;x<size;x++) {
				tiles[x][y] = 0;
			}
		}
	}

}
