package com.shepico.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.shepico.game.characters.GameCharacter;
import com.shepico.game.characters.Hero;
import com.shepico.game.characters.Monster;

import java.util.*;

public class GameScreen {
    private SpriteBatch batch;
    private BitmapFont font24;
    private Hero hero;
    private Map map;
    private ItemsEmiter itemsEmiter;
    private List<GameCharacter> allCharaters;
    private List<Monster> allMonsters;

    private Comparator<GameCharacter> drawOrderComparator;
    ///////////////////////
    public Hero getHero() {
        return hero;
    }

    public List<Monster> getAllMonsters() {
        return allMonsters;
    }

    public Map getMap() {
        return map;
    }

    public GameScreen (SpriteBatch batch){
        this.batch = batch;
    }

    public void create(){
        map = new Map();
        allCharaters = new ArrayList<>();
        allMonsters = new ArrayList<>();
        hero = new Hero(this);
        itemsEmiter = new ItemsEmiter();
        allCharaters.addAll(Arrays.asList(
                hero,
                new Monster(this),
                new Monster(this),
                new Monster(this),
                new Monster(this),
                new Monster(this),
                new Monster(this),
                new Monster(this)
        ));
        for (int i=0; i<allCharaters.size(); i++){
            if (allCharaters.get(i) instanceof Monster){
                allMonsters.add((Monster) allCharaters.get(i));
            }
        }
        drawOrderComparator = new Comparator<GameCharacter>() {
            @Override
            public int compare(GameCharacter o1, GameCharacter o2) {
                return (int) (o2.getPosition().y - o1.getPosition().y) ;
            }
        };

        font24 = new BitmapFont(Gdx.files.internal("font24.fnt"));

    }

    public void render(){
        float dt = Gdx.graphics.getDeltaTime();
        update(dt);
        Gdx.gl.glClearColor(0.6f, 0.85f, 0.92f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        map.render(batch);
        Collections.sort(allCharaters, drawOrderComparator);
        for (int i=0; i<allCharaters.size(); i++){
            allCharaters.get(i).render(batch, font24);
        }
        itemsEmiter.render(batch);
        hero.renderHUD(batch, font24);
        batch.end();
    }

    public void update (float dt){
        for (int i=0; i<allCharaters.size(); i++){
            allCharaters.get(i).update(dt);
        }

        for (int i=0; i<allMonsters.size(); i++){
            Monster currentMonster  = allMonsters.get(i);
            if (!currentMonster.isAlive()){
                allMonsters.remove(currentMonster);
                allCharaters.remove(currentMonster);
                itemsEmiter.generaterandomItem(currentMonster.getPosition().x, currentMonster.getPosition().y);
                hero.killMonster(currentMonster);
            }

        }

        for (int i = 0; i < itemsEmiter.getItems().length; i++) {
            Item it = itemsEmiter.getItems()[i];
            if (it.isActive()) {
                float dst = hero.getPosition().dst(it.getPosition());
                if (dst <24){
                    hero.useItem(it);
                }
            }
            
        }
                
        itemsEmiter.update(dt);
    }
}
