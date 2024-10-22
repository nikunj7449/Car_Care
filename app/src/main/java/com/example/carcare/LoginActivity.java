package com.example.carcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class LoginActivity extends AppCompatActivity {
    TextView apptitle;
    ImageView backgroundImg1;
    EditText  edUsername;
    EditText  edPassword;
    TextView tv;
    Button btn;
    SharedPreferences SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;});

        edUsername = (EditText) findViewById(R.id.editTextUsername1);
        edPassword = (EditText)findViewById(R.id.editTextLoginPassword1);
        btn = (Button) findViewById(R.id.buttonLogin);
        tv = (TextView) findViewById(R.id.textViewNewUser);
        backgroundImg1 = (ImageView) findViewById(R.id.backgroundImageView);
        apptitle = (TextView) findViewById(R.id.AppTitle1);
        SP = getSharedPreferences("UserName",MODE_PRIVATE);
        SharedPreferences.Editor editor = SP.edit();
        Database db = new Database(getApplicationContext(),"userdetail",null,1);
        Animation buttonClickAnimation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.button_click_animation);
        Animation focusAnimation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.edittext_focus_animation);
        Animation focusBackAnimation = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.edittext_back_animation);
        Animation fadeIn = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.fade_in);

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

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClickAnimation);
               // Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                //startActivity(intent);
                //finish();
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                if (username.isEmpty() || password.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please fill All details", Toast.LENGTH_SHORT).show();
                } else {
                    if(db.login(username,password)==1) {
                        Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_SHORT).show();
                        editor.putString("username",username);
                        editor.commit();
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        edUsername.setTextColor(Color.RED);
                        edPassword.setTextColor(Color.RED);
                        Animation shake = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.shake);
                        edUsername.startAnimation(shake);
                        edPassword.startAnimation(shake);

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                edUsername.setTextColor(Color.WHITE);
                                edPassword.setTextColor(Color.WHITE);
                            }
                        },1000);
                        Toast.makeText(getApplicationContext(), "Invalid Username and Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.startAnimation(buttonClickAnimation);
                Intent intent=new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}