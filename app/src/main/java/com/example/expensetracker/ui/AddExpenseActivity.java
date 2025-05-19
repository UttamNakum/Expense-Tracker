package com.example.expensetracker.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.expensetracker.R;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.viewmodel.ExpenseViewModel;

public class AddExpenseActivity extends AppCompatActivity {

    private EditText etTitle, etAmount, etCategory, etDate;
    private ExpenseViewModel expenseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_expense);

        etTitle = findViewById(R.id.etTitle);
        etAmount = findViewById(R.id.etAmount);
        etCategory = findViewById(R.id.etCategory);
        etDate = findViewById(R.id.etDate);
        Button btnSave = findViewById(R.id.btnSave);

        expenseViewModel = new ViewModelProvider(this).get(ExpenseViewModel.class);

        btnSave.setOnClickListener(v -> {
            String title = etTitle.getText().toString().trim();
            String category = etCategory.getText().toString().trim();
            String date = etDate.getText().toString().trim();
            String amountStr = etAmount.getText().toString().trim();

            if (TextUtils.isEmpty(title) || TextUtils.isEmpty(category) ||
                    TextUtils.isEmpty(date) || TextUtils.isEmpty(amountStr)) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            double amount = Double.parseDouble(amountStr);

            Expense expense = new Expense(title, amount,category, date);
            expenseViewModel.insert(expense);

            Toast.makeText(this, "Expense Saved", Toast.LENGTH_SHORT).show();
            finish();  // Return to MainActivity
        });
    }
}