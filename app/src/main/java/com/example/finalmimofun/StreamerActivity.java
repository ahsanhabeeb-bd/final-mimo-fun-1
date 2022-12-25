package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StreamerActivity extends AppCompatActivity {

    Button streamer ;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streamer);

        streamer= (Button) findViewById(R.id.streamer);

        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        buttonclick();


    }

    private void buttonclick() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Boolean official = (Boolean) snapshot.child("official").getValue();
                Boolean agency = (Boolean) snapshot.child("agency").getValue();
                Boolean diamond_seller = (Boolean) snapshot.child("diamond_seller").getValue();
                Boolean host = (Boolean) snapshot.child("host").getValue();


                if (official==true){
                    streamer.setText("official");

                    streamer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(StreamerActivity.this,OfficialActivity.class));
                        }
                    });
                }else if (agency==true){
                    streamer.setText("agency");

                    streamer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(StreamerActivity.this,AgencyActivity.class));
                        }
                    });
                }else if (diamond_seller==true){
                    streamer.setText("diamond_seller");

                    streamer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(StreamerActivity.this,DiamondsellarActivity.class));
                        }
                    });
                }else if (host==true){
                    streamer.setText("host");

                    streamer.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(StreamerActivity.this,HostActivity.class));
                        }
                    });
                }
                else if (official==false && agency==false && diamond_seller==false && host==false){
                    streamer.setText("user");

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}