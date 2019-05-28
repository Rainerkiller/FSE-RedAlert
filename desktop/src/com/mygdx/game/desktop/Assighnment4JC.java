package com.mygdx.game.desktop;
import javafx.scene.Parent;
import org.lwjgl.Sys;
import org.lwjgl.opengl.SGISGenerateMipmap;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Assighnment4JC {
    private static String path = "D:/test/";
    private static String filenameTemp;
    public static int creatTxtFile(String name) throws IOException {
        filenameTemp = path + name + ".txt";
        File filename = new File(filenameTemp);
        if (!filename.exists()) {
            filename.createNewFile();
        }
        return 1;
    }
    public static int writeTxtFile(String newStr) throws IOException {
        String FileIn = newStr + "\r\n";
        String tmp = "";

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

            for (int j = 1;(tmp = br.readLine())!= null; j++) {
                buf = buf.append(tmp);
                buf = buf.append(System.getProperty("line.separator"));// new line
            }
            buf.append(FileIn);

            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buf.toString().toCharArray());
            pw.flush();
        } catch (IOException ok) {
            throw ok;
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
        return 1;
    }
    public static void main(String[] args) throws IOException{
        int TotalNum = 0;// number of cars
        HashMap<String,offense> Cars = new HashMap<String, offense>();// plate + offense
        boolean exit = false;
        while(!exit){// running
            Scanner kb = new Scanner(System.in);
            System.out.println("******************");
            System.out.println("******************");
            System.out.println("******************");

            System.out.println("Read or Create a file");
            if(kb.nextLine().equals("Read")){
                filenameTemp = "cars.txt";
                FileReader buffer = new FileReader(filenameTemp);
                BufferedReader text = new BufferedReader(buffer);
                Scanner texts = new Scanner(text);
                TotalNum = Integer.parseInt(texts.nextLine());
                int ReadCar = 0;
                while( ReadCar < TotalNum){//
                    String Plate = texts.nextLine();
                    int totalofenses = Integer.parseInt(texts.nextLine());
                    offense ready =new offense();// a tmp the store the data
                    for(int it = 0;it<totalofenses;it++){
                        String line = texts.nextLine();
                        String date = line.split(",")[0];//date
                        String time = line.split(",")[1];//time
                        String name = line.split(",")[2];//name
                        ready.AddOffense(date,time,name);// add back
                    }
                    Cars.put(Plate,ready);// hashmap is so cool
                    ReadCar++;//finished ine
                }
            }
            System.out.println("******************");
            System.out.println("******************");

            System.out.println("Enter order:");
            String In = kb.nextLine();
            if(In.equals("Exit")){
                exit=true;
            }else if(In.equals("Show all Offense")){
                System.out.println("Enter the plate:");
                String Plate = kb.nextLine();
                if(Cars.containsKey(Plate)){
                    offense offnse = Cars.get(Plate);
                    for(int i = 0;i<offnse.Number();i++){
                        System.out.println(offnse.date() +" at "+offnse.time() +", signed by "+ offnse.name());
                    }
                }else{
                    System.out.println("No data");
                }
            }else if(In.equals("Add a new offense")){
                System.out.println("Enter the Plate");
                String Plate = kb.nextLine();
                System.out.println("Enter the date:");
                String date = kb.nextLine();
                System.out.println("Enter the time:");
                String time = kb.nextLine();
                System.out.println("Enter the name:");
                String name = kb.nextLine();

                if(Cars.containsKey(Plate)){// not a new car
                    offense Current = Cars.get(Plate);
                    Current.AddOffense(date,time,name);
                    Cars.put(Plate,Current);
                }else{
                    TotalNum = TotalNum+1;// new car
                    Cars.put(Plate,new offense(date,time,name));
                }
            }
        }
        creatTxtFile("cars.txt");
        writeTxtFile(Integer.toString(TotalNum));
        for(int i = 0;i<TotalNum;i++) {
            writeTxtFile( Cars.keySet().toArray()[i].toString());
            offense omg = Cars.get(Cars.keySet().toArray()[i]);
            writeTxtFile(Integer.toString(omg.Number()));
            for(int k = 0;k < omg.Number();k++){
                writeTxtFile(omg.date() + ", "+omg.time() +", " + omg.name());
            }
        }
    }
}
class offense{// offense class which has number of offense, date ,time and name,
    private ArrayList<String> date = new ArrayList<String>();
    private ArrayList<String> time = new ArrayList<String>();
    private ArrayList<String> name = new ArrayList<String>();
    private int num;
    public  offense(){
        System.out.println("NULL const");
        date = null;
        time = null;
        name = null;
    }
    public void AddOffense(String date,String time,String name){
        this.date.add(date);
        this.time.add(time
        );
        this.name.add(name);
        num++;
    }
    public offense(String date,String time,String name){
        this.date.add(date);
        this.time.add(time);
        this.name.add(name);
        num++;
    }
    public int Number(){
        return num;
    }
    public ArrayList date(){
        return date;
    }
    public ArrayList time(){
        return time;
    }
    public ArrayList name(){
        return name;
    }
}