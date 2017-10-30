package org.tustcs.photov.config;

import org.tustcs.photov.entity.User;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Airmacx on 2017/10/17.
 */
public class Calend {
    public static void main(String[] args) {
        Calendar c=Calendar.getInstance();
       // c.setTimeInMillis(new Date().getTime()-60*60*1000*24);
        System.out.println(c.get(Calendar.DAY_OF_MONTH));
        User u=null;
        long value=(1508860800000l) % (3600 * 1000 * 24);
        System.out.println(value);
    }
}
