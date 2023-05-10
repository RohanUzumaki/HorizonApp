package com.example.horizonapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class DoctorProfileActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView departmentTextView;
    private TextView experienceTextView;
    EditText patientNameValue;

    // Define the doctor object

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference appointmentsRef = database.getReference("Appointments");
        // Initialize the TextView widgets
        nameTextView = findViewById(R.id.doc_name);
        departmentTextView = findViewById(R.id.doc_department);
        experienceTextView = findViewById(R.id.doc_experience);
        patientNameValue = findViewById(R.id.patientName);

        Intent intent = getIntent();
        String departmentName = intent.getStringExtra("DEPARTMENT_NAME");
        String doctorName = intent.getStringExtra("DOCTOR_NAME");
        String doctorExperience = intent.getStringExtra("DOCTOR_EXPERIENCE");
        // Set the text of the TextView widgets using the doctor object
        nameTextView.setText(doctorName);
        departmentTextView.setText(departmentName);
        experienceTextView.setText(doctorExperience);

        Spinner spinnerTimeSlot = findViewById(R.id.spinnerTimeSlot);

        String[] timeSlots = {"9:00am - 10:00am", "10:00am - 11:00am", "11:00am - 12:00pm", "12:00pm - 1:00pm", "3:00pm - 4:00pm"};
        ArrayAdapter<String> timeSlotAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, timeSlots);
        spinnerTimeSlot.setAdapter(timeSlotAdapter);
// Set an OnClickListener on the EditText to open the date picker dialog
        Button datePickerButton = findViewById(R.id.buttonDatePicker);
        datePickerButton.setOnClickListener(v -> showDatePickerDialog(datePickerButton));


// Set an OnClickListener on the EditText to open the date picker dialog

        // Set up the Confirm Appointment button
        Button confirmAppointmentButton = findViewById(R.id.buttonBookAppointment);
        confirmAppointmentButton.setOnClickListener(task -> {

            EditText patientNameValue = findViewById(R.id.patientName);
            EditText patientAgeEditText = findViewById(R.id.patientAge);
            String patientName = patientNameValue.getText().toString().trim();
            int patientAge = Integer.parseInt(patientAgeEditText.getText().toString().trim());
            String appointmentDate = datePickerButton.getText().toString().trim();
            String timeSlot = spinnerTimeSlot.getSelectedItem().toString();

            if (TextUtils.isEmpty(patientName)) {
                patientNameValue.setError("Patient name is required");
                return;
            }
            if (TextUtils.isEmpty(patientAgeEditText.getText().toString())) {
                patientAgeEditText.setError("Patient age is required");
                return;
            }

            if (TextUtils.isEmpty(appointmentDate)) {
                datePickerButton.setError("Appointment date is required");
                return;
            }

            Log.d("Time Slot", "Selected: " + timeSlot); // before appointment object creation
            Appointment appointment = new Appointment(doctorName, departmentName, doctorExperience, patientName, patientAge, appointmentDate, timeSlot);
            Log.d("Time Slot", "Stored: " + appointment.getAppointmentTimeSlot()); // after appointment object creation
            appointmentsRef.push().setValue(appointment);

            Intent intent2 = new Intent(DoctorProfileActivity.this, BookingSuccessActivity.class);
            startActivity(intent2);
        });

    }

    private void showDatePickerDialog(Button datePickerButton) {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        int currentHourOfDay = calendar.get(Calendar.HOUR_OF_DAY);

        // Set the minimum date for the date picker to today's date
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (datePicker, selectedYear, selectedMonth, selectedDayOfMonth) -> {
                    // Convert the selected date to a calendar object
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDayOfMonth);

                    // Check if the selected date is before today's date
                    if (selectedDate.before(calendar)) {
                        // Show an error message and reset the date to today's date
                        Toast.makeText(this, "Please select a date after today", Toast.LENGTH_SHORT).show();
                        datePickerButton.setText(android.text.format.DateFormat.format("dd-MM-yyyy", calendar));
                    } else {
                        // Check if the selected date is today's date
                        if (selectedDate.get(Calendar.YEAR) == currentYear
                                && selectedDate.get(Calendar.MONTH) == currentMonth
                                && selectedDate.get(Calendar.DAY_OF_MONTH) == currentDayOfMonth) {
                            // Check if the current time is before 4:00 pm
                            if (currentHourOfDay < 16) {
                                // Set the selected date in the EditText
                                datePickerButton.setText(android.text.format.DateFormat.format("dd-MM-yyyy", selectedDate));
                            } else {
                                // Show an error message and reset the date to today's date
                                Toast.makeText(this, "Please select a date after today", Toast.LENGTH_SHORT).show();
                                datePickerButton.setText(android.text.format.DateFormat.format("dd-MM-yyyy", calendar));
                            }
                        } else {
                            // Set the selected date in the EditText
                            datePickerButton.setText(android.text.format.DateFormat.format("dd-MM-yyyy", selectedDate));
                        }
                    }
                }, currentYear, currentMonth, currentDayOfMonth);
        datePickerDialog.getDatePicker().setMinDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }
}