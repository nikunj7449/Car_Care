package com.example.carcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CarCheckup extends AppCompatActivity {


    ImageView back_a;
    Button btngotocart;
    private final String[][] packages = {
            {" 1: Basic Care Package", "", "", "", "₹2500"},
            {" 2: Wheel Alignment Package", "", "", "", "₹4500"},
            {" 3: Brake Check Package", "", "", "", "₹3500"},
            {" 4: Complete Service Package", "", "", "", "₹15000"},
            {" 5: Tire Care Package", "", "", "", "₹6000"},
            {" 6: Engine Diagnostic Package", "", "", "", "₹9000"},
            {" 7: Premium Care Package", "", "", "", "₹22000"},
            {" 8: Winter Prep Package", "", "", "", "₹11000"},
            {" 9: Summer Prep Package", "", "", "", "₹9000"},
            {" 10: Express Maintenance Package", "", "", "", "₹7000"},
            {" 11: Battery Health Package", "", "", "", "₹4000"},
            {" 12: Suspension Check Package", "", "", "", "₹8000"},
            {" 13: Eco-Check Package", "", "", "", "₹5500"},
            {" 14: Luxury Vehicle Care Package", "", "", "", "₹35000"}
    };
    private final String[] package_details = {
            "General car inspection\nFluid level check (oil, brake fluid, coolant, etc.)\nTire pressure check and top-up",
            "Front and rear wheel alignment\nTire rotation\nSuspension inspection",
            "Brake pad inspection\nRotor and drum check\nBrake fluid level check",
            "Engine oil change\nAir filter replacement\nFull vehicle diagnostic scan",
            "Tire balancing\nTire rotation\nTire tread wear inspection",
            "Engine check and diagnostic\nECU scan for faults\nExhaust system inspection",
            "Full vehicle inspection (engine, transmission, suspension)\nFluid flush and replacement\nBattery health test",
            "Tire swap to winter tires\nBattery health test\nHeating system check",
            "Air conditioning system check and refill\nCar wash and interior cleaning\nBrake fluid and coolant check",
            "Oil and oil filter change\nFluid top-up\nBasic safety inspection",
            "Battery testing and diagnostics\nTerminal cleaning and corrosion check\nElectrical system check",
            "Full suspension system inspection\nWheel alignment check\nBrake inspection",
            "Exhaust emissions check\nTire pressure and alignment check\nFluid top-ups",
            "Comprehensive vehicle inspection\nPremium oil and filter change\nInterior and exterior detailing"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.carcheckup);

            back_a = findViewById(R.id.back_arrow);
        btngotocart = findViewById(R.id.GoToCart);
            ArrayList list;
            SimpleAdapter sa;
            HashMap<String, String> item;

            back_a.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(CarCheckup.this, HomeActivity.class));
                    finish();
                }
            });

            btngotocart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(CarCheckup.this, MyCart.class));
                    finish();
                }
            });

            list = new ArrayList();
            for (int i = 0; i < packages.length; i++) {
                item = new HashMap<String, String>();
                item.put("line1", packages[i][0]);
                item.put("line2", packages[i][1]);
                item.put("line3", packages[i][2]);
                item.put("line4", packages[i][3]);
                item.put("line5", "Tool Cost: " + packages[i][4] + "/-");
                list.add(item);
            }

            sa = new SimpleAdapter(this, list,
                    R.layout.multi_lines,
                    new String[]{"line1", "line2", "line3", "line4", "line5"},
                    new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
            ListView lst = findViewById(R.id.listviewDD);
            lst.setAdapter(sa);

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(CarCheckup.this, CheckUpDetails.class);
                    intent.putExtra("text1", packages[i][0]);
                    intent.putExtra("text2", package_details[i]);
                    intent.putExtra("text3", packages[i][4]);
                    startActivity(intent);


                }
            });
    }
}
