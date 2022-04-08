package com.bawp.todoister.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {
    //this class is inline of Seperation of concern
    //will contain helper methods
    //like formatting date, whereever its required
    public static String fromDateFormat(Date date){
        SimpleDateFormat simpleDateFormat= (SimpleDateFormat) SimpleDateFormat.getDateInstance();
        simpleDateFormat.applyPattern("EEE MM yyyy");
        return simpleDateFormat.format(date);

    }
}
