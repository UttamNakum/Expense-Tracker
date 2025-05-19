package com.example.expensetracker.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import com.example.expensetracker.adapter.ExpenseAdapter;
import com.example.expensetracker.R;
import com.example.expensetracker.viewmodel.ExpenseViewModel;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private ExpenseAdapter adapter;
    private ExpenseViewModel expenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewExpenses);
        FloatingActionButton fab = findViewById(R.id.fabAdd);

        Button btnViewSummary = findViewById(R.id.btnViewSummary);
        btnViewSummary.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
            startActivity(intent);
        });

        adapter = new ExpenseAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);
        expenseViewModel.getAllExpenses().observe(this, expenses -> {
            adapter.setExpenseList(expenses);
        });

        fab.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddExpenseActivity.class));
        });
    }
}