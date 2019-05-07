package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Units {
    private int health;
    private String Name;
    private String Type;
    private Sprite StaticPicture;
    private Sprite CurrentAction;
    private Sprite [] MovingActions;
    private String Weapon;
    public Units(){
        health = 0;
        Type =null;
        StaticPicture = null;
        Weapon = null;
    }
    public Units(int hp,String Arm,Sprite StaticMotion ,String weapon){
        health = hp;
        Type =Arm;
        StaticPicture = StaticMotion;
        Weapon = weapon;
    }
    public void LoadUnits(int hp,String Arm,Sprite StaticMotion ,String weapon){
        health = hp;
        Type =Arm;
        StaticPicture = StaticMotion;
        Weapon = weapon;
    }
    public int GetHealth(){
        return health;
    }
    public Sprite ShowMotion(){
        return CurrentAction;
    }
    public void ShowUnit(SpriteBatch batch){
        StaticPicture.draw(batch);
    }
    public void SetPostition(float x,float y){
        StaticPicture.setPosition(x,y);
    }
    public void MoveTo(float x,float y,SpriteBatch batch){
        float CurrentX = StaticPicture.getX();
        float CurrentY = StaticPicture.getY();
        while(CurrentX<x&&CurrentY<y){
            if(CurrentX<x){
                CurrentX+=0.5;
            }
            if(CurrentY<y){
                CurrentY+=0.5;
            }
            SetPostition(CurrentX,CurrentY);
            ShowUnit(batch);
        }
    }

}
