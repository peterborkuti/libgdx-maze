package bp.gdx.maze.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bp.gdx.maze.Const;
import bp.gdx.maze.MazeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Const.TILE_SIZE * (Const.MAZE_MAGNIFY_TO_WORDL + 1);
		config.height = Const.TILE_SIZE * (Const.MAZE_MAGNIFY_TO_WORDL + 1);
		new LwjglApplication(new MazeGame(), config);
	}
}
