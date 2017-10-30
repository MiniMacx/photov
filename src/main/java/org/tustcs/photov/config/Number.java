package org.tustcs.photov.config;

/**
 * Created by Airmacx on 2017/10/30.
 */
public class Number {


    public static void main(String[] args) {
        for(int i=1; i<10000; i++){
            if(isWanShu(i)){
                System.out.print(i + ",");
            }
        }
    }


    private static boolean isWanShu(int a){
        int cup = 0;
        for(int i=1; i<a; i++){
            if(a%i == 0)
                cup = cup + i;
        }
        return (cup == a);
    }
}

