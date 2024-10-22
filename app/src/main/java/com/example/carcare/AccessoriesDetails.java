package com.example.carcare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AccessoriesDetails extends AppCompatActivity {
    ImageView back_a;
    EditText name,  fe;
    TextView info;
    Button btn;
    private String pname;
    private String numericPart;
    private static final String DB_NAME="userdetail";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.accessoriesdetails);

        back_a = findViewById(R.id.back_arrow);
        name = (EditText) findViewById(R.id.packageName);
        info = (TextView) findViewById(R.id.packageinfo);
        fe = (EditText) findViewById(R.id.Fee);
        btn = (Button) findViewById(R.id.buttonAdd);




        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccessoriesDetails.this, CarAccessories.class));
                finish();
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences ( "UserName", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString( "username", "").toString();
                String product = pname;
                float price = Float.parseFloat(numericPart);
                Database db = new Database (getApplicationContext(),  DB_NAME, null,  1);
                if(db.checkCart(username, product)==1){
                    Toast.makeText(getApplicationContext(),  "Product Alredy Added", Toast.LENGTH_SHORT).show();
                }else {
                    db.addCart(username, product, price,  "caraccessories");
                    Toast.makeText(getApplicationContext(), "Record Inserted to Cart", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent( AccessoriesDetails.this, CarAccessories.class));
                    finish();
                }

            }
        });

        name.setKeyListener(null);
        info.setKeyListener(null);
        fe.setKeyListener(null);

        Intent it = getIntent();
        pname = it.getStringExtra("text1");
        String inf = it.getStringExtra("text2");
        String fees = it.getStringExtra("text3");
        numericPart = fees.replaceAll("[^0-9]", "");

        name.setText(pname);
        info.setText(inf);
        fe.setText(numericPart  + "/-");


    }
}