package bp.gdx.maze.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import bp.gdx.maze.Const;
import bp.gdx.maze.MazeGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = Const.ROOM_OUTER_WIDTH;
		config.height = Const.ROOM_OUTER_HEIGHT;
		new LwjglApplication(new MazeGame(), config);
	}
}
