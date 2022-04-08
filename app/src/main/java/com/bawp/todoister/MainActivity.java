package com.bawp.todoister;

import android.os.Bundle;

import com.bawp.todoister.adapter.Adapter_Todo;
import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;

import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;
    private RecyclerView recyclerView;
    private Adapter_Todo adapterTodo;

    public static final String TAG="ITEM";
    BottomSheetFragment bottomSheetFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

bottomSheetFragment=new BottomSheetFragment();
ConstraintLayout constraintLayout=findViewById(R.id.bottomSheet);
BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior=BottomSheetBehavior.from(constraintLayout);
bottomSheetBehavior.setPeekHeight(BottomSheetBehavior.STATE_HIDDEN);


        taskViewModel =new ViewModelProvider.AndroidViewModelFactory(MainActivity.this.getApplication()
        )
                .create(TaskViewModel.class);

        taskViewModel.getAllTask().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
//                for(Task task:tasks){
//                    Log.d(TAG,"messageOncreate"+task.getTask());
//                }
                adapterTodo=new Adapter_Todo(tasks);
                recyclerView.setAdapter(adapterTodo);
            }
        });

      //  Log.v("Task_Database",taskViewModel.allTasks.getValue().toArray().toString());

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //can convert it into lamda
                Task task=new Task("Todo", Priority.HIGH, Calendar.getInstance().getTime(),Calendar.getInstance().getTime(),
                        false);
                TaskViewModel.insert(task); //we directly use class to access insert (bcoz insert is static in viewmodel)
                //but will need instance for data like liveData in viewmodel which are not static

//show bottoms sheet
                showBottomSheet();

            }
        });
    }

    private void showBottomSheet() {
        bottomSheetFragment.show(getSupportFragmentManager(),bottomSheetFragment.getTag());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}