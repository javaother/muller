package com.shepico.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Map {
    public static final int CELLS_X = 16;
    public static final int CELLS_Y = 9;
    public static final int CELLS_SIZE = 80;

    private byte[][] dataLayer;
    private byte[][] typeLayer;

    private Texture grassTexture;
    private Texture textureWalls;
    private TextureRegion[] regions;

    public Map() {
        this.dataLayer = new byte[CELLS_X][CELLS_Y];
        this.typeLayer = new byte[CELLS_X][CELLS_Y];
        for (int i = 0; i < 15 ; i++) {
            int cellx = MathUtils.random(0, CELLS_X-1);
            int celly = MathUtils.random(0, CELLS_Y-1);
            dataLayer[cellx][celly] = 1;
            typeLayer[cellx][celly]  = (byte) MathUtils.random(0, 3);

        }

        grassTexture = new Texture("water.jpg");
        textureWalls = new Texture("walls.png");
        regions = new TextureRegion(textureWalls).split(100, 100)[0];
    }
    public boolean isCellPassable (Vector2 position){
        if (position.x < 0 || position.x > 1280 || position.y < 0 || position.y > 720 ) {
            return false;
        }
        return dataLayer[(int) (position.x / CELLS_SIZE)][(int) (position.y / CELLS_SIZE)] == 0;
    }

    public void render (SpriteBatch batch){
        for (int i=0; i<16; i++){
            for (int j=0; j<9; j++){
                batch.draw(grassTexture, i*80, j*80);                
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 9; j++) {
                if (dataLayer[i][j] == 1){
                    batch.draw(regions[typeLayer[i][j]], i*80 - 10, j * 80 - 10);
                }
                
            }
            
        }
    }

}
