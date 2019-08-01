package com.shepico.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shepico.game.GameScreen;

public class Hero extends GameCharacter {


    public Hero(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.texture = new Texture("hero.png");
        this.textureHp = new Texture("bar.png");
        this.position = new Vector2(200, 200);
        this.speed = 200;
        this.hpMax = 100.0f ;
        this.hp = hpMax;
        this.weapon = new Weapon("Sword", 50, 0.5f, 4);
    }



    @Override
    public void update(float dt){
        float dst = gameScreen.getMonster().getPosition().dst(this.position);
        if(dst < weapon.getAttackPeriod()){
            attackTimer += dt;
            if (attackTimer >= weapon.getAttackPeriod()) {
                attackTimer = 0;
                gameScreen.getMonster().takeDamage(weapon.getDamage());
            }
        }

        damageEffectTimer -=dt;
        if (damageEffectTimer <0) {
            damageEffectTimer = 0;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            position.x += speed * dt;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            position.x -= speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            position.y += speed * dt;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            position.y -= speed * dt;
        }
        checkScreenBounds();
    }



}
