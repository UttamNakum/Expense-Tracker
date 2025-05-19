package com.example.expensetracker.model;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Expense {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String title;
    public String category;
    public double amount;
    public String date; // format: YYYY-MM-DD

    // Constructor
    public Expense(String title, double amount, String category, String date) {
        this.title = title;
        this.amount = amount;
        this.category = category;
        this.date = date;
    }
}
