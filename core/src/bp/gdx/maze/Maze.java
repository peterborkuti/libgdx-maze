package bp.gdx.maze;


public class Maze {

	public static final char WALL = 'H';
	public static final char NOTWALL = ' ';

	public enum PLACE {wall, empty, visited};

	private PLACE maze[][] = new PLACE[Const.WORLD_HEIGHT][Const.WORLD_WIDTH];

	public String toString() {
		char carr[] = new char[(Const.WORLD_WIDTH + 1) * Const.WORLD_HEIGHT];

		for (int r = 0; r < Const.WORLD_HEIGHT; r++) {
			for (int c = 0; c < Const.WORLD_WIDTH; c++) {
				char ch = (maze[r][c] == PLACE.wall) ? WALL : NOTWALL;
				carr[ r * (Const.WORLD_WIDTH + 1) + c] = ch;
			}
			carr[ (r + 1) * (Const.WORLD_WIDTH + 1) - 1 ] = '\n';
		}

		return new String(carr);
	}

	public static boolean isValidPlace(int row, int col) {
		return
			((row >= 0) && (row < Const.WORLD_HEIGHT)) &&
			((col >= 0) && (col < Const.WORLD_WIDTH));
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
}
