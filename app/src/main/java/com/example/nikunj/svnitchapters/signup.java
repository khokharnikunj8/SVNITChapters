package com.example.nikunj.svnitchapters;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

public class signup extends AppCompatActivity {
    public EditText input_email1;
    public EditText input_name1;
    public EditText input_password1;
    public TextView link_login;
    public String inputemail1;
    public String inputname1;
    public String inputpassword1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.signup);
        input_email1=(EditText)findViewById(R.id.input_email1);
        input_name1=(EditText)findViewById(R.id.input_name1);
        input_password1=(EditText)findViewById(R.id.input_password1);
        link_login=(TextView)findViewById(R.id.link_login);
        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(signup.this,MainActivity.class));
            }
        });
    }
}
