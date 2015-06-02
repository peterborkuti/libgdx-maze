package bp.gdx.maze;

public enum Direction {
	UP(0,1), DOWN(0,-1), LEFT(-1,0), RIGHT(1,0), NONE(0,0);

	private final int dx, dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}

	public int dx() { return dx; }
	public int dy() { return dy; }

	public static Direction getRandomDirection(){
		int len = Direction.values().length - 1;
		return Direction.values()[(int)(Math.random() * len)];
	}

}