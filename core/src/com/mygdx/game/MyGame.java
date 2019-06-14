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

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    String status = "game";
    ArrayList<orl> orls = new ArrayList<orl>();
    loadingClass A = new loadingClass();
    otherObject B = new otherObject();
    Player role = new Player();
    float x = 641;
    float y = 477;
    float constantX=5;
    float constantY=10;
    ArrayList<Sprite>sprites = new ArrayList<Sprite>();
    BitmapFont font;
    Rectangle worldRect = new Rectangle(541,500,200,300);
    ArrayList<Monster> monsters = new ArrayList<Monster>();
    int startUpTimer = 0;
    int menuSelection = 0; //0 for start game, 1 for guide
    int loadingCounter = 0;
    int spriteIndex = 0;

    public void world(){
        A = new loadingClass("a");
        role = A.getRole();
        orl dirt = A.getDirt();
        orl coal = A.getCoal();
        role.setPosition("");
        orl gold = A.getGold();
        orl copper = A.getCopper();
        orl crystal = A.getCrystal();
        A.back.setPosition(0,-476);
        B = new otherObject();
        int monster = 0;
        for(int i = 0;i<3000;i++){
            int percentCoal = (int)(Math.random()*3);
            int percentCopper = (int)(Math.random()*5);
            int percentGold = (int)(Math.random()*10);
            int percentCrystal = (int)(Math.random()*50);
            int percentSmallGoblin =(int)(Math.random()*200);
            if(percentSmallGoblin == 1){
                Monster smallGoblin = A.getSmallGoblin();
                smallGoblin.setPostion(x,y);
                monsters.add(new Monster(smallGoblin));
                monster++;
                i+=7;
            }
            else if(percentCrystal == 1){
                orls.add(new orl(crystal));
            }
            else if(percentGold == 1) {
                orls.add(new orl(gold));
            }
            else if(percentCopper == 1){
                orls.add(new orl(copper));
            }else if(percentCoal == 1){
                orls.add(new orl(coal));
            }
            else{
                orls.add(new orl(dirt));
            }
            orls.get(i-8*monster).setPostion(x,y);
            x = x+76;
            if(i%30==0){
                x = 0;
                y-=76;
            }
        }
    }
    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(140,140,60,1);
        world();
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


            A.getBack().draw(batch);



            if(role.getBody().overlaps(worldRect)) {
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    // use translate(vx,vy), translateX(vx) or translateY(vy)
                    if(!role.getCollideUp(orls)) {
                        role.setPosition("up");
                        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                            role.setPosition("left");
                        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                            role.setPosition("right");
                        }
                    } else if(role.getCollideUp(orls)){
                        role.setPosition("fakeUp");
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                    if (role.getCollideLeft(orls)) {
                        if (orls.indexOf(role.getCollideOrlLeft(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlLeft(orls))).mining();
                            role.digLeft();
                        }
                    } else if (!role.getCollideDown(orls)&&!role.getCollideLeft(orls)) {
                        role.setPosition("left");
                        role.setPosition("acc");
                    } else {
                        role.setPosition("left");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    if (role.getCollideRight(orls)) {
                        if (orls.indexOf(role.getCollideOrlRight(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlRight(orls))).mining();
                            role.digRight();
                        }
                    }
                    else if (!role.getCollideRight(orls)&&!role.getCollideDown(orls)) {
                        role.setPosition("right");
                        role.setPosition("acc");
                    }
                    else if (!role.getCollideRight(orls)) {
                        role.setPosition("right");
                    }
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.S) && role.getCollide(orls)) {
                    if (orls.indexOf(role.getCollideOrl(orls)) != -1) {
                        orls.get(orls.indexOf(role.getCollideOrl(orls))).mining();
                        role.digDown();
                    }
                } else if (role.getCollideDown(orls)) {
                    role.setPosition("static");
                } else {
                    role.setPosition("acc");
                }
            }else {
                if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                    if(!role.getCollideUp(orls)) {
                        moveWorld(0,-constantY);
                        role.setPosition("fakeUp");
                        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                            role.setPosition("fakeUpLeft");
                            moveWorld(constantX,-constantY);
                        } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                            role.setPosition("fakeUpRight");
                            moveWorld(-constantX,-constantY);

                        }
                    } else if(role.getCollideUp(orls)) {
                        role.setPosition("fakeUp");
                    }

                } else if (Gdx.input.isKeyPressed(Input.Keys.A)) {

                    if (role.getCollideLeft(orls)) {
                        if (orls.indexOf(role.getCollideOrlLeft(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlLeft(orls))).mining();
                            role.digLeft();
                        }
                    } else if (!role.getCollideDown(orls)) {
                        role.setPosition("fakeLeft");
                        moveWorld(constantX,0);
                        role.setPosition("fakeAcc");
                        moveWorld(0,constantY);
                    } else {
                        moveWorld(constantX,0);
                        role.setPosition("fakeLeft");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    if (role.getCollideRight(orls)) {
                        if (orls.indexOf(role.getCollideOrlRight(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlRight(orls))).mining();
                            role.digRight();
                        }
                    } else if (!role.getCollideDown(orls)) {
                        role.setPosition("fakeRight");
                        moveWorld(-constantX,0);
                        role.setPosition("fakeAcc");
                        moveWorld(0,constantY);
                    } else {
                        moveWorld(-constantX,0);
                        role.setPosition("fakeRight");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.S) && role.getCollide(orls)) {
                    if (orls.indexOf(role.getCollideOrl(orls)) != -1) {
                        orls.get(orls.indexOf(role.getCollideOrl(orls))).mining();
                        role.digDown();
                    }
                } else if (role.getCollideDown(orls)) {
                    role.setPosition("static");
                } else {
                    role.setPosition("fakeAcc");
                    moveWorld(0,constantY);
                }
            }

            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                status  = "pause";
            }
            if(role.getCollideOrl(orls).getOrl()){
                role.getCollideOrl(orls).pickUp();
                role.getOrl(role.getCollideOrl(orls));
            }
            for(int i =0;i<orls.size();i++) {
                if(orls.get(i).Appear) {
                    if(orls.get(i).getCurrent().getX()>-100&&orls.get(i).getCurrent().getX()<1300) {
                        if (orls.get(i).getCurrent().getY() > -100 && orls.get(i).getCurrent().getY() < 1000) {
                                orls.get(i).getCurrent().draw(batch);
                        }
                    }
                }else{
                    orls.remove(i);
                }
            }
            for(int i =0;i<monsters.size();i++){
                if(monsters.get(i).getCurrent().getX()>-100&&monsters.get(i).getCurrent().getX()<1300) {
                    if (monsters.get(i).getCurrent().getY() > -100 && monsters.get(i).getCurrent().getY() < 1000) {
                        monsters.get(i).getCurrent().draw(batch);
                    }
                }
            }
            System.out.println(monsters.size());
            role.getCurrent().draw(batch);
        }
        monsters.get(0).getCurrent().draw(batch);
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
        A.getBack().translateX(x);
        A.getBack().translateY(y);


    }
}
