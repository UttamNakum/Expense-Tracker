package com.example.expensetracker.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.expensetracker.model.CategoryTotal;
import com.example.expensetracker.model.Expense;

import java.util.List;

@Dao
public interface ExpenseDao {

    // Insert a new expense
    @Insert
    void insert(Expense expense);

    @Query("SELECT * FROM expense")
    List<Expense> getAllExpensesDirect();

    // Fetch all expenses, ordered by date (latest first)
    @Query("SELECT * FROM Expense ORDER BY date DESC")
    LiveData<List<Expense>> getAllExpenses();

    // Group expenses by category and sum their total
    @Query("SELECT category, SUM(amount) AS total FROM Expense GROUP BY category")
    LiveData<List<CategoryTotal>> getCategoryTotals();
}
