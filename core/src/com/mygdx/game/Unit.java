package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;

class Unit {
    private int health;
    private String name;
    private String type;
    private Sprite staticPicture;
    private Sprite currentAction;
    private Sprite [] movingActions;
    private String weapon;
    private String status;

    //--------------------Set up----------------------

    public Unit(){
        health = 0;
        type = null;
        staticPicture = null;
        weapon = null;
    }

    public Unit(int hp,String Arm,Sprite StaticMotion ,String Weapon){
        health = hp;
        type = Arm;
        staticPicture = StaticMotion;
        weapon = Weapon;
    }

    //-----------------Return Type-----------------------b

    public int getHealth(){
        return health;
    }
    public Sprite getSprite(){
        return staticPicture;
    }
    public boolean isAlive(){return  health>0; }
    public Rectangle getRect(){
        return staticPicture.getBoundingRectangle();
    }
    //-----------------Void Type-----------------------

    public void loadUnits(int hp,String Arm,Sprite StaticMotion ,String Weapon){
        health = hp;
        type = Arm;
        staticPicture = StaticMotion;
        weapon = Weapon;
    }
    public void showUnit(SpriteBatch background){
        staticPicture.draw(background);
    }
    public void setPosition(float x,float y){
        staticPicture.setPosition(x,y);
    }
    public boolean getInScreen(){
        return (staticPicture.getX()>0&&staticPicture.getX()<1920)&&(staticPicture.getY()>0&&staticPicture.getY()<1080);
    }

}
