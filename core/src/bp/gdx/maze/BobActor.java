package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class BobActor extends Actor {
	private TiledMap tiledMap;
	private Direction direction = Direction.NONE;

	public Direction getDirection() {
		return direction;
	}

	public BobActor(CameraAdapter cameraInputAdapter, TiledMap tiledMap) {
		super();
		this.camAdapter = cameraInputAdapter;
		this.tiledMap = tiledMap;
		this.setWidth(Const.TILE_SIZE);
		this.setHeight(Const.TILE_SIZE);
		this.setVisible(true);
		Vector2 pos =
			MazeUtil.getTileCoordinate(
				Const.ROOM_OUTER_WIDTH / 2, Const.ROOM_OUTER_HEIGHT / 2);
		this.setPosition(pos.x, pos.y);
	}

	final static float delay = 0.2f;
	private boolean moving = false;
	CameraAdapter camAdapter;
	float z = 0;

	class StopMoving extends RunnableAction {
		public void run() {
			Gdx.app.log("BobActor", "StopMoving");
			moving = false;
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (moving) {
			return;
		}

		Direction state = camAdapter.getStatus();

		if (state == Direction.NONE) {
			return;
		}

		Rectangle bobBoundary = new Rectangle(getX(), getY(), getWidth(),
				getHeight());

		float d = Const.TILE_SIZE;

		if (state == Direction.DOWN) {
			bobBoundary.setPosition(getX(), getY() - d);
		}
		if (state == Direction.UP) {
			bobBoundary.setPosition(getX(), getY() + d);
		}
		if (state == Direction.LEFT) {
			bobBoundary.setPosition(getX() - d, getY());
		}
		if (state == Direction.RIGHT) {
			bobBoundary.setPosition(getX() + d, getY());
		}

		if (MazeUtil.isEmptyCell(tiledMap, bobBoundary.x, bobBoundary.y)) {
			direction = state;

			moving = true;

			StopMoving stopAction = new StopMoving();

			MoveToAction action = Actions.moveTo(bobBoundary.x, bobBoundary.y,
					delay);

			this.addAction(Actions.sequence(action, stopAction));
		}
	}

	private ShapeRenderer renderer = new ShapeRenderer();

	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		if (this.moving) {
			Gdx.app.log("BobActor", "draw:"  + getX() + "," + getY() + "," + getWidth() + "," + getHeight());
		}

		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.translate(getX(), getY(), 0);

		renderer.begin(ShapeType.Filled);
		renderer.setColor(Color.BLUE);
		renderer.rect(0, 0, getWidth(), getHeight());
		renderer.end();

		batch.begin();
	}

}
