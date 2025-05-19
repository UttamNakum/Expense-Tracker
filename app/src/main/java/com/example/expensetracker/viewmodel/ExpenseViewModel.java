package com.example.expensetracker.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.expensetracker.database.ExpenseDao;
import com.example.expensetracker.database.ExpenseDatabase;
import com.example.expensetracker.model.CategoryTotal;
import com.example.expensetracker.model.Expense;

import java.util.List;

public class ExpenseViewModel extends AndroidViewModel {

    private ExpenseDao expenseDao;
    private LiveData<List<Expense>> allExpenses;
    private LiveData<List<CategoryTotal>> categoryTotals;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        ExpenseDatabase db = ExpenseDatabase.getDatabase(application);
        expenseDao = db.expenseDao();
        allExpenses = expenseDao.getAllExpenses();
        categoryTotals = expenseDao.getCategoryTotals();
    }

    public LiveData<List<Expense>> getAllExpenses() {
        return allExpenses;
    }

    public LiveData<List<CategoryTotal>> getCategoryTotals() {
        return categoryTotals;
    }

    public void insert(Expense expense) {
        new Thread(() -> expenseDao.insert(expense)).start();
    }
}

