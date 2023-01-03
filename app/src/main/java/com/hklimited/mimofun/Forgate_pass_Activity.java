package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Forgate_pass_Activity extends AppCompatActivity
{
    private EditText f_mail;
    private Button f_submit;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgate_pass);

        f_mail = findViewById(R.id.f_mail);
        f_submit = findViewById(R.id.f_submit);

        auth = FirebaseAuth.getInstance();

        f_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String f_mail1 = f_mail.getText().toString();

                if(f_mail1.isEmpty()){
                    f_mail.setError("Email Required");
                } else {
                    for_submit();
                }

            }
        });

    }

    private void for_submit()
    {
        String f_mail2 = f_mail.getText().toString();
        auth.sendPasswordResetEmail(f_mail2)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Forgate_pass_Activity.this, "Check Email for Password", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Forgate_pass_Activity.this,Login_mail_Activity.class));
                            finish();
                        }else {
                            Toast.makeText(Forgate_pass_Activity.this, "ERROR : "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Forgate_pass_Activity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                });
    }
}