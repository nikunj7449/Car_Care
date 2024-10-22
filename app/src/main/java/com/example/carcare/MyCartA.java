package com.example.carcare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.regex.Pattern;

public class MyCartA extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    ImageView back_a;
    TextView tvTotal;
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String, String> item;
    private static final String DB_NAME="userdetail";
    String packages[][] = {};
    String tp = "";


    TextView dt;
    Button btncheckout;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.mycarta);

        back_a = findViewById(R.id.back_arrow);
        dt = (TextView) findViewById(R.id.Appointmnet_date);
        btncheckout = (Button) findViewById(R.id.checkout);
        tvTotal = (TextView) findViewById(R.id.totalcost);

        SharedPreferences sharedpreferences = getSharedPreferences ( "UserName", Context.MODE_PRIVATE);
        String username = sharedpreferences.getString( "username", "").toString();
        Database db = new Database (getApplicationContext(),  DB_NAME, null,  1);

        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username,"caraccessories");

        packages = new String[dbData.size()][];
        for(int i=0;i<packages.length;i++) {
            packages[i] = new String[5];
        }
        for(int i=0;i<dbData.size();i++){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i] [4] = "Cost: "+strData[1]+"/-";
            totalAmount = totalAmount + Float.parseFloat(strData[1]);
            tp = Float.toString(totalAmount);
        }
        tvTotal.setText(tp);
        list = new ArrayList();
        for(int i=0;i<packages.length;i++) {
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
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e} );
        ListView lst = findViewById(R.id.listviewDD);
        lst.setAdapter(sa);



        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MyCartA.this, CarAccessories.class));
                finish();
            }
        });

        initDatePicker();
        dt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog.show();
            }
        });

        String numericPart = tp.replaceAll("[^0-9]", "");
        int pi = Integer.parseInt(numericPart);
        btncheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MyCartA.this, CheckOutA.class);
                it.putExtra("price",pi);
                it.putExtra("date",dt.getText().toString());
                startActivity(it);
                finish();
            }
        });


    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1 = i1+1;
                dt.setText(i2+"/"+i1+"/"+i);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);
    }

}