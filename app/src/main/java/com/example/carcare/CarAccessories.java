package com.example.carcare;

import androidx.activity.EdgeToEdge;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CarAccessories extends AppCompatActivity {

    ImageView back_a;
    Button btngotocart;
    private final String[][] packages = {
            {"1: Seat Cover Premium", "", "", "", "₹1500"},
            {"2: Steering Wheel Cover Leather", "", "", "", "₹900"},
            {"3: Phone Holder Adjustable", "", "", "", "₹450"},
            {"4: Air Freshener Gel", "", "", "", "₹300"},
            {"5: Car Care Cleaning Kit", "", "", "", "₹600"},
            {"6: Dashboard Camera", "", "", "", "₹3500"},
            {"7: Sunshade for Windshield", "", "", "", "₹400"},
            {"8: Car Vacuum Cleaner", "", "", "", "₹2500"},
            {"9: Car Organizer", "", "", "", "₹800"},
            {"10: LED Headlight Bulbs", "", "", "", "₹2000"},
            {"11: Tire Inflator Portable", "", "", "", "₹1800"},
            {"12: Car Bluetooth FM Transmitter", "", "", "", "₹1200"},
            {"13: Car Seat Neck Pillow", "", "", "", "₹800"},
            {"14: Backseat Organizer with Tablet Holder", "", "", "", "₹1000"},
            {"15: Trunk Organizer", "", "", "", "₹1400"},
            {"16: Car Cover Waterproof", "", "", "", "₹2200"},
            {"17: All-Weather Floor Mats", "", "", "", "₹1600"},
            {"18: Car Window Rain Guards", "", "", "", "₹900"}
    };

    private final String[] package_details = {
            "Provides superior comfort and style for your car seats.\n" +
                    "Protects the original seat upholstery from wear and tear.",

            "Enhances grip and comfort for driving.\n" +
                    "Made from durable leather for a stylish look.",

            "Adjustable holder to securely mount your phone while driving.\n" +
                    "Supports various phone sizes for hands-free navigation.",

            "Long-lasting fragrance for a fresh car interior.\n" +
                    "Eliminates unpleasant odors with a refreshing gel-based formula.",

            "Complete cleaning solution for your car's interior and exterior.\n" +
                    "Includes microfiber cloths, brushes, and cleaning sprays.",

            "Captures high-quality footage while driving for safety and security.\n" +
                    "Comes with a built-in storage capacity for video recording.",

            "Protects your car interior from sun damage.\n" +
                    "Easy to install and fold for storage.",

            "Compact vacuum cleaner designed for car interiors.\n" +
                    "Powerful suction for cleaning dirt and debris from seats and floor mats.",

            "Organizes small items like phones, chargers, and documents in your car.\n" +
                    "Keeps your car clutter-free and tidy.",
            "High-performance LED bulbs for brighter headlights.\n" +
                    "Long-lasting with low power consumption.",

            "Portable tire inflator for quick and easy inflation on the go.\n" +
                    "Compact and includes a digital display for accuracy.",

            "Streams music and calls wirelessly through car speakers via Bluetooth.\n" +
                    "Features hands-free calling and USB charging.",

            "Ergonomic neck pillow for comfort during long drives.\n" +
                    "Made from memory foam for added support.",

            "Backseat organizer with multiple compartments for storage.\n" +
                    "Includes a tablet holder for entertainment on long trips.",

            "Heavy-duty trunk organizer for storing groceries and car accessories.\n" +
                    "Foldable and easy to clean.",

            "Waterproof and dust-resistant car cover to protect your vehicle.\n" +
                    "Fits most car sizes and offers all-weather protection.",

            "Durable all-weather floor mats for maximum interior protection.\n" +
                    "Easy to clean and designed for a custom fit.",

            "Rain guards to prevent water from entering through windows.\n" +
                    "Easy installation and reduces wind noise while driving."
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.caraccessories);

        back_a = findViewById(R.id.back_arrow);
        btngotocart = findViewById(R.id.GoToCart);
        ArrayList list;
        SimpleAdapter sa;
        HashMap<String, String> item;

        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CarAccessories.this, HomeActivity.class));
                finish();
            }
        });

        btngotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CarAccessories.this, MyCartA.class));
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
                Intent intent = new Intent(CarAccessories.this, AccessoriesDetails.class);
                intent.putExtra("text1", packages[i][0]);
                intent.putExtra("text2", package_details[i]);
                intent.putExtra("text3", packages[i][4]);
                startActivity(intent);


            }
        });
    }}