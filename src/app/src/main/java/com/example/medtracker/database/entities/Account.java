package com.example.medtracker.database.entities;

import androidx.room.*;

@Entity(foreignKeys = @ForeignKey(entity = Account.class,
        parentColumns = "account_id",
        childColumns = "caretaker_id"))
public class Account {

        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "account_id")
        public int accountId;

        @ColumnInfo(name = "first_name")
        public String firstName;

        @ColumnInfo(name = "last_name")
        public String lastName;

        @ColumnInfo(name = "dob")
        public String dob;

        @ColumnInfo(name = "user_type")
        public String userType;

        @ColumnInfo(name = "caretaker_id")
        public Integer caretaker;
}
