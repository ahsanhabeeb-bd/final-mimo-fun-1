package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_main_image_Activity extends AppCompatActivity {

    private CircleImageView photo_round1;
    private ImageView photomain1;//work
    private TextView name;
    private TextView age1;
    private TextView recevinging_lavel;
    private TextView sending_level;
    private TextView uid1;
    private TextView bio;
    private ImageView edit;//work
    private ImageView gender;



    ArrayList<Integer> data = new ArrayList<Integer>();
    ArrayList<Integer> data1 = new ArrayList<Integer>();
    RecyclerView rvc;
    RecyclerView.LayoutManager layoutManager;




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




                Picasso.get().load(picture).into(photo_round1);
                Picasso.get().load(photomain).into(photomain1);
                name.setText(name1);
                age1.setText(age);
                recevinging_lavel.setText(recevinging_lavel1);
                sending_level.setText(sending_level1);
                uid1.setText(uid12);
                bio.setText(bio1);

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

                if (badge1.equals(true)){
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
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


}