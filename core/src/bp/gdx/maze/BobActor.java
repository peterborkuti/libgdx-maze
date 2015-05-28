package bp.gdx.maze;

import bp.gdx.maze.CameraActor.StopMoving;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public class BobActor extends Actor {
	public BobActor(CameraAdapter cameraInputAdapter) {
		super();
		this.camAdapter = cameraInputAdapter;
	}

	final static int delay = 1;
	private boolean moving = false;
	CameraAdapter camAdapter;
	float z = 0;

	class StopMoving extends RunnableAction {
		public void run() {
			Gdx.app.log("BobActor","StopMoving");
			moving = false;
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);

		if (moving) {
			return;
		}

		CameraAdapter.STATUS state = camAdapter.getStatus();

		if (state == CameraAdapter.STATUS.NONE) {
			return;
		}

		StopMoving stopAction = new StopMoving();

		MoveByAction action = null;

		float x = getX();
		float y = getY();

		float roomX = x / 

		float d = Const.TILE_SIZE;

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

}
