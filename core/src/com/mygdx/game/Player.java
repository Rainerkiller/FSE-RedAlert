package com.mygdx.game;
import java.util.ArrayList;
class Player {
    private String name;
    private String Country;
    private int money;
    private int maxPower;
    private int usedPower;
    private ArrayList<Building> buildings;
    private ArrayList<Unit> units;
    private boolean LOSE = false;
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

}
