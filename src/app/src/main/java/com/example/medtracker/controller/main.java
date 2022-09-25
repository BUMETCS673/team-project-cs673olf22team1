package com.example.medtracker.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.widget.Toolbar;

import com.example.medtracker.R;



public class main extends AppCompatActivity{

    Button addMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Press add medication button
        addMed = (Button) findViewById(R.id.addMedButton);
        addMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openMed();
            }
        });

    }

    public void openMed(){
        Intent intent = new Intent(this,addMed.class);
        startActivity(intent);
    }
}
