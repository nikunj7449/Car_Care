package com.example.carcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.graphics.Color;

public class RegistrationActivity extends AppCompatActivity {
    EditText edUsername,edPassword,edEmail,edConfirm;
    ImageView backgroundImg1;
    TextView tv,apptitle;
    Button btn;
    private static final String DB_NAME="UserDetail";
    private static final int DB_VERSION=1;
    SharedPreferences SP;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edUsername = (EditText) findViewById(R.id.editTextUsername);
        edPassword = (EditText) findViewById(R.id.editTextLoginPassword);
        edEmail = (EditText) findViewById(R.id.editTextEmailUsername);
        btn = (Button) findViewById(R.id.buttonRegistor);
        edConfirm = (EditText) findViewById(R.id.editTextLoginConfirmPassword);
        tv = (TextView) findViewById(R.id.textViewExistUser);
        backgroundImg1 = (ImageView) findViewById(R.id.backgroundImageView1);
        apptitle = (TextView) findViewById(R.id.AppTitle1);
        SP = getSharedPreferences("UserEmail",MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();

        Animation focusAnimation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.edittext_focus_animation);
        Animation focusBackAnimation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.edittext_back_animation);
        Animation buttonClickAnimation = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.button_click_animation);
        Animation fadeIn = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.fade_in);

        backgroundImg1.startAnimation(fadeIn);
        apptitle.startAnimation(fadeIn);


        edUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                    view.startAnimation(focusAnimation);
                }
                else{
                    view.startAnimation(focusBackAnimation);
                }
            }
        });

        edPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                    view.startAnimation(focusAnimation);
                }
                else{
                    view.startAnimation(focusBackAnimation);
                }
            }
        });

        edEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                    view.startAnimation(focusAnimation);
                }
                else{
                    view.startAnimation(focusBackAnimation);
                }
            }
        });

        edConfirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus) {

                    view.startAnimation(focusAnimation);
                }
                else{
                    view.startAnimation(focusBackAnimation);
                }
            }
        });


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view){
                view.startAnimation(buttonClickAnimation);
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }

        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClickAnimation);
                String username = edUsername.getText().toString();
                String email = edEmail.getText().toString();
                String password=edPassword.getText().toString();
                String confirm = edConfirm.getText().toString();
                Database db = new Database(getApplicationContext(),DB_NAME,null,DB_VERSION);
                if(username.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(password.compareTo(confirm)==0) {
                       if (isValidEmail(email)) {
                            db.register(username,email,password);
                           editor.putString("useremail",email);
                           editor.commit();
                            Toast.makeText(getApplicationContext(), "Record Inserted", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                           finish();
                        } else {
                           Toast.makeText(getApplicationContext(), "Check email address", Toast.LENGTH_SHORT).show();
                           edEmail.setTextColor(Color.RED);
                           Animation shake = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.shake);
                           edEmail.startAnimation(shake);
                            new Handler().postDelayed(new Runnable() {
                               @Override
                               public void run() {
                                   edEmail.setTextColor(Color.WHITE);
                               }
                           },1000);

                        }
                    } else{
                        edPassword.setTextColor(Color.RED);
                        edConfirm.setTextColor(Color.RED);
                        Animation shake = AnimationUtils.loadAnimation(RegistrationActivity.this, R.anim.shake);
                        edPassword.startAnimation(shake);
                        edConfirm.startAnimation(shake);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edPassword.setTextColor(Color.WHITE);
                                edConfirm.setTextColor(Color.WHITE);
                            }
                        },1000);

                        Toast.makeText(getApplicationContext(),"Password and Confirm password didn't match", Toast.LENGTH_SHORT).show();
                        }

                    }

            }
        });


    }

    private boolean isValidEmail(String email) {
        // Regular expression pattern for validating email with specific domains
        String emailRegex = "^[a-zA-Z0-9_.+-]+@(gmail\\.com|yahoo\\.com)$";

        // Compile the regular expression
        Pattern pattern = Pattern.compile(emailRegex);

        // Match the input email against the pattern
        Matcher matcher = pattern.matcher(email);

        // Return whether the email matches the pattern
        return matcher.matches();
    }

    /*public static boolean isValid(String passwordhere) {
        int f1=0,f2=0,f3=0;
        if(passwordhere.length() < 8 ) {
            return false;
        } else {
            for (int p = 0; p < passwordhere.length(); p++) {
                if (Character.isLetter(passwordhere.charAt(p))) {
                    f1=1;
                }
            }
            for (int r = 0; r < passwordhere.length(); r++){
                if (Character.isDigit(passwordhere.charAt(r))) {
                    f2 = 1;
                }
            }
            for (int s = 0; s < passwordhere.length(); s++) {
                char c= passwordhere.charAt(s);
                if(c>=33&&c<=46||c==64){
                    f3=1;
                }
            }

            if (f1==1 && f2==1 && f3==1) {
                return true;
            }
            return false;
        }

    }*/
}





