package com.example.fitnesstrackerapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        EditText stepsInput = findViewById(R.id.steps_input);
        Button calculateCaloriesButton = findViewById(R.id.calculate_calories_button);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        calculateCaloriesButton.setOnClickListener(view -> {
            String stepsText = stepsInput.getText().toString().trim();
            if (stepsText.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please enter steps", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(MainActivity.this, CaloriesActivity.class);
            int steps = Integer.parseInt(stepsText);
            intent.putExtra("steps", steps);
            startActivity(intent);
        });
    }


}