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
	 * Maze height, unitless, should be odd (2*n + 1)
	 */
	public static final int MAZE_HEIGHT = 11;
	/**
	 * Maze width, unitless, should be odd (2*n + 1)
	 */
	public static final int MAZE_WIDTH = 11;
	/**
	 * 1 room in the maze will be nXn in tiles
	 * but walls and doors remain 1 tile
	 */
	public static final int MAZE_MAGNIFY_TO_WORDL = 16;

	public static final int ROOM_OUTER_HEIGHT =
		TILE_SIZE * (MAZE_MAGNIFY_TO_WORDL + 1);

	public static final int ROOM_OUTER_WIDTH =
		TILE_SIZE * (MAZE_MAGNIFY_TO_WORDL + 1);
}
