package com.example.diceroller;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.animation.ObjectAnimator;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    private TextView welcomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_activity);

        // Find the welcome text view
        welcomeText = findViewById(R.id.welcomeText);

        // Fade-in animation for the welcome text
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(welcomeText, "alpha", 0f, 1f);
        fadeIn.setDuration(1500); // 1.5 seconds for fade-in
        fadeIn.start();

        // Handler to delay the navigation to MainActivity (Dice Roller)
        new Handler().postDelayed(() -> {
            // Start the Dice Roller activity after a 2-second delay
            Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish the welcome activity so it's removed from the back stack
        }, 2500); // 2.5 seconds delay before navigating to MainActivity
    }
}
