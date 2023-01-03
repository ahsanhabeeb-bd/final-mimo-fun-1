package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class ProfileActivity extends AppCompatActivity
{
    private ImageView main_image;
    private CircleImageView photo_round;
    private ImageView bach;

    private TextView name1;
    private TextView uid1;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    private Button recharge,coins,streamer,user_level,friendly_point,vip_center,mall,family,setting,about;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        photo_round = (CircleImageView) findViewById(R.id.photo_round);
        bach = (ImageView) findViewById(R.id.bach);
        name1 = (TextView) findViewById(R.id.name1);
        uid1 = (TextView) findViewById(R.id.uid1);

        main_image = (ImageView) findViewById(R.id.main_image);

        //button cousting start
        recharge = (Button)findViewById(R.id.recharge);
        coins = (Button)findViewById(R.id.coins);
        streamer = (Button)findViewById(R.id.streamer);
        user_level = (Button)findViewById(R.id.user_level);
        friendly_point = (Button)findViewById(R.id.friendly_point);
        vip_center = (Button)findViewById(R.id.vip_center);
        mall = (Button)findViewById(R.id.mall);
        family = (Button)findViewById(R.id.family);
        setting = (Button)findViewById(R.id.setting);
        about = (Button)findViewById(R.id.about);

        //Button cousting end



        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = String.valueOf(snapshot.child("name").getValue());
                String uid1n = String.valueOf(snapshot.child("id_number").getValue());
                String uid2n = String.valueOf(snapshot.child("picture").getValue());
                String uid3n = String.valueOf(snapshot.child("picturemain").getValue());
                String name_color = String.valueOf(snapshot.child("name_color").getValue());


                name1.setText(name);
                uid1.setText(uid1n);
                name1.setTextColor(Integer.parseInt(name_color));


                // Uri uri =user.getPhotoUrl();
                Picasso.get().load(uid2n).into(photo_round);
                Picasso.get().load(uid3n).into(main_image);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        main_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,Profile_main_image_Activity.class));

            }
        });

        recharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,RechargeActivity.class));
            }
        });

        coins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,CoinsActivity.class));

            }
        });

        streamer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,StreamerActivity.class));

            }
        });

        user_level.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,User_lavel_Activity.class));

            }
        });

        friendly_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,Friendly_pointActivity.class));

            }
        });

        vip_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,Vip_center_Activity.class));

            }
        });

        mall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,MallActivity.class));

            }
        });

        family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,FamilyActivity.class));

            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,SettingActivity.class));

            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this,AboutActivity.class));

            }
        });

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ProfileActivity.this,HomeActivity.class));
        super.onBackPressed();
    }
}