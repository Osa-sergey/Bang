package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class MyGdxGame extends Game {

	private SpriteBatch batch;
	private Assets assets;
	static private OrthographicCamera camera;
	static private Viewport viewport;

	public static OrthographicCamera getCamera() {
		return camera;
	}

	public static Viewport getViewport() {
		return viewport;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		assets = new Assets();
		viewport = new StretchViewport(1920, 1080);
		camera = new OrthographicCamera(1920,1080);
		setScreen(new Menu(this));

	}

	@Override
	public void render () {
		super.render();
	}

	public SpriteBatch getBatch() {
		return batch;
	}

}
