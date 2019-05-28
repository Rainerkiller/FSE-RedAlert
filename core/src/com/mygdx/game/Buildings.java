package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

class Building {
    private String name;
    private float health;
    private int power;
    private int usedPower;
    private int cost;
    private int coolDown;
    private String type = "BUILDING";
    private String country;
    private Sprite image;
    private Weapon weapon;
    private LinkedList<Unit> queue = new LinkedList<Unit>();
    private HashSet<String> unlockBuildings;

    //-----------------Return Type-----------------------

    public float getHp() {
        return health;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getType() {
        return type;
    }
    public int getCost(){
        return cost;
    }
    public Sprite getSprite(){
        return image;
    }
    public Rectangle getRect(){
        return image.getBoundingRectangle();
    }
    //-----------------Void Type--------------------------
    public void setPosition(float x,float y){
        image.setPosition(x,y);
    }
    public void showBuilds(SpriteBatch background) {
        image.draw(background);
    }

    public void takeDamage(Weapon CurrentWeapon) {
        health = health - CurrentWeapon.getDamage();
    }
    public void draw(SpriteBatch batch){
        image.draw(batch);
    }
    public Building(float HealthP, String name, int powerProvide, int powerused, String Country, Sprite img, Weapon Weapon, HashSet<String> Reb) {
        health = HealthP;
        country = Country;
        power = powerProvide;
        usedPower = powerused;
        image = img;
        weapon = weapon;
        this.name = name;
        unlockBuildings = Reb;
    }

    public Building(float HealthP, String name, int powerProvide, int powerused, String Country, Sprite img, Weapon Weapon) {
        health = HealthP;
        country = Country;
        power = powerProvide;
        usedPower = powerused;
        image = img;
        weapon = weapon;
        this.name = name;
    }
    //

}

