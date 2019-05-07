package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import sun.swing.plaf.synth.DefaultSynthStyle;

import java.util.Set;

class Units {
    private int health;
    private String armor;
    private Sprite staticPicture;
    private Sprite currentAction;
    private Sprite [] movingActions;
    private String weapon;

    public Units(){
        health = 0;
        armor =null;
        staticPicture = null;
        weapon = null;
    }

    public Units(int hp,String arm,Sprite staticMotion ,String Weapon){
        health = hp;
        armor =arm;
        staticPicture = staticMotion;
        weapon = Weapon;
    }

    public void loadUnits(int hp,String Arm,Sprite StaticMotion ,String Weapon){
        health = hp;
        armor = Arm;
        staticPicture = StaticMotion;
        weapon = Weapon;
    }

    public int getHealth(){
        return health;
    }

    public Sprite showMotion(){
        return currentAction;
    }

    public void showUnit(SpriteBatch batch){
        staticPicture.draw(batch);
    }

    public void setPosition(float x,float y){
        staticPicture.setPosition(x,y);
    }

    public void moveTo(float x,float y,SpriteBatch batch) {
        float CurrentX = staticPicture.getX();
        float CurrentY = staticPicture.getY();
        while (CurrentX < x && CurrentY < y) {
            if (CurrentX < x) {
                CurrentX += 0.5;
            }
            if (CurrentY < y) {
                CurrentY += 0.5;
            }
            setPosition(CurrentX, CurrentY);
            showUnit(batch);
        }
    }
}
