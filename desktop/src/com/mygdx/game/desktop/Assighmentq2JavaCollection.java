package com.mygdx.game.desktop;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
//this is a program that will check your spelling
public class Assighmentq2JavaCollection {
    public static void main(String[] args) throws IOException {
        String path = "dictionary.txt";//load file
        FileReader buffer = new FileReader(path);
        BufferedReader text = new BufferedReader(buffer);
        Scanner texts = new Scanner(text);
        Scanner keyB = new Scanner(System.in);
        HashSet<String> ake = new HashSet<String>();
        while (texts.hasNextLine()) {
            ake.add(texts.nextLine());
        }
        String aks = keyB.nextLine();// enter an argument  or i can load the file, it's few line codes
        String[] ab = aks.toLowerCase().replaceAll("\\p{P}", " ").split(" ");// split it into words by words
        for (int i = 0; i < ab.length; i++) {
            if (!ake.contains(ab[i])&& ab[i].equals("")&&ab[i].equals(" ")) {
                System.out.println(ab[i]+" is wrong");//if its wrong , tell it.
            }
        }
    }
}
