package com.hklimited.mimofun;

import static androidx.recyclerview.widget.LinearLayoutManager.HORIZONTAL;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MallActivity extends AppCompatActivity {

    private ImageView photo_round,head_frame;


    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mall);

        photo_round = findViewById(R.id.photo_round);
        head_frame = findViewById(R.id.head_frame);

        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());


            

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String picture= String.valueOf(snapshot.child("picture").getValue());

                Picasso.get().load(picture).into(photo_round);


                // Picasso.get().load(R.drawable.frame_1).into(head_frame);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

}