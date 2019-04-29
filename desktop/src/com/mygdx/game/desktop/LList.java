package com.mygdx.game.desktop;
//created by jacob
// this is LList class which you can use this class to create, edit and clone likedlist.
// most of them are double lists
// this is based on the LNode that teacher provides
// m is the universal copy of head in this evil LList world
public class LList {
    private LNode head = null;
    private LNode tail = null;
    public void push(int n) {// add from tail
        LNode tmp = new LNode(n,tail,null);// create a new LNode
        if (head == null) { // empty?
            head = tmp;     // add directky
        }
        else if(tmp.getPrevious()!=null){ // not empty
            tmp.getPrevious().setNext(tmp);// change the previous to this one
        }
        tail = tmp;
    }
    public void enque(int n) { // same as push , i mean same
        LNode tmp = new LNode(n,tail,null);
        if (head == null) {
            head = tmp;
        }
        else if(tmp.getPrevious()!=null){
            tmp.getPrevious().setNext(tmp);
        }
        tail = tmp;
    }
    public LNode deque(){// just let the head be the next head, no need about tail.
        LNode m = new LNode(head.getVal(),null,head.getNext());
        pop();
        return m;
    }
    public void pop(){
        head = head.getNext();
    }//same as deque except return a LNODE
    public void add(int n){
        LNode tem = new LNode(head,n);
        head = tem;
    }//classical add
    public void delete(LNode guy){//delete a node
        LNode k = guy; // to make sure we can take its previous and next node
        if(guy.getPrevious() == null){
            head = head.getNext();// if its first one them pop off
        }
        else if(guy.getNext() == null){// is its last one, cut chain between tail and head
            guy.getPrevious().setNext(null);
        }
        else {// last situation, the previous's tail will directly connected with next's previous
                // and next's previous will connect to the previous's next
            guy.getPrevious().setNext(k.getNext());
            guy.getNext().setPrevious(k.getPrevious());

        }
    }//
    public void delete(int num){// find a the node contains that number and delete it; once
        LNode m = head;
        while(m.getVal()!=num){
           m = m.getNext();
           break;//ONCE
        }
        delete(m);
    }
    public void deleteAt(int index){// find the node throw how many getNext().
        LNode m = head;
        if(index == 0){
            System.out.println("?");// why 0?
        }
        for(int i = 0;i<index-1;i++){
            m.getNext();// i cut down (index-1) times and delete it
        }
        delete(m);
    }
    public void sortedInser(LNode mm){// sort by finding a good place
        int mk = mm.getVal(); // m is head k is value so it is critical value that need to be sorted
        LNode m = head; // universal copy
        //System.out.println(1);
        while(m.getVal()>mk){// if the spot is not fit, them go down.
            //System.out.println(m.getVal()); test
            m = m.getNext(); //try next one
        }
        mm.setNext(m); //once we find spot, we will do similar thing as delete which the head will connect to previous
        mm.setPrevious(m.getPrevious());// tail will connect to next
        m.getPrevious().setNext(mm);// and finish the double linked
        m.setPrevious(mm);

    }
    public void soted(){

    }
    public void delete(int k, LNode victim){// find a that the node is the valve , if it is delete this node
        // for duplicates
        while(victim != null){
            if(victim.getVal() == k){
                delete(victim);
            }
            victim = victim.getNext();
            // find next time
        }
    }
    public void removeDuplicates(){
        LNode m = head; //UC
        while (m!=null){// till death
            delete(m.getVal(),m.getNext());// find and gone
            m = m.getNext();
        }
    }
    public void sort(int num){// you only need too add a number, what a conveniece thing.
        LNode m = new LNode(num,null,null);
        sortedInser(m);
    }
    public void reverse() {// recerse the list,
        LNode prev = null; //store the current previous
        LNode m = head;
        LNode next = head; // store the old head
        while (m != null) {//till death
            LNode temp = m.getNext(); // temp stored the current next
            m.setNext(prev);// set previous to next's previous
            prev = m;// up date the storage
            m = temp;// let is keep going
        }
        // fix head && tail
        head = prev;
        tail = next;

    }
    public LList clone(){ // clone
        LList k = new LList();//store nodes
        LNode m = head; // UC
        while(m!=null){
            k.enque(m.getVal()); // add every single nodes
            m = m.getNext();
        }
        return  k;//return it
    }

    @Override
    public String toString(){//to string
        String ans = "[";
        LNode mk = head;
        while(mk!=null){
            ans = ans + mk.getVal()+", ";
            mk = mk.getNext();
            //System.out.println(1);

        }
        if(ans.length()>1){
            ans = ans.substring(0,ans.length()-2);
        }
        return ans+"]";
    }
}
