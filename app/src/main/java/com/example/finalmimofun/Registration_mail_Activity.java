package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration_mail_Activity extends AppCompatActivity

{

    private EditText mail_re;
    private EditText password_re;
    private EditText re_password_re;
    private Button submit_re;
    private ProgressBar progress1;

    private FirebaseAuth auth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_registration);

        mail_re = findViewById(R.id.mail_re);
        password_re = findViewById(R.id.password_re);
        re_password_re = findViewById(R.id.re_password_re);
        submit_re = findViewById(R.id.submit_re);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        user = auth.getCurrentUser();


        progress1 = findViewById(R.id.progress1);



        submit_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progress1.setVisibility(View.VISIBLE);
                String mail1 = mail_re.getText().toString();
                String pas1 = password_re.getText().toString();
                String pas2 = re_password_re.getText().toString();
                if(mail1.isEmpty()) {
                    mail_re.setError("Email Required");

                }else if(pas1.length() < 8){
                    password_re.setError(" 8 digit PASSWORD");
                }else  if(!pas1.equals(pas2)){
                    re_password_re.setError("Password not match");
                }else if(pas1.equals(pas2)){
                    re_data_save();


                }


            }
        });



    }

    private void re_data_save()
    {
        String mail = mail_re.getText().toString();
        String pass = password_re.getText().toString();

        auth.createUserWithEmailAndPassword(mail,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if(task.isSuccessful()){
                            Toast.makeText(Registration_mail_Activity.this, "Registration Successful", Toast.LENGTH_LONG).show();
                            progress1.setVisibility(View.INVISIBLE);

                            startActivity(new Intent(Registration_mail_Activity.this,Profile_data_Activity.class));
                            finish();
                        } else {
                            Toast.makeText(Registration_mail_Activity.this, ""+task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progress1.setVisibility(View.INVISIBLE);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Registration_mail_Activity.this, ""+e, Toast.LENGTH_SHORT).show();
                    }
                });

    }


}