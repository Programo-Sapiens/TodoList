package com.bawp.todoister.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bawp.todoister.data.TaskDao;
import com.bawp.todoister.model.Task;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class},version = 1,exportSchema = false)
@TypeConverters({Converter.class})
public abstract class TaskRoomDatabase extends RoomDatabase {
    public static int NUMBER_OF_THREAD=4;
    private  static volatile TaskRoomDatabase INSTANCE;
    public static final ExecutorService databaseWriterExecutor   //its public so that we can use it from Repository ex Insert call
            = Executors.newFixedThreadPool(NUMBER_OF_THREAD);
    public static final RoomDatabase.Callback sRoomDatabaseCallback
            =new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            databaseWriterExecutor.execute(()->{
                        //invoke DAO, and wirte
                TaskDao taskDao= INSTANCE.taskDao();
                taskDao.deleteAllTask();//clean everything before writing

                    }

                    );
        }
    };

    //create a method to get instance it should be public as instance is private
    public static final TaskRoomDatabase getInstance(Context context){
        if (INSTANCE==null){
            synchronized (TaskRoomDatabase.class){
                if (INSTANCE==null){
                    INSTANCE= Room.databaseBuilder(context.getApplicationContext(),TaskRoomDatabase.class,"DATABASE_TASK")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    return INSTANCE;
                }
            }
        }
        return INSTANCE;
    }

    public abstract TaskDao taskDao();
}
