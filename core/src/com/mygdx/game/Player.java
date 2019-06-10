package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Array;
import java.util.ArrayList;

class Player {
    String name;
    int Money;
    Sprite Current;
    Rectangle body;
    ArrayList<Sprite> mining = new ArrayList<Sprite>();
    ArrayList<Sprite> noMotion = new ArrayList<Sprite>();
    ArrayList<Sprite> movingL = new ArrayList<Sprite>();
    ArrayList<Sprite> movingR = new ArrayList<Sprite>();
    ArrayList<Sprite> takeDamage = new ArrayList<Sprite>();


    String [] bag = new String[18];
    float health;
    float oxygen;
    float depth = 0;
    float currentX;
    float currentY;

    float acceleration = 5;

    int timer = 0;
    int picture = 0;
    public Player(ArrayList<Sprite> noMotion, ArrayList<Sprite> movingL, ArrayList<Sprite> movingR, ArrayList<Sprite> takeDamage){
        Current = noMotion.get(0);
        this.noMotion = noMotion;
        this.movingL = movingL;
        this.movingR = movingR;
        this.takeDamage = takeDamage;
        name = "role";
        Money = 0;
        health = 100;
        oxygen = 100;
        body = Current.getBoundingRectangle();
    }
    public Player(){
        name = "role";
        Money = 0;
        health = 100;
        oxygen = 100;
    }
    public int getMoney(){
        return Money;
    }

    public float getHealth() {
        return health;
    }

    public float getOxygen() {
        return oxygen;
    }

    public String[] getBag() {
        return bag;
    }

    public void setPosition(String a){
        if(a.equals("up")){
            currentY+=10;
            acceleration = 5;
        }
        else if(a.equals("acc")) {
            currentY -=acceleration;
            acceleration++;
        }
        else if(a.equals("down")){
            currentY-=5;
        }
        else if(a.equals("left")){
            currentX-=5;
            left();
        }
        else if(a.equals("right")){
            right();
            currentX+=5;
        } else if(a.equals("static")){
            Static();
        }
        Current.setPosition(currentX,currentY);
    }
    public void Static(){
        if (picture < movingL.size()-1&& timer>100) {
            picture++;
            timer = 0;
        } else{
            timer+=5;
            picture = 0;
        }
        Current = noMotion.get(picture);
    }
    public void left() {
        if (picture < movingL.size()-1) {
            picture++;
        } else {
            picture = 0;
        }
        Current = movingL.get(picture);
    }
    public void right() {
        if (picture < movingR.size()-1) {
            picture++;
        } else {
            picture = 0;
        }
        Current = movingR.get(picture);
    }

    public void refreshPosition(){
        for(int i =0;i<noMotion.size();i++){
            noMotion.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<movingL.size();i++){
            movingL.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<movingR.size();i++){
            movingR.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<takeDamage.size();i++){
            takeDamage.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<mining.size();i++){
            mining.get(i).setPosition(currentX,currentY);
        }
    }
    public void takeDamge(int damage){
        health = health - damage;
    }
    public Sprite getCurrent(){
        return Current;
    }
    public boolean getCollide(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++){
            if(orls.get(i).getRect().overlaps(body)){
                return true;
            }
        }
        return false;
    }

}
