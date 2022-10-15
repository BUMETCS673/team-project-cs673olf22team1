package com.example.medtracker.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.ProvidedTypeConverter;
import androidx.room.Room;

import com.example.medtracker.MedTracker;
import com.example.medtracker.R;
import com.example.medtracker.components.Medicine;
import com.example.medtracker.components.Schedule;
import com.example.medtracker.components.frequencies.Daily;
import com.example.medtracker.components.frequencies.Frequency;
import com.example.medtracker.components.frequencies.Hourly;
import com.example.medtracker.database.MedTrackerDatabase;
import com.example.medtracker.database.MedTrackerDatabaseSingleton;
import com.example.medtracker.database.MedicationDao;
import com.example.medtracker.database.entities.Medication;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addMed extends AppCompatActivity {

    ImageButton returnMain;
    Button createMed;
    Spinner notifySpin;
    Spinner repeatSpin;
    String notifySelect;
    String repeatSelect;
    String medName;
    Float medDoses;
    final Calendar medCal = Calendar.getInstance();
    final Calendar compareCal = Calendar.getInstance();
    EditText endDate;
    EditText endTime;
    int medNotify;
    Frequency freq;
    SimpleDateFormat dateFormat;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_med);

        endDate = (EditText) findViewById(R.id.medDate);
        endTime = (EditText) findViewById(R.id.medTime);

        //create date dialog
        DatePickerDialog.OnDateSetListener date = (view, year, month, day) -> {
            medCal.set(Calendar.YEAR, year);
            medCal.set(Calendar.MONTH,month);
            medCal.set(Calendar.DAY_OF_MONTH,day);
            updateDate();
        };
        endDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(addMed.this,date,medCal.get(Calendar.YEAR),medCal.get(Calendar.MONTH),medCal.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //create time dialog
        TimePickerDialog.OnTimeSetListener time = (timePicker, i, i1) -> {
            medCal.set(Calendar.HOUR_OF_DAY, i);
            medCal.set(Calendar.MINUTE, i1);
            updateTime();
        };
        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TimePickerDialog(addMed.this,time,medCal.get(Calendar.HOUR_OF_DAY),medCal.get(Calendar.MINUTE), true).show();
            }
        });

        //add content to the repeat spinner
        repeatSpin = findViewById(R.id.repeatSpinner);
        String[] items2 = new String[]{"Select Repeat", "every day", "every hour"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        repeatSpin.setAdapter(adapter2);
        repeatSpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK);
                repeatSelect = repeatSpin.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //add content to the notification spinner
        notifySpin = findViewById(R.id.notifySpinner);
        String[] items = new String[]{"Select Time", "5 minutes", "10 minutes", "15 minutes", "30 minutes", "60 minutes"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        notifySpin.setAdapter(adapter);
        notifySpin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(Color.BLACK);
                notifySelect = notifySpin.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //press create medication button
        createMed = (Button) findViewById(R.id.createMed);
        createMed.setOnClickListener(view -> {
            try {
                createMed();
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        //return home page
        returnMain = (ImageButton) findViewById(R.id.backButton);
        returnMain.setOnClickListener(view -> returnParent());
    }


    public void createMed() throws ParseException {
        //parse user input
        medName = ((EditText) findViewById(R.id.medName)).getText().toString();
        String dosesTemp = ((EditText) findViewById(R.id.medDoses)).getText().toString();
        if(!dosesTemp.isEmpty()){
            medDoses = Float.valueOf(dosesTemp);
        }else{
            medDoses = (float)-1;
        }

        //parse repetition info
        switch(repeatSelect){
            case "every day":
                freq = new Daily();
                break;
            case "every hour":
                freq = new Hourly();
                break;
            default:
                freq = new Frequency() {
                    @Override
                    public LocalDateTime updateTime(LocalDateTime oldTime) {
                        return null;
                    }
                };
        }

        //parse notification input
        switch(notifySelect){
            case "5 minutes":
                medNotify = 5;
                break;
            case "10 minutes":
                medNotify = 10;
                break;
            case "15 minutes":
                medNotify = 15;
                break;
            case "30 minutes":
                medNotify = 30;
                break;
            case "60 minutes":
                medNotify = 60;
                break;
            default:
                medNotify = -1;
        }

        //check whether user input is valid
        int compareResult = medCal.getTime().compareTo(compareCal.getTime());
        if(medName.equals("")){
            //error handling while the medication name is empty
            AlertDialog.Builder nameAlert = new AlertDialog.Builder(addMed.this);
            nameAlert.setMessage("Please enter a valid medication name");
            nameAlert.setTitle("Invalid Name!");
            nameAlert.setCancelable(true);
            nameAlert.setPositiveButton("OK", (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog nameDialog = nameAlert.create();
            nameDialog.show();
        }else if(compareResult < 0){
            //error handling while medication deadline is before now
            AlertDialog.Builder dateAlert = new AlertDialog.Builder(addMed.this);
            dateAlert.setMessage("Please enter a date after now");
            dateAlert.setTitle("Invalid Date!");
            dateAlert.setCancelable(true);
            dateAlert.setPositiveButton("OK", (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog nameDialog = dateAlert.create();
            nameDialog.show();
        }else{
            //auto-generated deadline two hours after now if user input is empty
            if(compareResult == 0){
                medCal.add(Calendar.HOUR_OF_DAY, 2);
            }

            //create medication object
            Medication medication = new Medication(medDoses, medCal.getTime(), medName, freq);

            //add medication object to database
            MedTrackerDatabase db = MedTrackerDatabaseSingleton.getInstance(MedTracker.getAppContext());
            MedicationDao medDao = db.medicationDao();
            medDao.addMed(medication);

            // Use this intent to pass data back to the main activity so it knows which
            // medication was added
            Intent intent = new Intent();
            intent.putExtra("name", medication.medName);
            setResult(1, intent);
            finish();
        }
    }

    private void updateDate(){
        String dateOut = "MM/dd/yyyy";
        dateFormat = new SimpleDateFormat(dateOut, Locale.US);
        endDate.setText(dateFormat.format(medCal.getTime()));
    }

    private void updateTime(){
        String timeOut = "";
        timeOut += medCal.get(Calendar.HOUR_OF_DAY) + ": ";
        timeOut += medCal.get(Calendar.MINUTE);
        endTime.setText(timeOut);
    }


    public void returnParent(){
        finish();
    }
}
