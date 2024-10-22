package com.example.carcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class ServiceType extends AppCompatActivity {

    TextView tv;
    ImageView back_a;

    String[][] service_center_detail_BMS = {
            {"Service Center Name: AutoCare Center", "Service Center Address: Ahmedabad", "Exp: 5yrs", "Mobile No: 9898989898", "608"},
            {"Service Center Name: QuickFix Auto", "Service Center Address: Surat", "Exp: 15yrs", "Mobile No: 7898989898", "980"},
            {"Service Center Name: Speedy Repairs", "Service Center Address: Vadodara", "Exp: 8yrs", "Mobile No: 8898989898", "300"},
            {"Service Center Name: Wheel Masters", "Service Center Address: Rajkot", "Exp: 6yrs", "Mobile No: 9898000000", "500"},
            {"Service Center Name: Pro Auto Solutions", "Service Center Address: Jamnagar", "Exp: 7yrs", "Mobile No: 7898000000", "450"},
            {"Service Center Name: Elite Car Service", "Service Center Address: Bharuch", "Exp: 10yrs", "Mobile No: 8898000000", "700"}
    };

    String[][] service_center_detail_AS = {
            {"Service Center Name: Total Auto Care", "Service Center Address: Bhavnagar", "Exp: 12yrs", "Mobile No: 9898999999", "650"},
            {"Service Center Name: Prime Car Repair", "Service Center Address: Junagadh", "Exp: 9yrs", "Mobile No: 7798999999", "520"},
            {"Service Center Name: Supreme Auto Works", "Service Center Address: Patan", "Exp: 4yrs", "Mobile No: 6798999999", "410"},
            {"Service Center Name: Precision Auto Garage", "Service Center Address: Gandhinagar", "Exp: 6yrs", "Mobile No: 5798999999", "580"},
            {"Service Center Name: Rapid Auto Fix", "Service Center Address: Ahmedabad", "Exp: 8yrs", "Mobile No: 9898980000", "730"},
            {"Service Center Name: FastFix Mechanics", "Service Center Address: Surat", "Exp: 11yrs", "Mobile No: 7798980000", "940"}
    };

    String[][] service_center_detail_ES = {
            {"Service Center Name: QuickAssist Towing", "Service Center Address: Vadodara", "Exp: 6yrs", "Mobile No: 8898980000", "560"},
            {"Service Center Name: Speedy Help Auto", "Service Center Address: Rajkot", "Exp: 5yrs", "Mobile No: 7898009898", "640"},
            {"Service Center Name: 24x7 Auto Rescue", "Service Center Address: Jamnagar", "Exp: 7yrs", "Mobile No: 8798009898", "720"},
            {"Service Center Name: Rescue Car Care", "Service Center Address: Bharuch", "Exp: 10yrs", "Mobile No: 6798009898", "580"},
            {"Service Center Name: Roadside Assist", "Service Center Address: Bhavnagar", "Exp: 4yrs", "Mobile No: 5798009898", "610"},
            {"Service Center Name: Auto Lifesavers", "Service Center Address: Junagadh", "Exp: 9yrs", "Mobile No: 4798009898", "690"}
    };

    String[][] service_center_detail_SS = {
            {"Service Center Name: Detailing Masters", "Service Center Address: Ahmedabad", "Exp: 10yrs", "Mobile No: 9898789898", "820"},
            {"Service Center Name: Supreme Finish", "Service Center Address: Surat", "Exp: 8yrs", "Mobile No: 8898789898", "930"},
            {"Service Center Name: AutoCare Detailing", "Service Center Address: Vadodara", "Exp: 12yrs", "Mobile No: 7898789898", "780"},
            {"Service Center Name: Prime Gloss", "Service Center Address: Rajkot", "Exp: 6yrs", "Mobile No: 6898789898", "870"},
            {"Service Center Name: ShinePro Auto", "Service Center Address: Jamnagar", "Exp: 9yrs", "Mobile No: 5898789898", "850"},
            {"Service Center Name: Crystal Shine", "Service Center Address: Bharuch", "Exp: 7yrs", "Mobile No: 4898789898", "810"}
    };

    String[][] service_center_detail_IS = {
            {"Service Center Name: Precision Auto Check", "Service Center Address: Gandhinagar", "Exp: 5yrs", "Mobile No: 6798789898", "650"},
            {"Service Center Name: Auto Diagnostics", "Service Center Address: Patan", "Exp: 6yrs", "Mobile No: 5798789898", "720"},
            {"Service Center Name: InspectPro", "Service Center Address: Ahmedabad", "Exp: 7yrs", "Mobile No: 4798789898", "780"},
            {"Service Center Name: SafeDrive Inspect", "Service Center Address: Surat", "Exp: 9yrs", "Mobile No: 3798789898", "890"},
            {"Service Center Name: TotalCheck Auto", "Service Center Address: Rajkot", "Exp: 11yrs", "Mobile No: 2798789898", "850"},
            {"Service Center Name: Auto Test Experts", "Service Center Address: Jamnagar", "Exp: 10yrs", "Mobile No: 1798789898", "920"}
    };
    String[][] servicer_center_detail = {};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.servicetype);
        tv = findViewById(R.id.Title);
        back_a = findViewById(R.id.back_arrow);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);
        ArrayList list;
        SimpleAdapter sa;
        HashMap<String, String> item;

        if (title.compareTo("Basic Maintenance Service") == 0)
            servicer_center_detail = service_center_detail_BMS;
        else if (title.compareTo("Advanced Service") == 0)
            servicer_center_detail = service_center_detail_AS;
        else if (title.compareTo("Emergency Services") == 0)
            servicer_center_detail = service_center_detail_ES;
        else if (title.compareTo("Specialized Services") == 0)
            servicer_center_detail = service_center_detail_SS;
        else
            servicer_center_detail = service_center_detail_IS;


        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ServiceType.this, ServiceCenters.class));
                finish();
            }
        });

        list = new ArrayList();
        for (int i = 0; i < servicer_center_detail.length; i++) {
            item = new HashMap<String, String>();
            item.put("line1", servicer_center_detail[i][0]);
            item.put("line2", servicer_center_detail[i][1]);
            item.put("line3", servicer_center_detail[i][2]);
            item.put("line4", servicer_center_detail[i][3]);
            item.put("line5", "service Fees: " + servicer_center_detail[i][4] + "/-");
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
                    Intent intent = new Intent(ServiceType.this, BookAppointment.class);
                    intent.putExtra("text1", title);
                    intent.putExtra("text2",servicer_center_detail[i][0]);
                    intent.putExtra("text3",servicer_center_detail[i][1]);
                    intent.putExtra("text4",servicer_center_detail[i][3]);
                    int pi = Integer.parseInt(servicer_center_detail[i][4]);//price string to int
                    intent.putExtra("fees",pi);
                    startActivity(intent);

                }
            });
    }
}