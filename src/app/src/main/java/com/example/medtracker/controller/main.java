package com.example.medtracker.controller;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.medtracker.R;



public class main extends AppCompatActivity{

    Button addMed;
    ImageButton setting_button;
    ActivityResultLauncher<Intent> addMedLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //press add medication button
        addMed = (Button) findViewById(R.id.addMedButton);
        addMed.setOnClickListener(view -> openMed());
        addMedLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 1) {
                            Toast.makeText(getApplicationContext(), "Add Medicine Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //press account image button
        setting_button = (ImageButton) findViewById(R.id.settingImage);
        setting_button.setOnClickListener(view -> openSetting());

    }

    public void openMed(){
        Intent intent = new Intent(this,addMed.class);
        addMedLauncher.launch(intent);
    }

    public void openSetting(){
        Intent intent = new Intent(this,setting.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }
}
