package com.example.sumaiyamashfi.tab_abd_list;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Sumaiya Mashfi on 2/5/2016.
 */
public class Date_Utility {
    public static Date String_To_Date(String S) {
        String dtStart = S;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try
        {
            Date date = format.parse(dtStart);
            return date;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String Date_To_String(Date d)
    {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            String datetime = dateformat.format(d);
            return datetime;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "";
    }
}
