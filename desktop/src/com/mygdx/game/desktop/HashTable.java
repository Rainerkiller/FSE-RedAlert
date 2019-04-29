package com.mygdx.game.desktop;
import com.sun.org.apache.regexp.internal.RE;

import java.lang.reflect.Array;
import java.util.*;
/*
ignore these texts;
B/L Test
given numbers, make a tree
efficiency
Pros/Cons:
    primitive array:
        + efficient positional access.
        + simple syntax(count[i]+=1 ;; count.set(i,count.get(i)+1)
        - fixed size
    ArrayList:
        + can grow/sketch
        + efficient positional access.
        - some operation are inefficient, and it is not obvious
    Linked List:
        + O(1) add/get/remove from front/back
        + space efficient ; can grow and shrink
        - slow position access
    Binary Tree:
        +  stays in sorted order
        + O(lgn) add/find/delete is pretty good
        - no positional access
        * no repeat value
write a Linked List and Binary Tree.
*/
/*
To use a hash table we read to be able to get a hash code(a number) for our objects.
eg. System.out.println("Mr".hashCode)

collision: when two or more objects hash to the same location
load:# of elements/# spots
when your load goes above 70%, you need to resize your array to reduce your load;
   */
/*
Here is the start;
This is HashTable assignment which provides simple funtions on managing a hashTable,include add, remove
resize,contains,setLoad,getLoad and setMaxLoad

*/

public class HashTable<T> {
    private  ArrayList<LinkedList<T>>table;
    private double MaxLoad;
    private int size;//how many spots were used
    private int CurrentSize;// total spots
    public HashTable(){//provided
        size = 0;
        MaxLoad = 0.7;
        CurrentSize = 10;
        clear(10);
    }

    private void clear(int n){//provided
        table = new ArrayList<LinkedList<T>>();
        for(int i = 0;i<n;i++){
            table.add(null);
        }
    }

    public void add(T val) {//this is the add that what you give us
        int pos = Math.abs(val.hashCode()) % table.size();
        LinkedList<T> list = table.get(pos);
        if (list == null) {
            list = new LinkedList<T>();
            table.set(pos, list);
        }
        list.add(val);
        size++;
        if((double)(size/CurrentSize) > MaxLoad){//if its oversized then resize it;
            resize();
        }
    }
    public void resize(){// i dont know how this thing works, but you gave us this
        int n = table.size()*10;
        clear(n);
        size = 0;
        CurrentSize = n;
        for(LinkedList<T> list:table){
            if(list != null){
                for(T val : list){
                    add(val);
                }
            }
        }
    }
    public boolean contains(T val) {// find wheather it contains this val or not
        int pos = Math.abs(val.hashCode()) % table.size();//use simple math to find the spot in the
        //                                                  array list
        if (table.get(pos)==(null)) {//if that spot is null
            return false;
        }
        for (T k : table.get(pos)) {// if there is a linkedlist
            if (k.equals(val)) {//dig it through
                return true;
            }
        }
        return false;
    }
    public ArrayList toArray(){ // change hashtable to arrayList without null.
        ArrayList<T> fk = new ArrayList<T>();//the arrayList that contains these Objects
        for(LinkedList<T> M : table){//find every LinkedList in this table
            if(M!=null){// if its not null
                for(T val : M){//get every objects in lINEKEDLIST
                    fk.add(val);//add them in
                }
            }
        }
        return fk;//return and
    }
    public int getCurrentSize(){
        return CurrentSize;
    }
    public void remove(T val){//remove a object from the table
        int pos = Math.abs(val.hashCode())%table.size();//find the postion in table
        if(contains(val)){// if there is
            for(T k:table.get(pos)){ // make sure there is
                if(k.hashCode()==val.hashCode()){//if it is
                    table.get(pos).remove(k);//remove it
                }
            }
        }
    }
    public int getSize(){//size include null
        return size;
    }
    public void setMaxLoad(double k ){//set maxload
        if(k>0.1&&k<0.8){//in range
            MaxLoad=k;
        }
    }
    public void setLoad(double k){//set load
        if(k>MaxLoad){//if it is larger ,then nothing happened except setMaxLoad
            setMaxLoad(k);
        }else if(k<MaxLoad&&k>0.1){ // if it is smaller than current and it is over 0.1
            if((double)(size/CurrentSize)>k)
            resize();//resize it
            setMaxLoad(k);//set it
        }
    }
    public double getLoad(){//return current load
        return (double)(size/CurrentSize);
    }
    @Override
    public String toString(){//??????
        String ans = "";
        for(LinkedList<T> list:table){
            if(list !=null){
                for(T val:list){
                    ans+=", "+val;
                }
            }
        }

        if(ans!=""){
            ans = ans.substring(2);
        }
        return ans;
    }
}
