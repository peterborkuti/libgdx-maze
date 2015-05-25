package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class CameraActor extends Actor {

	CameraAdapter camAdapter;
	float z = 0;
	final static int delay = 1; // seconds

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

		CameraAdapter.STATUS state = camAdapter.getStatus();

		if (state == CameraAdapter.STATUS.NONE) {
			return;
		}

		moving = true;

		StopMoving stopAction = new StopMoving();

		MoveByAction action = null;

		float d = Const.TILE_SIZE * Const.MAZE_MAGNIFY_TO_WORDL;

		if (state == CameraAdapter.STATUS.DOWN) {
			action = Actions.moveBy(0f, -d, delay);
		}
		if (state == CameraAdapter.STATUS.UP) {
			action = Actions.moveBy(0f, d, delay);
		}
		if (state == CameraAdapter.STATUS.LEFT) {
			action = Actions.moveBy(-d, 0f, delay);
		}
		if (state == CameraAdapter.STATUS.RIGHT) {
			action = Actions.moveBy(d, 0f, delay);
		}

		this.addAction(Actions.sequence(action, stopAction));
	}

	public CameraActor(CameraAdapter cameraInputAdapter) {
		camAdapter = cameraInputAdapter;
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
