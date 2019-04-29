package com.mygdx.game.desktop;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Htest {
    public static void main(String [] args)throws IOException{
        HashTable<String> a = new HashTable<String>();
        System.out.println("aah".hashCode());
        // load up
        String path = "dictionary.txt";
        FileReader buffer = new FileReader(path);
        BufferedReader text = new BufferedReader(buffer);
        Scanner texts = new Scanner(text);
        HashTable<String> dictionary = new HashTable<String>();

        // sir you lied to us there are only 80369 words
        while(texts.hasNextLine()){
            dictionary.add(texts.nextLine());
        }
        texts.close();
        //System.out.println(dictionary.contains("scutum"));
        Scanner key = new Scanner(System.in);//scanner
        String word = key.nextLine();//letters
        ArrayList words = permutation(word,"",new ArrayList());//get all posibles
        ArrayList dic = new ArrayList();//real words
        for(Object Val: words){// if dictionary contains that word, then add the real word
            if(dictionary.contains(Val.toString())){
                dic.add(Val.toString());
            }
        }

        HashSet<String> set = new HashSet<String>(dic);// hashset is similar to Btree and its very good to clean up
        dic = new ArrayList<String>(set);//                 these repeat values
        for(Object T: dic){
            System.out.println(T.toString());//print it
        }
    }
    public static ArrayList<String> permutation(String str,String ans,ArrayList ans1){//changed a little bit of the recursion
        if(str.length()==0){            //current str,the container of current ans, the ArrayList of all answer
            ans1.add(ans);//base case?
            return ans1;
        }else{
            for(int i = 0;i<str.length();i++) {
                char ch = str.charAt(i);//i forgot all these things but whatever
                String ros = str.substring(0, i)+str.substring(i+1);
                permutation(ros,ans+ch,ans1);
            }
        }
        return ans1;
    }
}
