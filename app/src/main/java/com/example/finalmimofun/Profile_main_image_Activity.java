package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_main_image_Activity extends AppCompatActivity {

    private CircleImageView photo_round1;
    private ImageView photomain1;
    private TextView name;
    private TextView age1;
    private TextView recevinging_lavel;
    private TextView sending_level;
    private TextView uid1;
    private TextView bio;
    private ImageView edit;
    private ImageView gender;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main_image);

        photo_round1 = (CircleImageView) findViewById(R.id.photo_round1);
        photomain1 = (ImageView)findViewById(R.id.photomain1);
        name = (TextView)findViewById(R.id.name);
        age1 = (TextView)findViewById(R.id.age1);
        recevinging_lavel = (TextView)findViewById(R.id.recevinging_lavel);
        sending_level = (TextView)findViewById(R.id.sending_level);
        uid1 = (TextView)findViewById(R.id.uid1);
        bio = (TextView)findViewById(R.id.bio);
        edit = (ImageView)findViewById(R.id.edit);
        gender = (ImageView)findViewById(R.id.gender);


        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String picture= String.valueOf(snapshot.child("picture").getValue());
                String photomain = String.valueOf(snapshot.child("picture").getValue());
                String name1 = String.valueOf(snapshot.child("name").getValue());
                String age = String.valueOf(snapshot.child("age").getValue());
                String recevinging_lavel1 = String.valueOf(snapshot.child("gift_received_level").getValue());
                String sending_level1 = String.valueOf(snapshot.child("gift_send_level").getValue());
                String uid12 = String.valueOf(snapshot.child("id_number").getValue());
                String bio1 = String.valueOf(snapshot.child("bio").getValue());
                String gender1 = String.valueOf(snapshot.child("gender").getValue());



                Picasso.get().load(picture).into(photo_round1);
                Picasso.get().load(photomain).into(photomain1);
                name.setText(name1);
                age1.setText(age);
                recevinging_lavel.setText(recevinging_lavel1);
                sending_level.setText(sending_level1);
                uid1.setText(uid12);
                bio.setText(bio1);
              //  Picasso.get().load(R.drawable.male_sign).into(gender);
                if(gender1.length()==4)
                {
                    Picasso.get().load(R.drawable.male_sign).into(gender);
                }else if(gender1.length()==6)
                {
                    Picasso.get().load(R.drawable.female_sign).into(gender);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Profile_main_image_Activity.this,EditActivity.class));
            }
        });
    }
}