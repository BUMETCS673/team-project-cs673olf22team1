package com.example.medtracker.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.medtracker.R;

public class setting extends AppCompatActivity {

    ImageButton homeButton;
    Button nameButton;
    Button ageButton;
    Button genderButton;
    Button usernameButton;
    Button passwordButton;
    String nameTitle = "Enter your Name";
    String ageTitle = "Enter your Age";
    String genderTitle = "Enter your Gender";
    String usernameTitle = "Enter your Username";
    String passwordTitle = "Enter your Password";
    TextView nameText;
    TextView ageText;
    TextView genderText;
    TextView usernameText;
    TextView passwordText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        //find all text views
        nameText = (TextView) findViewById(R.id.nameText);
        ageText = (TextView) findViewById(R.id.ageText);
        genderText = (TextView) findViewById(R.id.genderText);
        usernameText = (TextView) findViewById(R.id.usernameText);
        passwordText = (TextView) findViewById(R.id.passwordText);

        //press home image button
        homeButton = (ImageButton) findViewById(R.id.homeImage);
        homeButton.setOnClickListener(view -> openHome());

        //press "change name" button
        nameButton = (Button) findViewById(R.id.nameButton);
        nameButton.setOnClickListener(view -> nameButtonDialog(nameTitle, "Name"));

        //press "change age" button
        ageButton = (Button) findViewById(R.id.ageButton);
        ageButton.setOnClickListener(view -> nameButtonDialog(ageTitle, "Age"));

        //press "change gender" button
        genderButton = (Button) findViewById(R.id.genderButton);
        genderButton.setOnClickListener(view -> nameButtonDialog(genderTitle, "Gender"));

        //press "change username" button
        usernameButton = (Button) findViewById(R.id.usernameButton);
        usernameButton.setOnClickListener(view -> nameButtonDialog(usernameTitle, "Username"));

        //press "change password" button
        passwordButton = (Button) findViewById(R.id.passwordButton);
        passwordButton.setOnClickListener(view -> nameButtonDialog(passwordTitle, "Password"));

    }

    public void nameButtonDialog(String title, String dialogChecker) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.alert_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText eText = (EditText) dialogView.findViewById(R.id.enterValue);

        dialogBuilder.setTitle(title);
        dialogBuilder.setPositiveButton("Done", (dialog, whichButton) -> {
            String editVal = eText.getText().toString();
            switch (dialogChecker) {
                case "Name":
                    nameText.setText(editVal);
                    break;
                case "Age":
                    ageText.setText(editVal);
                    break;
                case "Gender":
                    genderText.setText(editVal);
                    break;
                case "Username":
                    usernameText.setText(editVal);
                    break;
                case "Password":
                    break;
            }
            Toast.makeText(getApplicationContext(), dialogChecker + " Changed", Toast.LENGTH_SHORT).show();

        });
        dialogBuilder.setNegativeButton("Cancel", (dialog, whichButton) -> {
            //pass
        });
        AlertDialog b = dialogBuilder.create();
        b.show();
    }



    public void openHome(){
        Intent intent = new Intent(this,main.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }


}
