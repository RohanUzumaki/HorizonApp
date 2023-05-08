package com.example.horizonapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DoctorProfileActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView departmentTextView;
    private TextView experienceTextView;
    // Define the doctor object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        // Initialize the TextView widgets
        nameTextView = findViewById(R.id.doc_name);
        departmentTextView = findViewById(R.id.doc_department);
        experienceTextView = findViewById(R.id.doc_experience);

        Intent intent = getIntent();
        String departmentName = intent.getStringExtra("DEPARTMENT_NAME");
        String doctorName = intent.getStringExtra("DOCTOR_NAME");
        String doctorExperience = intent.getStringExtra("DOCTOR_EXPERIENCE");
        // Set the text of the TextView widgets using the doctor object
        nameTextView.setText(doctorName);
        departmentTextView.setText(departmentName);
        experienceTextView.setText(doctorExperience);

        EditText editTextDate = findViewById(R.id.editDate);

// Set an OnClickListener on the EditText to open the date picker dialog
        editTextDate.setOnClickListener(view -> {
            // Get the current date to set as the minimum date in the date picker
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a date picker dialog and set the minimum date to today's date
            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    (datePicker, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                        // Convert the selected date to a calendar object
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);

                        // Check if the selected date is before today's date
                        if (selectedDate.before(calendar)) {
                            // Show an error message and reset the date to today's date
                            Toast.makeText(this, "Please select a date after today", Toast.LENGTH_SHORT).show();
                            editTextDate.setText(android.text.format.DateFormat.format("dd-MM-yyyy", calendar));
                        } else {
                            // Set the selected date in the EditText
                            editTextDate.setText(android.text.format.DateFormat.format("dd-MM-yyyy", selectedDate));
                        }
                    }, year, month, dayOfMonth);
            datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
            datePickerDialog.show();
        });
        // Set up the Confirm Appointment button
        Button confirmAppointmentButton = findViewById(R.id.buttonBookAppointment);
        confirmAppointmentButton.setOnClickListener(task ->{
            Intent intent2 = new Intent(DoctorProfileActivity.this,BookingSuccessActivity.class);
            startActivity(intent2);
        });
    }
}