package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;

    Sprite miner = new Sprite(new Texture("miner.jpg"));

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            // use translate(vx,vy), translateX(vx) or translateY(vy)
            miner.setPosition(miner.getX(),miner.getY()+1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.S)){
            miner.setPosition(miner.getX(),miner.getY()-1);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.A)){
            miner.setPosition(miner.getX()-1,miner.getY());

        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            miner.setPosition(miner.getX()+1,miner.getY());
        }
        if (Gdx.input.isKeyPressed(Input.Keys.P)){

        }        batch.draw(img, 0, 0);
        batch.end();
    }
}
