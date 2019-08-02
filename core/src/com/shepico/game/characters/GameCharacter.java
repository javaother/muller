package com.shepico.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.shepico.game.GameScreen;
import com.shepico.game.Weapon;

public abstract class GameCharacter {
    Texture texture;
    Texture textureHp;
    Vector2 position;
    Vector2 direction;
    Vector2 temp;
    float speed;
    float hp, hpMax;
    GameScreen gameScreen;
    float damageEffectTimer;
    float attackTimer;
    Weapon weapon;

    public Vector2 getPosition() {
        return position;
    }

    public boolean isAlive (){
        return hp > 0;
    }

    public abstract void update (float dt);

    public void render (SpriteBatch batch, BitmapFont font24) {
        if (damageEffectTimer > 0) {
            batch.setColor(1, 1 - damageEffectTimer ,1 - damageEffectTimer ,1);
        }

        batch.draw(texture, position.x-40, position.y-40);
        batch.setColor(1, 1 ,1 ,1);
        batch.setColor(0, 0 ,0 ,1);
        batch.draw(textureHp, position.x-42, position.y + 80 - 42, 84, 15);
        batch.setColor(1, 0 ,0 ,1);
        batch.draw(textureHp, position.x-40, position.y + 80 - 40, 0, 0, hp/hpMax * 80, 11, 1, 1, 0, 0, 0, 80, 11, false, false);
        batch.setColor(1, 1 ,1 ,1);
        font24.draw(batch, String.valueOf((int) hp), position.x - 40, position.y +80 - 22, 80, 1, false);
    }


    public void checkScreenBounds(){
        if (position.x > 1280.0f) {
            position.x = 1280.0f;
        }
        if (position.x < 0.0f) {
            position.x = 0.0f;
        }

        if (position.y > 720.0f) {
            position.y = 720.0f;
        }
        if (position.y < 0.0f) {
            position.y = 0.0f;
        }
    }

    public void takeDamage (float amount){
        hp -= amount;
        damageEffectTimer += 0.5f;
        if (damageEffectTimer > 1){
            damageEffectTimer = 1;
        }
    }
}
