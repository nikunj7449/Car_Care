package com.example.carcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class CarArticles extends AppCompatActivity {

    ImageView back_a;
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String, String> item;
    private String[][] health_details = {
            {"When Should I Change My Car's Oil", "", "", "", "Click for More"},
            {"Importance of Tire Pressure", "", "", "", "Click for More "},
            {"How to Maintain Your Battery", "", "", "", "Click for More "},
            {"Signs Your Brake Pads Need Replacement", "", "", "", "Click for More "},
            {"Keeping Your Engine Cool", "", "", "", "Click for More "},
            {"The Importance of Regular Tire Rotation", "", "", "", "Click for More "},
            {"Understanding Your Car’s Warning Lights", "", "", "", "Click for More "}, // New entry
            {"How to Protect Your Car's Paint", "", "", "", "Click for More "},  // New entry
            {"Preparing Your Car for Winter", "", "", "", "Click for More "},  // New entry
    };
private int [] images = {
        R.drawable.whenshouldchngegoil,
        R.drawable.importanceoftirepressure,
        R.drawable.howtomaintainyourbattery,
        R.drawable.signsyourbrakepadsneedreplacement,
        R.drawable.keepingyourenginecool,
        R.drawable.theimportanceofregulartirerotation,
        R.drawable.understandingyourcarswarninglights,
        R.drawable.howtoprotectyourcarspaint,
        R.drawable.preparingyourcarforwinter,
};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.cararticles);

        back_a = findViewById(R.id.back_arrow);

        list = new ArrayList();
        for(int i=0;i<health_details.length;i++) {
            item = new HashMap<String, String>();
            item.put("line1", health_details[i][0]);
            item.put("line2", health_details[i][1]);
            item.put("line3", health_details[i][2]);
            item.put("line4", health_details[i][3]);
            item.put("line5", health_details[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines,
                new String[]{"line1", "line2", "line3", "line4", "line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e} );
        ListView lst = findViewById(R.id.listviewDD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(CarArticles.this, ArticaleView.class);
                intent.putExtra("text1",health_details[i][0]);
                intent.putExtra("text2",images[i]);
                startActivity(intent);

            }
        });

        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CarArticles.this, HomeActivity.class));
                finish();
            }
        });


    }
}

