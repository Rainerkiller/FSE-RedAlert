package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

class Toolbox {
    private boolean mouseLeftClicked, mouseRightClicked;

    //get mouse X position
    public float getMouseX() {
        return Gdx.input.getX();
    }

    //get mouse Y position
    public float getMouseY() {
        return Gdx.input.getY();
    }

    // detect if the mouse left button is clicked
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

    //detect if the mouse right button is clicked
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

    //random number generator
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
