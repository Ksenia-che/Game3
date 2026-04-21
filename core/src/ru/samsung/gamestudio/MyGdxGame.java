package ru.samsung.gamestudio;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.World;
import ru.samsung.gamestudio.screens.GameScreen;

import static ru.samsung.gamestudio.GameSettings.*;

public class MyGdxGame extends Game {
	public SpriteBatch batch;
	public OrthographicCamera camera;
	public GameScreen gameScreen;
	public World world;
	public Vector3 touch;
	float accumulator = 0;

	public void stepWorld() {
		float delta = Gdx.graphics.getDeltaTime();
		accumulator += delta;

		if (accumulator >= STEP_TIME) {
			accumulator -= STEP_TIME;
			world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		}
	}
	@Override
	public void create() {
		Box2D.init();
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);

		world = new World(new Vector2(0, 0), true);
		world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);

	}

	@Override
	public void dispose() {

		batch.dispose();
	}
}

