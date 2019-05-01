package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import sun.swing.plaf.synth.DefaultSynthStyle;

class Units {
    private int health;
    private String Armor;
    private Texture StaticPicture;
    private Sprite CurrentAction;
    private Sprite [] MovingActions;
    private String Weapon;
    public void LoadUnit(int hp,String Arm,Texture StaticMotion ,Sprite[] AllMotion,String weapon){
        health = hp;
        Armor =Arm;
        StaticPicture = StaticMotion;
        MovingActions = AllMotion;
        Weapon = weapon;
    }
    public int GetHealth(){
        return health;
    }
    public Sprite ShowMotion(){
        return CurrentAction;
    }
}
