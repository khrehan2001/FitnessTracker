package com.example.fitnesstrackerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class CaloriesActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calories);
        int steps = getIntent().getIntExtra("steps", 0);
        TextView caloriesBurned = findViewById(R.id.calories_burned);
        EditText weightInput = findViewById(R.id.weight_input);
        EditText ageInput = findViewById(R.id.age_input);
        Button calculateButton = findViewById(R.id.calculate_button);
        Button backButton = findViewById(R.id.back_button);

        // Get text from EditText and convert to int



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculateButton.setOnClickListener(view -> {
            try {
                int weight = Integer.parseInt(weightInput.getText().toString());
                int age = Integer.parseInt(ageInput.getText().toString());

                double calories = calculateCalories(weight, age, steps);
                caloriesBurned.setText("Calories Burned: " + Math.round(calories));

            }catch (Exception exception){
                Log.d("Exception----->", Objects.requireNonNull(exception.getMessage()));
            }
        });

        backButton.setOnClickListener(view -> {
            finish();
        });


    }




    private double calculateCalories(int weight, int age, int steps) {
        // MET value for walking at moderate speed (e.g., 3.5 METs)
        double met = 3.5;
        // Average step length in miles (e.g., 2,000 steps per mile)
        double stepsPerMile = 2000;
        // Convert steps to miles
        double miles = steps / stepsPerMile;
        // Adjust the MET value based on age (example adjustment, you can refine this)
        double ageFactor = 1 - (age - 20) * 0.001;
        // Calories burned per mile = METs * weight (lbs) * miles * ageFactor
        double caloriesPerMile = met * weight * miles * ageFactor;
        return caloriesPerMile;
    }
}