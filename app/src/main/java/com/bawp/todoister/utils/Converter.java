package com.bawp.todoister.utils;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.bawp.todoister.model.Priority;

import java.util.Date;

public class Converter {

    @TypeConverter
    public  static Date  fromTimeStamp(Long  value){
        return value==null ?null:new Date(value);

    }

    @TypeConverter
    public  static Long  DateToTimeStamp(Date  date){
        return date==null ?null:  date.getTime();

    }
    @TypeConverter
    public String fromPriority(Priority priority){
        return priority==null?null:priority.name();
    }
}
