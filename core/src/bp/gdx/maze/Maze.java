package bp.gdx.maze;

import bp.gdx.maze.Const;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;


public class Maze {

	public static final char WALL = 'H';
	public static final char NOTWALL = ' ';

	private TiledMap map;
	private TiledMapRenderer renderer;
	private StaticTiledMapTile wallTile = null;

	public enum PLACE {wall, empty, visited};

	private PLACE maze[][] = new PLACE[Const.WORLD_HEIGHT][Const.WORLD_WIDTH];

	public Maze(String wallFile) {
		super();
		Texture wall = new Texture(wallFile);
		wallTile = new StaticTiledMapTile(new TextureRegion(wall));
	}

	public String toString() {
		char carr[] = new char[(Const.WORLD_WIDTH + 1) * Const.WORLD_HEIGHT];

		for (int r = 0; r < Const.WORLD_HEIGHT; r++) {
			for (int c = 0; c < Const.WORLD_WIDTH; c++) {
				char ch = (maze[r][c] == PLACE.wall) ? WALL : NOTWALL;
				carr[ r * (Const.WORLD_WIDTH + 1) + c] = ch;
			}
			carr[ (r + 1) * (Const.WORLD_WIDTH + 1) - 1 ] = '\n';
		}

		return new String(carr);
	}

	public static boolean isValidPlace(int row, int col) {
		return
			((row >= 0) && (row < Const.WORLD_HEIGHT)) &&
			((col >= 0) && (col < Const.WORLD_WIDTH));
	}

	public void setPlace(int row, int col, PLACE place) {
		if (isValidPlace(row, col)) {
			maze[row][col] = place;
		}
	}

	public PLACE getPlace(int row, int col) {
		PLACE place = null;

		if (isValidPlace(row, col)) {
			place = maze[row][col];
		}

		return place;
	}

	private void createLayerFromMaze(TiledMapTileLayer layer) {
		for (int r = 0; r < Const.WORLD_HEIGHT; r++) {
			for (int c = 0; c < Const.WORLD_WIDTH; c++) {
				if (maze[r][c] == PLACE.wall) {
					Cell cell = new Cell();
					cell.setTile(wallTile);
					layer.setCell(c, r, cell);
				}
			}
		}
	}

	public void createTiledMap() {
		TiledMapTileLayer layer =
				new TiledMapTileLayer(
					Const.WORLD_WIDTH, Const.WORLD_HEIGHT,
					Const.TILE_SIZE, Const.TILE_SIZE);

		createLayerFromMaze(layer);

		map = new TiledMap();
		map.getLayers().add(layer);

		renderer = new OrthogonalTiledMapRenderer(map);
	}
	public TiledMapRenderer getRenderer() {
		if (renderer == null) {
			createTiledMap();
		}

		return renderer;
	}
}
