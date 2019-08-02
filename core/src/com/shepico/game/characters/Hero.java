package com.shepico.game.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.shepico.game.GameScreen;
import com.shepico.game.Item;
import com.shepico.game.Weapon;

public class Hero extends GameCharacter {

    private int coins;
    private int level;
    private int exp;
    private int[] expTo = {0, 0, 100, 300, 600};

    public Hero(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        this.level = 1;
        this.texture = new Texture("hero.png");
        this.textureHp = new Texture("bar.png");
        this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        while(!gameScreen.getMap().isCellPassable(position)){
            this.position = new Vector2(MathUtils.random(0, 1280), MathUtils.random(0, 720));
        }
        direction = new Vector2(0, 0);
        temp = new Vector2(0, 0 );
        this.speed = 200;
        this.hpMax = 100.0f ;
        this.hp = hpMax;
        this.weapon = new Weapon("Sword", 50, 0.5f, 5);
    }

    public void renderHUD(SpriteBatch batch, BitmapFont font24){
        font24.draw(batch, "Knight: Pavel\nExp: " + exp + "/ " + expTo[level+1] +"\nScore: 200000\nCoins: " + coins + "\nLevel: " + level, 20, 700);
    }

    @Override
    public void update(float dt){
        damageEffectTimer -=dt;
        if (damageEffectTimer <0) {
            damageEffectTimer = 0;
        }
        Monster nearMonster = null;
        float minDst = Float.MAX_VALUE;
        for (int i = 0; i < gameScreen.getAllMonsters().size() ; i++) {
            float dst = gameScreen.getAllMonsters().get(i).getPosition().dst(this.position);
            if (dst < minDst) {
                minDst = dst;
                nearMonster = gameScreen.getAllMonsters().get(i);
            }
        }

        if(nearMonster != null && minDst < weapon.getAttackPeriod()){
            attackTimer += dt;
            if (attackTimer >= weapon.getAttackPeriod()) {
                attackTimer = 0;
                nearMonster.takeDamage(weapon.getDamage());
            }
        }

        direction.set(0, 0);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            //position.x += speed * dt;
            direction.x = 1;
        }
       if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            //position.x -= speed * dt;
            direction.x = -1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            //position.y += speed * dt;
            direction.y = 1;
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            //position.y -= speed * dt;
            direction.y = -1;
        }
        temp.set(position).mulAdd(direction, speed * dt);

        if (gameScreen.getMap().isCellPassable(temp)){
            position.set(temp);
        }
        checkScreenBounds();
    }

    public void useItem(Item it){
        switch (it.getType()){
            case COINS:
                coins +=1;
                break;
        }
        it.deactivate();
    }

    public void killMonster(Monster monster){
        exp += monster.hpMax*5;
        if (exp >= expTo[level + 1]){
            level++;
            exp -= expTo[level];
            hpMax += 10;
            hp = hpMax;
        }
    }

}
