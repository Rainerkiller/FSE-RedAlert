package com.mygdx.game.desktop;
/*
we will study linked list, binary trees and HASH TABLE
ArrayLists

 */
public class LNode {
    // tail is next
    //head is pre
    private LNode head;
    public int val;
    private LNode tail;
    public LNode(LNode head, int v){
        val = v;
        this.head = head;

    }
    public LNode(int v,LNode prev, LNode next){
        val = v;
        this.tail = next;
        this.head = prev;
    }
    public int getVal(){
        return val;
    }
    public void setVal(int a){
        val=a;
    }
    public LNode getNext(){ return tail;
    }
    public void setNext(LNode n){
        tail = n;
    }
    public LNode getPrevious(){
        return head;
    }
    public void setPrevious(LNode k){
        head = k;
    }
}