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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class BookAppointment extends AppCompatActivity {
    ImageView back_a;
    EditText name, add, phn, fe, cn;
    TextView dt, tm;
    Button btn;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private static final String DB_NAME="userdetail";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bookappointment);

        back_a = findViewById(R.id.back_arrow);
        name = (EditText) findViewById(R.id.UserFullName);
        add = (EditText) findViewById(R.id.address);
        phn = (EditText) findViewById(R.id.UserPhoneNumber);
        fe = (EditText) findViewById(R.id.UserFee);
        dt = (TextView) findViewById(R.id.Appointmnet_date);
        tm = (TextView) findViewById(R.id.appointment_time);
        cn = (EditText) findViewById(R.id.carnumber);
        btn = (Button) findViewById(R.id.buttonBook);


        back_a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BookAppointment.this, ServiceCenters.class));
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

        initTimePicker();
        tm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timePickerDialog.show();
            }
        });


        name.setKeyListener(null);
        add.setKeyListener(null);
        phn.setKeyListener(null);
        fe.setKeyListener(null);

        Intent it = getIntent();
        String title = it.getStringExtra("text1");
        String fullname = it.getStringExtra("text2");
        String address = it.getStringExtra("text3");
        String contact = it.getStringExtra("text4");
        int fees = it.getIntExtra("fees",0);
        String feesString = Integer.toString(fees);


        name.setText(fullname);
        add.setText(address);
        phn.setText(contact);
        fe.setText("Service Fees: " + feesString + "/-");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedpreferences = getSharedPreferences("UserName", Context.MODE_PRIVATE);
                String username = sharedpreferences.getString("username", "").toString();
                Database db = new Database(getApplicationContext(), DB_NAME, null, 1);

                if (db.checkAppointmentExists(username, title + " => " + fullname, address, contact, dt.getText().toString(), tm.getText().toString()) == 1) {
                    Toast.makeText(getApplicationContext(), "Appointmnet already booked", Toast.LENGTH_LONG).show();
                } else {
                    db.addOrder(username, title + " => " + fullname, address, contact, "", dt.getText().toString(), tm.getText().toString(), fees, "appointment");
                    Toast.makeText(getApplicationContext(), "Appointmnet booked", Toast.LENGTH_LONG).show();
                    Intent inext = new Intent(BookAppointment.this,ServiceType.class);
                    inext.putExtra("title",title);
                    startActivity(inext);
                }
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

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                tm.setText(i+":"+i1);
            }
        };
        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR);
        int mins = cal.get(Calendar.MINUTE);
        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}
