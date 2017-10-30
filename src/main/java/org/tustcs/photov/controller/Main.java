package org.tustcs.photov.controller;

import java.util.Calendar;
import java.util.Locale;

/**
 * Created by Airmacx on 2017/10/14.
 */
public class Main {
    public static void main(String[] args) {
        Calendar c=Calendar.getInstance(Locale.CHINA);
        System.out.println(c.get(Calendar.DAY_OF_WEEK));
    }
}
