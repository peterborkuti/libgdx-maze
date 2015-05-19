package bp.gdx.maze;

import com.badlogic.gdx.Game;

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
