package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.awt.*;
import java.util.ArrayList;

class Monster {
    int health;
    Rectangle body;
    Sprite current;
    ArrayList <Sprite>motion;
    public Monster(){
        health = 0;
    }
    public Monster(int hp, ArrayList<Sprite> lists){
        health = hp;
        for(Sprite copy : lists){
            lists.add(new Sprite(copy));
        }
    }
    public void setPostion(float x,float y){
        current.translateX(x);
        current.translateY(y);
        for(int i =0; i<motion.size();i++){
            motion.get(i).translateY(y);
            motion.get(i).translateX(x);

        }
    }
    public void Moving(String direction){
        if(direction.equals("left")){
            setPostion(-5,0);
        }else if(direction.equals("right")){
            setPostion(5,0);
        }
    }
}
