package com.mygdx.game;

import javafx.scene.effect.Light;

import java.util.ArrayList;

class Weapon {
    private String Name;
    private float[]TypeResistance = {1,1,1,1};
    private int Damage;

    //-----------------Set Up------------------------

    public Weapon(){
        Name = "NEWWEAPON";
        Damage = 0;
    }
    public Weapon(String name,float LightArmor,float MedArmor,float HeavyArmor,float Building,int Damage){
        Name = name;
        TypeResistance[0] = LightArmor;
        TypeResistance[1] = MedArmor;
        TypeResistance[2] = HeavyArmor;
        TypeResistance[3] = Building;
        this.Damage = Damage;
    }

    //-----------------Return Type-----------------------

    public int GetDamage(){
        return Damage;
    }
    public String GetName(){
        return Name;
    }
    public float[] GetTypeRsistance(){
        return TypeResistance;
    }

}
