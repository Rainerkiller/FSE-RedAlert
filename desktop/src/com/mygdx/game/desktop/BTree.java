package com.mygdx.game.desktop;
// Binary Tree assignment
//by jacob
/* this is a simple class that has some simple tools to mange binary tree, includes add, depth(find the depth of a value)
countleaves(count how many nodes that has no son), height(the total height of the tree), isBalanced, delete, isAncestor and
delete
*/
public class BTree {
    private BNode root;
    public void BNode(){
        root = null;
    }
    public BNode find(int v){ ///recursion solve
        return find(v,root);
    }
    private BNode find(int v,BNode node){
        if(node == null||node.getVal() == v){// base case
            return node;
        }
        else{// fo left(small) or go right(large)
            return v<node.getVal()? find(v,node.getLeft()):find(v,node.getRight());
        }
    }
    public void add(int v){// add is add
        if(root == null){
            root = new BNode(v);
        }
        else{
            add(v,root);
        }
    }
    private void add(int v,BNode k){//copy from class
        if(v>k.getVal()){
            if(k.getRight()==null){
                BNode tmp = new BNode(v);
                k.setRight(tmp);
                //find(5).setLeft(k);
            }
            else {
                add(v,k.getRight());
            }
        }
        else if(v<k.getVal()){
            if(k.getLeft() == null){
                k.setLeft(new BNode(v));
            }
            else {
                add(v,k.getLeft());
                }
        }
    }
    public int depth(int a ){// recursion
        return depth(root,a,0);
    }
    private int depth(BNode node, int a ,int count){// find the depth of a single number
        if(node == null){//null pointer
            return -1;
        }
        else if(node.getVal()>a){//go left
            return depth(node.getLeft(),a,count+1);
        }
        else if(node.getVal()<a){//go right
            return depth(node.getRight(),a,count+1);
        }
        else {
            return count+1;//return the finally number
        }
    }
    private int countLeave(BNode node){// find how many leaves
        if(node == null)// null check
            return 0;
        if(node.getLeft() == null&&node.getRight() == null) {// if there is no leave (if this is the leave)
            return 1;
        } else {//
            return countLeave(node.getLeft())+countLeave(node.getRight());//how many 1
        }
    }
    public int countLeave(){// recursion point
        int k = countLeave(root);
        return k;
    }
    private int height(BNode node){// find height of the tree
        if(node == null){// check null
            return 0;
        }
        int Left = height(node.getLeft());//left
        int Right = height(node.getRight());//right
        if(Left>Right){// which side is larger                                  3
            return Left+1;//                                                   1  2
        }//                                                                      1 1
        else {//from bottom to top.
            return Right+1;
        }
    }
    public int height(){
        return height(root);
    }
    public int minHeight(){
        return minHeight(root);
    }
    private int minHeight(BNode node){
        if(node == null){//null check
            return 0;
        }
        int Left = height(node.getLeft());
        int Right = height(node.getRight());
        if (Left == 0||Right == 0){//hope it is not a single line tree
            return Left+Right+1;
        }
        else{//find the smaller one to return
            return Math.min(Left,Right)+1;
        }
    }

    public boolean isBalanced(){//difference of the max height and minium
        return Math.pow(Math.pow((double)(minHeight()-height()),2),0.5)<2;
    }
/*
    public BNode findF(BNode node,int a){
        BNode f = node;
        if(node==null){
            return null;
        }
        if(f.getVal()==a){
            return f;
        }
        BNode NEXT = findF(f.getLeft(),a);
        if(NEXT == null){
            return findF(f.getRight(),a);
        }else {
            return NEXT;
        }
    }
    */
    public void delete(int a ){ //recursion delete
        root = delete(root,a);
    }
    private BNode delete(BNode node, int a){
        if(node == null){// if there is nothing to delete then nothing
            return null;
        }
        if(a == node.getVal()){// when the case is here
            if(node.getLeft() == null&&node.getRight() == null){// it is a leaf
                return null;//cut off
            }
            if(node.getLeft() == null){  // if it has a single leave
                return node.getRight();
            }
            if(node.getRight() == null){
                return node.getLeft();
            }
            BNode Node = findMin(node.getRight());// find the mini number in order to replace the delete num
            node.setVal(Node.getVal());//replace it
            node.setRight(delete(node.getRight(), Node.getVal()));//keep doing this
            return node;
        }else if( a < node.getVal() ){//if it is smaller
            node.setLeft(delete(node.getLeft(), a));//go to find left side
            return node;
        }else{// larger
            node.setRight(delete(node.getRight(), a));//right side
            return node;
        }
    }
    private BNode findMin(BNode node){// find smallest value
       while(node.getLeft()!=null){
           node = node.getLeft();
       }
       return node;
    }
    public void display(String a ){// display
        display(root,a);
        System.out.println();
    }
    private void display(BNode node,String a){
        if(a.equals("POST")){//post is the opposite order of pre
            if(node != null){
                display(node.getLeft(),"POST");
                display(node.getRight(),"POST");
                System.out.print(node.getVal() + " ,");

            }
        }
        if(a.equals("PRE")){//get code from a super good teacher
            if(node != null){
                System.out.print(node.getVal() + " ,");
                display(node.getLeft(),"PRE");
                display(node.getRight(),"PRE");

            }
        }
        if(a.equals("IN")){// System.out.println(TreeString(root));
            System.out.println(TreeString(root));
        }
    }
    public boolean isAncestor(int a, int b){// recursion find it is ancestor
        BNode node = root;
        node = find(a);
        return isAncestor(node,b);
    }
    private boolean isAncestor(BNode node, int b ){
        if(node == null){//nothing to find return false
            return false;
        }
        if(node.getVal()>b){//go left if current value is larger
            node = node.getLeft();
            return isAncestor(node,b);
        }
        else if(node.getVal()<b){//smaller go right
            node = node.getRight();
            return isAncestor(node,b);
        }
        else{
            return true;//get it
        }

    }
    public BNode getRoot(){//i need root from a tree
        return root;
    }
    public void add(BTree node){
        add(node.getRoot());// add a that root
    }
    private void add(BNode node){
        if(node != null) {//null checker
            add(node.getVal());//print
            add(node.getLeft());//left
            add(node.getRight());//right
        }
    }
    public void sproud(){
        sproud(root);
    }
    public void sproud(BNode node){
        BNode Father = node;
        if(Father!=null) {
            BNode CopyL = Father.getLeft();
            BNode CopyR = Father.getRight();
            if (CopyL != null) {
                if (CopyL.getLeft() == null && CopyL.getRight() == null) {
                    add((Father.getVal() + CopyL.getVal()) / 2);
                } else if (CopyL.getLeft() != null) {
                    sproud(Father.getLeft());
                } else {
                    sproud(Father.getRight());
                }
            }
            if (CopyR != null) {
                if (CopyR.getLeft() == null && CopyR.getRight() == null) {
                    add((Father.getVal() + CopyR.getVal()) / 2);
                } else if (CopyR.getLeft() != null) {
                    sproud(Father.getLeft());
                } else {
                    sproud(Father.getRight());
                }
            }
        }
    }
    @Override
    public String toString(){
        return ("["+TreeString(root)+"]").replace(", ]","]");
    }
    public String TreeString(BNode brad){
        if(brad == null){
            return " ";
        }
        else {
            return TreeString(brad.getLeft()) + brad.getVal() + " ," + TreeString(brad.getRight());
        }
    }
}
