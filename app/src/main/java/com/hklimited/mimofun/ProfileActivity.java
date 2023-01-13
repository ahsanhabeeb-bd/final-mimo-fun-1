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
    private ImageView main_image,head_frame;
    private CircleImageView photo_round;


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

        photo_round = (CircleImageView) findViewById(R.id.photo_round1);
        name1 = (TextView) findViewById(R.id.name1);
        uid1 = (TextView) findViewById(R.id.uid1);

        main_image = (ImageView) findViewById(R.id.main_image);
        head_frame = (ImageView) findViewById(R.id.head_frame);

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


                Boolean frame1 = (Boolean) snapshot.child("frame1").getValue();
                Boolean frame2 = (Boolean) snapshot.child("frame2").getValue();
                Boolean frame3 = (Boolean) snapshot.child("frame3").getValue();
                Boolean frame4 = (Boolean) snapshot.child("frame4").getValue();
                Boolean frame5 = (Boolean) snapshot.child("frame5").getValue();
                Boolean frame6 = (Boolean) snapshot.child("frame6").getValue();
                Boolean frame7 = (Boolean) snapshot.child("frame7").getValue();
                Boolean frame8 = (Boolean) snapshot.child("frame8").getValue();
                Boolean frame9 = (Boolean) snapshot.child("frame9").getValue();
                Boolean frame10 = (Boolean) snapshot.child("frame10").getValue();
                Boolean frame11 = (Boolean) snapshot.child("frame11").getValue();
                Boolean frame12 = (Boolean) snapshot.child("frame12").getValue();
                Boolean frame13 = (Boolean) snapshot.child("frame13").getValue();
                Boolean frame14 = (Boolean) snapshot.child("frame14").getValue();
                Boolean frame15 = (Boolean) snapshot.child("frame15").getValue();
                Boolean frame16 = (Boolean) snapshot.child("frame16").getValue();
                Boolean frame17 = (Boolean) snapshot.child("frame17").getValue();
                Boolean frame18 = (Boolean) snapshot.child("frame18").getValue();
                Boolean frame19 = (Boolean) snapshot.child("frame19").getValue();
                Boolean frame20 = (Boolean) snapshot.child("frame20").getValue();
                Boolean frame21 = (Boolean) snapshot.child("frame21").getValue();
                Boolean frame22 = (Boolean) snapshot.child("frame22").getValue();
                Boolean frame23 = (Boolean) snapshot.child("frame23").getValue();
                Boolean frame24 = (Boolean) snapshot.child("frame24").getValue();


                name1.setText(name);
                uid1.setText(uid1n);
                name1.setTextColor(Integer.parseInt(name_color));


                // Uri uri =user.getPhotoUrl();
                Picasso.get().load(uid2n).into(photo_round);
                Picasso.get().load(uid3n).into(main_image);


                if (frame1 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_1).into(head_frame);
                }
                else if (frame2 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_2).into(head_frame);
                }
                else if (frame3 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_3).into(head_frame);
                }
                else if (frame4 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_4).into(head_frame);
                }
                else if (frame5 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_5).into(head_frame);
                }
                else if (frame6 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_6).into(head_frame);
                }
                else if (frame7 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_7).into(head_frame);
                }
                else if (frame8 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_8).into(head_frame);
                }
                else if (frame9 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_9).into(head_frame);
                }
                else if (frame10 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_10).into(head_frame);
                }
                else if (frame11 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_11).into(head_frame);
                }
                else if (frame12 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_12).into(head_frame);
                }
                else if (frame13 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_13).into(head_frame);
                }
                else if (frame14 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_14).into(head_frame);
                }
                else if (frame15 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_15).into(head_frame);
                }
                else if (frame16 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_16).into(head_frame);
                }
                else if (frame17 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_17).into(head_frame);
                }
                else if (frame18 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_18).into(head_frame);
                }
                else if (frame19 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_19).into(head_frame);
                }
                else if (frame20 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_20).into(head_frame);
                }
                else if (frame21 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_21).into(head_frame);
                }
                else if (frame22 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_22).into(head_frame);
                }
                else if (frame23 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_23).into(head_frame);
                }
                else if (frame24 == true){
                    head_frame.setVisibility(View.VISIBLE);
                    Picasso.get().load(R.drawable.frame_24).into(head_frame);
                }



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