package bp.gdx.maze.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class LoggingOrthoCam extends OrthographicCamera {

	public LoggingOrthoCam(float viewportWidth, float viewportHeight) {
		super(viewportWidth, viewportHeight);
	}

	@Override
	public String toString() {
		String s = "[" + 
			"viewport(" + this.viewportWidth + "," + this.viewportHeight + ")" +
			", pos(" + this.position.x + "," + this.position.y + ")";
		return s;
	}

}
