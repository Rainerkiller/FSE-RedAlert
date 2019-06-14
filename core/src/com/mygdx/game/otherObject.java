package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.util.ArrayList;

class otherObject extends ApplicationAdapter {
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
    ArrayList<Sprite> smallBombUse = new ArrayList<Sprite>();
    ArrayList<Sprite> bigBombUse = new ArrayList<Sprite>();
    int timer = 100;
    boolean used = false;
    boolean bombPlanted = false;


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
    public boolean dealtDamage(Rectangle body){
        if(bigBomb.getBoundingRectangle().overlaps(body)){
            return true;
        }
        return false;
    }

    public void booming() {
        if (timer > 0) {
            timer -= 1;
        } else {
            used = true;
            if (timer < 10) {

            } else if (timer < 20) {

            } else if (timer < 30) {

            } else if (timer < 50) {

            } else if (timer < 65) {

            } else if (timer < 80) {

            } else if (timer < 95) {

            }
        }
    }
    public otherObject() {
            oxyNum = 0;
            smallBombNum = 0;
            bigBombNum = 0;
            aidNum = 0;
            ;
            reviveNum = 0;

            itemBar = new Sprite(new Texture("pics/itemBar.png"));
            oxygenCapsule = new Sprite(new Texture("pics/oxygenCapsule.png"));
            smallBomb = new Sprite(new Texture("pics/smallBomb.png"));
            bigBomb = new Sprite(new Texture("pics/bigBomb.png"));
            firstAid = new Sprite(new Texture("pics/firstAid.png"));
            reviveKit = new Sprite(new Texture("pics/reviveKit"));
            smallBombUse.add(smallBomb);
            bigBombUse.add(bigBomb);
            for (int i = 0; i < 6; i++) {
                smallBombUse.add(new Sprite(new Texture("pics/ex" + Integer.toString(i + 1) + ".png")));
            }
            for (int i = 0; i < 6; i++) {
                bigBombUse.add(new Sprite(new Texture("pics/ex" + Integer.toString(i + 1) + ".png")));
            }
        }
    }

