package bp.gdx.maze;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class MazeInput extends InputAdapter {

	public static boolean up = false;
	public static boolean down = false;
	public static boolean plus = false;
	public static boolean minus = false;
	public static boolean space = false;
	public static boolean escape = false;

	private Movable movable; 

	public MazeInput(Movable _movable) {
		movable = _movable;
	}

	@Override
	public boolean keyDown(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			movable.setState(Movable.STATE.LEFT);
			break;
		case Keys.RIGHT:
			movable.setState(Movable.STATE.RIGHT);
			break;
		case Keys.UP:
			movable.setState(Movable.STATE.UP);
			break;
		case Keys.DOWN:
			movable.setState(Movable.STATE.DOWN);
			break;
		case Keys.PLUS:
			plus = true;
			break;
		case Keys.MINUS:
			minus = true;
			break;
		case Keys.SPACE:
			space = true;
			break;
		case Keys.ESCAPE:
			escape = true;
			break;

		}

		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		switch (keycode) {
		case Keys.LEFT:
			movable.setState(Movable.STATE.STOP);
			movable.setLastState(Movable.STATE.LEFT);
			break;
		case Keys.RIGHT:
			movable.setState(Movable.STATE.STOP);
			movable.setLastState(Movable.STATE.RIGHT);
			break;
		case Keys.UP:
			movable.setState(Movable.STATE.STOP);
			movable.setLastState(Movable.STATE.UP);
			break;
		case Keys.DOWN:
			movable.setState(Movable.STATE.STOP);
			movable.setLastState(Movable.STATE.DOWN);
			break;
		case Keys.PLUS:
			plus = false;
			break;
		case Keys.MINUS:
			minus = false;
			break;
		case Keys.SPACE:
			space = false;
			break;

		}

		return true;
	}

}
