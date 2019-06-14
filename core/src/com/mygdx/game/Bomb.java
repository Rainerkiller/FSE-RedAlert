package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

class Bomb {
    int damage;
    Sprite current;
    ArrayList<Sprite> actions = new ArrayList<Sprite>();
    int timer = 100;
    boolean Planted= false;
    boolean explored = false;
    public Bomb(int damage, Sprite current, ArrayList<Sprite> actions){
        this.damage = damage;
        this.current = current;
        this.actions = actions;
    }
    public Sprite getCurrent(){
        return current;
    }
    public boolean exploerd(){
        return explored;
    }
    public void booming(){
        if(timer>0){
            timer--;
        }else{
            explored = true;
        }
        if (timer < 10) {
            current = new Sprite(actions.get(5));
        } else if (timer < 20) {
            current = new Sprite(actions.get(4));

        } else if (timer < 30) {
            current = new Sprite(actions.get(3));

        } else if (timer < 50) {
            current = new Sprite(actions.get(2));

        } else if (timer < 65) {
            current = new Sprite(actions.get(1));

        } else if (timer < 80) {
            current = new Sprite(actions.get(0));

        }
    }
    public void setPosition(float x, float y){
        for(int i =0;i<actions.size();i++){
            actions.get(i).setPosition(x,y);
        }
        current.setPosition(x,y);
    }

}
