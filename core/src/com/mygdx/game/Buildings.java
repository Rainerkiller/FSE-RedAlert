package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashSet;

class Buildings {
    private float health;
    private String name;
    private int Power;
    private int UsedPower;
    private String Type = "BUILDING";
    private String Country;
    private Sprite Image;
    private Weapon Weapon;
    private HashSet<String> UnlockBuildings;

    //-----------------Return Type-----------------------

    public float GetHp(){
        return health;
    }
    public String GetName(){
        return name;
    }
    public String GetCountry(){
        return Country;
    }
    public String GetType(){
        return Type;
    }
    //-----------------Void Type--------------------------

    public void ShowBuilds(SpriteBatch batch){
        Image.draw(batch);
    }
    public void TakeDamage(Weapon CurrentWeapon){
        health = health-CurrentWeapon.GetDamage();
    }
    public Buildings(float HealthP,String name,int powerprovide,int powerused,String country, Sprite img,Weapon weapon, HashSet<String>Reb){
        health=HealthP;
        Country = country;
        Power = powerprovide;
        UsedPower = powerused;
        Image = img;
        Weapon = weapon;
        this.name=name;
        UnlockBuildings = Reb;
    }

}
