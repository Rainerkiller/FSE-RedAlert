package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import java.util.HashSet;

class Buildings {
    private float health;
    private String name;
    private String Type = "BUILDING";
    private String Country;
    private Sprite Image;
    private Weapon Weapon;
    private HashSet<String> RequiredBuilding;
    public float GetHp(){
        return health;
    }
    public String GetName(){
        return name;
    }
    public String GetCountry(){
        return Country;
    }
    public 
    public Buildings(float HealthP,String name,String country, Sprite img,Weapon weapon, HashSet<String>Reb){
        health=HealthP;
        Country = country;
        Image = img;
        Weapon = weapon;
        this.name=name;
        RequiredBuilding = Reb;
    }

}
