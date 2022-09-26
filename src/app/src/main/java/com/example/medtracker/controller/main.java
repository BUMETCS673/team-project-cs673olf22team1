package com.example.medtracker.controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;

import com.example.medtracker.R;



public class main extends AppCompatActivity{

    Button addMed;
    ImageButton setting_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //press add medication button
        addMed = (Button) findViewById(R.id.addMedButton);
        addMed.setOnClickListener(view -> openMed());

        //press account image button
        setting_button = (ImageButton) findViewById(R.id.settingImage);
        setting_button.setOnClickListener(view -> openSetting());

    }

    public void openMed(){
        Intent intent = new Intent(this,addMed.class);
        startActivity(intent);
    }

    public void openSetting(){
        Intent intent = new Intent(this,setting.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
