package com.example.nikunj.svnitchapters;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
   public EditText input_email;
    public EditText input_password;
    public Button btn_login;
    public String inputemail;
    public String inputpassword;
    public TextView link_signup;
    public FirebaseAuth firebaseAuth;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.login);


        progressDialog=new ProgressDialog(this);
        input_email=(EditText) findViewById(R.id.input_email);
        input_password=(EditText)findViewById(R.id.input_password);
        btn_login=(Button)findViewById(R.id.btn_login);
        link_signup=(TextView)findViewById(R.id.link_signup);
        firebaseAuth=FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,Chapters.class));
        }
        btn_login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                inputemail=input_email.getText().toString().trim();
                inputpassword=input_password.getText().toString().trim();
                if (TextUtils.isEmpty(inputemail)) {
                    Toast.makeText(MainActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(inputpassword)) {
                    Toast.makeText(MainActivity.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressDialog.setMessage("Logging in...");
                progressDialog.show();
                firebaseAuth.signInWithEmailAndPassword(inputemail,inputpassword).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "Successfully Logged in.", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(MainActivity.this,Chapters.class));
                        }

                        else Toast.makeText(MainActivity.this,"Try Again.",Toast.LENGTH_SHORT).show();
                    }

                });
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
