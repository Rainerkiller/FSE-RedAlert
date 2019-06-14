package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;

class Monster {
    int health;
    Rectangle body;
    int damage;
    Sprite current;
    ArrayList <Sprite>motion;
    public Monster(){
        health = 0;
    }
    public Monster(Monster goblin){
        health = goblin.getHealth();
        damage = goblin.getDamage();
        current = new Sprite(goblin.getCurrent());
        body = current.getBoundingRectangle();
    }
    public Monster(int hp,int damage, Sprite current){
        health = hp;
        this.damage = damage;
        this.current = current;
        body = current.getBoundingRectangle();
    }
    public int getHealth(){
        return health;
    }
    public int getDamage(){
        return damage;
    }
    public Sprite getCurrent(){
        return current;
    }
    public void setPosition(float x,float y){
        current.setPosition(x,y);
    }
    public void setPostion(float x,float y){
        current.translateX(x);
        current.translateY(y);
    }
    public void Moving(String direction){
        if(direction.equals("left")){
            setPostion(-5,0);
        }else if(direction.equals("right")){
            setPostion(5,0);
        }
    }
}
