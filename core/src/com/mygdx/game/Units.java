package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

class Units {
    private int health;
    private String name;
    private String type;
    private Sprite staticPicture;
    private Sprite currentAction;
    private Sprite [] movingActions;
    private String weapon;

    //--------------------Set up----------------------

    public Units(){
        health = 0;
        type = null;
        staticPicture = null;
        weapon = null;
    }
    public Units(int hp,String Arm,Sprite StaticMotion ,String Weapon){
        health = hp;
        type = Arm;
        staticPicture = StaticMotion;
        weapon = Weapon;
    }

    //-----------------Return Type-----------------------

    public int getHealth(){
        return health;
    }
    public Sprite showMotion(){
        return currentAction;
    }

    //-----------------Return Type-----------------------

    public void loadUnits(int hp,String Arm,Sprite StaticMotion ,String Weapon){
        health = hp;
        type = Arm;
        staticPicture = StaticMotion;
        weapon = Weapon;
    }
    public void showUnit(SpriteBatch background){
        staticPicture.draw(background);
    }
    public void setPostition(float x,float y){
        staticPicture.setPosition(x,y);
    }
    public void MoveTo(float x,float y,SpriteBatch background){
        float CurrentX = staticPicture.getX();
        float CurrentY = staticPicture.getY();
        while(CurrentX < x && CurrentY < y){
            if(CurrentX < x){
                CurrentX += 0.5;
            }
            if(CurrentY < y){
                CurrentY += 0.5;
            }
            setPostition(CurrentX, CurrentY);
            showUnit(background);
        }
    }

}
