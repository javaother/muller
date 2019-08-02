package com.shepico.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	//Texture img;
	private GameScreen gameScreen;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("hero.png");
		gameScreen = new GameScreen(batch);
		gameScreen.create();

	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
		gameScreen.render();
	}

	private void update(float dt){
		gameScreen.update(dt);
    }


	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
