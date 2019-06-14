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
    SpriteBatch batch;
    Texture img;
    String status = "game";
    ArrayList<orl> orls = new ArrayList<orl>();
    loadingClass A = new loadingClass();
    Player role = new Player();
    float x = 641;
    float y = 477;
    float constantX=5;
    float constantY=5;
    ArrayList<Sprite>sprites = new ArrayList<Sprite>();
    BitmapFont font;
    Rectangle worldRect = new Rectangle(541,327,200,300);
    ArrayList<Monster> monsters = new ArrayList<Monster>(
    );
    int startUpTimer = 0;
    int menuSelection = 0; //0 for start game, 1 for guide
    int loadingCounter = 0;
    int spriteIndex = 0;




    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(140,140,60,1);
        A = new loadingClass("a");
        role = A.getRole();
        orl dirt = A.getDirt();
        orl coal = A.getCoal();
        role.setPosition("");
        for(int i = 0;i<3000;i++){
            orl copy2 = new orl(coal);
            orls.add(copy2);
            orls.get(i).setPostion(x,y);
            x = x+76;
            if(i%30==0){
                x = 0;
                y-=76;
            }
        }
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

        }


        else if(status.equals("game")) {

            if(role.getBody().overlaps(worldRect)) {
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    // use translate(vx,vy), translateX(vx) or translateY(vy)
                    role.setPosition("up");
                    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                        role.setPosition("upLeft");
                    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                        role.setPosition("upRight");
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                    if (role.getCollideLeft(orls)) {
                        if (orls.indexOf(role.getCollideOrlLeft(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlLeft(orls))).mining();
                        }
                    } else if (!role.getCollide(orls)) {
                        role.setPosition("left");
                        role.setPosition("acc");
                    } else {
                        role.setPosition("left");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    role.setPosition("right");
                    if (!role.getCollide(orls)) {
                        role.setPosition("acc");
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.S) && role.getCollide(orls)) {
                    if (orls.indexOf(role.getCollideOrl(orls)) != -1) {
                        orls.get(orls.indexOf(role.getCollideOrl(orls))).mining();
                    }
                } else if (role.getCollide(orls)) {
                    role.setPosition("static");
                } else {
                    role.setPosition("acc");
                }
            }else {
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    role.setPosition("up");
                    moveWorld(0,-constantY);
                    if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                        role.setPosition("upLeft");
                        moveWorld(constantX,-constantY);
                    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                        role.setPosition("upRight");
                        moveWorld(-constantX,-constantY);
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    System.out.println(role.getCollideLeft(orls));

                    if (role.getCollideLeft(orls)) {
                        if (orls.indexOf(role.getCollideOrlLeft(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlLeft(orls))).mining();
                        }
                    } else if (!role.getCollide(orls)) {
                        role.setPosition("left");
                        role.setPosition("acc");
                        moveWorld(constantX,0);
                        role.setPosition("fakeAcc");
                        moveWorld(0,role.getSpeed());
                    } else {
                        moveWorld(constantX,0);
                        role.setPosition("left");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    role.setPosition("right");
                    moveWorld(-constantX,0);
                    if (!role.getCollide(orls)) {
                        role.setPosition("acc");
                        role.setPosition("fakeAcc");
                        moveWorld(0,role.getSpeed());
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.S) && role.getCollide(orls)) {
                    if (orls.indexOf(role.getCollideOrl(orls)) != -1) {
                        orls.get(orls.indexOf(role.getCollideOrl(orls))).mining();
                    }
                } else if (role.getCollide(orls)) {
                    role.setPosition("static");
                } else {
                    role.setPosition("fakeAcc");
                    role.setPosition("acc");
                    moveWorld(0,role.getSpeed());
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                status  = "pause";
            }
            if(role.getCollideOrl(orls).getOrl()){
                role.getCollideOrl(orls).pickUp();
            }
            for(int i =0;i<orls.size();i++) {
                if(orls.get(i).Appear) {
                    if(orls.get(i).getCurrent().getX()>-100&&orls.get(i).getCurrent().getX()<1300) {
                        if(orls.get(i).getCurrent().getY()>-100&&orls.get(i).getCurrent().getY()<1000) {
                            if(!orls.get(i).getName().equals("dirt")){
                                orls.get(i).getCurrent().draw(batch);
                            }
                        }
                    }
                }else{
                    orls.remove(i);
                }
            }
            role.getCurrent().draw(batch);

        }
        batch.end();
    }
    @Override
    public void dispose(){
        batch.dispose();
    }
    public void moveWorld(float x,float y){
        for(int i = 0;i<orls.size();i++){
            orls.get(i).moveOrl(x,y);
        }
    }
}
