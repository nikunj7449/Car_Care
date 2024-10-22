package com.example.carcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MaintenanceTips extends AppCompatActivity {
    ImageView back_a;
    private String[][] packages = {
            {"When Should I Change My Car's Oil", "", "", "", "Click for More"},
            {"Importance of Tire Pressure", "", "", "", "Click for More"},
            {"How to Maintain Your Battery", "", "", "", "Click for More"},
            {"Signs Your Brake Pads Need Replacement", "", "", "", "Click for More"},
            {"Keeping Your Engine Cool", "", "", "", "Click for More"},
            {"The Importance of Regular Tire Rotation", "", "", "", "Click for More"},
            {"Understanding Your Car’s Warning Lights", "", "", "", "Click for More"},
            {"How to Wash Your Car Properly", "", "", "", "Click for More"},
            {"Preparing Your Car for Winter", "", "", "", "Click for More"},
            {"Checking and Replacing Windshield Wipers", "", "", "", "Click for More"},
            {"How to Clean Your Car’s Interior", "", "", "", "Click for More"},
            {"Importance of Regular Fluid Checks", "", "", "", "Click for More"},
            {"How to Inspect Your Car's Belts and Hoses", "", "", "", "Click for More"},
            {"Understanding Your Car's Suspension System", "", "", "", "Click for More"},
            {"How to Change a Flat Tire", "", "", "", "Click for More"},
            {"Benefits of Regular Engine Tune-Ups", "", "", "", "Click for More"},
            {"How to Clean and Maintain Your Car’s Headlights", "", "", "", "Click for More"},
            {"Checking Your Brake Fluid Level", "", "", "", "Click for More"},
            {"Understanding Your Car’s Exhaust System", "", "", "", "Click for More"},
            {"How to Prepare Your Car for a Road Trip", "", "", "", "Click for More"},
            {"Importance of Keeping Your Car Clean", "", "", "", "Click for More"}
    };


    private final String[] package_details = {
            "When Should I Change My Car's Oil:\n\n" +
                    "It is essential to change your car's oil regularly to ensure optimal engine performance. " +
                    "Typically, this should be done every 3,000 to 5,000 miles, depending on the type of oil used. " +
                    "Regular oil changes help remove dirt, debris, and contaminants from the engine, preventing wear and tear. " +
                    "Always refer to your owner’s manual for specific recommendations.",
            "Importance of Tire Pressure:\n\n" +
                    "Maintaining the correct tire pressure is crucial for safety, fuel efficiency, and tire longevity. " +
                    "Under-inflated tires can lead to poor handling and increased wear, while over-inflated tires can reduce traction " +
                    "and increase the risk of blowouts. Check your tire pressure at least once a month and before long trips, " +
                    "using a reliable tire gauge.",
            "How to Maintain Your Battery:\n\n" +
                    "A well-maintained battery is vital for your car's performance. Regularly inspect the battery for corrosion around terminals, " +
                    "and clean them with a mixture of baking soda and water. Ensure the battery is securely mounted and that cables are tight. " +
                    "Testing the battery's charge every few months can help prevent unexpected failures.",
            "Signs Your Brake Pads Need Replacement:\n\n" +
                    "Watch for signs that your brake pads need replacing, such as squeaking or grinding noises, a spongy brake pedal, " +
                    "or a warning light on your dashboard. If you notice any of these symptoms, it's important to have your brakes inspected " +
                    "and serviced to ensure your safety on the road.",
            "Keeping Your Engine Cool:\n\n" +
                    "Your engine's cooling system plays a vital role in preventing overheating. Regularly check the coolant level, " +
                    "and look for leaks in hoses or the radiator. Flushing the cooling system as recommended in your owner’s manual can help " +
                    "maintain efficient engine operation and prevent overheating during long drives.",
            "The Importance of Regular Tire Rotation:\n\n" +
                    "Tire rotation helps to promote even wear on all tires, extending their lifespan and improving safety. " +
                    "It is generally recommended to rotate your tires every 5,000 to 7,500 miles or as specified in your owner’s manual. " +
                    "Regular rotations help maintain optimal traction and handling.",
            "Understanding Your Car’s Warning Lights:\n\n" +
                    "Dashboard warning lights are essential indicators of your car's health. Familiarize yourself with the meanings of various lights, " +
                    "such as the check engine light, oil pressure warning, and battery alert. Addressing warning lights promptly can prevent minor issues " +
                    "from escalating into major repairs.",
            "How to Wash Your Car Properly:\n\n" +
                    "Regular washing helps maintain your car's exterior and prevent rust and corrosion. Use a pH-balanced car soap, " +
                    "a microfiber cloth, and rinse thoroughly. Avoid washing in direct sunlight to prevent soap spots and always dry your car " +
                    "with a soft towel to avoid scratches.",
            "Preparing Your Car for Winter:\n\n" +
                    "Winter can be harsh on vehicles, so preparing your car is essential. Ensure your antifreeze is topped up, " +
                    "switch to winter tires, check your battery's health, and keep an emergency kit in your car. " +
                    "Regularly clean your windshield and ensure wiper blades are in good condition for optimal visibility.",
            "Checking and Replacing Windshield Wipers:\n\n" +
                    "Worn-out windshield wipers can significantly affect visibility during rain or snow. Inspect wipers for cracks or tears " +
                    "and replace them at least once a year or as needed. Use high-quality blades that match your vehicle for optimal performance.",
            "How to Clean Your Car’s Interior:\n\n" +
                    "Keeping your car’s interior clean can improve air quality and enhance your driving experience. Regularly vacuum the seats and carpets, " +
                    "wipe down surfaces with a suitable cleaner, and condition leather seats to prevent cracking. Don’t forget to clean the air vents " +
                    "to ensure good airflow.",
            "Importance of Regular Fluid Checks:\n\n" +
                    "Regularly checking your vehicle's fluids, including oil, coolant, brake fluid, and transmission fluid, can prevent major engine issues. " +
                    "Low fluid levels can lead to overheating and other problems, so check them regularly and top them off as needed.",
            "How to Inspect Your Car's Belts and Hoses:\n\n" +
                    "Regular inspections of your car's belts and hoses can help identify potential issues before they lead to breakdowns. " +
                    "Look for cracks, fraying, or leaks in belts, and check hoses for bulges or soft spots. Replace any damaged components promptly " +
                    "to ensure your car runs smoothly.",
            "Understanding Your Car's Suspension System:\n\n" +
                    "A well-functioning suspension system ensures a smooth ride and optimal handling. Regularly inspect components such as shocks, struts, " +
                    "and springs for wear. Listen for unusual noises when driving over bumps, which could indicate suspension problems that require attention.",
            "How to Change a Flat Tire:\n\n" +
                    "Knowing how to change a flat tire is a vital skill for any driver. Always keep a spare tire, jack, and lug wrench in your car. " +
                    "If you get a flat, find a safe location to pull over, loosen the lug nuts, lift the car with the jack, replace the tire, " +
                    "and securely tighten the lug nuts before driving again.",
            "Benefits of Regular Engine Tune-Ups:\n\n" +
                    "Regular engine tune-ups can enhance fuel efficiency, improve performance, and extend the life of your vehicle. " +
                    "A tune-up typically includes checking and replacing spark plugs, air filters, and fuel filters, along with inspecting belts " +
                    "and hoses to ensure everything is functioning correctly.",
            "How to Clean and Maintain Your Car’s Headlights:\n\n" +
                    "Cloudy or dim headlights can reduce visibility and safety. Clean your headlights using a headlight restoration kit or a mixture " +
                    "of baking soda and vinegar. Regularly check the alignment of your headlights to ensure they are properly aimed for optimal visibility.",
            "Checking Your Brake Fluid Level:\n\n" +
                    "Brake fluid is critical for your braking system to function effectively. Regularly check the brake fluid level and look for any signs " +
                    "of leaks. If the fluid is low, top it off with the recommended type and consider having your brakes inspected if you notice any issues.",
            "Understanding Your Car’s Exhaust System:\n\n" +
                    "A well-functioning exhaust system is essential for reducing emissions and ensuring engine efficiency. Regularly inspect for leaks, " +
                    "rust, or damage in the exhaust system. Unusual noises or a decrease in fuel efficiency may indicate a problem that needs addressing.",
            "How to Prepare Your Car for a Road Trip:\n\n" +
                    "Preparing your car for a road trip involves checking fluid levels, ensuring your tires are properly inflated, " +
                    "and testing your brakes and lights. Bring an emergency kit, map, and snacks, and consider scheduling a tune-up before a long journey.",
            "Importance of Keeping Your Car Clean:\n\n" +
                    "Regularly cleaning your car helps maintain its appearance and protect its value. Clean both the interior and exterior, " +
                    "paying special attention to removing dirt, dust, and debris that can lead to wear over time. A clean car also enhances your overall driving experience."
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.maintenancetips);

        back_a = findViewById(R.id.back_arrow);
        ArrayList list;
        SimpleAdapter sa;
        HashMap<String, String> item;

        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MaintenanceTips.this, HomeActivity.class));
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
            item.put("line5", packages[i][4]);
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
                Intent intent = new Intent(MaintenanceTips.this, MaintenanceTipsDetails.class);
                intent.putExtra("text1", package_details[i]);
                startActivity(intent);


            }
        });
    }
}
