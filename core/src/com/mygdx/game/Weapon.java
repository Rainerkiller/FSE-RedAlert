package com.mygdx.game;

import javafx.scene.effect.Light;

import java.util.ArrayList;

class Weapon {
    private String name;
    private float[]typeResistance = {1, 1, 1, 1};//toLightArmor, toMediumArmor, toHeavyArmor, toBuildings
    private int damage;//standard weapon damage

    //-----------------Set Up------------------------

    public Weapon(){
        name = "NEWWEAPON";
        damage = 0;
    }
    public Weapon(String Name,float lightArmor,float medArmor,float heavyArmor,float building,int Damage){
        name = Name;
        typeResistance[0] = lightArmor;
        typeResistance[1] = medArmor;
        typeResistance[2] = heavyArmor;
        typeResistance[3] = building;
        this.damage = Damage;
    }

    //-----------------Return Type-----------------------

    public int getDamage(){
        return damage; //return the damage of the weapon
    }

    public String getName(){
        return name; //return the name of the weapon
    }

    public float[] getTypeRsistance(){
        return typeResistance; //return an array with resistances to different objects
    }
}
