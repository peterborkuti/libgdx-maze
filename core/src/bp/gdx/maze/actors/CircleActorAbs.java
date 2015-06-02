package bp.gdx.maze.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;

public abstract class CircleActorAbs extends Actor {

	protected Color color;

	public CircleActorAbs(int width, int height, Color color, float delay) {
		super();
		this.setWidth(width);
		this.setHeight(height);
		this.setVisible(true);
		this.setPosition(0, 0);
		this.color = color;
		init();
	}

	public void init() {};

	protected float delay;
	protected boolean moving;

	public float getDelay() {
		return delay;
	}

	public void setDelay(float delay) {
		this.delay = delay;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	class StopMoving extends RunnableAction {
		public void run() {
			Gdx.app.log("CircleActor", "StopMoving");
			moving = false;
		}
	}

	private ShapeRenderer renderer = new ShapeRenderer();

	public void draw(Batch batch, float parentAlpha) {
		batch.end();

		renderer.setProjectionMatrix(batch.getProjectionMatrix());
		renderer.setTransformMatrix(batch.getTransformMatrix());
		renderer.translate(getX(), getY(), 0);

		renderer.begin(ShapeType.Filled);
		renderer.setColor(color);
		renderer.ellipse(0, 0, getWidth(), getHeight());
		renderer.end();

		batch.begin();
	}

}
