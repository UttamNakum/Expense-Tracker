package com.example.expensetracker.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.expensetracker.model.Expense;

@Database(entities = {Expense.class}, version = 1)
public abstract class   ExpenseDatabase extends RoomDatabase {

    private static ExpenseDatabase instance;

    public abstract ExpenseDao expenseDao();

    public static synchronized ExpenseDatabase getDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            ExpenseDatabase.class, "expense_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries() // Only for simplicity in small apps — not recommended for production
                    .build();
        }
        return instance;
    }
}

