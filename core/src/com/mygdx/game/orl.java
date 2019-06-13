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
    ArrayList<Sprite> inPiece = new ArrayList<Sprite>();

    public orl(String name,int p, ArrayList<Sprite> load, Sprite current){
        this.name = name;
        Current = current;
        price = p;
        inPiece = load;
        setPostion(0,0);
    }
    public orl(){

    }
    public orl(orl orls){
        name = (orls.getName());
        Current = new Sprite(orls.getCurrent());
        price = orls.getPrice();
        for(Sprite copy : orls.getInDirty()){
            inPiece.add(new Sprite(copy));
        }

        setPostion(0,0);
    }
    public ArrayList<Sprite> getInDirty(){
        return inPiece;
    }
    public String getName(){
        return name;
    }
    public int getPrice(){
        return price;
    }
    public Rectangle getRect(){
        return Current.getBoundingRectangle();
    }
    public boolean getApear(){
        return Appear;
    }
    public Sprite getCurrent(){return Current;}
    public boolean getOrlLeft(Player role){
        float roleX = role.getBody().getX();
        float roleY = role.getBody().getY();
        float roleMid = roleY + role.getBody().getHeight()/2;
        if(Current.getBoundingRectangle().overlaps(role.getBody())){
            if(Current.getX()>)
        }
    }
    public void mining(){
        if(percentage>0) {
            percentage -= 5;
        }else{
            Appear = false;
        }
        if(percentage<10){
            Current = getInDirty().get(5);
        }else if(percentage<25){
            Current = getInDirty().get(4);
        }else if(percentage<40){
            Current = getInDirty().get(3);
        }else if(percentage<55){
            Current = getInDirty().get(2);
        }else if(percentage<70){
            Current = getInDirty().get(1);
        }else if(percentage<85){
            Current = getInDirty().get(0);
        }
    }
    public void setPostion(float x,float y){
        for(int i = 0; i < inPiece.size();i++){
            inPiece.get(i).setPosition(x,y);
        }
        Current.setPosition(x,y);
    }
    public void pickUp(){
        if(Appear){
            Appear = false;
        }
    }

}
