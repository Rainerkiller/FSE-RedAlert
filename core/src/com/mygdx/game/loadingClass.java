package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

class loadingClass extends ApplicationAdapter {
    Player role;
    orl dirt;
    orl coal;
    orl steel;
    orl gold;
    orl dimand;
    public loadingClass(){

    }
    public loadingClass(String a){
        loadDirt(6);
        loadRole();
    }
    public void loadDirt(int numOfSprite){
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < numOfSprite; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth1.png"))));
        }
        dirt = new orl("dirt", 0,picture);
    }
    public void loadRole(){
        ArrayList<Sprite> walkR = new ArrayList<Sprite>();
        ArrayList<Sprite> walkL = new ArrayList<Sprite>();
        ArrayList<Sprite> roleStatic = new ArrayList<Sprite>();
        ArrayList<Sprite> roleTakeDamage = new ArrayList<Sprite>();

        walkL.add(new Sprite(new Texture("pics/roleWalkLeft1.png")));
        walkL.add(new Sprite(new Texture("pics/roleWalkLeft2.png")));
        walkR.add(new Sprite(new Texture("pics/roleWalkRight1.png")));
        walkR.add(new Sprite(new Texture("pics/roleWalkRight2.png")));
        roleStatic.add(new Sprite(new Texture("pics/roleStatic1.png")));
        roleStatic.add(new Sprite(new Texture("pics/roleStatic2.png")));
        roleTakeDamage.add(new Sprite(new Texture("pics/roleTakeDamage1.png")));
        roleTakeDamage.add(new Sprite(new Texture("pics/roleTakeDamage1.png")));

        role = new Player(roleStatic, walkL,walkR,roleTakeDamage);
    }
    public void loadccoal(){
    }

    public Player getRole(){
        return role;
    }
}
