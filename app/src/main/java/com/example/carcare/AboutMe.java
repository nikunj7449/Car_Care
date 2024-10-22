package com.example.carcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AboutMe extends AppCompatActivity {
    String aboutCarCare = "Welcome to CarCare, your one-stop solution for all car-related needs!\n\n" +
            "Find trusted service centers partnered with CarCare in your area.\n" +
            "Easily book appointments for car services at your preferred service center.\n" +
            "Choose from our comprehensive car checkup packages like Basic Care Package, Wheel Alignment Package, and Brake Check Package.\n" +
            "Shop for a variety of car accessories, including seat covers, steering wheel covers, phone holders, air fresheners, and more.\n" +
            "Access valuable maintenance tips to keep your car in top condition, covering topics like engine care, tire maintenance, and seasonal car care.\n" +
            "Stay informed with the latest car-related articles, offering insights on car trends, safety tips, and how-to guides.\n\n" +
            "CarCare is committed to providing you with the best car service experience. Enjoy a smooth and hassle-free journey with us!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.aboutme);
        EditText text = (EditText) findViewById(R.id.aboutme);
        text.setKeyListener(null);
        text.setText(aboutCarCare);
        ImageView back_a = findViewById(R.id.back_arrow);
        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AboutMe.this, UserProfile.class));
                finish();
            }
        });


    }
}