package com.shepico.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Monster {
    private Texture texture;
    private int x;
    private int y;
    private float speed;
    private float activityRadius;

    private MyGdxGame game;

    public Monster(MyGdxGame game){
        this.texture = new Texture("zomby.png");
        this.x = 400;
        this.y = 400;
        this.speed = 125;
        this.game = game;
        this.activityRadius = 200f;
    }

    public void render (SpriteBatch batch){
        batch.draw(texture, x, y);
    }

    public void update(float dt){
        float dst = (float) Math.sqrt((game.getHero().getX() - this.x) * (game.getHero().getX() - this.x) + (game.getHero().getY() - this.y) * (game.getHero().getY() - this.y));
        if (dst < activityRadius) {
            if (x < game.getHero().getX()) {
                x += speed * dt;
            }
            if (x > game.getHero().getX()) {
                x -= speed * dt;
            }
            if (y < game.getHero().getY()) {
                y += speed * dt;
            }
            if (y > game.getHero().getY()) {
                y -= speed * dt;
            }
        }else {
            x += speed * dt;
            if (x > 1280) {
                x = 0;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
