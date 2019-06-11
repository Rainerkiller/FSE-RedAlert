package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

class loadingClass extends ApplicationAdapter {
    Player role;
    ArrayList<orl> world = new ArrayList<orl>();
    orl dirt;
    orl coal;
    orl steel;
    orl gold;
    orl dimand;
    public loadingClass(){

    }
    public loadingClass(String a){
        loadDirt();
        loadRole();
        creatOrl();
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
    public ArrayList<orl> getWorld(){
        return world;
    }
    public Player getRole(){
        return role;
    }
    public void creatOrl(){
        float x =0;
        float y = 100;
        for(int i =0;i<1000;i++){
            dirt.setPostion(x,y);
            System.out.print(x);
            System.out.println(" "+y);
            world.add(dirt);
            x+=76;
            if(i%10 == 0){
                System.out.println("nexTlINE");
                y-=76;
                x=0;
            }
        }
    }
}
