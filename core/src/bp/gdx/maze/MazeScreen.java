package bp.gdx.maze;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

public class MazeScreen implements Screen {

	private OrthographicCamera camera;
	private TiledMapRenderer mazeRenderer;

	private CameraInputAdapter camInput;

	public MazeScreen() {
		super();
		camera = new OrthographicCamera();
		MazeCreator mazeCreator = new MazeCreator();
		mazeRenderer = mazeCreator.getMazeRenderer();
		_setupCamera();
		camInput = new CameraInputAdapter(camera);
		Gdx.input.setInputProcessor(camInput);
	}

	private void _setupCamera() {
		float aspectRatio = Gdx.graphics.getWidth() / Gdx.graphics.getHeight();
		int pixelSize = Const.TILE_SIZE * Const.SCREEN_SIZE;

		camera.setToOrtho(false, aspectRatio * pixelSize, pixelSize);
		camera.update();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mazeRenderer.setView(camera);
		mazeRenderer.render();
	}

	@Override
	public void resize(int width, int height) {
		_setupCamera();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}


}
