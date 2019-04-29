package com.mygdx.game.desktop;

public class BTest {
    public static void main (String []args){
        BTree dollar = new BTree();
        BTree pounds = new BTree();
        dollar.add(50);
        dollar.add(60);
        dollar.add(45);
        dollar.add(30);
        dollar.add(70);
        dollar.sproud();
        System.out.println(dollar);
        //System.out.println(dollar.depth(17));
        //System.out.println(dollar.isAncestor(45,17));
        //System.out.println(dollar.isBalanced());
        //System.out.println(dollar.depth(200));
        //dollar.delete(45);
        //System.out.println(dollar);
        //System.out.println(dollar.height());
    }
}
