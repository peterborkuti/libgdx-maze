package bp.gdx.maze;

import bp.gdx.maze.Maze.PLACE;

public class MazeStringRenderer implements StringRenderer {

	private final Maze maze;

	public MazeStringRenderer(Maze maze) {
		this.maze = maze;
	}

	public String toString() {
		char carr[] = new char[(Const.MAZE_WIDTH + 1) * Const.MAZE_HEIGHT];

		for (int r = 0; r < Const.MAZE_HEIGHT; r++) {
			for (int c = 0; c < Const.MAZE_WIDTH; c++) {
				char ch = (maze.getPlace(r, c) == PLACE.wall) ? WALL : NOTWALL;
				carr[ (Const.MAZE_HEIGHT - r - 1) * (Const.MAZE_WIDTH + 1) + c] = ch;
			}
			carr[ (r + 1) * (Const.MAZE_WIDTH + 1) - 1 ] = '\n';
		}

		return new String(carr);
	}

	public void render() {
		this.toString();
	}

}
