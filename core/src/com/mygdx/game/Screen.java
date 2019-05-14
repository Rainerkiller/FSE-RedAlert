package com.mygdx.game;

class Screen {
    float x, y;
    float width, length;
    //--------------------------return type------------------------------

    public float getX() {
        return x;
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public float getY() {
        return y;
    }
    //------------------------void Type-------------------------

    public void setLength(float length) {
        this.length = length;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }
}
