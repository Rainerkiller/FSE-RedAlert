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
    float x =0;
    float y = 100;

    orl diamond;
    orl loadPics;
    public loadingClass(){

    }
    public loadingClass(String a){
        loadDirt();
        loadRole();
    }

    public void loadDirt(){
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        dirt = new orl("dirt", 0,picture,new Sprite(new Texture("pics/earth1.png")));
    }
    public void loadRole(){
        ArrayList<Sprite> walkR = new ArrayList<Sprite>();
        ArrayList<Sprite> walkL = new ArrayList<Sprite>();
        ArrayList<Sprite> roleStatic = new ArrayList<Sprite>();
        ArrayList<Sprite> roleTakeDamage = new ArrayList<Sprite>();
        ArrayList<Sprite> roleJetLeft = new ArrayList<Sprite>();
        ArrayList<Sprite> roleJetRight = new ArrayList<Sprite>();
        ArrayList<Sprite> roleJet = new ArrayList<Sprite>();

        walkL.add(new Sprite(new Texture("pics/roleWalkLeft1.png")));
        walkL.add(new Sprite(new Texture("pics/roleWalkLeft2.png")));
        walkR.add(new Sprite(new Texture("pics/roleWalkRight1.png")));
        walkR.add(new Sprite(new Texture("pics/roleWalkRight2.png")));
        roleStatic.add(new Sprite(new Texture("pics/roleStatic1.png")));
        roleStatic.add(new Sprite(new Texture("pics/roleStatic2.png")));
        roleTakeDamage.add(new Sprite(new Texture("pics/roleTakeDamage1.png")));
        roleTakeDamage.add(new Sprite(new Texture("pics/roleTakeDamage1.png")));
        roleJetLeft.add(new Sprite(new Texture("pics/roleJetLeft1.png")));
        roleJetLeft.add(new Sprite(new Texture("pics/roleJetLeft2.png")));
        roleJetRight.add(new Sprite(new Texture("pics/roleJetRight1.png")));
        roleJetRight.add(new Sprite(new Texture("pics/roleJetRight2.png")));
        roleJet.add(new Sprite(new Texture("pics/roleJet1.png")));
        roleJet.add(new Sprite(new Texture("pics/roleJet2.png")));

        role = new Player(roleStatic, walkL,roleJet,roleJetRight,roleJetLeft,walkR,roleTakeDamage);
    }
    public void loadccoal(){
    }
    public orl getDirt(){return dirt;}
    public Player getRole(){
        return role;
    }
}
