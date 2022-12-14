package com.hklimited.mimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MainActivity extends AppCompatActivity
{

    private Button sign_phone;
    private Button sign_mail;

    private TextView pilicy;
    private TextView or;


    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_phone =findViewById(R.id.sign_phone);
        sign_mail =findViewById(R.id.sign_mail);

        pilicy  =findViewById(R.id.pilicy);
        or  =findViewById(R.id.or);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();




        if(user != null){

            sign_phone.setVisibility(View.GONE);
            sign_mail.setVisibility(View.GONE);
            or.setVisibility(View.GONE);

            startActivity(new Intent(MainActivity.this,HomeActivity.class));








        }




        sign_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Log_phone_Activity.class));

            }
        });

        sign_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login_mail_Activity.class));
            }
        });

        pilicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PolicyActivity.class));

            }
        });





    }


}