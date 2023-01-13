package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_main_image_Activity extends AppCompatActivity {

    private CircleImageView photo_round1;
    private ImageView photomain1;
    private ImageView head_frame;
    private TextView name;
    private TextView age1;
    private TextView recevinging_lavel;
    private TextView sending_level;
    private TextView position;
    private TextView uid1;
    private TextView bio;
    private ImageView edit;
    private ImageView gender;



    private ArrayList<Integer> data = new ArrayList<Integer>();
    private ArrayList<Integer> data1 = new ArrayList<Integer>();
    private RecyclerView rvc;
    private RecyclerView.LayoutManager layoutManager;




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
        position = (TextView)findViewById(R.id.position);
        recevinging_lavel = (TextView)findViewById(R.id.recevinging_lavel);
        sending_level = (TextView)findViewById(R.id.sending_level);
        uid1 = (TextView)findViewById(R.id.uid1);
        bio = (TextView)findViewById(R.id.bio);
        edit = (ImageView)findViewById(R.id.edit);
        gender = (ImageView)findViewById(R.id.gender);
        head_frame = (ImageView)findViewById(R.id.head_frame);



        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        rvc  =(RecyclerView) findViewById(R.id.resical);
        layoutManager = new GridLayoutManager(this,4);
        rvc.setLayoutManager(layoutManager);

        BadgetLogic();

        rvc.setAdapter(new badgeAdapter(this,data,data1));




        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String picture= String.valueOf(snapshot.child("picture").getValue());
                String photomain = String.valueOf(snapshot.child("picturemain").getValue());
                String name1 = String.valueOf(snapshot.child("name").getValue());
                String age = String.valueOf(snapshot.child("age").getValue());
                String recevinging_lavel1 = String.valueOf(snapshot.child("gift_received_level").getValue());
                String sending_level1 = String.valueOf(snapshot.child("gift_send_level").getValue());
                String uid12 = String.valueOf(snapshot.child("id_number").getValue());
                String bio1 = String.valueOf(snapshot.child("bio").getValue());
                String gender1 = String.valueOf(snapshot.child("gender").getValue());
                String name_color = String.valueOf(snapshot.child("name_color").getValue());



                Boolean official = (Boolean) snapshot.child("official").getValue();
                Boolean agency = (Boolean) snapshot.child("agency").getValue();
                Boolean diamond_seller = (Boolean) snapshot.child("diamond_seller").getValue();
                Boolean host = (Boolean) snapshot.child("host").getValue();


                Picasso.get().load(picture).into(photo_round1);
                Picasso.get().load(photomain).into(photomain1);
                name.setText(name1);
                age1.setText(age);
                recevinging_lavel.setText(recevinging_lavel1);
                sending_level.setText(sending_level1);
                uid1.setText(uid12);
                bio.setText(bio1);
                name.setTextColor(Integer.parseInt(name_color));

                if(gender1.length()==4)
                {
                    Picasso.get().load(R.drawable.male_sign).into(gender);
                }else if(gender1.length()==6)
                {
                    Picasso.get().load(R.drawable.female_sign).into(gender);
                }


                if (official == true) {
                    position.setVisibility(View.VISIBLE);
                    position.setText("Official");

                }else if (agency == true) {
                    position.setVisibility(View.VISIBLE);
                    position.setText("Agency");

                }else if (diamond_seller == true) {
                    position.setVisibility(View.VISIBLE);
                    position.setText("D.Seller");

                }else if (host == true) {
                    position.setVisibility(View.VISIBLE);
                    position.setText("Host");
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

    private void BadgetLogic() {

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Boolean badge1 = (Boolean) snapshot.child("badge1").getValue();
                Boolean badge2 = (Boolean) snapshot.child("badge2").getValue();
                Boolean badge3 = (Boolean) snapshot.child("badge3").getValue();
                Boolean badge4 = (Boolean) snapshot.child("badge4").getValue();
                Boolean badge5 = (Boolean) snapshot.child("badge5").getValue();
                Boolean badge6 = (Boolean) snapshot.child("badge6").getValue();
                Boolean badge7 = (Boolean) snapshot.child("badge7").getValue();
                Boolean badge8 = (Boolean) snapshot.child("badge8").getValue();
                Boolean badge9 = (Boolean) snapshot.child("badge9").getValue();
                Boolean badge10 = (Boolean) snapshot.child("badge10").getValue();
                Boolean badge11 = (Boolean) snapshot.child("badge11").getValue();
                Boolean badge12 = (Boolean) snapshot.child("badge12").getValue();
                Boolean badge13 = (Boolean) snapshot.child("badge13").getValue();
                Boolean badge14 = (Boolean) snapshot.child("badge14").getValue();
                Boolean badge15 = (Boolean) snapshot.child("badge15").getValue();
                Boolean badge16 = (Boolean) snapshot.child("badge16").getValue();
                Boolean badge17 = (Boolean) snapshot.child("badge17").getValue();
                Boolean badge18 = (Boolean) snapshot.child("badge18").getValue();
                Boolean badge19 = (Boolean) snapshot.child("badge19").getValue();
                Boolean badge20 = (Boolean) snapshot.child("badge20").getValue();
                Boolean badge21 = (Boolean) snapshot.child("badge21").getValue();
                Boolean badge22 = (Boolean) snapshot.child("badge22").getValue();
                Boolean badge23 = (Boolean) snapshot.child("badge23").getValue();
                Boolean badge24 = (Boolean) snapshot.child("badge24").getValue();
                Boolean badge25 = (Boolean) snapshot.child("badge25").getValue();
                Boolean badge26 = (Boolean) snapshot.child("badge26").getValue();
                Boolean badge27 = (Boolean) snapshot.child("badge27").getValue();
                Boolean badge28 = (Boolean) snapshot.child("badge28").getValue();
                Boolean badge29 = (Boolean) snapshot.child("badge29").getValue();
                Boolean badge30 = (Boolean) snapshot.child("badge30").getValue();
                Boolean badge31 = (Boolean) snapshot.child("badge31").getValue();
                Boolean badge32 = (Boolean) snapshot.child("badge32").getValue();
                Boolean badge33 = (Boolean) snapshot.child("badge33").getValue();
                Boolean badge34 = (Boolean) snapshot.child("badge34").getValue();
                Boolean badge35 = (Boolean) snapshot.child("badge35").getValue();
                Boolean badge36 = (Boolean) snapshot.child("badge36").getValue();




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




                if (badge1 == true){
                    data.add(R.drawable.badge1);
                    data1.add(R.drawable.popup1);}
                if (badge2 == true)
                    {data.add(R.drawable.badge2);
                    data1.add(R.drawable.popup2);}
                if (badge3 == true)
                    {data.add(R.drawable.badge3);
                    data1.add(R.drawable.popup3);}
                if (badge4 == true)
                    {data.add(R.drawable.badge4);
                    data1.add(R.drawable.popup4);}
                if (badge5 == true)
                    {data.add(R.drawable.badge5);
                    data1.add(R.drawable.popup5);}
                if (badge6 == true)
                    {data.add(R.drawable.badge6);
                    data1.add(R.drawable.popup6);}
                if (badge7 == true)
                    {data.add(R.drawable.badge7);
                    data1.add(R.drawable.popup7);}
                if (badge8 == true)
                    {data.add(R.drawable.badge8);
                    data1.add(R.drawable.popup8);}
                if (badge9 == true)
                    {data.add(R.drawable.badge9);
                    data1.add(R.drawable.popup9);}
                if (badge10 == true)
                    {data.add(R.drawable.badge10);
                    data1.add(R.drawable.popup10);}
                if (badge11 == true)
                    {data.add(R.drawable.badge11);
                    data1.add(R.drawable.popup11);}
                if (badge12 == true)
                    {data.add(R.drawable.badge12);
                    data1.add(R.drawable.popup12);}
                if (badge13 == true)
                    {data.add(R.drawable.badge13);
                    data1.add(R.drawable.popup13);}
                if (badge14 == true)
                    {data.add(R.drawable.badge14);
                    data1.add(R.drawable.popup14);}
                if (badge15 == true)
                    {data.add(R.drawable.badge15);
                    data1.add(R.drawable.popup15);}
                if (badge16 == true)
                    {data.add(R.drawable.badge16);
                    data1.add(R.drawable.popup16);}
                if (badge17 == true)
                    {data.add(R.drawable.badge17);
                    data1.add(R.drawable.popup17);}
                if (badge18 == true)
                    {data.add(R.drawable.badge18);
                    data1.add(R.drawable.popup18);}
                if (badge19 == true)
                    {data.add(R.drawable.badge19);
                    data1.add(R.drawable.popup19);}
                if (badge20 == true)
                    {data.add(R.drawable.badge20);
                    data1.add(R.drawable.popup20);}
                if (badge21 == true)
                    {data.add(R.drawable.badge21);
                    data1.add(R.drawable.popup21);}
                if (badge22 == true)
                    {data.add(R.drawable.badge22);
                    data1.add(R.drawable.popup22);}
                if (badge23 == true)
                    {data.add(R.drawable.badge23);
                    data1.add(R.drawable.popup23);}
                if (badge24 == true)
                    {data.add(R.drawable.badge24);
                    data1.add(R.drawable.popup24);}
                if (badge25 == true)
                    {data.add(R.drawable.badge25);
                    data1.add(R.drawable.popup25);}
                if (badge26 == true)
                    {data.add(R.drawable.badge26);
                    data1.add(R.drawable.popup26);}
                if (badge27 == true)
                    {data.add(R.drawable.badge27);
                    data1.add(R.drawable.popup27);}
                if (badge28 == true)
                {data.add(R.drawable.badge28);
                    data1.add(R.drawable.popup28);}
                if (badge29 == true)
                {data.add(R.drawable.badge29);
                    data1.add(R.drawable.popup29);}
                if (badge30 == true)
                {data.add(R.drawable.badge30);
                    data1.add(R.drawable.popup30);}
                if (badge31 == true)
                {data.add(R.drawable.badge31);
                    data1.add(R.drawable.popup31);}
                if (badge32 == true)
                {data.add(R.drawable.badge32);
                    data1.add(R.drawable.popup32);}
                if (badge33 == true)
                {data.add(R.drawable.badge33);
                    data1.add(R.drawable.popup33);}
                if (badge34 == true)
                {data.add(R.drawable.badge34);
                    data1.add(R.drawable.popup34);}
                if (badge35 == true)
                {data.add(R.drawable.badge35);
                    data1.add(R.drawable.popup35);}
                if (badge36 == true)
                {data.add(R.drawable.badge36);
                    data1.add(R.drawable.popup36);}


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


    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Profile_main_image_Activity.this,ProfileActivity.class));
        super.onBackPressed();
    }


}