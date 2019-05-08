package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

class Toolbox {
    private boolean mouseLeftClicked, mouseRightClicked;

    public int getMouseX() {
        return Gdx.input.getX();
    }

    public int getMouseY() {
        return Gdx.input.getY();
    }

    public boolean mouseLeftClick(){
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            mouseLeftClicked = true;
            return false;
        }
        if(mouseLeftClicked){
            mouseLeftClicked = false;
            return true;
        }
        return false;
    }

    public boolean mouseRightClick(){
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            mouseRightClicked = true;
            return false;
        }
        if(mouseRightClicked){
            mouseLeftClicked = false;
            return true;
        }
        return false;
    }

    public int randomNumber (int number){
        return (int)(Math.random()*number);
    }


    public void wait(int ms){
        try{
            Thread.sleep(ms);
        }
        catch (InterruptedException ie){
            Thread.currentThread().interrupt();
            System.out.println("Error: wait() -> Toolbox.java");
        }
    }
}
