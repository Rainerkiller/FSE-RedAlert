package com.mygdx.game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

class Player {
    private String name;
    private String Country;
    private int money;
    private int maxPower;
    private int usedPower;
    private ArrayList<Building> buildings = new ArrayList<Building>();
    private ArrayList<Unit> units = new ArrayList<Unit>();
    private boolean LOSE = false;
    //-----------------Set Up------------------------
    public Player(){
        name = "NULL";
        money = 0;
    }

    //-----------------Return Type-----------------------

    public boolean gameOn(){
        return LOSE;
    }

    public int getMoney(){
        return money;
    }

    public String getCountry() {
        return Country;
    }

    public int getMaxPower() {
        return maxPower;
    }

    public int getUsedPower() {
        return usedPower;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public String getName() {
        return name;
    }

    //-----------------Void Type---------------------------

    public void spendMoney(int number){
        money = money - number;
    }

    public void spendMoney(Building build){
        money = money - build.getCost();
    }
    public void addBuilding( Building e){
        money = money - e.getCost();
        buildings.add(e);
    }
    public void addUnit(Unit a){
        units.add(a);
    }
    public void showBuilding(SpriteBatch batch){
        for(int i =0;i<buildings.size();i++) {
            buildings.get(i).draw(batch);
        }
    }
    public void showBuilding(SpriteBatch batch, Rectangle screen){
        for(int i =0;i<buildings.size();i++) {
            if(buildings.get(i).getRect().overlaps(screen)) {
                buildings.get(i).draw(batch);
            }
        }
    }
    public void showUnits(SpriteBatch batch){
        for(int i =0;i<units.size();i++) {
        }
    }
}
