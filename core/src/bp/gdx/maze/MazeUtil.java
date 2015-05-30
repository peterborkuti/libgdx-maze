package bp.gdx.maze;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class MazeUtil {

	public static Vector2 getNearestTilePosition(float x, float y) {
		Vector2 v = new Vector2(0, 0);
		v.x = (float) Math.ceil(x / Const.TILE_SIZE) * Const.TILE_SIZE;
		v.y = (float) Math.ceil(y / Const.TILE_SIZE) * Const.TILE_SIZE;

		return v;
	}

	public static Place WorldToMap(float x, float y) {
		int row = (int) Math.floor(x / getRoomOuterWidth());
		int col = (int) Math.floor(y / getRoomOuterHeight());

		return new Place(row, col);
	}

	public static Vector2 MapToWorld(int row, int col) {
		Vector2 v = new Vector2(0, 0);

		v.x = col * getRoomOuterWidth();
		v.y = row * getRoomOuterHeight();

		return v;
	}

	public static int getRoomOuterHeight() {
		return Const.TILE_SIZE * (Const.MAZE_MAGNIFY_TO_WORDL + 1);
	}

	public static int getRoomOuterWidth() {
		return Const.TILE_SIZE * (Const.MAZE_MAGNIFY_TO_WORDL + 1);
	}

	public static Rectangle getRoomInnerBoundary(float x, float y) {
		Rectangle boundary = getRoomBoundary(x, y);

		return
			new Rectangle(boundary.x + Const.TILE_SIZE - 1,
				boundary.y + Const.TILE_SIZE - 1,
				boundary.width - Const.TILE_SIZE + 1,
				boundary.height - Const.TILE_SIZE + 1);
	}

	public static Vector2 getRoomOuterLeftBottom(float x, float y) {
		Place p = WorldToMap(x, y);
		Vector2 v = MapToWorld(p.row, p.col);

		return v;
	}

	public static Rectangle getRoomBoundary(float x, float y){
		Vector2 v = getRoomOuterLeftBottom(x, y);
		Rectangle boundary =
			new Rectangle(v.x, v.y, getRoomOuterWidth(), getRoomOuterHeight());

		return boundary;
	};


}
