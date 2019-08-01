package com.shepico.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {
    private Texture hero;
    private Texture textureHp;
    private int x;
    private int y;
    private float speed;
    private float hp, hpMax;

    public Hero(){
        hero = new Texture("hero.png");
        textureHp = new Texture("bar.png");
        x = 200;
        y = 200;
        speed = 200;
        hpMax = 100.0f ;
        hp = hpMax;
    }

    public void render (SpriteBatch batch){
        batch.draw(hero, x, y);
        batch.setColor(1, 0 ,0 ,1);
        batch.draw(textureHp, x, y + 85, 0, 0, hp, 11, 1, 1, 0, 0, 0, 80, 11, false, false);
        batch.setColor(1, 1 ,1 ,1);
    }

    public void update(float dt){
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= speed * dt;
        }
    }

    public void takeDamage (float amount){
        hp -= amount;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
