package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class CameraActor extends Actor {

	BobActor bobActor = null;
	TiledMap map = null;
	float z = 0;
	final static int delay = 1; // seconds
	private static final float HYSTERESIS = 10;

	private boolean moving = false;

	class StopMoving extends RunnableAction {
		public void run() {
			Gdx.app.log("CameraActor","StopMoving");
			moving = false;
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (moving) {
			Camera cam = this.getStage().getCamera();
			Vector3 vec = new Vector3(getX(), getY(), z);
			cam.position.set(vec);
			Gdx.app.log("CameraActor", "act:" + vec);
			cam.update();
			return;
		}

		Place bobPlace = MazeUtil.getTilePlace(bobActor.getX(), bobActor.getY());

		Direction bobDirection = bobActor.getDirection();

		if (bobDirection == Direction.NONE ||
			!MazeUtil.isDoor(map, bobPlace)) {
			return;
		}

		// bob is in a door and it's direction show where it wants to go to

		Place goal = new Place(bobPlace.row, bobPlace.col);

		if (bobDirection == Direction.DOWN) {
			goal.row -= 1;
		}
		if (bobDirection == Direction.UP) {
			goal.row += 1;
		}
		if (bobDirection == Direction.LEFT) {
			goal.col -= 1;
		}
		if (bobDirection == Direction.RIGHT) {
			goal.col += 1;
		}

		if (!MazeUtil.isEmptyCell(map, goal)) {
			return;
		}

		Vector2 roomCenter = MazeUtil.getRoomCameraPosition(goal);

		if ((Math.abs(roomCenter.x - getX()) > HYSTERESIS) ||
			(Math.abs(roomCenter.y - getY()) > HYSTERESIS)) {

			StopMoving stopAction = new StopMoving();

			moving = true;

			this.addAction(
				Actions.sequence(
					Actions.moveTo(roomCenter.x, roomCenter.y), stopAction));
		}
	}

	public CameraActor(TiledMap map, BobActor bobActor) {
		this.bobActor = bobActor;
		this.map = map;
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		Vector3 vec = new Vector3(stage.getCamera().position);
		setX(vec.x);
		setY(vec.y);
		z = vec.z;
	}

}
