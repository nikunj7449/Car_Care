package com.example.carcare;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import jp.wasabeef.blurry.Blurry;

public class ServiceCenters extends AppCompatActivity {


    private static final String DB_NAME="UserDetail";
    private static final int DB_VERSION=1;
    private View rootView;
    ImageView back_a;
    final String[] cities = {"Ahmedabad", "Surat", "Vadodara", "Rajkot", "Jamnagar",
            "Bharuch", "Bhavnagar", "Junagadh", "Patan", "Gandhinagar"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.servicecenters);

        rootView = findViewById(android.R.id.content);
        back_a = findViewById(R.id.back_arrow);
       // showCityDialog();

        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceCenters.this, HomeActivity.class));
                finish();
            }
        });

        CardView basic_maintenance_service = findViewById(R.id.Basic_Maintenance_Service);
        basic_maintenance_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(ServiceCenters.this,ServiceType.class);
                inext.putExtra("title","Basic Maintenance Service");
                startActivity(inext);
            }
        });

        CardView advanced_service = findViewById(R.id.Advanced_Service);
        advanced_service.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(ServiceCenters.this,ServiceType.class);
                inext.putExtra("title","Advanced Service");
                startActivity(inext);
            }
        });

        CardView emergency_services = findViewById(R.id.Emergency_Services);
        emergency_services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(ServiceCenters.this,ServiceType.class);
                inext.putExtra("title","Emergency Services");
                startActivity(inext);
            }
        });

        CardView Specialized_Services = findViewById(R.id.Specialized_Services);
        Specialized_Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(ServiceCenters.this,ServiceType.class);
                inext.putExtra("title","Specialized Services");
                startActivity(inext);
            }
        });

        CardView Inspection_Services = findViewById(R.id.Inspection_Services);
        Inspection_Services.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent inext = new Intent(ServiceCenters.this,ServiceType.class);
                inext.putExtra("title","Inspection Services");
                startActivity(inext);
            }
        });


    }

    private void showCityDialog() {
        // Create AlertDialog
        new AlertDialog.Builder(this)
                .setTitle("Select Your City")
                .setSingleChoiceItems(cities, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 'which' represents the index of the selected item in cities array
                        String selectedCity = cities[which];
                        saveCity(selectedCity);  // Save the selected city
                        dialog.dismiss();  // Close the dialog after selection
                    }
                })
                .setCancelable(true)  // Prevent dismissing without interaction
                .show();
    }

    private void saveCity(String city) {
        // Save the selected city in SharedPreferences or send it to the server
        getSharedPreferences("USERCITY", MODE_PRIVATE)
                .edit()
                .putString("user_city", city)
                .apply();

        // Notify the user
        Toast.makeText(this, "City saved: " + city, Toast.LENGTH_SHORT).show();
    }
}

