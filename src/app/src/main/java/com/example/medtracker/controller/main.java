package com.example.medtracker.controller;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.medtracker.R;
import com.example.medtracker.database.entities.Medication;
import com.example.medtracker.viewmodel.CurMedViewModel;
import com.example.medtracker.viewmodel.MedListViewModel;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;


public class main extends AppCompatActivity{

    private MedListViewModel listViewModel;
    private CurMedViewModel viewModel;
    Button addMed;
    ImageButton setting_button;
    ActivityResultLauncher<Intent> addMedLauncher;
    Button defaultDelete;

    private NotificationChannel notificationChannel;
    private NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(ContentValues.TAG, "onCreate() called");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        // Notifications
        notificationChannel = new NotificationChannel("MedTracker", "MedTracker", NotificationManager.IMPORTANCE_HIGH);
        notificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(notificationChannel);

        listViewModel = new ViewModelProvider(this).get(MedListViewModel.class);
        viewModel = new ViewModelProvider(this).get(CurMedViewModel.class);

        Log.d("Main", "Viewmodels instantiated.");

        //press add medication button
        addMed = (Button) findViewById(R.id.addMedButton);
        addMed.setOnClickListener(view -> openMed());
        addMedLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == 1) {
                            //generate the medication

                            LinearLayout ll = (LinearLayout) findViewById(R.id.medLayout);
                            View card = getLayoutInflater().inflate(R.layout.med_card, null);
                            Button deleteMed = (Button) card.findViewById(R.id.deleteButton);
                            deleteMed.setOnClickListener(view -> openDelete(deleteMed));
                            ll.addView(card);

                            TextView medInfo = (TextView)card.findViewById(R.id.medInfo);
                            medInfo.setText(result.getData().getStringExtra("name"));

                            Toast.makeText(getApplicationContext(), "Add Medicine Successfully!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //press account image button
        setting_button = (ImageButton) findViewById(R.id.settingImage);
        setting_button.setOnClickListener(view -> openSetting());

        // Load medications from database or add this intent to a stack
        loadMeds();

        runHandler();
    }

    public void loadMeds(){
        List<Medication> medList = listViewModel.getAllMeds();
        Log.d("Main", "MedList Setup.");
        for (Medication medication:medList) {
            LinearLayout ll = (LinearLayout) findViewById(R.id.medLayout);
            View card = getLayoutInflater().inflate(R.layout.med_card, null);
            Button deleteMed = (Button) card.findViewById(R.id.deleteButton);
            deleteMed.setOnClickListener(view -> openDelete(deleteMed));
            ll.addView(card);

            TextView medInfo = (TextView)card.findViewById(R.id.medInfo);
            medInfo.setText(medication.medName);
        }
    }

    public void openMed(){
        Log.d(ContentValues.TAG, "openMed() called");
        Intent intent = new Intent(this,addMed.class);
        addMedLauncher.launch(intent);
    }

    public void openSetting(){
        Log.d(ContentValues.TAG, "openSetting() called");
        Intent intent = new Intent(this,setting.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
    }

    public void openDelete(Button delButton){
        Log.d(ContentValues.TAG, "openDelete() called");
        AlertDialog.Builder deleteAlert = new AlertDialog.Builder(main.this);
        deleteAlert.setMessage("Are you sure you want to delete this medication?");
        deleteAlert.setTitle("Delete Medication!");
        deleteAlert.setCancelable(true);
        deleteAlert.setPositiveButton("Yes", (dialog, which) -> {
            ((ViewGroup) delButton.getParent().getParent()).removeAllViews();

            // Remove record from database

            CardView cardView = (CardView) delButton.getParent();
            TextView textView = (TextView)cardView.findViewById(R.id.medInfo);
            Log.d("test", textView.getText().toString());

            List<Medication> medicationList = listViewModel.searchMedName(textView.getText().toString());
            for (Medication medication : medicationList) {
                listViewModel.deleteMed(medication);
            }
            dialog.cancel();
        });
        deleteAlert.setNegativeButton("No", (dialog, which) -> {
            dialog.cancel();
        });
        AlertDialog deleteDialog = deleteAlert.create();
        deleteDialog.show();
    }

    Handler handler = new Handler(Looper.getMainLooper());
    public void runHandler() {
        handler.post(runnableCode);
    }

    private Runnable runnableCode = new Runnable() {
        @Override
        public void run() {
            // Do something here on the main thread
            checkForNotifications();
            // Repeat this the same runnable code block again another 2 seconds
            // 'this' is referencing the Runnable object
            handler.postDelayed(this, 1000);
        }
    };
    public void checkForNotifications() {
        //Look up medications
        List<Medication> medList = listViewModel.getAllMeds();
        Calendar now = Calendar.getInstance();
        for (Medication medication:medList) {
            medication.reminder.toInstant().toEpochMilli();

            // Allow a 1 second window for the notification to be sent
            if (Math.abs(medication.reminder.toInstant().toEpochMilli() - now.toInstant().toEpochMilli()) < 1000) {
                Notification.Builder builder = new Notification.Builder(getApplicationContext(), notificationChannel.getId());
                builder.setContentTitle(medication.medName);
                builder.setContentText("Time to take your medication!");
                builder.setSmallIcon(R.drawable.photo);
                Notification notify= builder.build();

                notify.flags |= Notification.FLAG_AUTO_CANCEL;
                notificationManager.notify(0, notify);

                // Update reminder time based on frequency
            }
        }
    }
}
