package com.example.diceroller;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView diceImage;
    private TextView diceNumber;
    private MediaPlayer diceSound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        diceImage = findViewById(R.id.dice);
        diceNumber = findViewById(R.id.dice_number);
        Button rollButton = findViewById(R.id.rollButton);
        Button exitButton = findViewById(R.id.exitButton);

        // Load the dice roll sound from res/raw
        diceSound = MediaPlayer.create(this, R.raw.dice_roll);

        // Roll button listener
        rollButton.setOnClickListener(v -> rollDice());

        // Exit button listener
        exitButton.setOnClickListener(v -> {
            // Close the activity (exit the app)
            finish();
        });
    }

    private void rollDice() {
        // Play roll sound
        if (diceSound != null) {
            diceSound.start();
        }

        // Dice animation (rotation and scaling)
        animateDice();

        // Generate random number between 1 and 6
        int number = new Random().nextInt(6) + 1;

        // Get corresponding dice image
        int resId = getResources().getIdentifier("dice_" + number, "drawable", getPackageName());

        // Set image and number after animation
        diceImage.setImageResource(resId);
        diceNumber.setText(String.valueOf(number));

        // Fade-in the dice number
        fadeInText(diceNumber);
    }

    private void animateDice() {
        // Rotation animation (rotate 360 degrees)
        ObjectAnimator rotate = ObjectAnimator.ofFloat(diceImage, "rotation", 0f, 1080f); // 1080 degrees (3 full rotations)
        rotate.setDuration(1000); // Duration in milliseconds (1 second)

        // Scale animation (bigger then back to normal)
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(diceImage, "scaleX", 1f, 1.5f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(diceImage, "scaleY", 1f, 1.5f, 1f);
        scaleX.setDuration(500);  // Duration for scaling effect
        scaleY.setDuration(500);

        // Start animations together
        rotate.start();
        scaleX.start();
        scaleY.start();
    }

    private void fadeInText(TextView textView) {
        // Fade-in animation for dice number
        ObjectAnimator fadeIn = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f);
        fadeIn.setDuration(500); // Duration for fading effect
        fadeIn.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Release MediaPlayer resource
        if (diceSound != null) {
            diceSound.release();
            diceSound = null;
        }
    }
    private void bounceDice() {
        // Bouncing effect (translationY)
        ObjectAnimator bounceUp = ObjectAnimator.ofFloat(diceImage, "translationY", 0f, -20f, 0f);
        bounceUp.setDuration(300); // Duration for bounce effect
        bounceUp.start();
    }

}
