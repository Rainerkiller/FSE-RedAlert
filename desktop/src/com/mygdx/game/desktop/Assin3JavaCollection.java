package com.mygdx.game.desktop;

import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.*;
//this is a program that will help you manage people's choice on movies
public class Assin3JavaCollection {
    private static String path = "D:/test/";
    private static String filenameTemp;
    public static boolean creatTxtFile(String name) throws IOException {// create file
        boolean flag = false;
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {//recover existed file
            filename.createNewFile();
            flag = true;
        }
        return flag;
    }
    //learn from online, how to write in file
    public static boolean writeTxtFile(String newStr) throws IOException {//write it
        boolean flag = false;
        String filein = newStr + "\r\n";
        String temp = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;

        FileOutputStream fos = null;
        PrintWriter pw = null;
        try {
            File file = new File(filenameTemp);

            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);//continous step
            br = new BufferedReader(isr);

            StringBuffer buf = new StringBuffer();

            for (int j = 1; (temp = br.readLine()) != null; j++) {
                buf = buf.append(temp);
                buf = buf.append(System.getProperty("line.separator"));// new line
            }
            buf.append(filein);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
            flag = true;
        } catch (IOException e1) {
            throw e1;
        } finally {
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return flag;
    }

    //start of the prohram
    public static void main(String[] ARGS) throws IOException {
        String path = "picks.txt";//read the file
        FileReader buffer = new FileReader(path);
        BufferedReader text = new BufferedReader(buffer);
        Scanner texts = new Scanner(text);
        List<String> ake = new ArrayList<String>();
        while (texts.hasNextLine()) {
            ake.add(texts.nextLine());
        }
        List<String> movies = new ArrayList<String>();
        ArrayList<ArrayList<String>> reusltss = new ArrayList<ArrayList<String>>();// not a hashmap, i think its same
        for (int i = 0; i < ake.size(); i++) {// we knew that for a line there are 2 names and a movie
            String s1 = ake.toArray()[i].toString().split(",")[0];//1 2 3
            String s2 = ake.toArray()[i].toString().split(",")[1];
            String film = ake.toArray()[i].toString().split(",")[2];
            if (!movies.contains(film)) {// if its not here
                movies.add(film);// them add up
                ArrayList names = new ArrayList();
                names.add(s1);
                names.add(s2);
                reusltss.add(names);
            } else {
                int index = movies.indexOf(film);// if the movie has been there already, find out that index it is
                ArrayList contians = reusltss.get(index);// all names
                contians.add(s1);// add names
                contians.add(s2);
                Collections.sort(contians);//sort it
                reusltss.set(index, contians);//set it bakc
            }
        }
        creatTxtFile("result.txt");//file
        for(int i = 0;i<reusltss.size();i++){//in
            writeTxtFile(movies.toArray()[i].toString() +"----"+reusltss.toArray()[i].toString());
        }
    }
}