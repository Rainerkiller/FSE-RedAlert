package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.sun.deploy.association.utility.AppAssociationReader;

import java.awt.*;
import java.util.ArrayList;

class orl {
    String name;
    float percentage = 100;
    int price;
    boolean Appear = true;
    Sprite Current;
    Sprite Pic = new Sprite();
    ArrayList<Sprite> inDirty = new ArrayList<Sprite>();

    public orl(String name,int p, ArrayList<Sprite> load){
        this.name = name;
        price = p;
        inDirty = load;
    }
    public Rectangle getRect(){
        return Current.getBoundingRectangle();
    }
    public boolean getApear(){
        return Appear;
    }

    public void mining(){
        if(percentage>0) {
            percentage -= 0.01;
            int index = (int) (100 / percentage);
            Current = inDirty.get(index);
        }else{
            Current = Pic;
        }
    }

    public void pickUp(){
        if(Appear){
            Appear = false;
        }
    }

}
