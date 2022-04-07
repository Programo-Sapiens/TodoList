package com.bawp.todoister.data;


import android.app.Application;

import androidx.lifecycle.LiveData;

import com.bawp.todoister.model.Task;
import com.bawp.todoister.utils.TaskRoomDatabase;

import java.util.List;

public class TodoRepository {

    private final TaskDao taskDao;
    private final LiveData<List<Task>> alltask;



    public TodoRepository(Application application) {
        TaskRoomDatabase database=TaskRoomDatabase.getInstance(application);
        taskDao = database.taskDao();
        alltask = taskDao.getTask();
    }
    //now we want to create some public interfaces for any one above this layer to get data
    public LiveData<List<Task>> getAlltask(){
        return alltask;
    }

    public void InsertTask(Task task){
        //will use executor service define in TaskRoomDatabse hence it was public there
        TaskRoomDatabase.databaseWriterExecutor.execute(()->
        {
            taskDao.insertTask(task);
        });
    }

    public LiveData<Task> getID(long id){
        return taskDao.get(id);
    }

    public void update(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->{
            taskDao.update(task);
        });
    }

    public  void  delete(Task task){
        TaskRoomDatabase.databaseWriterExecutor.execute(()->{
            taskDao.delte(task);
        });
    }
}
