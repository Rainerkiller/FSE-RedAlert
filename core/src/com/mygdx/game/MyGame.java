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
    String status = "game";
    ArrayList<orl> orls = new ArrayList<orl>();
    loadingClass A = new loadingClass();
    Player role = new Player();
    @Override
    public void create () {
        batch = new SpriteBatch();
        A = new loadingClass("a");
        role = A.getRole();
        orls = A.getWorld();
    }

    @Override
    public void render () {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        if(status.equals("game")) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                // use translate(vx,vy), translateX(vx) or translateY(vy)
                role.setPosition("up");
                if(Gdx.input.isKeyPressed(Input.Keys.A)){
                    role.setPosition("left");
                }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                    role.setPosition("right");
                }
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                role.setPosition("left");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                role.setPosition("right");
            } else if (role.getCollide(orls)){
                role.setPosition("static");
            }else{
                role.setPosition("acc");
            }

            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                status  = "pause";
            }
            for(int i =0;i<10;i++){
                orls.get(i).getCurrent().draw(batch);
            }
            role.getCurrent().draw(batch);

        }
        batch.end();
    }
    @Override
    public void dispose(){
        batch.dispose();
    }
}
