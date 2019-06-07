package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

class Player {
    String name;
    int Money;
    Sprite Current;
    ArrayList<Sprite> mining = new ArrayList<Sprite>();
    ArrayList<Sprite> noMotion = new ArrayList<Sprite>();
    ArrayList<Sprite> moving = new ArrayList<Sprite>();
    ArrayList<Sprite> takeDamage = new ArrayList<Sprite>();
    String [] bag = new String[18];
    float health;
    float oxygen;
    float depth = 0;
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
            Current.setPosition(Current.getX(),Current.getY()+1);
        }
        if(a.equals("down")){
            Current.setPosition(Current.getX(),Current.getY()-1);
        }
        if(a.equals("left")){
            Current.setPosition(Current.getX()-1,Current.getY());
        }
        if(a.equals("right")){
            Current.setPosition(Current.getX()+1,Current.getY());
        }
    }
    public void takeDamge(int damage){
        health = health - damage;
    }
}
