package com.example.perrito_projet;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaskAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Task> taskList;

    public TaskAdapter(Context context, ArrayList<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }

    @Override
    public int getCount() {
        return taskList.size();
    }

    @Override
    public Object getItem(int position) {
        return taskList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_list, null);
        }

        // Get the task at the current position
        Task currentTask = taskList.get(position);

        // Get references to views in the list item layout
        TextView dateTextView = convertView.findViewById(R.id.date);
        TextView taskInfoTextView = convertView.findViewById(R.id.taskInfo);
        TextView priorityInfoTextView = convertView.findViewById(R.id.priorityInfo);
        TextView categoryInfoTextView = convertView.findViewById(R.id.categoryInfo);
        CheckBox checkTaskCheckBox = convertView.findViewById(R.id.checkTask);

        // Set the values of the views with the information of the current task
        dateTextView.setText(currentTask.getDate());
        taskInfoTextView.setText(currentTask.getName());
        priorityInfoTextView.setText(currentTask.isPriority() ? "Prioritaire" : "Non Prioritaire");
        categoryInfoTextView.setText(currentTask.getCategory());
        checkTaskCheckBox.setChecked(currentTask.isCompleted());

        // Add a Listener to the CheckBox
        checkTaskCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Update the completed status on the task
                currentTask.setCompleted(isChecked);

                // Show a message if the task is marked as completed
                if (isChecked) {
                    Toast.makeText(context, "Activité complétée : " + currentTask.getName(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return convertView;
    }
}
