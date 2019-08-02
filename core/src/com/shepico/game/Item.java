package com.shepico.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Item {
    public enum Type {
        COINS(0);

        int index;

        Type (int index) {
            this.index = index;
        }

    }
    private Vector2 position;
    private Vector2 velocity;
    private Type type;
    private float time;
    private float timeMax;
    private boolean active;

    public Vector2 getPosition() {
        return position;
    }

    public Type getType() {
        return type;
    }

    public boolean isActive(){
        return active;
    }

    public void deactivate(){
        active = false;
    }

    public void setup(float x, float y, Type type){
        this.position.set(x,y);
        this.velocity.set(MathUtils.random(-50, 50), MathUtils.random(-50, 50));
        this.type = type;
        this.active = true;
        this.time =0;
    }

    public Item() {
        this.position = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        this.type = Type.COINS;
        this.timeMax = 5;
        this.time = 0;
        this.active = false;
    }

    public void update (float dt){
        this.time +=dt;
        position.mulAdd(velocity, dt);
        if (time > timeMax) {
            active = false;
        }
    }
}
