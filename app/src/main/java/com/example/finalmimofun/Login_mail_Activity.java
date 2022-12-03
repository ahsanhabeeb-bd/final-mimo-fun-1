package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_mail_Activity extends AppCompatActivity {

    private EditText mail;
    private EditText password;
    private TextView forgot_password;
    private Button submit;
    private Button registration;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mail);

        mail =(EditText)findViewById(R.id.mail);
        password =(EditText)findViewById(R.id.password);
        forgot_password =(TextView)findViewById(R.id.forgate_password);
        submit =(Button)findViewById(R.id.submit);
        registration =(Button)findViewById(R.id.registration);

        auth = FirebaseAuth.getInstance();

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_mail_Activity.this,Registration_mail_Activity.class));
            }
        });

        forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login_mail_Activity.this,Forgate_pass_Activity.class));
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mail.getText().toString();
                String password1 =password.getText().toString();

                if(email.isEmpty()){
                    mail.setError("Email is Empty");
                } else if(password1.isEmpty()){
                    password.setError("Password is Empty");
                }else if(password1.length()<8){

                }else {
                    signin();
                }
            }
        });




    }

    private void signin()
    {
        String email = mail.getText().toString();
        String password1 = password.getText().toString();

        auth.signInWithEmailAndPassword(email,password1)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startActivity(new Intent(Login_mail_Activity.this,HomeActivity.class));
                            finish();
                        }else {
                            Toast.makeText(Login_mail_Activity.this, ""+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                })

                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Login_mail_Activity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}