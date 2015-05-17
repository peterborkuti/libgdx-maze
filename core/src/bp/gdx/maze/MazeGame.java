package bp.gdx.maze;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

public class MazeGame extends Game {

	@Override
	public void create () {
		this.setScreen(new MazeScreen());
	}

	@Override
	public void render () {
		super.render();
	}
}
