package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;

public class MazeCreator {
	class Place {
		int row;
		int col;

		public Place(int row, int col) {
			super();
			this.row = row;
			this.col = col;
		}

		@Override
		public String toString() {
			return "Place [row=" + row + ", col=" + col + "]";
		}
	}

	enum PLACE {wall, empty, visited};

	PLACE maze[][] = new PLACE[Const.WORLD_HEIGHT][Const.WORLD_WIDTH];

	public MazeCreator() {
		createMaze();
		Gdx.app.log("MazeCreator", "\n" + this.toString());
	}

	public String toString() {
		char carr[] = new char[(Const.WORLD_WIDTH + 1) * Const.WORLD_HEIGHT];

		for (int r = 0; r < Const.WORLD_HEIGHT; r++) {
			for (int c = 0; c < Const.WORLD_WIDTH; c++) {
				char ch = (maze[r][c] == PLACE.wall) ? '*' : ' ';
				carr[ r * (Const.WORLD_WIDTH + 1) + c] = ch;
			}
			carr[ (r + 1) * (Const.WORLD_WIDTH + 1) - 1 ] = '\n';
		}

		return new String(carr);
	}

	/*
		***********
		***********
		***********
		***********
		***********
		***********
		***********
		***********
		***********
		***********
		***********
	*/
	private void fillWithWalls() {
		for (int r = 0; r < Const.WORLD_HEIGHT; r++) {
			for (int c = 0; c < Const.WORLD_WIDTH; c++) {
				maze[r][c] = PLACE.wall;
			}
		}
	}

	/*
		***********
		* * * * * *
		***********
		* * * * * *
		***********
		* * * * * *
		***********
		* * * * * *
		***********
		* * * * * *
		***********
	 */
	private void createDefaultRooms() {
		for (int r = 1; r < Const.WORLD_HEIGHT; r += 2) {
			for (int c = 1; c < Const.WORLD_WIDTH; c += 2) {
				maze[r][c] = PLACE.empty;
			}
		}
	}

	/**
	 * Exit (or Entry) must be on the left or right side
	 */
	private Place createExit() {
		// exit should be on the left or right side
		int c = (Math.random() < 0.5) ? 0 : Const.WORLD_WIDTH - 1;
		// exit should be on odd numbers
		int r = MathUtils.random(Const.WORLD_HEIGHT / 2) * 2 + 1;
		return new Place(r, c);
	}

	private void log() {
		Gdx.app.log("MazeCreator", "\n" + this.toString());
	}

	/*
		***********
		*         *
		* *** *** *
		* * *   * *
		* * *** ***
		*     * *     <= Exit/Entry point
		***** * * *
		*     *   *
		* *********
		*         *
		***********
	 */
	private void createMaze() {
		fillWithWalls();

		createDefaultRooms();

		Place exit = createExit();

		maze[exit.row][exit.col] = PLACE.empty;

		Place nextRoom =
			new Place(
				exit.row, (exit.col == 0) ? 1 : exit.col -1);

		createRoom(nextRoom);
	}

	//Ügyes vagy apa! :-)    [^_^]    [˘I˘]    [˙__˙]
	
	/**
	 * http://en.wikipedia.org/wiki/Maze_generation_algorithm
	 * Depth-first search
	 * Quotations from the wikipedia
	 */
	private void createRoom(Place room) {
		// "Mark the current cell as visited"
		maze[room.row][room.col] = PLACE.visited;

		// "and get a list of its neighbors"
		// it may be a bit faster if I get only the
		// not visited neighbors, but definitely consumes
		// less memory than if I use a list with all
		// neighbor rooms
		Array<Place> neighbours = getNotVisitedNeighbours(room);

		// no neighbors, nothing to do, exit
		if (neighbours.size == 0) {
			return;
		}

		// "starting with a randomly selected neighbor"
		neighbours.shuffle();

		// "For each neighbor"
		while (neighbours.size > 0) {
			Place nextRoom = neighbours.pop();

			// "If that neighbor hasn't been visited,"
			// it must be checked here also
			// (getNotVisitedNeighbours is not enough)
			// because as time goes by algorithm visits
			// places
			if (!isVisitedOrOutOfWorld(nextRoom)) {
				// "remove the wall between this cell and that neighbor"
				maze[(room.row + nextRoom.row) / 2][(room.col + nextRoom.col) / 2] =
					PLACE.empty;

				// "and then recur with that neighbor as the current cell"
				createRoom(nextRoom);
			}
		}
	}

	private boolean isVisitedOrOutOfWorld(Place place) {
		int row = place.row;
		int col = place.col;

		return
			(row < 0 || row >= Const.WORLD_HEIGHT) ||
			(col < 0 || col >= Const.WORLD_WIDTH) ||
			maze[row][col] == PLACE.visited;
	}

	/**
	 * Creates an array of the not visited neighbor rooms
	 * @param room
	 * @return
	 */
	private Array<Place> getNotVisitedNeighbours(Place room) {
		Array<Place> notVisited = new Array<Place>(true, 4);

		int r = room.row;
		int c = room.col;

		// there are WALLS or DOORS at (r,c-1),(r,c+1),...
		// because the walls are also consumes 1 place
		Place[] neighbours = {
			new Place(r, c - 2), new Place(r, c + 2),
			new Place(r - 2, c), new Place(r + 2, c)
		};

		for (Place p : neighbours) {
			if (!isVisitedOrOutOfWorld(p)) notVisited.add(p);
		}

		return notVisited;
	}


}
