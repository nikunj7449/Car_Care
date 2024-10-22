package com.example.carcare;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class CheckOut extends AppCompatActivity {
    ImageView back_a;
    EditText name, add, phn, cn;
    Button btn;
    private static final String DB_NAME="userdetail";


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);

        back_a = findViewById(R.id.back_arrow);
        name = (EditText) findViewById(R.id.UserFullName);
        add = (EditText) findViewById(R.id.address);
        phn = (EditText) findViewById(R.id.UserPhoneNumber);
        cn = (EditText) findViewById(R.id.carnumber);
        btn = (Button) findViewById(R.id.buttonBook);

        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CheckOut.this, MyCart.class));
                finish();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent intent = getIntent();
                    int price = intent.getIntExtra("price",0);
                    String date = intent.getStringExtra("date");
                    String time = intent.getStringExtra("time");
                    SharedPreferences sharedpreferences = getSharedPreferences("UserName", Context.MODE_PRIVATE);
                    String username = sharedpreferences.getString("username", "").toString();
                    Database db = new Database(getApplicationContext(), DB_NAME, null, 1);
                    db.addOrder(username,
                            name.getText().toString(),
                            add.getText().toString(),
                            phn.getText().toString(),
                            cn.getText().toString(),
                            date,
                            time,
                            price,
                            "carcheckup");

                    db.removeCart(username, "carcheckup");
                    db.close();
                    Toast.makeText(CheckOut.this, "Your Booking is Done Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(CheckOut.this, HomeActivity.class));
                    finish();
                }
                catch (Exception e){
                    Log.e("CarCheckup", "Error", e);

                }

            }
        });


    }
}
