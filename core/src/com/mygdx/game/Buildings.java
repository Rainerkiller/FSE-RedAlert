package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashSet;

class Building {
    private float health;
    private String name;
    private int power;
    private int usedPower;
    private int cost;
    private String type = "BUILDING";
    private String country;
    private Sprite image;
    private Weapon weapon;
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
    //-----------------Void Type--------------------------

    public void showBuilds(SpriteBatch background) {
        image.draw(background);
    }

    public void takeDamage(Weapon CurrentWeapon) {
        health = health - CurrentWeapon.GetDamage();
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

    //

}

