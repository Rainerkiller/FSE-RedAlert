package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import sun.security.provider.ConfigFile;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
    private SpriteBatch batch;
    Texture img;
    private String status = "startUp";
    ArrayList<orl> orls = new ArrayList<orl>();
    loadingClass A = new loadingClass();
    private Player role = new Player();
    private float x = 0;
    private float y = 500;
    private ArrayList<Sprite>sprites = new ArrayList<Sprite>();
    private BitmapFont font;


    private int startUpTimer = 0;
    private int menuSelection = 0; //0 for start game, 1 for guide
    private int loadingCounter = 0;
    private int spriteIndex = 0;




    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(140,140,60,1);
        A = new loadingClass("a");
        role = A.getRole();
        orl dirt = A.getDirt();
        role.setPosition("");
        for(int i = 0;i<1000;i++){
            Sprite copy = new Sprite(dirt.getCurrent());
            sprites.add(copy);
            sprites.get(i).setPosition(x,y);
            x = x+76;
            if(i%10==0){
                x = 0;
                y-=76;
            }
        }
        System.out.println(sprites.get(0).getX());
    }
    @Override
    public void render () {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        if(status.equals("startUp")){

            Sprite cover = new Sprite(new Texture("pics/cover.png"));
            cover.setPosition(0,0);
            if(startUpTimer > 10 && startUpTimer < 20){
                cover.draw(batch);
                startUpTimer += 1;
            }
            else if(startUpTimer == 20){
                startUpTimer = 0;
                cover.draw(batch);
            }
            else{
                cover.draw(batch);
                font.draw(batch, "Press any button to start.", 480, 100);
                startUpTimer+=1;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){
                status = "menu";
            }
        }
        else if(status.equals("menu")){
            Sprite menuStartGame = new Sprite(new Texture("pics/menuStartGame.png"));
            Sprite menuGuide = new Sprite(new Texture("pics/menuGuide.png"));
            menuStartGame.setPosition(0,0);
            menuGuide.setPosition(0,0);
            if(menuSelection == 0){
                menuStartGame.draw(batch);
                if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
                    menuSelection = 1;
                }
                else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

                    status = "loading";
                }
            }
            else if(menuSelection == 1){
                menuGuide.draw((batch));
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                    menuSelection = 0;
                }
                //enter guide
            }
        }




        else if(status.equals("loading")){
            ArrayList<Sprite> loadingBar = new ArrayList<Sprite>();
            for(int i = 0; i<5;i++){
                Sprite pic = new Sprite(new Texture(Gdx.files.internal("pics/loading"+Integer.toString(i+1)+".png")));
                pic.setPosition(0,0);
                loadingBar.add(pic);
            }
            if(loadingCounter%5==0){
                loadingBar.get(spriteIndex).draw(batch);
                spriteIndex += 1;

            }
            else if((loadingCounter-1)%20==0){
                spriteIndex = 0;
                loadingBar.get(spriteIndex).draw(batch);
            }

            else{
                loadingBar.get(spriteIndex).draw(batch);
            }
            if(loadingCounter == 10){
                status = "game";
            }

            loadingCounter += 1;
            System.out.println(loadingCounter);

        }




        else if(status.equals("game")) {
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                // use translate(vx,vy), translateX(vx) or translateY(vy)
                role.setPosition("up");
                if(Gdx.input.isKeyPressed(Input.Keys.A)){
                    role.setPosition("upLeft");
                }else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                    role.setPosition("upRight");
                }
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.A)){
                role.setPosition("left");
            }
            else if(Gdx.input.isKeyPressed(Input.Keys.D)){
                role.setPosition("right");
            } else if (role.getCollide()){
                role.setPosition("static");
            }else{
                role.setPosition("acc");
            }

            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                status  = "pause";
            }

            batch.begin();
            for(int i =0;i<1000;i++) {
                sprites.get(i).draw(batch);
            }
            for(int i = 0;i < 10;i++){
                orls.get(i).getCurrent().draw(batch);
            }

            role.getCurrent().draw(batch);
            batch.end();

        }
        batch.end();
    }
    @Override
    public void dispose(){
        batch.dispose();
    }
}
