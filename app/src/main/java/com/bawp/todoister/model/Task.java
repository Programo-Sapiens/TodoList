package com.bawp.todoister.model;



import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "task_table")
public class Task {
    //all fields must be public:WHY
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    public long taskId;
    @ColumnInfo(name = "task")
    public String task;

    @ColumnInfo(name = "priority")
    public Priority priority;

    public Task(String task, Priority priority, Date createdDate, Date dueDate, boolean isDone) {
        this.task = task;
        this.priority = priority;
        this.createdDate = createdDate;
        DueDate = dueDate;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", task='" + task + '\'' +
                ", priority=" + priority +
                ", createdDate=" + createdDate +
                ", DueDate=" + DueDate +
                ", isDone=" + isDone +
                '}';
    }

    public long getTaskId() {
        return taskId;
    }

    public void setTaskId(long taskId) {
        this.taskId = taskId;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public void setDueDate(Date dueDate) {
        DueDate = dueDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    @ColumnInfo(name = "created_date")
    public Date createdDate;

    @ColumnInfo(name = "due_date")
    public Date DueDate;
    @ColumnInfo(name = "done")
    public boolean isDone;
}
