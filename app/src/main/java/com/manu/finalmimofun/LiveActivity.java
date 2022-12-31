package com.manu.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class LiveActivity extends AppCompatActivity {

    private EditText joinroom,roomname;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live);

        joinroom = findViewById(R.id.joinroom);
        roomname = findViewById(R.id.roomname);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = String.valueOf(snapshot.child("name").getValue());
                String roomid = String.valueOf(snapshot.child("id_number").getValue());


                long appID = 686632462;
                String appSign = "6092a73a416464b51916c2eb38fab32c1dd06115722368daab0739a062971e4a";
                String userID = generateUserID();
                String userName = name ;
                String roomID = roomid;

                findViewById(R.id.start_live).setOnClickListener(v -> {
                    Intent intent = new Intent(LiveActivity.this, LiveAudioRoomActivity.class);
                    intent.putExtra("host", true);
                    intent.putExtra("roomID", roomID);
                    intent.putExtra("appID", appID);
                    intent.putExtra("appSign", appSign);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userName", userName);
                    intent.putExtra("roomname", roomname.getText().toString());
                    startActivity(intent);
                });


                findViewById(R.id.watch_live).setOnClickListener(v -> {
                    Intent intent = new Intent(LiveActivity.this, LiveAudioRoomActivity.class);
                    intent.putExtra("host", false);
                    intent.putExtra("roomID", joinroom.getText().toString());
                    intent.putExtra("appID", appID);
                    intent.putExtra("appSign", appSign);
                    intent.putExtra("userID", userID);
                    intent.putExtra("userName", userName);
                    startActivity(intent);
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }

    private void liverun() {




    }

    private String generateUserID() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < 5) {
            int nextInt = random.nextInt(10);
            if (builder.length() == 0 && nextInt == 0) {
                continue;
            }
            builder.append(nextInt);
        }
        return builder.toString();
    }

}