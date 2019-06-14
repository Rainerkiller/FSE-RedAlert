package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.*;

import java.util.ArrayList;

public class MyGame extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    String status = "menu";
    ArrayList<orl> orls = new ArrayList<orl>();
    loadingClass A = new loadingClass();
    Toolbox C = new Toolbox();
    Player role = new Player();
    float x = 641;// x,y for role
    float y = 477;
    float constantX=5;
    float constantY=10;
    BitmapFont font;
    Rectangle worldRect = new Rectangle(541,500,200,300);
    ArrayList<Monster> monsters = new ArrayList<Monster>();
    int startUpTimer = 0;
    int menuSelection = 0; //0 for start game, 1 for guide
    int loadingCounter = 0;
    int spriteIndex = 0;
    int shopCounter = 0;
    float gasLine = 0;
    float timer1 = 0;
    Boolean guideS = false;
    ArrayList<Bomb> bombs = new ArrayList<Bomb>();


    public void createWorld(){//create dei
        C=new Toolbox();
        A = new loadingClass("a");
        role = A.getRole();
        orl dirt = A.getDirt();
        orl coal = A.getCoal();
        role.setPosition("");
        orl gold = A.getGold();
        orl copper = A.getCopper();
        orl crystal = A.getCrystal();
        A.getShopRect().setPosition(640,478);

        A.getItemBar().setPosition(440,820);


        orl Wall = A.getWall();

        int monster = 0;
        ArrayList<orl> fakeOrl = new ArrayList<orl>();

        float copyX = -152;
        float copyY = y;

        for(int i =0;i<8;i++){
            orl copy = new orl(Wall);
            copy.setPostion(copyX+(7+i)*76,copyY-76);
            fakeOrl.add(copy);
        }
         copyX = -152;
         copyY = y;
        for(int i = 0; i < 200;i++){
            orl copy = new orl(Wall);
            orl copy2 = new orl(Wall);
            copy.setPostion(copyX,copyY+76*100);
            copy2.setPostion(copyX+33*76,copyY+76*100);
            fakeOrl.add(copy);
            fakeOrl.add(copy2);
            copyY-=76;
        }
        copyX = -152;
        copyY = y;
        for(int i = 0; i<30;i++){
            orl copy = new orl(Wall);
            copy.setPostion(copyX,copyY-76*101);
            copyX++;
        }

        for(int i = 0;i<3200;i++) {
            int percentCoal = (int) (Math.random() * 3);
            int percentCopper = (int) (Math.random() * 5);
            int percentGold = (int) (Math.random() * 10);
            int percentCrystal = (int) (Math.random() * 50);
            int percentSmallGoblin = (int) (Math.random() * 200);
            if (percentSmallGoblin == 1 && i > 64) {
                Monster smallGoblin = new Monster(A.getSmallGoblin());
                smallGoblin.setPostion(x, y);
                monsters.add(smallGoblin);
            }



                 if (percentCrystal == 1) {
                    orls.add(new orl(crystal));
                } else if (percentGold == 1) {
                    orls.add(new orl(gold));
                } else if (percentCopper == 1) {
                    orls.add(new orl(copper));
                } else if (percentCoal == 1) {
                    orls.add(new orl(coal));
                } else {
                    orls.add(new orl(dirt));
                }
                orls.get(i).setPostion(x, y);
                x = x + 76;
                if (i % 32 == 0) {
                    x = -76;
                    y -= 76;
                }
        }
        orls.remove(0);//remove the very first blok that was for test purpose
        ArrayList<orl> collideToMonster = new ArrayList<orl>();

        for(int i = 0; i<orls.size();i++){
            for(int k = 0;k < monsters.size();k++){
                if(orls.get(i).getRect().overlaps(monsters.get(k).getCurrent().getBoundingRectangle())){
                    collideToMonster.add(orls.get(i));
                }
            }
        }
        for(orl orl:collideToMonster){
            orls.remove(orl);
        }
        for (orl orl : fakeOrl){
            orls.add(new orl(orl));
        }
        int loadCounter = 0;
        for(int i = 0;i<A.getBackList().size();i++){
            A.getBackList().get(i).setPosition(loadCounter*1282,-476*(i/5+1));

        }
        orls.remove(0);//remove the very first blok that was for test purpose
        for(int i = 0;i<A.getBackList().size();i++){
            A.getBackList().get(i).setPosition(loadCounter*1282,-476*(i/3+1));

            if(loadCounter==3) {
                loadCounter = 0;
            }else{
                loadCounter+=1;
            }
        }
        for(int i = 0;i<A.getShopPics().size();i++){
            A.getShopPics().get(i).setPosition(430,478);
        }
        A.getSkyPic().setPosition(-500,0);
    }
    @Override
    public void create() {
        img = new Texture("pics/background.png");
        batch = new SpriteBatch();
        font = new BitmapFont();
        font.getData().setScale(2f);
        font.setColor(140,140,55,2);
        font.setColor(140,140,60,1);
        createWorld();
    }

    @Override
    public void render() {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();
        new Sprite(img).draw(batch);
        //start up menu
        if(status.equals("startUp")){

            Sprite cover = new Sprite(new Texture("pics/cover.png"));
            cover.setPosition(0,0);
            //for blinking text
            if(startUpTimer > 10 && startUpTimer < 20){
                cover.draw(batch);
                startUpTimer += 1;
            }
            else if(startUpTimer == 20){
                startUpTimer = 0;
                cover.draw(batch);
            }
            else{

                cover.draw(batch);
                font.draw(batch, "Press any button to start.", 480, 100);
                startUpTimer+=1;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.ANY_KEY)){//if user press any key, go to menu
                status = "menu";
            }
        }
        else if(status.equals("menu")){
            //load pics
            Sprite menuStartGame = new Sprite(new Texture("pics/menuStartGame.png"));
            Sprite menuGuide = new Sprite(new Texture("pics/menuGuide.png"));
            menuStartGame.setPosition(0,0);//set location for both buttons
            menuGuide.setPosition(0,0);
            if(menuSelection == 0){//now selecting start game
                menuStartGame.draw(batch);
                if(Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){//go down, select guide
                    menuSelection = 1;
                }
                else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){

                    status = "loading";//go to loading
                }
            }
            else if(menuSelection == 1){//guide
                menuGuide.draw((batch));
                if(Gdx.input.isKeyPressed(Input.Keys.UP)){
                    menuSelection = 0;//go back to start game
                }
                else if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
                    guideS = true;//user pressed enter, show guide

                }
            }
            if(guideS){
                Sprite guidePic = new Sprite(new Texture(Gdx.files.internal("pics/guideText.png")));
                guidePic.setPosition(20,20);
                guidePic.draw(batch);
                if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
                    guideS=false;//user want to close
                }
                else{
                    guidePic.draw(batch);//show the guide
                }

            }
        }
        //this is the shop
        else if(status.equals("shop")){

            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){//quit to game
                status = "game";
                role.setPosition(541,477);//reset position
            }
            else{

                A.getMouse().setPosition(C.getMouseX(),954-48-C.getMouseY());//get cursor location
                //set pic location
                A.getFirstAid().setPosition(305,453);
                A.getOxygenCapsule().setPosition(462,453);
                A.getSmallBomb().setPosition(610,448);
                A.getBigBomb().setPosition(780,452);
                A.getReviveKit().setPosition(942,450);

                A.getShopPage().setPosition(0,0);//background
                A.getShopPage().draw(batch);//draw

                if(A.getMouse().getBoundingRectangle().overlaps(A.getFirstAid().getBoundingRectangle())){//check collision
                    A.getFirstAid().draw(batch);//light up
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)) {//left click

                        if (role.getMoney() > 2000 || role.getMoney() == 2000) {//if enough money
                            A.aidNum += 1;//get one
                            role.Money -= 2000;//take money
                        }
                        C.wait(300);//pressing preventing
                    }
                    //same here
                }else if(A.getMouse().getBoundingRectangle().overlaps(A.getOxygenCapsule().getBoundingRectangle())){
                    A.getOxygenCapsule().draw(batch);
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

                        if(role.getMoney()>4000 || role.getMoney()==4000){
                            A.oxyNum+=1;
                            role.Money-=4000;
                        }
                        C.wait(300);
                    }

                }
                else if(A.getMouse().getBoundingRectangle().overlaps(A.getSmallBomb().getBoundingRectangle())){
                    A.getSmallBomb().draw(batch);
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

                        if(role.getMoney()>200 || role.getMoney()==200){
                            A.smallBombNum+=1;
                            role.Money-=200;
                        }
                        C.wait(300);
                    }

                }
                else if(A.getMouse().getBoundingRectangle().overlaps(A.getBigBomb().getBoundingRectangle())){
                    A.getBigBomb().draw(batch);
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){

                        if(role.getMoney()>1000 || role.getMoney()==1000){
                            A.bigBombNum+=1;
                            role.Money-=1000;
                        }
                        C.wait(300);
                    }

                }
                else if(A.getMouse().getBoundingRectangle().overlaps(A.getReviveKit().getBoundingRectangle())){
                    A.getReviveKit().draw(batch);
                    if(Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
                        if(role.getMoney()>5000 || role.getMoney()==5000){
                            A.reviveNum+=1;
                            role.Money-=5000;
                        }
                    }

                }


                //words
                font.draw(batch,"Click ESC to Exit",550,100);
                font.draw(batch,"Money:  "+Integer.toString(role.getMoney()),10,900);
                font.draw(batch,Integer.toString(A.aidNum),492,820);//num of items
                font.draw(batch,Integer.toString(A.oxyNum),560,820);
                font.draw(batch,Integer.toString(A.smallBombNum),630,820);
                font.draw(batch,Integer.toString(A.bigBombNum),700,820);
                font.draw(batch,Integer.toString(A.reviveNum),770,820);

                A.getItemBar().draw(batch);//draw bar
                A.getMouse().draw(batch);//draw cursor
            }
        }



        //for loading files
        else if(status.equals("loading")){
            ArrayList<Sprite> loadingBar = new ArrayList<Sprite>();
            for(int i = 0; i<5;i++){
                Sprite pic = new Sprite(new Texture(Gdx.files.internal("pics/loading"+Integer.toString(i+1)+".png")));
                pic.setPosition(0,0);
                loadingBar.add(pic);
            }
            //for sprite
            if(loadingCounter%5==0){
                loadingBar.get(spriteIndex).draw(batch);
                spriteIndex += 1;

            }
            else if((loadingCounter-1)%20==0){
                spriteIndex = 0;//reset
                loadingBar.get(spriteIndex).draw(batch);
            }

            else{
                loadingBar.get(spriteIndex).draw(batch);
            }
            if(loadingCounter == 20){
                status = "game";//loading end
            }

            loadingCounter += 1;

        }

        //game part
        else if(status.equals("game")) {

            timer1+=5;
            if (gasLine<-1000){
                role.loseOxygen();//if under 1000, lose oxygen
            }
            if(role.getOxygen()<0){
                role.loseHealth();//oxygen hits 0, lose health
                role.setPosition("damage");
            }
            if(role.getCollideMonster(monsters)){
                role.setPosition("takeFakeDamage");
            }
            //
            if(Gdx.input.isKeyPressed(Input.Keys.A)){
                if(A.aidNum>0){
                    role.useHealth();
                    A.aidNum-=1;
                }
            }
            if(Gdx.input.isKeyPressed(Input.Keys.S)){
                if(A.oxyNum>0){
                    role.useOxygen();
                    A.oxyNum-=1;
                }
            }


            if(Gdx.input.isKeyPressed(Input.Keys.D)){
                if(A.getSmallBombNum()>0){
                    bombs.add(A.getBomb());
                }
            }
            if(role.getCollideMonster(monsters)&& timer1>500){
                role.takeDamge(50);
                timer1 = 0;
            }
            if(shopCounter==10){
                shopCounter=0;
            }else{
                shopCounter+=1;
            }



            if(role.getBody().overlaps(worldRect)) {
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    // use translate(vx,vy), translateX(vx) or translateY(vy)
                    if(!role.getCollideUp(orls)) {
                        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                            role.setPosition("left");
                        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                            role.setPosition("right");
                        }
                    } else if(role.getCollideUp(orls)){
                        role.setPosition("fakeUp");
                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                    if (role.getCollideLeft(orls)) {
                        if (orls.indexOf(role.getCollideOrlLeft(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlLeft(orls))).mining();
                            role.digLeft();
                        }
                    } else if (!role.getCollideDown(orls)&&!role.getCollideLeft(orls)) {
                        role.setPosition("left");
                        role.setPosition("acc");
                    } else {
                        role.setPosition("left");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    if (role.getCollideRight(orls)) {
                        if (orls.indexOf(role.getCollideOrlRight(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlRight(orls))).mining();
                            role.digRight();
                        }
                    }
                    else if (!role.getCollideRight(orls)&&!role.getCollideDown(orls)) {
                        role.setPosition("right");
                        role.setPosition("acc");
                    }
                    else if (!role.getCollideRight(orls)) {
                        role.setPosition("right");
                    }
                }
                else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && role.getCollide(orls)) {
                    if (orls.indexOf(role.getCollideOrl(orls)) != -1) {
                        orls.get(orls.indexOf(role.getCollideOrl(orls))).mining();
                        role.digDown();
                    }
                } else if (role.getCollideDown(orls)) {
                    role.setPosition("static");
                } else {
                    role.setPosition("acc");
                }

            }else {
                if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
                    if(role.getBody().getY()-10<worldRect.getY()){
                        role.setPosition("up");
                    }
                    if(!role.getCollideUp(orls)) {
                        moveWorld(0,-constantY);
                        role.setPosition("fakeUp");
                        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
                            role.setPosition("fakeUpLeft");
                            moveWorld(constantX,-constantY);
                        } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                            role.setPosition("fakeUpRight");
                            moveWorld(-constantX,-constantY);

                        }
                    } else if(role.getCollideUp(orls)) {
                        role.setPosition("fakeUp");
                    }

                } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {

                    if (role.getCollideLeft(orls)) {
                        if (orls.indexOf(role.getCollideOrlLeft(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlLeft(orls))).mining();
                            role.digLeft();
                        }
                    } else if (!role.getCollideDown(orls)) {
                        role.setPosition("fakeLeft");
                        moveWorld(constantX,0);
                        role.setPosition("fakeAcc");
                        moveWorld(0,constantY);
                    } else {
                        moveWorld(constantX,0);
                        role.setPosition("fakeLeft");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
                    if (role.getCollideRight(orls)) {
                        if (orls.indexOf(role.getCollideOrlRight(orls)) != -1) {
                            orls.get(orls.indexOf(role.getCollideOrlRight(orls))).mining();
                            role.digRight();
                        }
                    } else if (!role.getCollideDown(orls)) {
                        role.setPosition("fakeRight");
                        moveWorld(-constantX,0);
                        role.setPosition("fakeAcc");
                        moveWorld(0,constantY);
                    } else {
                        moveWorld(-constantX,0);
                        role.setPosition("fakeRight");

                    }
                } else if (Gdx.input.isKeyPressed(Input.Keys.DOWN) && role.getCollide(orls)) {

                    if (orls.indexOf(role.getCollideOrl(orls)) != -1) {
                        orls.get(orls.indexOf(role.getCollideOrl(orls))).mining();
                        role.digDown();
                    }
                } else if (role.getCollideDown(orls)) {
                    role.setPosition("static");
                } else {
                    role.setPosition("fakeAcc");
                    moveWorld(0,constantY);
                }
            }
            if(role.getBody().overlaps(A.getShopRect().getBoundingRectangle())){
                if(Gdx.input.isKeyPressed(Input.Keys.ENTER)){
                    status = "shop";
                }

            }


            if (Gdx.input.isKeyPressed(Input.Keys.P)) {
                status  = "pause";
            }
            if(role.getCollideOrl(orls).getOrl()){
                role.getCollideOrl(orls).pickUp();
                role.getOrl(role.getCollideOrl(orls));
            }

            //start drawing


            A.getSkyPic().draw(batch);
            for(int i = 0;i<A.getBackList().size();i++){
                A.getBackList().get(i).draw(batch);
            }
            A.getShopPics().get(shopCounter/4).draw(batch);
            for(int i =0;i<orls.size();i++) {
                if(orls.get(i).Appear) {
                    if(orls.get(i).getCurrent().getX()>-100&&orls.get(i).getCurrent().getX()<1300) {
                        if (orls.get(i).getCurrent().getY() > -100 && orls.get(i).getCurrent().getY() < 1000) {
                            orls.get(i).getCurrent().draw(batch);
                        }
                    }
                }else{
                    orls.remove(i);
                }
            }
            for(int i =0;i<monsters.size();i++){
                if(monsters.get(i).getCurrent().getX()>-100&&monsters.get(i).getCurrent().getX()<1300) {
                    if (monsters.get(i).getCurrent().getY() > -100 && monsters.get(i).getCurrent().getY() < 1000) {
                        monsters.get(i).getCurrent().draw(batch);
                    }
                }
            }
            for(Bomb ok : bombs){
                if(ok.explored){
                    bombs.remove(ok);
                    break;
                }else{
                    ok.getCurrent().draw(batch);
                }
                    ok.booming();
            }
            role.getCurrent().draw(batch);
            font.draw(batch, "Your money: "+role.getMoney(), 10, 900);
            font.draw(batch, "Your health: "+role.getHealth(), 10, 700);
            font.draw(batch, "Your Oxygen: "+role.getOxygen(), 10, 800);
            font.draw(batch,Integer.toString(A.aidNum),492,820);//num of items
            font.draw(batch,Integer.toString(A.oxyNum),560,820);
            font.draw(batch,Integer.toString(A.smallBombNum),630,820);
            font.draw(batch,Integer.toString(A.bigBombNum),700,820);
            font.draw(batch,Integer.toString(A.reviveNum),770,820);

         }

        if(status.equals("game")||status.equals("shop")){
            A.getItemBar().draw(batch);
        }

        batch.end();
    }
    @Override
    public void dispose(){
        batch.dispose();
    }
    public void moveWorld(float x,float y){
        gasLine -= y;
        for(int i = 0;i<orls.size();i++){
            orls.get(i).moveOrl(x,y);

        }
        for(int i = 0;i<A.getBackList().size();i++){
            A.getBackList().get(i).translateX(x);
            A.getBackList().get(i).translateY(y);
        }
        for(int i = 0;i<A.getShopPics().size();i++){
            A.getShopPics().get(i).translateX(x);
            A.getShopPics().get(i).translateY(y);
        }
        A.getShopRect().translateX(x);
        A.getShopRect().translateY(y);
        for(int i = 0;i<monsters.size();i++){
            monsters.get(i).getCurrent().translateX(x);
            monsters.get(i).getCurrent().translateY(y);
        }

        A.getSkyPic().translateX(x);
        A.getSkyPic().translateY(y);
        for(int i = 0;i<A.getSmallBombUse().size();i++){
            A.getSmallBombUse().get(i).translateX(x);
            A.getSmallBombUse().get(i).translateY(y);
        }
        A.getBigBomb().translateX(x);
        A.getBigBomb().translateY(y);

    }
}
