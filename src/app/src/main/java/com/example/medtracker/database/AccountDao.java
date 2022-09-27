package com.example.medtracker.database;

import androidx.room.*;

import com.example.medtracker.database.entities.Account;

import java.util.List;


@Dao
public interface AccountDao {
    @Query("SELECT * FROM Account")
    List<Account> getAllUsers();

    @Query("SELECT * FROM Account WHERE account_id = :userId")
    Account getUserById(int userId);

    @Query("SELECT * FROM Account WHERE caretaker_id = :caretakerId")
    List<Account> getAllCaretakerPatients(int caretakerId);

    @Query("SELECT * FROM Account WHERE first_name LIKE :first AND " +
            "last_name LIKE :last AND dob LIKE :dob LIMIT 1")
    Account findUser(String first, String last, String dob);

    @Query("SELECT user_type FROM Account WHERE account_id = :userId")
    String getUserType(int userId);

    @Query("SELECT * FROM Account WHERE user_type = :userType")
    List<Account> getAccountsWithUserType(String userType);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addUser(Account... accounts);

    @Delete
    void deleteUser(Account account);
}