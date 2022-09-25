package com.example.medtracker.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracker.R;

public class addMed extends AppCompatActivity {

    ImageButton returnMain;
    Button createMed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_med);

        //press create medication button
        createMed = (Button) findViewById(R.id.createMed);
        createMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createMed();
            }
        });

        //return home page
        returnMain = (ImageButton) findViewById(R.id.backButton);
        returnMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                returnParent();
            }
        });
    }
    public void createMed(){
        finish();
    }


    public void returnParent(){
        finish();
    }
}
