package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class User_lavel_Activity extends AppCompatActivity {

    private TextView courent_level,target_level,courent_exp,target_exp,diamond;
    private ImageView gift_test;
    private ImageView gift_test2;





    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public User_lavel_Activity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_lavel);

        courent_level=(TextView) findViewById(R.id.courent_level);
        target_level=(TextView) findViewById(R.id.target_level);
        courent_exp=(TextView) findViewById(R.id.courent_exp);
        target_exp=(TextView) findViewById(R.id.target_exp);
        diamond=(TextView) findViewById(R.id.diamond);
        gift_test=(ImageView) findViewById(R.id.gift_test);
        gift_test2=(ImageView) findViewById(R.id.gift_test2);


        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String courent_level_00 = String.valueOf(snapshot.child("gift_send_level").getValue());
                String target_level_00 = String.valueOf(snapshot.child("gift_send_target_level").getValue());
                String courent_exp_00 = String.valueOf(snapshot.child("exp_courent_sendin").getValue());
                String target_exp_00 = String.valueOf(snapshot.child("exp_targate_sendin").getValue());
                String diamond_00 = String.valueOf(snapshot.child("diamond").getValue());


                courent_level.setText(courent_level_00);
                courent_exp.setText(courent_exp_00);
                target_level.setText(target_level_00);
                target_exp.setText(target_exp_00);
                diamond.setText(diamond_00);

                int courent_level_00_int = Integer.parseInt(courent_level_00);
                int target_level_00_int = Integer.parseInt(target_level_00);
                int courent_exp_00_int = Integer.parseInt(courent_exp_00);
                int target_exp_00_int = Integer.parseInt(target_exp_00);


                if(courent_level_00_int == 1)
                {

                    //databaseReference.child("gift_send_level").setValue(courent_level_00_int);
                    databaseReference.child("gift_send_target_level").setValue(2);
                    //databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int);
                    databaseReference.child("exp_targate_sendin").setValue(2000);

                    if (courent_exp_00_int == 2000 || courent_exp_00_int>2000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-2000);
                        databaseReference.child("gift_send_level").setValue(2);

                    }

                }
                else if (courent_level_00_int == 2)
                {
                    databaseReference.child("gift_send_target_level").setValue(3);
                    databaseReference.child("exp_targate_sendin").setValue(3000);
                    if (courent_exp_00_int ==3000 || courent_exp_00_int>3000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-3000);
                        databaseReference.child("gift_send_level").setValue(3);

                    }
                }

                else if (courent_level_00_int == 3)
                {
                    databaseReference.child("gift_send_target_level").setValue(4);
                    databaseReference.child("exp_targate_sendin").setValue(4000);
                    if (courent_exp_00_int ==4000 || courent_exp_00_int>4000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-4000);
                        databaseReference.child("gift_send_level").setValue(4);

                    }
                }

                else if (courent_level_00_int == 4)
                {
                    databaseReference.child("gift_send_target_level").setValue(5);
                    databaseReference.child("exp_targate_sendin").setValue(5000);
                    if (courent_exp_00_int ==5000 || courent_exp_00_int>5000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-5000);
                        databaseReference.child("gift_send_level").setValue(5);
                    }
                }

                else if (courent_level_00_int == 5)
                {
                    databaseReference.child("gift_send_target_level").setValue(6);
                    databaseReference.child("exp_targate_sendin").setValue(10000);
                    if (courent_exp_00_int ==10000 || courent_exp_00_int>10000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-10000);
                        databaseReference.child("gift_send_level").setValue(6);
                    }
                }

                else if (courent_level_00_int == 6)
                {
                    databaseReference.child("gift_send_target_level").setValue(7);
                    databaseReference.child("exp_targate_sendin").setValue(15000);
                    if (courent_exp_00_int ==15000 || courent_exp_00_int>15000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-15000);
                        databaseReference.child("gift_send_level").setValue(7);
                    }
                }

                else if (courent_level_00_int == 7)
                {
                    databaseReference.child("gift_send_target_level").setValue(8);
                    databaseReference.child("exp_targate_sendin").setValue(20000);
                    if (courent_exp_00_int ==20000 || courent_exp_00_int>20000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-20000);
                        databaseReference.child("gift_send_level").setValue(8);

                    }
                }

                else if (courent_level_00_int == 8)
                {
                    databaseReference.child("gift_send_target_level").setValue(9);
                    databaseReference.child("exp_targate_sendin").setValue(25000);
                    if (courent_exp_00_int ==25000 || courent_exp_00_int>25000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-25000);
                        databaseReference.child("gift_send_level").setValue(9);

                    }
                }

                else if (courent_level_00_int == 9)
                {
                    databaseReference.child("gift_send_target_level").setValue(10);
                    databaseReference.child("exp_targate_sendin").setValue(30000);
                    if (courent_exp_00_int ==30000 || courent_exp_00_int>30000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-30000);
                        databaseReference.child("gift_send_level").setValue(10);

                    }
                }

                else if (courent_level_00_int == 10)
                {
                    databaseReference.child("gift_send_target_level").setValue(11);
                    databaseReference.child("exp_targate_sendin").setValue(35000);
                    if (courent_exp_00_int ==35000 || courent_exp_00_int>35000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-35000);
                        databaseReference.child("gift_send_level").setValue(11);

                    }
                }

                else if (courent_level_00_int == 11)
                {
                    databaseReference.child("gift_send_target_level").setValue(12);
                    databaseReference.child("exp_targate_sendin").setValue(40000);
                    if (courent_exp_00_int ==40000 || courent_exp_00_int>40000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-40000);
                        databaseReference.child("gift_send_level").setValue(12);

                    }
                }

                else if (courent_level_00_int == 12)
                {
                    databaseReference.child("gift_send_target_level").setValue(13);
                    databaseReference.child("exp_targate_sendin").setValue(45000);
                    if (courent_exp_00_int ==45000 || courent_exp_00_int>45000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-45000);
                        databaseReference.child("gift_send_level").setValue(13);

                    }
                }

                else if (courent_level_00_int == 13)
                {
                    databaseReference.child("gift_send_target_level").setValue(14);
                    databaseReference.child("exp_targate_sendin").setValue(50000);
                    if (courent_exp_00_int ==50000 || courent_exp_00_int>50000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-50000);
                        databaseReference.child("gift_send_level").setValue(14);

                    }
                }

                else if (courent_level_00_int == 14)
                {
                    databaseReference.child("gift_send_target_level").setValue(15);
                    databaseReference.child("exp_targate_sendin").setValue(60000);
                    if (courent_exp_00_int ==60000 || courent_exp_00_int>60000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-60000);
                        databaseReference.child("gift_send_level").setValue(15);

                    }
                }

                else if (courent_level_00_int == 15)
                {
                    databaseReference.child("gift_send_target_level").setValue(16);
                    databaseReference.child("exp_targate_sendin").setValue(70000);
                    if (courent_exp_00_int ==70000 || courent_exp_00_int>70000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-70000);
                        databaseReference.child("gift_send_level").setValue(16);

                    }
                }

                else if (courent_level_00_int == 16)
                {
                    databaseReference.child("gift_send_target_level").setValue(17);
                    databaseReference.child("exp_targate_sendin").setValue(80000);
                    if (courent_exp_00_int ==80000 || courent_exp_00_int>80000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-80000);
                        databaseReference.child("gift_send_level").setValue(17);

                    }
                }

                else if (courent_level_00_int == 17)
                {
                    databaseReference.child("gift_send_target_level").setValue(18);
                    databaseReference.child("exp_targate_sendin").setValue(90000);
                    if (courent_exp_00_int ==90000 || courent_exp_00_int>90000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-90000);
                        databaseReference.child("gift_send_level").setValue(18);

                    }
                }

                else if (courent_level_00_int == 18)
                {
                    databaseReference.child("gift_send_target_level").setValue(19);
                    databaseReference.child("exp_targate_sendin").setValue(100000);
                    if (courent_exp_00_int ==100000 || courent_exp_00_int>100000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-100000);
                        databaseReference.child("gift_send_level").setValue(19);

                    }
                }

                else if (courent_level_00_int == 19)
                {
                    databaseReference.child("gift_send_target_level").setValue(20);
                    databaseReference.child("exp_targate_sendin").setValue(120000);
                    if (courent_exp_00_int ==120000 || courent_exp_00_int>120000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-120000);
                        databaseReference.child("gift_send_level").setValue(20);

                    }
                }

                else if (courent_level_00_int == 20)
                {
                    databaseReference.child("gift_send_target_level").setValue(21);
                    databaseReference.child("exp_targate_sendin").setValue(140000);
                    if (courent_exp_00_int ==140000 || courent_exp_00_int>140000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-140000);
                        databaseReference.child("gift_send_level").setValue(21);

                    }
                }

                else if (courent_level_00_int == 21)
                {
                    databaseReference.child("gift_send_target_level").setValue(22);
                    databaseReference.child("exp_targate_sendin").setValue(160000);
                    if (courent_exp_00_int ==160000 || courent_exp_00_int>160000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-160000);
                        databaseReference.child("gift_send_level").setValue(22);

                    }
                }

                else if (courent_level_00_int == 22)
                {
                    databaseReference.child("gift_send_target_level").setValue(23);
                    databaseReference.child("exp_targate_sendin").setValue(180000);
                    if (courent_exp_00_int ==180000 || courent_exp_00_int>180000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-180000);
                        databaseReference.child("gift_send_level").setValue(23);

                    }
                }

                else if (courent_level_00_int == 23)
                {
                    databaseReference.child("gift_send_target_level").setValue(24);
                    databaseReference.child("exp_targate_sendin").setValue(200000);
                    if (courent_exp_00_int ==200000 || courent_exp_00_int>200000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-200000);
                        databaseReference.child("gift_send_level").setValue(24);

                    }
                }

                else if (courent_level_00_int == 24)
                {
                    databaseReference.child("gift_send_target_level").setValue(25);
                    databaseReference.child("exp_targate_sendin").setValue(220000);
                    if (courent_exp_00_int ==220000 || courent_exp_00_int>220000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-220000);
                        databaseReference.child("gift_send_level").setValue(25);

                    }
                }

                else if (courent_level_00_int == 25)
                {
                    databaseReference.child("gift_send_target_level").setValue(26);
                    databaseReference.child("exp_targate_sendin").setValue(240000);
                    if (courent_exp_00_int ==240000 || courent_exp_00_int>240000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-240000);
                        databaseReference.child("gift_send_level").setValue(26);

                    }
                }

                else if (courent_level_00_int == 26)
                {
                    databaseReference.child("gift_send_target_level").setValue(27);
                    databaseReference.child("exp_targate_sendin").setValue(260000);
                    if (courent_exp_00_int ==260000 || courent_exp_00_int>260000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-260000);
                        databaseReference.child("gift_send_level").setValue(27);

                    }
                }

                else if (courent_level_00_int == 27)
                {
                    databaseReference.child("gift_send_target_level").setValue(28);
                    databaseReference.child("exp_targate_sendin").setValue(280000);
                    if (courent_exp_00_int ==280000 || courent_exp_00_int>280000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-280000);
                        databaseReference.child("gift_send_level").setValue(28);

                    }
                }

                else if (courent_level_00_int == 28)
                {
                    databaseReference.child("gift_send_target_level").setValue(29);
                    databaseReference.child("exp_targate_sendin").setValue(300000);
                    if (courent_exp_00_int ==300000 || courent_exp_00_int>300000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-300000);
                        databaseReference.child("gift_send_level").setValue(29);

                    }
                }

                else if (courent_level_00_int == 29)
                {
                    databaseReference.child("gift_send_target_level").setValue(30);
                    databaseReference.child("exp_targate_sendin").setValue(320000);
                    if (courent_exp_00_int ==320000 || courent_exp_00_int>320000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-320000);
                        databaseReference.child("gift_send_level").setValue(30);

                    }
                }

                else if (courent_level_00_int == 30)
                {
                    databaseReference.child("gift_send_target_level").setValue(31);
                    databaseReference.child("exp_targate_sendin").setValue(340000);
                    if (courent_exp_00_int ==340000 || courent_exp_00_int>340000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-340000);
                        databaseReference.child("gift_send_level").setValue(31);

                    }
                }

                else if (courent_level_00_int == 31)
                {
                    databaseReference.child("gift_send_target_level").setValue(32);
                    databaseReference.child("exp_targate_sendin").setValue(360000);
                    if (courent_exp_00_int ==360000 || courent_exp_00_int>360000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-360000);
                        databaseReference.child("gift_send_level").setValue(32);

                    }
                }

                else if (courent_level_00_int == 32)
                {
                    databaseReference.child("gift_send_target_level").setValue(33);
                    databaseReference.child("exp_targate_sendin").setValue(400000);
                    if (courent_exp_00_int ==400000 || courent_exp_00_int>400000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-400000);
                        databaseReference.child("gift_send_level").setValue(33);

                    }
                }
                else if (courent_level_00_int == 33)
                {
                    databaseReference.child("gift_send_target_level").setValue(34);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(34);

                    }
                }

                else if (courent_level_00_int == 34)
                {
                    databaseReference.child("gift_send_target_level").setValue(35);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(35);

                    }
                }

                else if (courent_level_00_int == 35)
                {
                    databaseReference.child("gift_send_target_level").setValue(36);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(36);

                    }
                }

                else if (courent_level_00_int == 36)
                {
                    databaseReference.child("gift_send_target_level").setValue(37);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(37);

                    }
                }

                else if (courent_level_00_int == 37)
                {
                    databaseReference.child("gift_send_target_level").setValue(38);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(38);

                    }
                }

                else if (courent_level_00_int == 38)
                {
                    databaseReference.child("gift_send_target_level").setValue(39);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(39);

                    }
                }
                else if (courent_level_00_int == 39)
                {
                    databaseReference.child("gift_send_target_level").setValue(40);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(40);

                    }
                }

                else if (courent_level_00_int == 40)
                {
                    databaseReference.child("gift_send_target_level").setValue(41);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(41);

                    }
                }

                else if (courent_level_00_int == 41)
                {
                    databaseReference.child("gift_send_target_level").setValue(42);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(42);

                    }
                }

                else if (courent_level_00_int == 42)
                {
                    databaseReference.child("gift_send_target_level").setValue(43);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(43);

                    }
                }

                else if (courent_level_00_int == 43)
                {
                    databaseReference.child("gift_send_target_level").setValue(44);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(44);

                    }
                }

                else if (courent_level_00_int == 44)
                {
                    databaseReference.child("gift_send_target_level").setValue(45);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(45);

                    }
                }

                else if (courent_level_00_int == 45)
                {
                    databaseReference.child("gift_send_target_level").setValue(46);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(46);

                    }
                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        gift_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dimond_xp = diamond.getText().toString();
                int dimond_xp_int = Integer.parseInt(dimond_xp);
                String courent_exp_str = courent_exp.getText().toString();
                if (dimond_xp_int>100){
                    int courent_exp_str_int = Integer.parseInt(courent_exp_str)+100;
                    int courent_dimond_xp_int = dimond_xp_int-100;
                    databaseReference.child("exp_courent_sendin").setValue(courent_exp_str_int);
                    databaseReference.child("diamond").setValue(courent_dimond_xp_int);
                }
                else {
                    Toast.makeText(User_lavel_Activity.this, "don't have enough diamond", Toast.LENGTH_SHORT).show();
                }

            }
        });

        gift_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dimond_xp = diamond.getText().toString();
                int dimond_xp_int = Integer.parseInt(dimond_xp);
                String courent_exp_str = courent_exp.getText().toString();
                if (dimond_xp_int>5000){
                    int courent_exp_str_int = Integer.parseInt(courent_exp_str)+5000;
                    int courent_dimond_xp_int = dimond_xp_int-5000;
                    databaseReference.child("exp_courent_sendin").setValue(courent_exp_str_int);
                    databaseReference.child("diamond").setValue(courent_dimond_xp_int);
                }
                else {
                    Toast.makeText(User_lavel_Activity.this, "don't have enough diamond", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }


}