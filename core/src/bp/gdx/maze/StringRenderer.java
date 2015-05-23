/**
 * 
 */
package bp.gdx.maze;

import bp.gdx.maze.Maze.PLACE;

/**
 * @author Peter Borkuti
 *
 */
public interface StringRenderer {
	public static final char WALL = 'H';
	public static final char NOTWALL = ' ';

	public void setMap(PLACE map[][]);

}
