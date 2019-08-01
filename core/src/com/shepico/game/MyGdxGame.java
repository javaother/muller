package com.shepico.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	//Texture img;
	private Hero hero;
	private Monster monster;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		//img = new Texture("hero.png");
		hero = new Hero();
		monster = new Monster(this);
	}

	@Override
	public void render () {
		float dt = Gdx.graphics.getDeltaTime();
		update(dt);
	    Gdx.gl.glClearColor(0.6f, 0.85f, 0.92f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		//batch.draw(img, 0, 0);
		hero.render(batch);
		monster.render(batch);
		batch.end();
	}

	private void update(float dt){
		hero.update(dt);
		monster.update(dt);
		float dst = (float) Math.sqrt((hero.getX() - monster.getX()) * (hero.getX() - monster.getX()) + (hero.getY() - monster.getY()) * (hero.getY() - monster.getY()));
		if (dst < 40) {
			hero.takeDamage(dt * 10.0f);
		}
    }

	public Hero getHero() {
		return hero;
	}

	public Monster getMonster() {
		return monster;
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		//img.dispose();
	}
}
