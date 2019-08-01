package com.shepico.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shepico.game.characters.Hero;
import com.shepico.game.characters.Monster;

public class GameScreen {
    private SpriteBatch batch;
    private Hero hero;
    private Monster monster;

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }

    public GameScreen (SpriteBatch batch){
        this.batch = batch;
    }

    public void create(){
        hero = new Hero(this);
        monster = new Monster(this);

    }

    public void render(){
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

    public void update (float dt){
        hero.update(dt);
        monster.update(dt);

        /*float dst = (float) Math.sqrt((hero.getX() - monster.getX()) * (hero.getX() - monster.getX()) + (hero.getY() - monster.getY()) * (hero.getY() - monster.getY()));
        if (dst < 40) {

        }*/
    }
}
