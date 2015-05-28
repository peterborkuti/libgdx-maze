package bp.gdx.maze;

public class Place {
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
