package com.mygdx.game;

public class Map {
    public int length;
    public int width;
    public String name;

    public void Map(){
        length = 1;
        width = 1;
        name = "unnamed map";
    }

    public void map(int Width, int Length, String mapName){
        width = Width;
        length = Length;
        name = mapName;
    }

    public int getLength(){
        return length;
    }
    public int getWidth(){
        return width;
    }
    public void setMapScale(int Width, int Length){
        width = Width;
        length = Length;
    }

    public String getName(){
        return name;
    }


}
