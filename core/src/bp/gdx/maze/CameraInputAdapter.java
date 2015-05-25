package bp.gdx.maze;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.ObjectMap;

public class CameraInputAdapter extends InputListener implements CameraAdapter {
	private float rotationSpeed = 0.5f;

	private ObjectMap<Integer, Boolean> keys = new ObjectMap<Integer, Boolean>();

	private OrthographicCamera camera;

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
	public void setCamera(OrthographicCamera camera) {
		this.camera = camera;
	}

	@Override
	public boolean keyDown(InputEvent event, int keycode) {
		if (keys.containsKey(keycode)) {
			keys.put(keycode, true);
		}

		return super.keyDown(event, keycode);
	}

	@Override
	public boolean keyUp(InputEvent event, int keycode) {
		if (keys.containsKey(keycode)) {
			keys.put(keycode, false);
		}

		return super.keyUp(event, keycode);
	}

	private boolean isKeyPressed(int keycode) {
		boolean retVal = false;

		if (keys.containsKey(keycode)) {
			retVal = keys.get(keycode);
		}

		return retVal;
	}

	@Override
	public void updateCamera() {
		if (isKeyPressed(Keys.A)) {
			camera.zoom += 0.02;
		}
		if (isKeyPressed(Keys.Q)) {
			camera.zoom -= 0.02;
		}
		if (isKeyPressed(Keys.LEFT)) {
			camera.translate(-3, 0, 0);
		}
		if (isKeyPressed(Keys.RIGHT)) {
			camera.translate(3, 0, 0);
		}
		if (isKeyPressed(Keys.DOWN)) {
			camera.translate(0, -3, 0);
		}
		if (isKeyPressed(Keys.UP)) {
			camera.translate(0, 3, 0);
		}
		if (isKeyPressed(Keys.W)) {
			camera.rotate(-rotationSpeed, 0, 0, 1);
		}
		if (isKeyPressed(Keys.E)) {
			camera.rotate(rotationSpeed, 0, 0, 1);
		}

		camera.update();

	}
}
