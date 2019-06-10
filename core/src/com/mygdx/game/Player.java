package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.lang.reflect.Array;
import java.util.ArrayList;

class Player {
    String name;
    int Money;
    Sprite Current;
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

    int picture = 0;
    public Player(ArrayList<Sprite> noMotion, ArrayList<Sprite> movingL, ArrayList<Sprite> movingR, ArrayList<Sprite> takeDamage){
        this.noMotion = noMotion;
        this.movingL = movingL;
        this.movingR = movingR;
        this.takeDamage = takeDamage;
        name = "role";
        Money = 0;
        health = 100;
        oxygen = 100;
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
            currentY++;
        }
        if(a.equals("down")){
            currentY--;
        }
        if(a.equals("left")){
            currentX--;
            left();
        }
        if(a.equals("right")){
            right();
            currentX++;
        }
    }
    public void left() {
        if (picture < movingL.size()) {
            picture++;
        } else {
            picture = 0;
        }
        Current = movingL.get(picture);
    }
    public void right() {
        if (picture < movingR.size()) {
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
}
