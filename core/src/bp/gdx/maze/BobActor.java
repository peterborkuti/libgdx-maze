package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class BobActor extends Actor {
	public BobActor(CameraAdapter cameraInputAdapter) {
		super();
		this.camAdapter = cameraInputAdapter;
		this.setWidth(Const.TILE_SIZE);
		this.setHeight(Const.TILE_SIZE);
		this.setVisible(true);
		Vector2 pos =
			MazeUtil.getNearestTilePosition(
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
		Gdx.app.log("BobActor", "act: start");

		if (moving) {
			Gdx.app.log("BobActor", "act: bob is moving, quit");
			return;
		}

		CameraAdapter.STATUS state = camAdapter.getStatus();

		if (state == CameraAdapter.STATUS.NONE) {
			Gdx.app.log("BobActor", "act: no input, quit");
			return;
		}

		Rectangle bobBoundary = new Rectangle(getX(), getY(), getWidth(),
				getHeight());

		Rectangle roomInnerBoundary = MazeUtil.getRoomInnerBoundary(getX(),
				getY());

		float d = Const.TILE_SIZE;

		if (state == CameraAdapter.STATUS.DOWN) {
			bobBoundary.setPosition(getX(), getY() - d);
		}
		if (state == CameraAdapter.STATUS.UP) {
			bobBoundary.setPosition(getX(), getY() + d);
		}
		if (state == CameraAdapter.STATUS.LEFT) {
			bobBoundary.setPosition(getX() - d, getY());
		}
		if (state == CameraAdapter.STATUS.RIGHT) {
			bobBoundary.setPosition(getX() + d, getY());
		}

		if (roomInnerBoundary.contains(bobBoundary)) {
			Gdx.app.log("BobActor", "act: set moving");
			moving = true;

			StopMoving stopAction = new StopMoving();

			MoveToAction action = Actions.moveTo(bobBoundary.x, bobBoundary.y,
					delay);

			this.addAction(Actions.sequence(action, stopAction));
		}
		else {
			Gdx.app.log("BobActor", "act: out of bound, quit");
		}
	}

	private ShapeRenderer renderer = new ShapeRenderer();

	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		Gdx.app.log("BobActor", "draw:"  + getX() + "," + getY() + "," + getWidth() + "," + getHeight());

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
