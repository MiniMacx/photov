package org.tustcs.photov.config;

/**
 * Created by Airmacx on 2017/10/30.
 */
public class Random {
    public static void main(String[] args) {
        char[] a;

        while (true){

            a=new char[5];

            for (int i = 0; i < 5; i++) {
                a[i]=(char)((int)(Math.random()*14)+65);
            }

            if(String.valueOf(a).equals("CHINA")){
                System.out.println("i love "+String.valueOf(a));
                break;
            }
        }
    }
}
