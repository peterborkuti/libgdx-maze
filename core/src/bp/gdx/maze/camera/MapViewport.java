package bp.gdx.maze.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MapViewport extends Viewport {

	@Override
	public void update(int screenWidth, int screenHeight, boolean centerCamera) {
		this.setScreenBounds(0, 0, screenWidth, screenHeight);
		//this.getCamera().position.set(0, 0, 0);
		super.update(screenWidth, screenHeight, centerCamera);
		Gdx.app.log("MapViewport",
			"World:(" + this.getWorldWidth() + "," + this.getWorldHeight() + 
			"), Screen:(" + this.getScreenWidth() + "," + this.getScreenHeight());
	}

	private LoggingOrthoCam cam;

	public MapViewport(int worldWidth, int worldHeight) {
		super();
		this.setScreenBounds(0, 0, worldWidth, worldHeight);
		this.setWorldSize(worldWidth * 4, worldHeight * 4);
		cam = new LoggingOrthoCam(10, 10); // dummy values, it will be set later
		this.setCamera(cam);
		this.update(worldWidth, worldHeight, true); //dummy values, resize will update it correctly
	}

	

}
