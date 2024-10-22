package com.example.carcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MyDetails extends AppCompatActivity {
    private SharedPreferences SP;
    private SharedPreferences SP1;
    EditText name,  fe;
    ImageView back_a;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mydetails);
        back_a = findViewById(R.id.back_arrow);
        name = (EditText) findViewById(R.id.packageName);
        fe = (EditText) findViewById(R.id.Fee);
        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyDetails.this, UserProfile.class));
                finish();
            }
        });

        name.setKeyListener(null);
        fe.setKeyListener(null);

       SP = getSharedPreferences("UserEmail",MODE_PRIVATE);
       SP1 = getSharedPreferences("UserName",MODE_PRIVATE);
       String username = SP1.getString("username","BUDDY");
        String useremail = SP.getString("useremail","BUDDY");
       name.setText(username);
        fe.setText(useremail);

    }
}