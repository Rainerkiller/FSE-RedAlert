package com.mygdx.game.desktop;
/*
why use binary trees?
maintains sorted order
O(lgn) add speed;
O(lgn) sort speed;
 */
public class BNode {
    private int val;
    private BNode left,right;
    public BNode(int v){
        left = null;
        right = null;
        val = v;
    }
    public int getVal(){
        return val;
    }
    public void setVal(int a){
        val=a;
    }
    public BNode getRight(){
        return  right;
    }
    public BNode getLeft(){
        return left;
    }
    public void setLeft(BNode a){
        left = a;
    }
    public void setRight(BNode a){
        right = a;
    }
}
