package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

public class otherObject {
    Sprite itemBar;
    Sprite oxygenCapsule;
    Sprite smallBomb;
    Sprite bigBomb;
    Sprite firstAid;
    Sprite reviveKit;
    int oxyNum;
    int smallBombNum;
    int bigBombNum;
    int aidNum;
    int reviveNum;

    public int getAidNum() {
        return aidNum;
    }

    public int getBigBombNum() {
        return bigBombNum;
    }

    public int getOxyNum() {
        return oxyNum;
    }

    public int getReviveNum() {
        return reviveNum;
    }

    public int getSmallBombNum() {
        return smallBombNum;
    }

    public Sprite getBigBomb() {
        return bigBomb;
    }

    public Sprite getFirstAid() {
        return firstAid;
    }

    public Sprite getItemBar() {
        return itemBar;
    }

    public Sprite getOxygenCapsule() {
        return oxygenCapsule;
    }

    public Sprite getReviveKit() {
        return reviveKit;
    }

    public Sprite getSmallBomb() {
        return smallBomb;
    }

    public void otherObject(){
        oxyNum = 0;
        smallBombNum = 0;
        bigBombNum = 0;
        aidNum = 0;;
        reviveNum = 0;

        ArrayList<Sprite> smallBombUse = new ArrayList<Sprite>();
        ArrayList<Sprite> bigBombUse = new ArrayList<Sprite>();
        itemBar = new Sprite(new Texture("pics/itemBar.png"));
        oxygenCapsule = new Sprite(new Texture("pics/oxygenCapsule.png"));
        smallBomb = new Sprite(new Texture("pics/smallBomb.png"));
        bigBomb = new Sprite(new Texture("pics/bigBomb.png"));
        firstAid = new Sprite(new Texture("pics/firstAid.png"));
        reviveKit = new Sprite(new Texture("pics/reviveKit"));
        smallBombUse.add(smallBomb);
        bigBombUse.add(bigBomb);
        for(int i = 0; i<6;i++){
            smallBombUse.add(new Sprite(new Texture("pics/ex"+Integer.toString(i+1)+".png")));
        }
        for(int i = 0; i<6;i++){
            bigBombUse.add(new Sprite(new Texture("pics/ex"+Integer.toString(i+1)+".png")));
        }
    }
    public void otherObject(String a){

    }

}
