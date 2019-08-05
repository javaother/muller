package com.shepico.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.shepico.game.characters.GameCharacter;
import com.shepico.game.characters.Hero;
import com.shepico.game.characters.Monster;

import java.util.*;

public class GameScreen {
    private SpriteBatch batch;
    private BitmapFont font24;
    private Stage stage;
    private Hero hero;
    private Map map;
    private ItemsEmiter itemsEmiter;
    private TextEmmiter textEmiter;
    private List<GameCharacter> allCharaters;
    private List<Monster> allMonsters;

    private Comparator<GameCharacter> drawOrderComparator;
    private boolean paused;
///////////////////////

    public TextEmmiter getTextEmiter() {
        return textEmiter;
    }

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
        textEmiter = new TextEmmiter();
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
        stage = new Stage();

        Skin skin = new Skin();
        skin.add("simpleButton", new Texture("simpleButton.png"));
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("simpleButton");
        textButtonStyle.font = font24;
        TextButton pauseButton = new TextButton("PAUSE", textButtonStyle);
        TextButton exitButton = new TextButton("EXIT", textButtonStyle);
        pauseButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                paused = !paused;
            }
        });
        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.exit();
            }
        });
        Group menuGroup = new Group();
        menuGroup.addActor(pauseButton);
        menuGroup.addActor(exitButton);
        exitButton.setPosition(350, 0);
        menuGroup.setPosition(600, 650);
        stage.addActor(menuGroup);
        Gdx.input.setInputProcessor(stage);


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
        textEmiter.render(batch, font24);
        hero.renderHUD(batch, font24);
        batch.end();
        stage.draw();
    }

    public void update (float dt) {
        if (!paused) {
        for (int i = 0; i < allCharaters.size(); i++) {
            allCharaters.get(i).update(dt);
        }

        for (int i = 0; i < allMonsters.size(); i++) {
            Monster currentMonster = allMonsters.get(i);
            if (!currentMonster.isAlive()) {
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
                if (dst < 24) {
                    hero.useItem(it);
                }
            }

        }

        itemsEmiter.update(dt);
        textEmiter.update(dt);
    }
        stage.act(dt);
    }
}
