package com.example.nikunj.svnitchapters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
   public EditText input_email;
    public EditText input_password;
    public Button btn_login;
    public String inputemail;
    public String inputpassword;
    public TextView link_signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.login);
        input_email=(EditText) findViewById(R.id.input_email);
        input_password=(EditText)findViewById(R.id.input_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        link_signup=(TextView)findViewById(R.id.link_signup);
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inputemail=input_email.getText().toString().trim();
                inputpassword=input_password.getText().toString().trim();
                Toast.makeText(MainActivity.this,inputemail+inputpassword,Toast.LENGTH_LONG).show();
            }
        });
        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(MainActivity.this,signup.class));
            }
        });

    }
}
