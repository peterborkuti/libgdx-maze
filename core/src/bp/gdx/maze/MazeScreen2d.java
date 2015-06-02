package bp.gdx.maze;

import bp.gdx.maze.actors.Bouncer;
import bp.gdx.maze.actors.Fly;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
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
		Actor bobActor = new BobActor(camInput, mazeCreator.getTiledMap());
		Actor cameraActor =
			new CameraActor(mazeCreator.getTiledMap(), (BobActor) bobActor);
		ScreenViewport svp = new ScreenViewport();
		svp.update(Const.TILE_SIZE * Const.MAZE_MAGNIFY_TO_WORDL,
				Const.TILE_SIZE * Const.MAZE_MAGNIFY_TO_WORDL, true);
		stage = new Stage(svp);
		stage.addActor(cameraActor);
		Gdx.app.log("MazeScreen2d","bob is adding to stage");
		stage.addActor(bobActor);
		Gdx.app.log("MazeScreen2d","bob adding done");
		Actor bouncers[] = new Actor[7];
		for (int i = 0; i < bouncers.length; i++) {
			//bouncers[i] = new Bouncer(32, 32, getRandomColor(Color.BLACK), (float)(Math.random() * 0.6) + 0.1f, mazeCreator.getTiledMap());
			bouncers[i] = new Fly(32, 32, getRandomColor(Color.BLACK), (float)(Math.random() * 0.6) + 0.1f, mazeCreator.getTiledMap());
			stage.addActor(bouncers[i]);
		}
	}

		private Color getRandomColor(Color notColor) {
			Color colors[] = {Color.BLACK, Color.BLUE, Color.CYAN, Color.GRAY, Color.GREEN, Color.MAGENTA};
			return colors[MathUtils.random(colors.length - 1)];
		}

	@Override
	public void show() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(camInput);
		//Stage's InputAdapters was not called with keyboard events.
		//I think, it is logical, because keyboard events are global
		multiplexer.addProcessor(stage);
		Gdx.input.setInputProcessor(multiplexer);
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
