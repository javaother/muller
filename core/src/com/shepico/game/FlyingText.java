package com.shepico.game;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class FlyingText {
    private Vector2 position;
    private Vector2 velocity;
    private StringBuilder text;
    private float time;
    private float timeMax;
    private boolean active;

    public StringBuilder getText() {
        return text;
    }

    public Vector2 getPosition() {
        return position;
    }



    public boolean isActive(){
        return active;
    }

    public void deactivate(){
        active = false;
    }

    public void setup(float x, float y, StringBuilder text){
        this.position.set(x,y);
        this.velocity.set(20, 40);
        this.active = true;
        this.time =0;
        this.text.setLength(0);
        this.text.append(text);
    }

    public FlyingText() {
        this.position = new Vector2(0,0);
        this.velocity = new Vector2(0,0);
        this.timeMax = 5;
        this.time = 0;
        this.active = false;
        this.text = new StringBuilder();
    }

    public void update (float dt){
        this.time +=dt;
        position.mulAdd(velocity, dt);
        if (time > timeMax) {
            active = false;
        }
    }
}
