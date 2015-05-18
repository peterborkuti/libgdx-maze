package bp.gdx.maze;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.ObjectMap;

public class CameraInputAdapter extends InputAdapter {

	private ObjectMap<Integer, Boolean> keys = new ObjectMap<Integer, Boolean>();

	public CameraInputAdapter(OrthographicCamera camera) {
		super();
		this.camera = camera;

		keys.put(Keys.PLUS, false);
		keys.put(Keys.MINUS, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.DOWN, false);
	}

	private OrthographicCamera camera;

	@Override
	public boolean keyDown(int keycode) {
		if (keys.containsKey(keycode)) {
			keys.put(keycode, true);
		}

		return super.keyDown(keycode);
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keys.containsKey(keycode)) {
			keys.put(keycode, false);
		}

		return super.keyUp(keycode);
	}
}
