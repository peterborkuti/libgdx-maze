package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MazeScreen2d implements Screen {

	private Stage stage;

	private TiledMapRenderer mazeRenderer;

	private CameraInputAdapter camInput;

	public MazeScreen2d() {
		super();
		MazeCreator mazeCreator = new MazeCreator();
		mazeRenderer = mazeCreator.getMazeRenderer();

		camInput = new CameraInputAdapter();
		Actor cameraActor = new CameraActor(camInput);
		stage = new Stage(new ScreenViewport());
		Gdx.input.setInputProcessor(stage);
		stage.addActor(cameraActor);

	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act(delta);
		stage.draw();

		mazeRenderer.setView((OrthographicCamera) stage.getCamera());
		mazeRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

}
