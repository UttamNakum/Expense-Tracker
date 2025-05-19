package com.example.expensetracker.ui;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.expensetracker.R;
import com.example.expensetracker.model.Expense;
import com.example.expensetracker.database.ExpenseDatabase;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SummaryActivity extends AppCompatActivity {

    PieChart pieChart;
    List<Expense> allExpenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_summary);

        // Initialize the pie chart
        pieChart = findViewById(R.id.pieChart);

        // Get all expenses from database
        allExpenses = (List<Expense>) ExpenseDatabase.getDatabase(this).expenseDao().getAllExpensesDirect();

        // Calculate total expenses by category
        Map<String, Double> categoryTotals = new HashMap<>();
        for (Expense e : allExpenses) {
            categoryTotals.put(e.category, categoryTotals.getOrDefault(e.category, 0.0) + e.amount);
        }

        // Populate the pie chart
        ArrayList<PieEntry> entries = new ArrayList<>();
        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            entries.add(new PieEntry(entry.getValue().floatValue(), entry.getKey()));
        }

        // Customize the pie chart
        PieDataSet dataSet = new PieDataSet(entries, "Expenses by Category");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);

        // Customize the chart appearance
        Description description = new Description();
        description.setText("Summary of your expenses");
        pieChart.setDescription(description);

        pieChart.animateY(1000);
        pieChart.invalidate(); // Refresh chart

    }
}