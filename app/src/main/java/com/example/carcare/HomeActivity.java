package com.example.carcare;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeActivity extends AppCompatActivity {
    private SharedPreferences SP;
    ImageView backgroundImg1;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        TextView UN = (TextView) findViewById(R.id.Title);
        backgroundImg1 = (ImageView) findViewById(R.id.backgroundImageView);
        SP = getSharedPreferences("UserName",MODE_PRIVATE);
        String UserName = "Hello, " + SP.getString("username","BUDDY");
        UN.setText(UserName);



        Animation fadeIn = AnimationUtils.loadAnimation(HomeActivity.this, R.anim.fade_in);
        backgroundImg1.startAnimation(fadeIn);


        ImageView IM = (ImageView) findViewById(R.id.imageButton12);
        IM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this,UserProfile.class);
                startActivity(inext);


            }
        });

        CardView service_centers = findViewById(R.id.service_centers);
        service_centers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this, ServiceCenters.class);
                startActivity(inext);
            }
        });

      CardView my_cart = findViewById(R.id.My_Cart);
        my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this,MyOrders.class);
                startActivity(inext);
            }
        });

        CardView offers = findViewById(R.id.myappointment);
        offers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this, MyAppointment.class);
                startActivity(inext);
                finish();
            }
        });

        CardView caraccessories = findViewById(R.id.Car_Accessories);
        caraccessories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this,CarAccessories.class);
                startActivity(inext);
            }
        });

        CardView cararticles = findViewById(R.id.Car_Articles);
        cararticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this,CarArticles.class);
                startActivity(inext);
            }
        });

        CardView health = findViewById(R.id.Health_CheckUp);
        health.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this, CarCheckup.class);
                startActivity(inext);
            }
        });

        CardView maintenancetips = findViewById(R.id.Maintenance_Tips);
        maintenancetips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(HomeActivity.this, MaintenanceTips.class);
                startActivity(inext);
            }
        });


    }

}

