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
    boolean orl = false;
    boolean destroy = true;
    Sprite Current;
    ArrayList<Sprite> Pic = new ArrayList<Sprite>();
    ArrayList<Sprite> inPiece = new ArrayList<Sprite>();
    public orl(String name,int p, ArrayList<Sprite> load, Sprite current,ArrayList<Sprite>orl ){
        this.name = name;
        Current = current;
        price = p;
        inPiece = load;
        Pic = orl;
        this.destroy = true;
        setPostion(0,0);
    }
    public orl(String name,int p, ArrayList<Sprite> load, Sprite current,ArrayList<Sprite>orl, boolean status ){
        this.name = name;
        Current = current;
        price = p;
        inPiece = load;
        Pic = orl;
        this.destroy = status;
        setPostion(0,0);
    }
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
        float x = orls.getCurrent().getX();
        float y = orls.getCurrent().getY();
        name = (orls.getName());
        Current = new Sprite(orls.getCurrent());
        price = orls.getPrice();
        for(Sprite copy : orls.getInDirty()){
            inPiece.add(new Sprite(copy));
        }
        for(Sprite copy2 : orls.getPic()){
            Pic.add(new Sprite(copy2));
        }
        setPostion(x,y);
    }
    public ArrayList<Sprite> getPic(){return  Pic;}
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
    public boolean getOrlLeft(Rectangle role){
        float roleX = role.getX();
        float roleY = role.getY();
        float roleMid = roleY + role.getHeight()/2;
        if(Current.getBoundingRectangle().overlaps(role)){
            if(Current.getX()+Current.getHeight()>role.getY()+role.getHeight()/3){
                return true;
            }
        }
        return false;
    }
    public void mining(){
        float x = Current.getX();
        float y = Current.getY();
        if(percentage>0 && !name.equals("wall")) {
            percentage -= 5;
        }else if(name.equals("dirt")){
            Appear = false;
        }
        if(percentage<5&&!name.equals("dirt")&&!name.equals("wall")){
             getPic().get(1).setPosition(Current.getX()+20,Current.getY()+20);
             Current = new Sprite(getPic().get(1));
             orl = true;
        }
        else if(percentage<10){
            Current = new Sprite(getInDirty().get(5));
        }else if(percentage<25){
            Current = new Sprite(getInDirty().get(4));
        }else if(percentage<40){

            Current = new Sprite(getInDirty().get(3));
        }else if(percentage<55){

            Current = new Sprite(getInDirty().get(2));
        }else if(percentage<70){

            Current = new Sprite(getInDirty().get(1));
        }
    }
    public void setPostion(float x,float y){
        for(int i = 0; i < inPiece.size();i++){
            inPiece.get(i).setPosition(x,y);
        }
        for(int i = 0; i < Pic.size();i++){
            Pic.get(i).setPosition(x,y);
        }
        Current.setPosition(x,y);
    }
    public void pickUp(){
        if(Appear){
            Appear = false;
        }
    }
    public boolean getOrl(){
        return orl;
    }
    public void moveOrl(float number,float number2){
        Current.translateX(number);
        Current.translateY(number2);
        for(int i = 0; i < Pic.size();i++){
            Pic.get(i).setPosition(number,number2);
        }
        for(int i = 0;i<inPiece.size();i++){
            inPiece.get(i).translateX(number);
            inPiece.get(i).translateY(number2);
        }
    }
}
