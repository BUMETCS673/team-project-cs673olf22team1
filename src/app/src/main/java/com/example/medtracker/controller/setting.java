package com.example.medtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracker.R;

public class setting extends AppCompatActivity {

    ImageButton homeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        //press home image button
        homeButton = (ImageButton) findViewById(R.id.homeImage);
        homeButton.setOnClickListener(view -> openHome());

    }

    public void openHome(){
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }


}
