package bp.gdx.maze.actors;

import bp.gdx.maze.Const;
import bp.gdx.maze.Direction;
import bp.gdx.maze.MazeUtil;
import bp.gdx.maze.Place;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;

public class Bouncer extends CircleActorAbs {
	private TiledMap tiledMap;
	private Direction direction;
	int dx, dy;

	public Bouncer(int width, int height, Color color, float delay, TiledMap tiledMap) {
		super(width, height, color, delay);
		this.tiledMap = tiledMap;
		direction = Direction.getRandomDirection();
		dx = direction.dx();
		dy = direction.dy();

		int row = MathUtils.random(Const.MAZE_MAGNIFY_TO_WORDL - 2) + 1;

		if (row == Const.MAZE_MAGNIFY_TO_WORDL / 2) {
			row++;
		}

		int col = MathUtils.random(Const.MAZE_MAGNIFY_TO_WORDL - 2) + 1;

		if (col == Const.MAZE_MAGNIFY_TO_WORDL / 2) {
			col++;
		}

		Vector2 pos =
				MazeUtil.getTileCoordinate(col, row);

		this.setPosition(pos.x, pos.y);
		this.delay = delay;
		this.color = color;
	}

	public void init() {
		
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (moving) {
			return;
		}

		Place place = MazeUtil.getTilePlace(getX(), getY());

		Place newPlace = new Place(place.getRow() + dy, place.getCol() + dx);

		if (!MazeUtil.isEmptyCell(tiledMap, newPlace)) {
			dx = - dx;
			dy = - dy;
			newPlace = new Place(place.getRow() + dy, place.getCol() + dx);
		}

		moving = true;

		StopMoving stopAction = new StopMoving();

		Vector2 v = MazeUtil.getTileCoordinate(newPlace.getRow(), newPlace.getCol());

		MoveToAction action = Actions.moveTo(v.x, v.y, delay);

		this.addAction(Actions.sequence(action, stopAction));
	}

}
