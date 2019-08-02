package com.shepico.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.shepico.game.GameScreen;
import com.shepico.game.Weapon;

public class Monster extends GameCharacter{


    private float moveTimer;
    private float activityRadius;

    public Monster(GameScreen gameScreen){
        this.texture = new Texture("zomby.png");
        this.textureHp = new Texture("bar.png");
        this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        while(!gameScreen.getMap().isCellPassable(position)){
            this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        }

        direction = new Vector2(0, 0);
        temp = new Vector2(0, 0 );
        this.speed = 30;
        //this.game = game;
        this.activityRadius = 200f;
        this.gameScreen = gameScreen;
        this.hpMax = 40;
        this.hp = this.hpMax;
        this.weapon = new Weapon("Rust", 40, 0.8f, 3);
    }

    @Override
    public void update(float dt){
        float dst = gameScreen.getHero().getPosition().dst(this.position);
        if(dst < activityRadius){
            direction.set(gameScreen.getHero().getPosition());
            direction.sub(this.position).nor();
        }else {
            //position.x += speed * dt;
            //position.mulAdd(direction, speed * dt);
            moveTimer -= dt;
            if (moveTimer < 0.0f) {
                moveTimer = MathUtils.random(2.0f, 4.0f);
                direction.set(MathUtils.random(-1.0f, 1.0f), MathUtils.random(-1.0f, 1.0f)).nor();
            }
        }

        temp.set(this.position).mulAdd(direction, speed * dt);

        if (gameScreen.getMap().isCellPassable(temp)){
            this.position.set(temp);
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
