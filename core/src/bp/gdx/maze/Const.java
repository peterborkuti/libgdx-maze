package bp.gdx.maze;

public class Const {
	/**
	 * The size of the tiles (bricks, ladders) in the game in word-units.
	 * Also the size ff the enemies, but use it with care for sprites,
	 * because sprites can be resized
	 */
	public static final int TILE_SIZE = 32;
	/**
	 * Screen size in tiles. Screen is a square, nxn
	 */
	public static final int SCREEN_SIZE = 21; // measured in tiles, n x n
	/**
	 * World height in tiles, should be odd (2*n + 1)
	 */
	public static final int WORLD_HEIGHT = 21;
	/**
	 * World width in tiles, should be odd (2*n + 1)
	 */
	public static final int WORLD_WIDTH = 21;
	/**
	 * Wordl width in unit
	 */
	public static final int WORLD_WIDTH_UNIT = WORLD_WIDTH * TILE_SIZE;

}
