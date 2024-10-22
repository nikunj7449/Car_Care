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

public class UserProfile extends AppCompatActivity {
    private SharedPreferences SP;
    ImageView backgroundImg1;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        backgroundImg1 = (ImageView) findViewById(R.id.backgroundImageView);



        Animation fadeIn = AnimationUtils.loadAnimation(UserProfile.this, R.anim.fade_in);
        backgroundImg1.startAnimation(fadeIn);


        ImageView IM = (ImageView) findViewById(R.id.back_arrow);
        IM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(UserProfile.this,HomeActivity.class);
                startActivity(inext);


            }
        });

        CardView mydetails1 = findViewById(R.id.mydetails);
        mydetails1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(UserProfile.this, MyDetails.class);
                startActivity(inext);
            }
        });

        CardView my_cart = findViewById(R.id.myorders);
        my_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(UserProfile.this,MyOrders.class);
                inext.putExtra("name","user");
                startActivity(inext);
            }
        });

        CardView myappointment = findViewById(R.id.myappointment);
        myappointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(UserProfile.this,MyAppointment.class);
                inext.putExtra("name","user");
                startActivity(inext);
            }
        });

        CardView logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(UserProfile.this, LoginActivity.class);
                startActivity(inext);
            }
        });

        CardView aboutme = findViewById(R.id.aboutme);
        aboutme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(UserProfile.this, AboutMe.class);
                startActivity(inext);
            }
        });



    }

}

