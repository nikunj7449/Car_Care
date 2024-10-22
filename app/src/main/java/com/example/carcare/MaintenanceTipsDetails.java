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

public class MaintenanceTipsDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.maintenancetpisdetails);

        EditText text = (EditText) findViewById(R.id.aboutme);

        Intent intent = getIntent();
        String MaintenanceTipsDetails = intent.getStringExtra("text1");

        text.setKeyListener(null);
        text.setText(MaintenanceTipsDetails);
        ImageView back_a = findViewById(R.id.back_arrow);
        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MaintenanceTipsDetails.this, MaintenanceTips.class));
                finish();
            }
        });
    }
}