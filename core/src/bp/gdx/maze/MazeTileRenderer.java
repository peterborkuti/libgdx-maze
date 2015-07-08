package bp.gdx.maze;

import bp.gdx.maze.Maze.PLACE;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.maps.tiled.tiles.StaticTiledMapTile;

public class MazeTileRenderer {
	protected TiledMap map;
	protected TiledMapRenderer renderer;
	protected StaticTiledMapTile wallTile = null;
	protected final Maze maze;
	protected int mazeMagnifyToWorld = Const.MAZE_MAGNIFY_TO_WORDL;

	public MazeTileRenderer(Maze maze, String wallFile) {
		Texture wall = new Texture(wallFile);
		wallTile = new StaticTiledMapTile(new TextureRegion(wall));
		this.maze = maze;
	}

	public TiledMap getMap() {
		return map;
	}

	private void fillWithWalls(TiledMapTileLayer layer) {
		for (int r = 0; r < layer.getHeight(); r++) {
			for (int c = 0; c < layer.getWidth(); c++) {
				Cell cell = new Cell();
				cell.setTile(wallTile);
				layer.setCell(c, r, cell);
			}
		}
	}

	

	private void createRooms(TiledMapTileLayer layer) {
		for (int r = 1; r < Const.MAZE_HEIGHT; r += 2) {
			for (int c = 1; c < Const.MAZE_WIDTH; c += 2) {
				createRoom(
					c * mazeMagnifyToWorld / 2,
					r * mazeMagnifyToWorld / 2, layer);
			}
		}
	}

	private void createRoom(int col, int row, TiledMapTileLayer layer) {
		Cell cell = null;

		int halfRoom = mazeMagnifyToWorld / 2 - 1;

		for (int r = row - halfRoom; r < row + halfRoom + 1; r++) {
			for (int c = col - halfRoom; c < col + halfRoom + 1; c++) {
				layer.setCell(c, r, cell);
			}
		}
	}

	private void createLayerFromMaze(TiledMapTileLayer layer) {
		fillWithWalls(layer);
		createRooms(layer);
		createDoors(layer);
	}

	private void createDoors(TiledMapTileLayer layer) {
		Cell cell = null;

		//doors in rows
		for (int r = 0; r < Const.MAZE_HEIGHT; r += 2) {
			for (int c = 0; c < Const.MAZE_WIDTH; c += 1) {
				if (maze.getPlace(r, c) == PLACE.empty) {
					layer.setCell(
						c * mazeMagnifyToWorld / 2,
						r * mazeMagnifyToWorld / 2, cell);
				}
			}
		}

		//doors in columns
		for (int r = 0; r < Const.MAZE_HEIGHT; r += 1) {
			for (int c = 0; c < Const.MAZE_WIDTH; c += 2) {
				if (maze.getPlace(r, c) == PLACE.empty) {
					layer.setCell(
						c * mazeMagnifyToWorld / 2,
						r * mazeMagnifyToWorld / 2, cell);
				}
			}
		}
	}

	public void createTiledMap() {
		int numOfRoomsX = (Const.MAZE_WIDTH - 1) / 2;
		int numOfRoomsY = (Const.MAZE_HEIGHT - 1) / 2;
		int mapWidth = numOfRoomsX * mazeMagnifyToWorld + 1;
		int mapHeight = numOfRoomsY * mazeMagnifyToWorld + 1;

		TiledMapTileLayer layer =
				new TiledMapTileLayer(
					mapWidth, mapHeight,
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

	public TiledMap getTiledMap() {
		return map;
	}

}
