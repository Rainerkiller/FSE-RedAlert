package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashSet;

class Buildings {
    private float health;
    private String name;
    private int power;
    private int usedPower;
    private String type = "BUILDING";
    private String country;
    private Sprite image;
    private Weapon weapon;
    private HashSet<String> unlockBuildings;

    //-----------------Return Type-----------------------

    public float getHp(){
        return health;
    }
    public String getName(){
        return name;
    }
    public String getCountry(){
        return country;
    }
    public String getType(){
        return type;
    }
    //-----------------Void Type--------------------------

    public void showBuilds(SpriteBatch background){
        image.draw(background);
    }
    public void takeDamage(Weapon CurrentWeapon){
        health = health - CurrentWeapon.getDamage();
    }
    public Buildings(float HealthP,String name,int powerProvide,int powerUsed,String Country, Sprite img,Weapon Weapon, HashSet<String>Reb){
        health= HealthP;
        country = Country;
        power = powerProvide;
        usedPower = powerUsed;
        image = img;
        weapon = Weapon;
        this.name = name;
        unlockBuildings = Reb;
    }

}
