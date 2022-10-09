package com.example.medtracker.controller;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import com.example.medtracker.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class addMed extends AppCompatActivity {

    ImageButton returnMain;
    Button createMed;
    Spinner notifySpin;
    String notifySelect;
    String medName;
    Double medDoses;
    final Calendar medCal = Calendar.getInstance();
    EditText endDate;
    EditText endTime;
    int medNotify;


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
        createMed.setOnClickListener(view -> createMed());

        //return home page
        returnMain = (ImageButton) findViewById(R.id.backButton);
        returnMain.setOnClickListener(view -> returnParent());
    }
    public void createMed(){
        //parse user input
        medName = ((EditText) findViewById(R.id.medName)).getText().toString();
        String dosesTemp = ((EditText) findViewById(R.id.medDoses)).getText().toString();
        if(!dosesTemp.equals("")){
            medDoses = Double.valueOf(dosesTemp);
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
        if(medName.equals("")){
            AlertDialog.Builder nameAlert = new AlertDialog.Builder(addMed.this);
            nameAlert.setMessage("Please enter a valid medication name");
            nameAlert.setTitle("Invalid Name!");
            nameAlert.setCancelable(true);
            nameAlert.setPositiveButton("OK", (dialog, which) -> {
                dialog.cancel();
            });
            AlertDialog nameDialog = nameAlert.create();
            nameDialog.show();
        }else{
            setResult(1);
            finish();
        }
    }

    private void updateDate(){
        String dateOut = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(dateOut, Locale.US);
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
