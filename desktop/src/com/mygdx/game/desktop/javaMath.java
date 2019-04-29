package com.mygdx.game.desktop;

import java.math.BigInteger;

public class javaMath {
    public static void main (String [] args){

        BigInteger a = new BigInteger("1");
        int num = 0;
        for(int k = 0; k < 10000;k++){
            String m = Integer.toString(k*100);
            String div = "792";
            BigInteger f = a;
            a = a.add(new BigInteger(m));
            if(a.remainder(new BigInteger(div)).equals(new BigInteger("0"))){
                num++;
                System.out.println(k);

            }

            a = f;
        }
        //System.out.println(num);


    }
}
