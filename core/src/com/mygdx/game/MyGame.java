package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Sprite miner = new Sprite(new Texture("miner.jpg"));
    Rectangle box = new Rectangle(miner.getBoundingRectangle());
    String status = "game";

    ArrayList<orl> orls = new ArrayList<orl>();

    Player role = new Player();
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
        if(status.equals("game")) {
            for(int i = 0;i<orls.size();i++){

            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                // use translate(vx,vy), translateX(vx) or translateY(vy)
                role.setPosition("up");
                if(Gdx.input.isKeyPressed(Input.Keys.A)){
                    role.setPosition("left");
                }if(Gdx.input.isKeyPressed(Input.Keys.D)){
                    role.setPosition("right");
                }
            }
            
            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                status  = "pause";
            }
            batch.draw(img, 0, 0);

        }
        batch.end();
    }
}
