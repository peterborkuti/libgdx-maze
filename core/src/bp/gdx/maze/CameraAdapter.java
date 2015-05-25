package bp.gdx.maze;


public interface CameraAdapter {
	public enum STATUS {UP, DOWN, LEFT, RIGHT, NONE};
	/**
	 * Sets the camera positions and updates the camera
	 */
	public STATUS getStatus();

}
