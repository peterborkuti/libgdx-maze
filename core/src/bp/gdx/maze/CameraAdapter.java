package bp.gdx.maze;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.EventListener;

public interface CameraAdapter extends EventListener {
	/**
	 * Sets the camera positions and updates the camera
	 */
	public void updateCamera();
	/**
	 * Sets the private camera field to use this object when updating the camera
	 */
	public void setCamera(OrthographicCamera camera);
}
