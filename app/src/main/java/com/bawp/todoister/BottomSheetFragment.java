package com.bawp.todoister;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.bawp.todoister.model.Priority;
import com.bawp.todoister.model.Task;
import com.bawp.todoister.model.TaskViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.android.material.chip.Chip;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.navigation.fragment.NavHostFragment;

import java.util.Calendar;
import java.util.Date;

public class BottomSheetFragment extends BottomSheetDialogFragment implements View.OnClickListener {
    private EditText enterTodoMsg;
    private ImageButton calanderButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calenderGroup; // it should import: import androidx.constraintlayout.widget.Group;

    private Date  dueDate;
    private Calendar calendar=Calendar.getInstance();
    public  BottomSheetFragment(){

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.bottom_sheet, container, false);
calenderGroup=view.findViewById(R.id.calendar_group);
enterTodoMsg=view.findViewById(R.id.enter_todo_et);
saveButton=view.findViewById(R.id.save_todo_button);
priorityRadioGroup=view.findViewById(R.id.radioGroup_priority);
priorityButton=view.findViewById(R.id.priority_todo_button);
calendarView=view.findViewById(R.id.calendar_view);
calanderButton=view.findViewById(R.id.today_calendar_button);

        Chip todayChip=view.findViewById(R.id.today_chip);

        Chip tommorowChip=view.findViewById(R.id.tomorrow_chip);
        Chip nextWeekChip=view.findViewById(R.id.next_week_chip);
        checkOnclick_Chip(todayChip,tommorowChip,nextWeekChip);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        calanderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calenderGroup.setVisibility(
                        calenderGroup.getVisibility()==View.GONE?View.VISIBLE:View.GONE
                );
            }
        });


        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int dayOfMonth) {
                calendar.clear();
                calendar.set(year,month,dayOfMonth);
                dueDate=calendar.getTime();
                Log.v("calenderView_Year",year+"");
                Log.v("calenderView_month",month+"");
                Log.v("calenderView_DofM",dayOfMonth+"");


            }
        });




        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(),"clicked",Toast.LENGTH_SHORT).show();
                String task=enterTodoMsg.getText().toString().trim();
                if (!TextUtils.isEmpty(task)&& dueDate!=null){
                    Task task1=new Task(task, Priority.HIGH, dueDate,
                            Calendar.getInstance().getTime(), false);
                    TaskViewModel.insert(task1);

                    enterTodoMsg.setText("");
                }
            }
        });

    }

    private void checkOnclick_Chip(Chip todayChip, Chip tommorowChip, Chip nextWeekChip) {
        todayChip.setOnClickListener(this);
        tommorowChip.setOnClickListener(this);
        nextWeekChip.setOnClickListener(this);
        //whichever is clicked will passed to onClick implementation below of interface

    }

    @Override
    public void onClick(View view) {
        //whichever is clicked will be notified here
        //check the id of these chips to get which one is implemented
        int id=view.getId();
        if (id==R.id.today_chip){
             calendar.add(calendar.DAY_OF_YEAR,0);
             dueDate=calendar.getTime();

        } else  if (id==R.id.tomorrow_chip){
            calendar.add(calendar.DAY_OF_YEAR,1);
            dueDate=calendar.getTime();

        } else if (id==R.id.next_week_chip){
            calendar.add(calendar.DAY_OF_YEAR,7);
            dueDate=calendar.getTime();

        }

    }
}