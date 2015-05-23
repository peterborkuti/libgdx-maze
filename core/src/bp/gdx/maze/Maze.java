package bp.gdx.maze;

import com.badlogic.gdx.maps.tiled.TiledMapRenderer;


public class Maze {

	public enum PLACE {wall, empty, visited};

	private PLACE maze[][] = new PLACE[Const.MAZE_HEIGHT][Const.MAZE_WIDTH];

	private StringRenderer stringRenderer;
	private MazeTileRenderer tileRenderer; 

	public Maze(String wallFile, StringRenderer stringRenderer) {
		super();
		this.stringRenderer = stringRenderer;
		stringRenderer.setMap(maze);
		tileRenderer = new MazeTileRenderer(maze, wallFile);
	}

	public String toString() {
		return stringRenderer.toString();
	}

	public static boolean isValidPlace(int row, int col) {
		return
			((row >= 0) && (row < Const.MAZE_HEIGHT)) &&
			((col >= 0) && (col < Const.MAZE_WIDTH));
	}

	public void setPlace(int row, int col, PLACE place) {
		if (isValidPlace(row, col)) {
			maze[row][col] = place;
		}
	}

	public PLACE getPlace(int row, int col) {
		PLACE place = null;

		if (isValidPlace(row, col)) {
			place = maze[row][col];
		}

		return place;
	}

	public TiledMapRenderer getRenderer() {
		return tileRenderer.getRenderer();
	}
}
