package bp.gdx.maze;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.ObjectMap;

public class CameraInputAdapter extends InputAdapter implements CameraAdapter {

	private STATUS status = STATUS.NONE;

	private ObjectMap<Integer, Boolean> keys = new ObjectMap<Integer, Boolean>();

	public CameraInputAdapter() {
		super();

		keys.put(Keys.A, false);
		keys.put(Keys.Q, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
		keys.put(Keys.W, false);
		keys.put(Keys.E, false);
	}

	@Override
	public boolean keyDown(int keycode) {
		Gdx.app.log("CameraInputAdapter", "keydown" + keycode);
		if (keys.containsKey(keycode)) {
			keys.put(keycode, true);
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		Gdx.app.log("CameraInputAdapter", "keyup" + keycode);
		if (keys.containsKey(keycode)) {
			keys.put(keycode, false);
			return true;
		}

		return false;
	}

	private boolean isKeyPressed(int keycode) {
		boolean retVal = false;

		if (keys.containsKey(keycode)) {
			retVal = keys.get(keycode);
		}

		return retVal;
	}

	private void update() {

		if (isKeyPressed(Keys.LEFT)) {
			status = STATUS.LEFT;
		}
		else if (isKeyPressed(Keys.RIGHT)) {
			status = STATUS.RIGHT;
		}
		else if (isKeyPressed(Keys.DOWN)) {
			status = STATUS.DOWN;
		}
		else if (isKeyPressed(Keys.UP)) {
			status = STATUS.UP;
		}
		else {
			status = STATUS.NONE;
		}
	}

	@Override
	public STATUS getStatus() {
		update();
		return status;
	}

}
