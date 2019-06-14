package com.mygdx.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

class loadingClass extends ApplicationAdapter {
    Player role;
    orl dirt;
    orl coal;
    orl copper;
    orl gold;
    orl crystal;
    float x =0;
    float y = 100;
    ArrayList<Sprite> backgroundPics = new ArrayList<Sprite>();
    ArrayList<Sprite> shopPics = new ArrayList<Sprite>();
    Sprite shopRect;
    orl Wall;


    orl diamond;
    orl loadPics;
    Monster smallGoblin;
    public loadingClass(){

    }

    public loadingClass(String a){
        loadDirt();
        loadRole();
        loadCoal();
        loadBack();
        loadShop();
        loadShopRect();
        loadWall();
    }
    public orl getWall(){
        return Wall;
    }
    public void loadWall(){
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        ArrayList<Sprite> orl = new ArrayList<Sprite>();

        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        orl.add(new Sprite(new Texture("pics/hardRock.png")));
        Wall = new orl("wall", 0,picture,new Sprite(new Texture("pics/hardRock.png")),orl,false);
    }


    public void loadShopRect(){
        shopRect = new Sprite(new Texture(Gdx.files.internal("pics/earth1.png")));
    }
    public void loadShop(){
        for(int i = 0; i<3;i++){
            shopPics.add(new Sprite(new Texture(Gdx.files.internal("pics/shop"+Integer.toString(i+1)+".png"))));
        }
        loadCopper();
        loadCrystal();
        loadGold();
        loadSmallGoblin();
    }
    public Monster getSmallGoblin(){
        return smallGoblin;
    }
    public void loadSmallGoblin(){
        Sprite sprite = new Sprite(new Texture("pics/smallGoblin1.png"));
        smallGoblin = new Monster(500,50,sprite);
    }
    public void loadBack(){
        for(int i = 0; i<24;i++){
            backgroundPics.add(new Sprite(new Texture(Gdx.files.internal("pics/earthScreen.png"))));
        }

    }

    public void loadDirt(){
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        dirt = new orl("dirt", 0,picture,new Sprite(new Texture("pics/earth1.png")));
    }
    public void loadCoal(){
        ArrayList<Sprite> orl = new ArrayList<Sprite>();
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        orl.add(new Sprite(new Texture("pics/coalOriginal.png")));
        orl.add(new Sprite(new Texture("pics/coalOxidized.png")));
        coal = new orl("coal", 10,picture,new Sprite(new Texture("pics/earth1.png")),orl);
    }
    public void loadCopper(){
        ArrayList<Sprite> orl = new ArrayList<Sprite>();
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        orl.add(new Sprite(new Texture("pics/copperOriginal.png")));
        orl.add(new Sprite(new Texture("pics/copperOxidized.png")));
        copper = new orl("copper", 100,picture,new Sprite(new Texture("pics/earth1.png")),orl);
    }
    public void loadCrystal(){
        ArrayList<Sprite> orl = new ArrayList<Sprite>();
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        orl.add(new Sprite(new Texture("pics/crystalOriginal.png")));
        orl.add(new Sprite(new Texture("pics/crystalOxidized.png")));
        crystal = new orl("crystal", 800,picture,new Sprite(new Texture("pics/earth1.png")),orl);
    }
    public void loadGold(){
        ArrayList<Sprite> orl = new ArrayList<Sprite>();
        ArrayList<Sprite> picture = new ArrayList<Sprite>();
        for(int i = 0; i < 6; i++){
            picture.add(new Sprite(new Texture(Gdx.files.internal("pics/earth"+Integer.toString(i+1)+".png"))));
        }
        orl.add(new Sprite(new Texture("pics/goldOriginal.png")));
        orl.add(new Sprite(new Texture("pics/goldOxidized.png")));
        gold = new orl("gold", 200,picture,new Sprite(new Texture("pics/earth1.png")),orl);
    }
    public void loadRole(){
        ArrayList<Sprite> walkR = new ArrayList<Sprite>();
        ArrayList<Sprite> walkL = new ArrayList<Sprite>();
        ArrayList<Sprite> roleStatic = new ArrayList<Sprite>();
        ArrayList<Sprite> roleTakeDamage = new ArrayList<Sprite>();
        ArrayList<Sprite> roleJetLeft = new ArrayList<Sprite>();
        ArrayList<Sprite> roleJetRight = new ArrayList<Sprite>();
        ArrayList<Sprite> roleJet = new ArrayList<Sprite>();
        ArrayList<Sprite> roleDig = new ArrayList<Sprite>();

        walkL.add(new Sprite(new Texture("pics/roleWalkLeft1.png")));
        walkL.add(new Sprite(new Texture("pics/roleWalkLeft2.png")));
        walkR.add(new Sprite(new Texture("pics/roleWalkRight1.png")));
        walkR.add(new Sprite(new Texture("pics/roleWalkRight2.png")));
        roleStatic.add(new Sprite(new Texture("pics/roleStatic1.png")));
        roleStatic.add(new Sprite(new Texture("pics/roleStatic2.png")));
        roleTakeDamage.add(new Sprite(new Texture("pics/roleTakeDamage1.png")));
        roleTakeDamage.add(new Sprite(new Texture("pics/roleTakeDamage1.png")));
        roleJetLeft.add(new Sprite(new Texture("pics/roleJetLeft1.png")));
        roleJetLeft.add(new Sprite(new Texture("pics/roleJetLeft2.png")));
        roleJetRight.add(new Sprite(new Texture("pics/roleJetRight1.png")));
        roleJetRight.add(new Sprite(new Texture("pics/roleJetRight2.png")));
        roleJet.add(new Sprite(new Texture("pics/roleJet1.png")));
        roleJet.add(new Sprite(new Texture("pics/roleJet2.png")));
        roleDig.add(new Sprite(new Texture("pics/roleDigDownBlue.png")));
        roleDig.add(new Sprite(new Texture("pics/roleDigLeftBlue.png")));
        roleDig.add(new Sprite(new Texture("pics/roleDigRightBlue.png")));

        role = new Player(roleStatic, walkL,roleJet,roleJetRight,roleJetLeft,walkR,roleTakeDamage,roleDig);
    }
    public orl getCoal(){return  coal;}
    public orl getDirt(){return dirt;}
    public Player getRole(){
        return role;
    }
    public ArrayList<Sprite> getBackList(){return backgroundPics;}

    public ArrayList<Sprite> getShopPics() {
        return shopPics;
    }
    public Sprite getShopRect(){
        return shopRect;
    }
    public orl getGold(){return  gold;}
    public orl getCopper(){return copper;}
    public orl getCrystal(){return crystal;}
}
