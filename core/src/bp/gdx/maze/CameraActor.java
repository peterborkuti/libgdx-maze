package bp.gdx.maze;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class CameraActor extends Actor {

	CameraAdapter camAdapter;

	@Override
	public void act(float delta) {
		super.act(delta);
		camAdapter.updateCamera();
	}

	public CameraActor (EventListener cameraInputAdapter) {
		camAdapter = (CameraAdapter) cameraInputAdapter;
		this.addListener(cameraInputAdapter);
	}

	@Override
	protected void setStage(Stage stage) {
		super.setStage(stage);
		camAdapter.setCamera((OrthographicCamera) stage.getCamera());
	}


}
