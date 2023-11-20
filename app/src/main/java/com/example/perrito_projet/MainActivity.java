package com.example.perrito_projet;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private EditText taskEditText;
    private CheckBox priorityCheckBox;
    private Spinner categorySpinner;
    private Button addTaskButton;
    private ListView taskListView;

    private ArrayList<Task> taskList;
    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskEditText = findViewById(R.id.task);
        priorityCheckBox = findViewById(R.id.priority);
        categorySpinner = findViewById(R.id.dropdown);
        addTaskButton = findViewById(R.id.addTask);
        taskListView = findViewById(R.id.listItem);

        // Populate the Spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.categories_array,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Initialize the task list and adapter
        taskList = new ArrayList<>();
        taskAdapter = new TaskAdapter(this, taskList);
        taskListView.setAdapter(taskAdapter);

        // Set listener for the Add Task button
        addTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        // Set listener for the ListView items
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task clickedTask = taskList.get(position);
                clickedTask.setCompleted(!clickedTask.isCompleted());
                taskAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addTask() {
        String taskName = taskEditText.getText().toString();
        boolean isPriority = priorityCheckBox.isChecked();
        String category = categorySpinner.getSelectedItem().toString();

        if (taskName.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get current date
        String currentDate = new SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault()).format(new Date());

        // Create a new Task object
        Task newTask = new Task(currentDate, taskName, isPriority, category);

        // Add the task to the list and update the ListView
        taskList.add(newTask);
        taskAdapter.notifyDataSetChanged();

        // Clear the input fields
        taskEditText.getText().clear();
        priorityCheckBox.setChecked(false);
    }
}
