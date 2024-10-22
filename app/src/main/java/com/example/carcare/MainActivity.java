package com.example.carcare;

import static com.example.carcare.R.id.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private ImageView backgroundImageView;
    private TextView splashTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


        backgroundImageView = findViewById(R.id.backgroundImageView);
        splashTextView = findViewById(R.id.splashTextView);

        // Fade in background image
        Animation fadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
        backgroundImageView.startAnimation(fadeIn);

        // Rotate and fade in the text
        Animation rotateFadeIn = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_fade_in);
        splashTextView.startAnimation(rotateFadeIn);

        // Wait for the animations to finish
        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                // Fade out background image
                Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);
                backgroundImageView.startAnimation(fadeOut);

                // Rotate and fade out the text
                //Animation rotateFadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.rotate_fade_out);
                //splashTextView.startAnimation(rotateFadeOut);

                // Start the main activity after the fade out
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {}

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class); // Replace with your main activity
                        startActivity(intent);
                        finish();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {}
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

    }
}




