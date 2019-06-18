package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;

class Bomb {
    int damage;
    Sprite current;
    ArrayList<Sprite> actions = new ArrayList<Sprite>();
    int timer = 100;
    Rectangle rect = new Rectangle(0,0,0,0);
    boolean explored = false;
    public Bomb(Bomb k ){
        damage = k.getDamage();
        current = new Sprite(k.getCurrent());
        actions = new ArrayList<Sprite>(k.getActions());

    }
    public Bomb(int damage, Sprite current, ArrayList<Sprite> actions){
        this.damage = damage;
        this.current = current;
        this.actions = new ArrayList<Sprite>(actions);
    }
    public int getDamage(){
        return damage;
    }
    public Sprite getCurrent(){
        return current;
    }
    public ArrayList getActions(){
        return actions;
    }
    public boolean exploerd(){
        return explored;
    }
    public Monster damaged(ArrayList<Monster> monsters){
        for(int i = 0;i<monsters.size();i++){
            if(rect.overlaps(monsters.get(i).getBody())){
                return monsters.get(i);

            }
        }
        return null;
    }
    public void booming(){
        System.out.println(timer);
        if(timer>0){
            timer--;
        }else{
            rect = actions.get(5).getBoundingRectangle();
            explored = true;
        }
        if (timer < 10) {
            current = new Sprite(actions.get(5));
        } else if (timer < 5) {
            current = new Sprite(actions.get(4));

        } else if (timer < 10) {
            current = new Sprite(actions.get(3));

        } else if (timer < 15) {
            current = new Sprite(actions.get(2));

        } else if (timer < 20) {
            current = new Sprite(actions.get(1));

        } else if (timer < 100) {
            current = new Sprite(actions.get(0));

        }
    }
    public void setPosition(float x, float y){
        for(int i =0;i<actions.size();i++){
            actions.get(i).setPosition(x,y);
            //rect.setPosition(x,y);
        }
        current.setPosition(x,y);
    }

}
