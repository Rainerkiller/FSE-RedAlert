package com.mygdx.game.desktop;
import java.io.BufferedReader;
import java.io.*;
import java.util.*;
//This is an class which can find out the % weight on each words in a whole story by using hashmap and arryList
public class JavaCollectionFramework {
    public static void main(String[] args) throws IOException {
        String path = "story.txt";// load file
        FileReader buffer = new FileReader(path);
        BufferedReader text = new BufferedReader(buffer);
        Scanner texts = new Scanner(text);
        List Dictionary = new ArrayList(); //this is all lines of story
        List Storage = new ArrayList();// all words
        Hashtable aka = new Hashtable();// get number of each words
        while (texts.hasNextLine()) {
            Dictionary.add(texts.nextLine());
        }
        for (int i = 0; i < Dictionary.size(); i++) {//split
            for (int l = 0; l < Dictionary.toArray()[i].toString().replaceAll("\\p{P}","").split(" ").length; l++) {
                Storage.add(Dictionary.toArray()[i].toString().replaceAll("\\p{P}","").split(" ")[l]);
            }
        }
        int counts = 0;// count how many words are there
        for (Object T : Storage) {// collection methon
            if (!T.equals("")) {
                if (!aka.containsKey(T)) {
                    aka.put(T, 1);
                    counts++;
                } else {
                    int n = ((Integer) aka.get(T)).intValue();
                    aka.put(T, n + 1);
                    counts++;
                }
            }
        }
        ArrayList values = new ArrayList(aka.values());// all values
        ArrayList keys = Collections.list(aka.keys());//all keys
        ArrayList Sotredvalues = new ArrayList(aka.values());//sorted values
        ArrayList sortednAME = new ArrayList();// sorted keys based on the sorted value
        Collections.sort(Sotredvalues, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {// redef compareTo
                return ((Integer) o2).compareTo((Integer)o1);
            }
        });
        for(int i =0;i<aka.size();i++){
            sortednAME.add(null);// nullpointer
        }
        for(int i = 0;i<values.size();i++){// for each value in all keys
            Object kaaa = keys.toArray()[i];/// we are going to find out how many repeats are there
            Object asd = values.toArray()[keys.indexOf(kaaa)];// and then we knew that there are n words that repeat once
            int af = Sotredvalues.indexOf(asd);// find where should i put the 1 or 2 or 28
            while(sortednAME.toArray()[af]!=null) {// is the spot is full, move to next slide
                af++;
            }
            sortednAME.set(af,kaaa);//set
        }
        ArrayList finalaNS= new ArrayList();//create the final ans which is finalaNS
        for(int i = 0;i<sortednAME.size();i++){
            String finalans = new String();
            float str = Integer.parseInt(Sotredvalues.toArray()[i].toString())*100/counts;
            finalans = sortednAME.toArray()[i].toString() + "=" +str+"%,";
            finalaNS.add(finalans);
        }
    }
}

class Person implements Comparable<Person>{
    String name;
    int mark;
    public Person(String n, int m){
        name = n;
        mark = m;
    }
    @Override
    public String toString(){
        return  name+": "+mark;
    }
    @Override
    public int compareTo(Person P ){
       if(mark == P.mark){
           return name.compareTo(P.name);
       }
       else{
           return mark - P.mark;
       }
    }
}