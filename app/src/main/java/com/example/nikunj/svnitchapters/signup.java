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
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class signup extends AppCompatActivity {
    public EditText input_email1;
    public EditText input_name1;
    public EditText input_admnum1;
    public EditText input_password1;
    public TextView link_login1;
    public String inputemail1;
    public String inputname1;
    public String inputpassword1;
    public String inputadmnum1;
    public String uid;
    public Button btn_signup1;
    public FirebaseAuth firebaseAuth1;
    public DatabaseReference databaseReference1;
    public ProgressDialog progressDialog1;

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
        input_admnum1=(EditText)findViewById(R.id.input_admnum1);
        link_login1=(TextView)findViewById(R.id.link_login1);
        btn_signup1=(Button)findViewById(R.id.btn_signup1);
        firebaseAuth1=FirebaseAuth.getInstance();
        progressDialog1=new ProgressDialog(this);
        databaseReference1= FirebaseDatabase.getInstance().getReference().child("users");
        if(firebaseAuth1.getCurrentUser()!=null)
        {
            finish();
            startActivity(new Intent(this,Chapters.class));
        }

        btn_signup1.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {
                                               inputname1 = input_name1.getText().toString().trim();
                                               inputadmnum1 = input_admnum1.getText().toString().trim();
                                               inputemail1 = input_email1.getText().toString().trim();
                                               inputpassword1 = input_password1.getText().toString().trim();
                                               if (TextUtils.isEmpty(inputname1)) {
                                                   Toast.makeText(signup.this, "Please enter Name", Toast.LENGTH_SHORT).show();
                                                   return;
                                               } else if (TextUtils.isEmpty(inputadmnum1)) {
                                                   Toast.makeText(signup.this, "Please enter Admission number", Toast.LENGTH_SHORT).show();
                                                   return;
                                               } else if (TextUtils.isEmpty(inputemail1)) {
                                                   Toast.makeText(signup.this, "Please enter Email Id", Toast.LENGTH_SHORT).show();
                                                   return;
                                               } else if (TextUtils.isEmpty(inputpassword1)) {
                                                   Toast.makeText(signup.this, "Please enter Password", Toast.LENGTH_SHORT).show();
                                                   return;
                                               }
                                               progressDialog1.setMessage("Registering user....");
                                               progressDialog1.show();

                                               firebaseAuth1.createUserWithEmailAndPassword(inputemail1, inputpassword1).addOnCompleteListener(signup.this, new OnCompleteListener<AuthResult>() {
                                                   @Override
                                                   public void onComplete(@NonNull Task<AuthResult> task) {
                                                       progressDialog1.dismiss();
                                                       if(task.isSuccessful()) {
                                                           uid = firebaseAuth1.getCurrentUser().getUid();
                                                           DatabaseReference current = databaseReference1.child(uid);
                                                           current.child("Name").setValue(inputname1);
                                                           current.child("Email").setValue(inputemail1);
                                                           current.child("Password").setValue(inputpassword1);
                                                           current.child("Admission").setValue(inputadmnum1);
                                                           databaseReference1.keepSynced(true);

                                                           Toast.makeText(signup.this, "Successfully Registered.", Toast.LENGTH_SHORT).show();
                                                           finish();
                                                           startActivity(new Intent(signup.this,Chapters.class));
                                                       }
                                                       else
                                                       {
                                                           Toast.makeText(signup.this, "Try Again.", Toast.LENGTH_SHORT).show();
                                                       }
                                                   }
                                               });
                                           }
                                       });



        link_login1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(signup.this,MainActivity.class));
            }
        });
    }
}
