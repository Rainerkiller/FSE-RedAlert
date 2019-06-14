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
    float currentX,currentY;
    public Monster(){
        health = 0;
    }
    public Monster(Monster goblin){
        health = goblin.getHealth();
        damage = goblin.getDamage();
        current = new Sprite(goblin.getCurrent());
        body = goblin.getBody();
    }
    public Monster(int hp,int damage, Sprite current){
        health = hp;
        this.damage = damage;
        this.current = current;
        body = current.getBoundingRectangle();
        body.setX(body.getX()+10);
        body.setY(body.getY()-5);
        body.setHeight(76);
        body.setWidth(50);
    }
    public Rectangle getBody(){
        return body;
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
            currentX-=0.1;
        }else if(direction.equals("right")){
            currentX+=0.1;
        }
        refreshPosition();
    }
    public void refreshPosition(){
        current.translateX(currentX);
        body = current.getBoundingRectangle();
        body.setX(body.getX()+10);
        body.setY(body.getY()-5);
        body.setHeight(76);
        body.setWidth(50);
    }
    public void takeDamage(int damage){
        health -= damage;
    }
}
