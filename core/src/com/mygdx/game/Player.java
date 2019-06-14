package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;

import java.lang.reflect.Array;
import java.security.DigestException;
import java.util.ArrayList;

class Player {
    String name;
    int Money;
    Sprite Current;
    Rectangle body;
    ArrayList<Sprite> mining = new ArrayList<Sprite>();
    ArrayList<Sprite> noMotion = new ArrayList<Sprite>();
    ArrayList<Sprite> movingL = new ArrayList<Sprite>();
    ArrayList<Sprite> movingR = new ArrayList<Sprite>();
    ArrayList<Sprite> takeDamage = new ArrayList<Sprite>();
    ArrayList<Sprite> fly = new ArrayList<Sprite>();
    ArrayList<Sprite> flyLeft = new ArrayList<Sprite>();
    ArrayList<Sprite> flyRight = new ArrayList<Sprite>();
    ArrayList<Sprite> roleDig = new ArrayList<Sprite>();

    String [] bag = new String[18];
    float health;
    float oxygen;
    float depth = 0;
    float currentX;
    float currentY;
    float acceleration = 1;
    int timer = 0;
    int picture = 0;
    float speed = 0;

    public Player(ArrayList<Sprite> noMotion, ArrayList<Sprite> movingL
            ,ArrayList<Sprite> fly,ArrayList<Sprite> flyRight,ArrayList<Sprite> flyLeft, ArrayList<Sprite> movingR, ArrayList<Sprite> takeDamage,ArrayList<Sprite> roleDig){
        Current = noMotion.get(0);
        this.noMotion = noMotion;
        this.movingL = movingL;
        this.movingR = movingR;
        this.takeDamage = takeDamage;
        this.fly = fly;
        this.flyLeft = flyLeft;
        this.flyRight = flyRight;
        this.roleDig = roleDig;
        name = "role";
        Money = 0;
        health = 100;
        oxygen = 100;
        body = Current.getBoundingRectangle();
        body.setX(body.getX()+10);
        body.setY(body.getY()+10);
        body.setHeight(70);
        body.setWidth(65);
        setPosition(641,477);
    }
    public Player(){
        name = "role";
        Money = 0;
        health = 100;
        oxygen = 100;
    }
    public float getSpeed(){
        return speed;
    }
    public int getMoney(){
        return Money;
    }

    public float getHealth() {
        return health;
    }

    public float getOxygen() {
        return oxygen;
    }

    public String[] getBag() {
        return bag;
    }
    public void setPosition(float x,float y){
        currentX = x;
        currentY = y;
        refreshPosition();
    }
    public void setPosition(String a){
        if(a.equals("up")){
            up();
            currentY+=speed;
            if(speed<8){
                speed+=acceleration;
            }
        }
        else if(a.equals("upLeft")){
            upLeft();
            currentY+=speed;
            currentX-=5;
        }
        else if(a.equals("upRight")){
            upRight();
            currentX+=5;
            currentY+=speed;
        }
        else if(a.equals("acc")) {
            acc();
            currentY -=speed;
            if(speed<8){
                speed+=acceleration;
            }
        }
        else if(a.equals("down")){
            currentY-=5;
        }
        else if(a.equals("left")){
            currentX-=5;
            left();
        }
        else if(a.equals("right")){
            right();
            currentX+=5;
        } else if(a.equals("static")){
            Static();
        }else if(a.equals("fakeAcc")){
            if(speed<8){
                speed+=acceleration;
            }
        }else if(a.equals("digDown")){
            digDown();
        }else if(a.equals("digRight")){
            digRight();
        }else if(a.equals("digLeft")){
            digLeft();
        }else if(a.equals("fakeUp")){
            fakeUp();
        }else if(a.equals("fakeRight")){
            right();
        }else if(a.equals("fakeUp")){
            up();
        }else if(a.equals("fakeLeft")){
            left();
        }else if(a.equals("fakeUpLeft")){
            upLeft();
        }else if(a.equals("fakeUpRight")){
            upRight();
        }
        refreshPosition();
        Current.setPosition(currentX,currentY);
        body = Current.getBoundingRectangle();
        body.setX(body.getX()+10);
        body.setY(body.getY()+5);
        body.setHeight(76);
        body.setWidth(50);

    }
    public void fakeUp(){
        Current = fly.get(0);
    }
    public void acc(){
        Current = noMotion.get(0);
    }
    public void upLeft(){
        Current = flyLeft.get(0);
    }
    public void upRight(){
        Current = flyRight.get(0);
    }
    public void up(){
        Current = fly.get(0);
    }
    public void digDown(){
        Current = roleDig.get(0);
    }
    public void digLeft(){
        Current = roleDig.get(1);
    }
    public void digRight(){
        Current = roleDig.get(2);
    }
    public void Static(){
        if (picture < movingL.size()-1&& timer>100) {
            picture++;
            timer = 0;
        } else{
            timer+=5;
            picture = 0;
        }
        Current = noMotion.get(picture);
    }
    public void left() {
        if (picture < movingL.size()-1) {
            picture++;
        } else {
            picture = 0;
        }
        Current = movingL.get(picture);
    }
    public void right() {
        if (picture < movingR.size()-1) {
            picture++;
        } else {
            picture = 0;
        }
        Current = movingR.get(picture);
    }
    public Rectangle getBody(){
        return body;
    }
    public void refreshPosition(){
        for(int i = 0;i< roleDig.size();i++){
            roleDig.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<noMotion.size();i++){
            noMotion.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<movingL.size();i++){
            movingL.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<movingR.size();i++){
            movingR.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<takeDamage.size();i++){
            takeDamage.get(i).setPosition(currentX,currentY);
        }
        for(int i =0;i<mining.size();i++){
            mining.get(i).setPosition(currentX,currentY);
        }
    }
    public void takeDamge(int damage){
        health = health - damage;
    }
    public Sprite getCurrent(){
        return Current;
    }
    public boolean getCollide(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++){
            Rectangle orl = orls.get(i).getRect();
            if(orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)){
                    return true;
            }
        }
        return false;
    }
    public boolean getCollideDown(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++){
            Rectangle orl = orls.get(i).getRect();
            if(orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)&&!orls.get(i).getOrl()){
                if(orl.getY()+orl.getHeight()/1.3<body.getY()) {
                    return true;
                }
            }
        }
        return false;
    }    public boolean getCollideRight(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++){
            Rectangle orl = orls.get(i).getRect();
            if(orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)&&!orls.get(i).getOrl()){
                if(orl.getY()<body.getY()+body.getHeight()){
                    if(orl.getY()+orl.getHeight()>body.getY()+20) {
                        if(orl.getX()>body.getX()+body.getWidth()-5){
                                return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean getCollideLeft(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++) {
            Rectangle orl = orls.get(i).getRect();
            if (orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)&&!orls.get(i).getOrl()) {
                if(orl.getY()<body.getY()+body.getHeight()){
                    if(orl.getY()+orl.getHeight()>body.getY()+20) {
                        if (orl.getX() + orl.getWidth() < body.getX() + 15) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
    public boolean getCollideUp(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++) {
            Rectangle orl = orls.get(i).getRect();
            if (orl.overlaps(body)&&!orls.get(i).getOrl()) {
                System.out.print(orl.getY()+"   ");
                System.out.println(orl.getY());
                if(orl.getY()>body.getY()+body.getHeight()/20){
                    return true;
                }
            }
        }
        return false;
    }
    public orl getCollideOrl(ArrayList<orl> orls){
        orl get = new orl();
        for(int i = 0;i<orls.size();i++) {
            if (orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)) {
                if (orls.get(i).getCurrent().getX() < body.getX()) {
                    get = orls.get(i);
                    break;
                }else {
                    for (int i2 = i; i2 < orls.size(); i2++) {
                        if (orls.get(i2).getCurrent().getBoundingRectangle().overlaps(body)) {
                              get = orls.get(i2);
                        }
                    }
                }
            }
        }
        return get;
    }
    public orl getCollideOrlLeft(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++) {
            Rectangle orl = orls.get(i).getRect();
            if (orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)) {
                if(orl.getY()<body.getY()+body.getHeight()){
                    if(orl.getY()+orl.getHeight()>body.getY()+20) {

                        if (orl.getX() + orl.getWidth() > body.getX() - 10) {
                            return orls.get(i);
                        }
                    }
                }
            }
        }
        return new orl();
    }
    public orl getCollideOrlRight(ArrayList<orl> orls){
        for(int i = 0;i<orls.size();i++) {
            Rectangle orl = orls.get(i).getRect();
            if(orls.get(i).getCurrent().getBoundingRectangle().overlaps(body)){
                if(orl.getY()<body.getY()+body.getHeight()){
                    if(orl.getY()+orl.getHeight()>body.getY()+20) {
                        if(orl.getX()>body.getX()+body.getWidth()-5){
                            return orls.get(i);
                        }
                    }
                }
            }
        }
        return new orl();
    }
}
