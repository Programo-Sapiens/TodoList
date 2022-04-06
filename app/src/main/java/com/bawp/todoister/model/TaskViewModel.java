package com.bawp.todoister.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.bawp.todoister.data.TodoRepository;

import java.util.List;


public class TaskViewModel extends AndroidViewModel {
public static TodoRepository repository;
public final LiveData<List<Task>> allTasks;



    public TaskViewModel(@NonNull Application application) {  //this application will be passed from main activity by using ViewmodelPRovider Factory to instanciate viewmodel

        super(application);
        repository=new TodoRepository(application);
        allTasks=repository.getAlltask();
    }

    public LiveData<List<Task>> getAllTask(){
        return allTasks;
    }

    public static void insert(Task task){
        repository.InsertTask(task);

    }

    public LiveData<Task> get(long id){
       return repository.getID(id);
    }

    public static void update(Task task){
        repository.update(task);
    }

    public static void delete(Task task){
        repository.delete(task);
    }
}
