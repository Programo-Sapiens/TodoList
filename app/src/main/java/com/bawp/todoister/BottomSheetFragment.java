package com.bawp.todoister;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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

public class BottomSheetFragment extends BottomSheetDialogFragment {
    private EditText enterTodoMsg;
    private ImageButton calanderButton;
    private ImageButton priorityButton;
    private RadioGroup priorityRadioGroup;
    private RadioButton selectedRadioButton;
    private int selectedButtonId;
    private ImageButton saveButton;
    private CalendarView calendarView;
    private Group calenderGroup; // it should import: import androidx.constraintlayout.widget.Group;
    public  BottomSheetFragment(){

    }
    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.bottom_sheet, container, false);
calenderGroup=view.findViewById(R.id.calendar_view);
enterTodoMsg=view.findViewById(R.id.enter_todo_et);
saveButton=view.findViewById(R.id.save_todo_button);
priorityRadioGroup=view.findViewById(R.id.radioGroup_priority);
priorityButton=view.findViewById(R.id.priority_todo_button);
calendarView=view.findViewById(R.id.calendar_view);
calanderButton=view.findViewById(R.id.today_calendar_button);

        Chip todayChip=view.findViewById(R.id.today_chip);
        Chip tommorowChip=view.findViewById(R.id.tomorrow_chip);
        Chip nextWeekChip=view.findViewById(R.id.next_week_chip);
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task=enterTodoMsg.getText().toString().trim();
                if (TextUtils.isEmpty(task)){
                    Task task1=new Task(task, Priority.HIGH, Calendar.getInstance().getTime(),
                            Calendar.getInstance().getTime(), false);
                    TaskViewModel.insert(task1);
                }
            }
        });

    }
}