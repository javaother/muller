package com.shepico.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.shepico.game.GameScreen;
import com.shepico.game.MyGdxGame;

public class Monster extends GameCharacter{
    private Vector2 direction;
    private Vector2 temp;
    private float moveTimer;
    private float activityRadius;

    public Monster(GameScreen gameScreen){
        this.texture = new Texture("zomby.png");
        textureHp = new Texture("bar.png");
        position = new Vector2(400, 400);
        direction = new Vector2(0, 0);
        temp = new Vector2(0, 0 );
        this.speed = 30;
        //this.game = game;
        this.activityRadius = 200f;
        this.gameScreen = gameScreen;
        this.hpMax = 40;
        this.hp = this.hpMax;
        this.weapon = new Weapon("Rust", 40, 0.8f, 5);
    }

    @Override
    public void update(float dt){
        float dst = gameScreen.getHero().getPosition().dst(this.position);
            if(dst < activityRadius){
                temp.set(gameScreen.getHero().getPosition());
                temp.sub(this.position);
                temp.nor();
                position.mulAdd(temp, speed * dt);
            }else {

                //position.x += speed * dt;
                position.mulAdd(direction, speed * dt);
                moveTimer -= dt;
                if (moveTimer < 0.0f) {
                    moveTimer = MathUtils.random(2.0f, 4.0f);
                    direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f)).nor();
                }
            }
            if (dst < weapon.getAttackRadius()) {
                attackTimer += dt;
                if (attackTimer >= weapon.getAttackPeriod()) {
                    attackTimer = 0;
                    gameScreen.getHero().takeDamage(weapon.getDamage());
                }
            }
        damageEffectTimer -=dt;
        if (damageEffectTimer <0) {
            damageEffectTimer = 0;
        }
            checkScreenBounds();

    }

}
