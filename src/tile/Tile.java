package tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Tile {

	public BufferedImage image;
	BufferedImage spTile;
	public boolean collision = false;
	
	public int tileNum;
	public int colorCode;
	public ArrayList<Integer> lightTileX = new ArrayList<>();
	public ArrayList<Integer> lightTileY = new ArrayList<>();
	protected int buffer = 2;
	protected int startTileX;
	protected int endTileX;
	protected int startTileY;
	protected int endTileY;
	protected int worldX;
	protected int worldY;
	protected int screenX;
	protected int screenY;
	protected int worldTileX;
	protected int worldTileY;
}
