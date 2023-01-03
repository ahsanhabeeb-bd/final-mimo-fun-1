package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class User_lavel_Activity extends AppCompatActivity {

    private TextView courent_level,courent_level2,target_level,courent_exp,target_exp,diamond,need_exp;
    private TextView courent_level_receive,courent_level2_receive,target_level_receive,courent_exp_receice,target_exp_receive,coins,need_exp_receive;

    private CircleImageView photo_round;
    private CircleImageView photo_round_receive;


    private ImageView  gift_test100,gift_test500,gift_test1000,gift_test5000;


    private ProgressBar progressbar;
    private ProgressBar progressbar_receive;




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
        courent_level2=(TextView) findViewById(R.id.courent_level2);
        target_level=(TextView) findViewById(R.id.target_level);
        courent_exp=(TextView) findViewById(R.id.courent_exp);
        target_exp=(TextView) findViewById(R.id.target_exp);
        diamond=(TextView) findViewById(R.id.diamond);
        need_exp=(TextView) findViewById(R.id.need_exp);

        courent_level_receive=(TextView) findViewById(R.id.courent_level_receive);
        courent_level2_receive=(TextView) findViewById(R.id.courent_level2_receive);
        target_level_receive=(TextView) findViewById(R.id.target_level_receive);
        courent_exp_receice=(TextView) findViewById(R.id.courent_exp_receice);
        target_exp_receive=(TextView) findViewById(R.id.target_exp_receive);
        coins=(TextView) findViewById(R.id.coins);
        need_exp_receive=(TextView) findViewById(R.id.need_exp_receive);


        photo_round= (CircleImageView)findViewById(R.id.photo_round);
        photo_round_receive= (CircleImageView)findViewById(R.id.photo_round_receive);//done

        progressbar = (ProgressBar)findViewById(R.id.progressbar);
        progressbar_receive = (ProgressBar)findViewById(R.id.progressbar_receive);

        gift_test100 = findViewById(R.id.but100);
        gift_test500 = findViewById(R.id.but500);
        gift_test1000 = findViewById(R.id.but1000);
        gift_test5000 = findViewById(R.id.but5000);


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
                String progress_curant_00 = String.valueOf(snapshot.child("progress_curant").getValue());


                String diamond_00 = String.valueOf(snapshot.child("diamond").getValue());
                String uid2n = String.valueOf(snapshot.child("picture").getValue());



                String courent_level_receive_00 = String.valueOf(snapshot.child("gift_received_level").getValue());
                String target_level_receive_00 = String.valueOf(snapshot.child("gift_received_target_level").getValue());
                String courent_exp_receive_00 = String.valueOf(snapshot.child("exp_courent_receive").getValue());
                String target_exp_receive_00 = String.valueOf(snapshot.child("exp_targate_receive").getValue());
                String progress_curant_receive_00 = String.valueOf(snapshot.child("progress_curant_receive").getValue());


                String coins_receive_00 = String.valueOf(snapshot.child("coin").getValue());


                Picasso.get().load(uid2n).into(photo_round);
                Picasso.get().load(uid2n).into(photo_round_receive);

                courent_level.setText(courent_level_00);
                courent_level2.setText(courent_level_00);
                courent_exp.setText(courent_exp_00);
                target_level.setText(target_level_00);
                target_exp.setText(target_exp_00);//copid

                courent_level_receive.setText(courent_level_receive_00);
                courent_level2_receive.setText(courent_level_receive_00);
                courent_exp_receice.setText(courent_exp_receive_00);
                target_level_receive.setText(target_level_receive_00);
                target_exp_receive.setText(target_exp_receive_00);



                diamond.setText(diamond_00);//copid
                coins.setText(coins_receive_00);

                int courent_level_00_int = Integer.parseInt(courent_level_00);
                int target_level_00_int = Integer.parseInt(target_level_00);
                int courent_exp_00_int = Integer.parseInt(courent_exp_00);
                int target_exp_00_int = Integer.parseInt(target_exp_00);//copid


                int courent_level_receive_00_int = Integer.parseInt(courent_level_receive_00);
                int target_level_receive_00_int = Integer.parseInt(target_level_receive_00);
                int courent_exp_receive_00_int = Integer.parseInt(courent_exp_receive_00);
                int target_exp_receive_00_int = Integer.parseInt(target_exp_receive_00);




                progressbar.setProgress(Integer.parseInt(progress_curant_00));
                progressbar.setMax(Integer.parseInt(target_exp_00));//copid

                progressbar_receive.setProgress(Integer.parseInt(progress_curant_receive_00));////////////
                progressbar_receive.setMax(Integer.parseInt(target_exp_receive_00));

                String ned_exp_int = String.valueOf((target_exp_00_int - courent_exp_00_int));//copid

                String ned_exp_receive_int = String.valueOf((target_exp_receive_00_int - courent_exp_receive_00_int));


                need_exp.setText(ned_exp_int)  ;

                need_exp_receive.setText(ned_exp_receive_int)  ;

                if(courent_level_00_int == 1)
                {
                    databaseReference.child("gift_send_target_level").setValue(2);
                    databaseReference.child("exp_targate_sendin").setValue(2000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int == 2000 || courent_exp_00_int>2000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-2000);
                        databaseReference.child("gift_send_level").setValue(2);
                    }
                }


                else if (courent_level_00_int == 2)
                {
                    databaseReference.child("gift_send_target_level").setValue(3);
                    databaseReference.child("exp_targate_sendin").setValue(3000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==3000 || courent_exp_00_int>3000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-3000);
                        databaseReference.child("gift_send_level").setValue(3);

                    }
                }

                else if (courent_level_00_int == 3)
                {
                    databaseReference.child("gift_send_target_level").setValue(4);
                    databaseReference.child("exp_targate_sendin").setValue(4000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==4000 || courent_exp_00_int>4000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-4000);
                        databaseReference.child("gift_send_level").setValue(4);

                    }
                }

                else if (courent_level_00_int == 4)
                {
                    databaseReference.child("gift_send_target_level").setValue(5);
                    databaseReference.child("exp_targate_sendin").setValue(5000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==5000 || courent_exp_00_int>5000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-5000);
                        databaseReference.child("gift_send_level").setValue(5);
                    }
                }

                else if (courent_level_00_int == 5)
                {
                    databaseReference.child("gift_send_target_level").setValue(6);
                    databaseReference.child("exp_targate_sendin").setValue(10000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==10000 || courent_exp_00_int>10000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-10000);
                        databaseReference.child("gift_send_level").setValue(6);
                    }
                }

                else if (courent_level_00_int == 6)
                {
                    databaseReference.child("gift_send_target_level").setValue(7);
                    databaseReference.child("exp_targate_sendin").setValue(15000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==15000 || courent_exp_00_int>15000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-15000);
                        databaseReference.child("gift_send_level").setValue(7);
                    }
                }

                else if (courent_level_00_int == 7)
                {
                    databaseReference.child("gift_send_target_level").setValue(8);
                    databaseReference.child("exp_targate_sendin").setValue(20000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==20000 || courent_exp_00_int>20000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-20000);
                        databaseReference.child("gift_send_level").setValue(8);

                    }
                }

                else if (courent_level_00_int == 8)
                {
                    databaseReference.child("gift_send_target_level").setValue(9);
                    databaseReference.child("exp_targate_sendin").setValue(25000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==25000 || courent_exp_00_int>25000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-25000);
                        databaseReference.child("gift_send_level").setValue(9);

                    }
                }

                else if (courent_level_00_int == 9)
                {
                    databaseReference.child("gift_send_target_level").setValue(10);
                    databaseReference.child("exp_targate_sendin").setValue(30000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==30000 || courent_exp_00_int>30000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-30000);
                        databaseReference.child("gift_send_level").setValue(10);

                    }
                }

                else if (courent_level_00_int == 10)
                {
                    databaseReference.child("gift_send_target_level").setValue(11);
                    databaseReference.child("exp_targate_sendin").setValue(35000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==35000 || courent_exp_00_int>35000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-35000);
                        databaseReference.child("gift_send_level").setValue(11);

                    }
                }

                else if (courent_level_00_int == 11)
                {
                    databaseReference.child("gift_send_target_level").setValue(12);
                    databaseReference.child("exp_targate_sendin").setValue(40000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==40000 || courent_exp_00_int>40000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-40000);
                        databaseReference.child("gift_send_level").setValue(12);

                    }
                }

                else if (courent_level_00_int == 12)
                {
                    databaseReference.child("gift_send_target_level").setValue(13);
                    databaseReference.child("exp_targate_sendin").setValue(45000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==45000 || courent_exp_00_int>45000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-45000);
                        databaseReference.child("gift_send_level").setValue(13);

                    }
                }

                else if (courent_level_00_int == 13)
                {
                    databaseReference.child("gift_send_target_level").setValue(14);
                    databaseReference.child("exp_targate_sendin").setValue(50000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==50000 || courent_exp_00_int>50000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-50000);
                        databaseReference.child("gift_send_level").setValue(14);

                    }
                }

                else if (courent_level_00_int == 14)
                {
                    databaseReference.child("gift_send_target_level").setValue(15);
                    databaseReference.child("exp_targate_sendin").setValue(60000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==60000 || courent_exp_00_int>60000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-60000);
                        databaseReference.child("gift_send_level").setValue(15);

                    }
                }

                else if (courent_level_00_int == 15)
                {
                    databaseReference.child("gift_send_target_level").setValue(16);
                    databaseReference.child("exp_targate_sendin").setValue(70000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==70000 || courent_exp_00_int>70000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-70000);
                        databaseReference.child("gift_send_level").setValue(16);

                    }
                }

                else if (courent_level_00_int == 16)
                {
                    databaseReference.child("gift_send_target_level").setValue(17);
                    databaseReference.child("exp_targate_sendin").setValue(80000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==80000 || courent_exp_00_int>80000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-80000);
                        databaseReference.child("gift_send_level").setValue(17);

                    }
                }

                else if (courent_level_00_int == 17)
                {
                    databaseReference.child("gift_send_target_level").setValue(18);
                    databaseReference.child("exp_targate_sendin").setValue(90000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==90000 || courent_exp_00_int>90000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-90000);
                        databaseReference.child("gift_send_level").setValue(18);

                    }
                }

                else if (courent_level_00_int == 18)
                {
                    databaseReference.child("gift_send_target_level").setValue(19);
                    databaseReference.child("exp_targate_sendin").setValue(100000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==100000 || courent_exp_00_int>100000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-100000);
                        databaseReference.child("gift_send_level").setValue(19);

                    }
                }

                else if (courent_level_00_int == 19)
                {
                    databaseReference.child("gift_send_target_level").setValue(20);
                    databaseReference.child("exp_targate_sendin").setValue(120000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==120000 || courent_exp_00_int>120000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-120000);
                        databaseReference.child("gift_send_level").setValue(20);

                    }
                }

                else if (courent_level_00_int == 20)
                {
                    databaseReference.child("gift_send_target_level").setValue(21);
                    databaseReference.child("exp_targate_sendin").setValue(140000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==140000 || courent_exp_00_int>140000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-140000);
                        databaseReference.child("gift_send_level").setValue(21);

                    }
                }

                else if (courent_level_00_int == 21)
                {
                    databaseReference.child("gift_send_target_level").setValue(22);
                    databaseReference.child("exp_targate_sendin").setValue(160000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==160000 || courent_exp_00_int>160000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-160000);
                        databaseReference.child("gift_send_level").setValue(22);

                    }
                }

                else if (courent_level_00_int == 22)
                {
                    databaseReference.child("gift_send_target_level").setValue(23);
                    databaseReference.child("exp_targate_sendin").setValue(180000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==180000 || courent_exp_00_int>180000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-180000);
                        databaseReference.child("gift_send_level").setValue(23);

                    }
                }

                else if (courent_level_00_int == 23)
                {
                    databaseReference.child("gift_send_target_level").setValue(24);
                    databaseReference.child("exp_targate_sendin").setValue(200000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==200000 || courent_exp_00_int>200000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-200000);
                        databaseReference.child("gift_send_level").setValue(24);

                    }
                }

                else if (courent_level_00_int == 24)
                {
                    databaseReference.child("gift_send_target_level").setValue(25);
                    databaseReference.child("exp_targate_sendin").setValue(220000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==220000 || courent_exp_00_int>220000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-220000);
                        databaseReference.child("gift_send_level").setValue(25);

                    }
                }

                else if (courent_level_00_int == 25)
                {
                    databaseReference.child("gift_send_target_level").setValue(26);
                    databaseReference.child("exp_targate_sendin").setValue(240000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==240000 || courent_exp_00_int>240000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-240000);
                        databaseReference.child("gift_send_level").setValue(26);

                    }
                }

                else if (courent_level_00_int == 26)
                {
                    databaseReference.child("gift_send_target_level").setValue(27);
                    databaseReference.child("exp_targate_sendin").setValue(260000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==260000 || courent_exp_00_int>260000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-260000);
                        databaseReference.child("gift_send_level").setValue(27);

                    }
                }

                else if (courent_level_00_int == 27)
                {
                    databaseReference.child("gift_send_target_level").setValue(28);
                    databaseReference.child("exp_targate_sendin").setValue(280000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==280000 || courent_exp_00_int>280000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-280000);
                        databaseReference.child("gift_send_level").setValue(28);

                    }
                }

                else if (courent_level_00_int == 28)
                {
                    databaseReference.child("gift_send_target_level").setValue(29);
                    databaseReference.child("exp_targate_sendin").setValue(300000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==300000 || courent_exp_00_int>300000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-300000);
                        databaseReference.child("gift_send_level").setValue(29);

                    }
                }

                else if (courent_level_00_int == 29)
                {
                    databaseReference.child("gift_send_target_level").setValue(30);
                    databaseReference.child("exp_targate_sendin").setValue(320000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==320000 || courent_exp_00_int>320000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-320000);
                        databaseReference.child("gift_send_level").setValue(30);

                    }
                }

                else if (courent_level_00_int == 30)
                {
                    databaseReference.child("gift_send_target_level").setValue(31);
                    databaseReference.child("exp_targate_sendin").setValue(340000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==340000 || courent_exp_00_int>340000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-340000);
                        databaseReference.child("gift_send_level").setValue(31);

                    }
                }

                else if (courent_level_00_int == 31)
                {
                    databaseReference.child("gift_send_target_level").setValue(32);
                    databaseReference.child("exp_targate_sendin").setValue(360000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==360000 || courent_exp_00_int>360000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-360000);
                        databaseReference.child("gift_send_level").setValue(32);

                    }
                }

                else if (courent_level_00_int == 32)
                {
                    databaseReference.child("gift_send_target_level").setValue(33);
                    databaseReference.child("exp_targate_sendin").setValue(400000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==400000 || courent_exp_00_int>400000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-400000);
                        databaseReference.child("gift_send_level").setValue(33);

                    }
                }
                else if (courent_level_00_int == 33)
                {
                    databaseReference.child("gift_send_target_level").setValue(34);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(34);

                    }
                }

                else if (courent_level_00_int == 34)
                {
                    databaseReference.child("gift_send_target_level").setValue(35);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(35);

                    }
                }

                else if (courent_level_00_int == 35)
                {
                    databaseReference.child("gift_send_target_level").setValue(36);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(36);

                    }
                }

                else if (courent_level_00_int == 36)
                {
                    databaseReference.child("gift_send_target_level").setValue(37);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(37);

                    }
                }

                else if (courent_level_00_int == 37)
                {
                    databaseReference.child("gift_send_target_level").setValue(38);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(38);

                    }
                }

                else if (courent_level_00_int == 38)
                {
                    databaseReference.child("gift_send_target_level").setValue(39);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(39);

                    }
                }
                else if (courent_level_00_int == 39)
                {
                    databaseReference.child("gift_send_target_level").setValue(40);
                    databaseReference.child("exp_targate_sendin").setValue(450000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==450000 || courent_exp_00_int>450000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_send_level").setValue(40);

                    }
                }

                else if (courent_level_00_int == 40)
                {
                    databaseReference.child("gift_send_target_level").setValue(41);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(41);

                    }
                }

                else if (courent_level_00_int == 41)
                {
                    databaseReference.child("gift_send_target_level").setValue(42);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(42);

                    }
                }

                else if (courent_level_00_int == 42)
                {
                    databaseReference.child("gift_send_target_level").setValue(43);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(43);

                    }
                }

                else if (courent_level_00_int == 43)
                {
                    databaseReference.child("gift_send_target_level").setValue(44);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(44);

                    }
                }

                else if (courent_level_00_int == 44)
                {
                    databaseReference.child("gift_send_target_level").setValue(45);
                    databaseReference.child("exp_targate_sendin").setValue(500000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==500000 || courent_exp_00_int>500000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_send_level").setValue(45);

                    }
                }

                else if (courent_level_00_int == 45)
                {
                    databaseReference.child("gift_send_target_level").setValue(46);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(46);

                    }
                }

                else if (courent_level_00_int == 46)
                {
                    databaseReference.child("gift_send_target_level").setValue(47);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(47);

                    }
                }


                else if (courent_level_00_int == 47)
                {
                    databaseReference.child("gift_send_target_level").setValue(48);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(48);

                    }
                }


                else if (courent_level_00_int == 48)
                {
                    databaseReference.child("gift_send_target_level").setValue(49);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(49);

                    }
                }


                else if (courent_level_00_int == 49)
                {
                    databaseReference.child("gift_send_target_level").setValue(50);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(50);

                    }
                }


                else if (courent_level_00_int == 50)
                {
                    databaseReference.child("gift_send_target_level").setValue(51);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(51);

                    }
                }


                else if (courent_level_00_int == 51)
                {
                    databaseReference.child("gift_send_target_level").setValue(52);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(52);

                    }
                }


                else if (courent_level_00_int == 52)
                {
                    databaseReference.child("gift_send_target_level").setValue(53);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(53);

                    }
                }


                else if (courent_level_00_int == 53)
                {
                    databaseReference.child("gift_send_target_level").setValue(54);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(54);

                    }
                }


                else if (courent_level_00_int == 54)
                {
                    databaseReference.child("gift_send_target_level").setValue(55);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(55);

                    }
                }


                else if (courent_level_00_int == 55)
                {
                    databaseReference.child("gift_send_target_level").setValue(56);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(56);

                    }
                }


                else if (courent_level_00_int == 56)
                {
                    databaseReference.child("gift_send_target_level").setValue(57);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(57);

                    }
                }


                else if (courent_level_00_int == 57)
                {
                    databaseReference.child("gift_send_target_level").setValue(58);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(58);

                    }
                }


                else if (courent_level_00_int == 58)
                {
                    databaseReference.child("gift_send_target_level").setValue(59);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(59);

                    }
                }


                else if (courent_level_00_int == 59)
                {
                    databaseReference.child("gift_send_target_level").setValue(60);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(60);

                    }
                }


                else if (courent_level_00_int == 60)
                {
                    databaseReference.child("gift_send_target_level").setValue(60);
                    databaseReference.child("exp_targate_sendin").setValue(550000);
                    progressbar.setProgress(courent_exp_00_int);
                    progressbar.setMax(target_exp_00_int);
                    if (courent_exp_00_int ==550000 || courent_exp_00_int>550000){
                        databaseReference.child("exp_courent_sendin").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_send_level").setValue(60);

                    }
                }


                if(courent_level_receive_00_int == 1)
                {
                    databaseReference.child("gift_received_target_level").setValue(2);
                    databaseReference.child("exp_targate_receive").setValue(2000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 2000 || courent_exp_receive_00_int>2000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-2000);
                        databaseReference.child("gift_received_level").setValue(2);
                    }
                }

                if(courent_level_receive_00_int == 2)
                {
                    databaseReference.child("gift_received_target_level").setValue(3);
                    databaseReference.child("exp_targate_receive").setValue(3000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 3000 || courent_exp_receive_00_int>3000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-3000);
                        databaseReference.child("gift_received_level").setValue(3);
                    }
                }

                if(courent_level_receive_00_int == 3)
                {
                    databaseReference.child("gift_received_target_level").setValue(4);
                    databaseReference.child("exp_targate_receive").setValue(4000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 4000 || courent_exp_receive_00_int>4000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-4000);
                        databaseReference.child("gift_received_level").setValue(4);
                    }
                }

                if(courent_level_receive_00_int == 4)
                {
                    databaseReference.child("gift_received_target_level").setValue(5);
                    databaseReference.child("exp_targate_receive").setValue(5000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 5000 || courent_exp_receive_00_int>5000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-5000);
                        databaseReference.child("gift_received_level").setValue(5);
                    }
                }

                if(courent_level_receive_00_int == 5)
                {
                    databaseReference.child("gift_received_target_level").setValue(6);
                    databaseReference.child("exp_targate_receive").setValue(10000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 10000 || courent_exp_receive_00_int>10000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-10000);
                        databaseReference.child("gift_received_level").setValue(6);
                    }
                }

                if(courent_level_receive_00_int == 6)
                {
                    databaseReference.child("gift_received_target_level").setValue(7);
                    databaseReference.child("exp_targate_receive").setValue(15000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 15000 || courent_exp_receive_00_int>15000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-15000);
                        databaseReference.child("gift_received_level").setValue(7);
                    }
                }

                if(courent_level_receive_00_int == 7)
                {
                    databaseReference.child("gift_received_target_level").setValue(8);
                    databaseReference.child("exp_targate_receive").setValue(20000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 20000 || courent_exp_receive_00_int>20000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-20000);
                        databaseReference.child("gift_received_level").setValue(8);
                    }
                }

                if(courent_level_receive_00_int == 8)
                {
                    databaseReference.child("gift_received_target_level").setValue(9);
                    databaseReference.child("exp_targate_receive").setValue(25000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 25000 || courent_exp_receive_00_int>25000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-25000);
                        databaseReference.child("gift_received_level").setValue(9);
                    }
                }

                if(courent_level_receive_00_int == 9)
                {
                    databaseReference.child("gift_received_target_level").setValue(10);
                    databaseReference.child("exp_targate_receive").setValue(30000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 30000 || courent_exp_receive_00_int>30000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-30000);
                        databaseReference.child("gift_received_level").setValue(10);
                    }
                }

                if(courent_level_receive_00_int == 10)
                {
                    databaseReference.child("gift_received_target_level").setValue(11);
                    databaseReference.child("exp_targate_receive").setValue(35000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 35000 || courent_exp_receive_00_int>35000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-35000);
                        databaseReference.child("gift_received_level").setValue(11);
                    }
                }

                if(courent_level_receive_00_int == 11)
                {
                    databaseReference.child("gift_received_target_level").setValue(12);
                    databaseReference.child("exp_targate_receive").setValue(40000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 40000 || courent_exp_receive_00_int>40000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-40000);
                        databaseReference.child("gift_received_level").setValue(12);
                    }
                }

                if(courent_level_receive_00_int == 12)
                {
                    databaseReference.child("gift_received_target_level").setValue(13);
                    databaseReference.child("exp_targate_receive").setValue(45000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 45000 || courent_exp_receive_00_int>45000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-45000);
                        databaseReference.child("gift_received_level").setValue(13);
                    }
                }

                if(courent_level_receive_00_int == 13)
                {
                    databaseReference.child("gift_received_target_level").setValue(14);
                    databaseReference.child("exp_targate_receive").setValue(50000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 50000 || courent_exp_receive_00_int>50000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-50000);
                        databaseReference.child("gift_received_level").setValue(14);
                    }
                }

                if(courent_level_receive_00_int == 14)
                {
                    databaseReference.child("gift_received_target_level").setValue(15);
                    databaseReference.child("exp_targate_receive").setValue(60000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 60000 || courent_exp_receive_00_int>60000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-60000);
                        databaseReference.child("gift_received_level").setValue(15);
                    }
                }

                if(courent_level_receive_00_int == 15)
                {
                    databaseReference.child("gift_received_target_level").setValue(16);
                    databaseReference.child("exp_targate_receive").setValue(70000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 70000 || courent_exp_receive_00_int>70000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-70000);
                        databaseReference.child("gift_received_level").setValue(16);
                    }
                }

                if(courent_level_receive_00_int == 16)
                {
                    databaseReference.child("gift_received_target_level").setValue(17);
                    databaseReference.child("exp_targate_receive").setValue(80000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 80000 || courent_exp_receive_00_int>80000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-80000);
                        databaseReference.child("gift_received_level").setValue(17);
                    }
                }

                if(courent_level_receive_00_int == 17)
                {
                    databaseReference.child("gift_received_target_level").setValue(18);
                    databaseReference.child("exp_targate_receive").setValue(90000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 90000 || courent_exp_receive_00_int>90000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-90000);
                        databaseReference.child("gift_received_level").setValue(18);
                    }
                }

                if(courent_level_receive_00_int == 18)
                {
                    databaseReference.child("gift_received_target_level").setValue(19);
                    databaseReference.child("exp_targate_receive").setValue(100000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 100000 || courent_exp_receive_00_int>100000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-100000);
                        databaseReference.child("gift_received_level").setValue(19);
                    }
                }

                if(courent_level_receive_00_int == 19)
                {
                    databaseReference.child("gift_received_target_level").setValue(20);
                    databaseReference.child("exp_targate_receive").setValue(120000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 120000 || courent_exp_receive_00_int>120000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-120000);
                        databaseReference.child("gift_received_level").setValue(20);
                    }
                }

                if(courent_level_receive_00_int == 20)
                {
                    databaseReference.child("gift_received_target_level").setValue(21);
                    databaseReference.child("exp_targate_receive").setValue(140000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 140000 || courent_exp_receive_00_int>140000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-140000);
                        databaseReference.child("gift_received_level").setValue(21);
                    }
                }

                if(courent_level_receive_00_int == 21)
                {
                    databaseReference.child("gift_received_target_level").setValue(22);
                    databaseReference.child("exp_targate_receive").setValue(160000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 160000 || courent_exp_receive_00_int>160000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-160000);
                        databaseReference.child("gift_received_level").setValue(22);
                    }
                }

                if(courent_level_receive_00_int == 22)
                {
                    databaseReference.child("gift_received_target_level").setValue(23);
                    databaseReference.child("exp_targate_receive").setValue(180000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 180000 || courent_exp_receive_00_int>180000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-180000);
                        databaseReference.child("gift_received_level").setValue(23);
                    }
                }

                if(courent_level_receive_00_int == 23)
                {
                    databaseReference.child("gift_received_target_level").setValue(24);
                    databaseReference.child("exp_targate_receive").setValue(200000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 200000 || courent_exp_receive_00_int>200000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-200000);
                        databaseReference.child("gift_received_level").setValue(24);
                    }
                }

                if(courent_level_receive_00_int == 24)
                {
                    databaseReference.child("gift_received_target_level").setValue(25);
                    databaseReference.child("exp_targate_receive").setValue(240000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 240000 || courent_exp_receive_00_int>240000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-240000);
                        databaseReference.child("gift_received_level").setValue(25);
                    }
                }

                if(courent_level_receive_00_int == 25)
                {
                    databaseReference.child("gift_received_target_level").setValue(26);
                    databaseReference.child("exp_targate_receive").setValue(260000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 260000 || courent_exp_receive_00_int>260000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-260000);
                        databaseReference.child("gift_received_level").setValue(26);
                    }
                }

                if(courent_level_receive_00_int == 26)
                {
                    databaseReference.child("gift_received_target_level").setValue(27);
                    databaseReference.child("exp_targate_receive").setValue(280000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 280000 || courent_exp_receive_00_int>280000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-280000);
                        databaseReference.child("gift_received_level").setValue(27);
                    }
                }

                if(courent_level_receive_00_int == 27)
                {
                    databaseReference.child("gift_received_target_level").setValue(28);
                    databaseReference.child("exp_targate_receive").setValue(300000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 300000 || courent_exp_receive_00_int>300000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-300000);
                        databaseReference.child("gift_received_level").setValue(28);
                    }
                }

                if(courent_level_receive_00_int == 28)
                {
                    databaseReference.child("gift_received_target_level").setValue(29);
                    databaseReference.child("exp_targate_receive").setValue(300000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 300000 || courent_exp_receive_00_int>300000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-300000);
                        databaseReference.child("gift_received_level").setValue(29);
                    }
                }

                if(courent_level_receive_00_int == 29)
                {
                    databaseReference.child("gift_received_target_level").setValue(30);
                    databaseReference.child("exp_targate_receive").setValue(320000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 320000 || courent_exp_receive_00_int>320000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-320000);
                        databaseReference.child("gift_received_level").setValue(30);
                    }
                }

                if(courent_level_receive_00_int == 30)
                {
                    databaseReference.child("gift_received_target_level").setValue(31);
                    databaseReference.child("exp_targate_receive").setValue(340000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 340000 || courent_exp_receive_00_int>340000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-340000);
                        databaseReference.child("gift_received_level").setValue(31);
                    }
                }

                if(courent_level_receive_00_int == 31)
                {
                    databaseReference.child("gift_received_target_level").setValue(32);
                    databaseReference.child("exp_targate_receive").setValue(360000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 360000 || courent_exp_receive_00_int>360000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-360000);
                        databaseReference.child("gift_received_level").setValue(32);
                    }
                }

                if(courent_level_receive_00_int == 32)
                {
                    databaseReference.child("gift_received_target_level").setValue(33);
                    databaseReference.child("exp_targate_receive").setValue(400000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 400000 || courent_exp_receive_00_int>400000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-400000);
                        databaseReference.child("gift_received_level").setValue(33);
                    }
                }

                if(courent_level_receive_00_int == 33)
                {
                    databaseReference.child("gift_received_target_level").setValue(34);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(34);
                    }
                }

                if(courent_level_receive_00_int == 34)
                {
                    databaseReference.child("gift_received_target_level").setValue(35);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(35);
                    }
                }

                if(courent_level_receive_00_int == 35)
                {
                    databaseReference.child("gift_received_target_level").setValue(36);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(36);
                    }
                }

                if(courent_level_receive_00_int == 36)
                {
                    databaseReference.child("gift_received_target_level").setValue(37);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(37);
                    }
                }

                if(courent_level_receive_00_int == 37)
                {
                    databaseReference.child("gift_received_target_level").setValue(38);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(38);
                    }
                }

                if(courent_level_receive_00_int == 38)
                {
                    databaseReference.child("gift_received_target_level").setValue(39);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(39);
                    }
                }

                if(courent_level_receive_00_int == 39)
                {
                    databaseReference.child("gift_received_target_level").setValue(40);
                    databaseReference.child("exp_targate_receive").setValue(450000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 450000 || courent_exp_receive_00_int>450000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-450000);
                        databaseReference.child("gift_received_level").setValue(40);
                    }
                }

                if(courent_level_receive_00_int == 40)
                {
                    databaseReference.child("gift_received_target_level").setValue(41);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(41);
                    }
                }

                if(courent_level_receive_00_int == 41)
                {
                    databaseReference.child("gift_received_target_level").setValue(42);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(42);
                    }
                }

                if(courent_level_receive_00_int == 42)
                {
                    databaseReference.child("gift_received_target_level").setValue(43);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(43);
                    }
                }

                if(courent_level_receive_00_int == 43)
                {
                    databaseReference.child("gift_received_target_level").setValue(44);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(44);
                    }
                }

                if(courent_level_receive_00_int == 44)
                {
                    databaseReference.child("gift_received_target_level").setValue(45);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(45);
                    }
                }

                if(courent_level_receive_00_int == 45)
                {
                    databaseReference.child("gift_received_target_level").setValue(46);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(46);
                    }
                }

                if(courent_level_receive_00_int == 46)
                {
                    databaseReference.child("gift_received_target_level").setValue(47);
                    databaseReference.child("exp_targate_receive").setValue(500000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 500000 || courent_exp_receive_00_int>500000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-500000);
                        databaseReference.child("gift_received_level").setValue(47);
                    }
                }

                if(courent_level_receive_00_int == 47)
                {
                    databaseReference.child("gift_received_target_level").setValue(48);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(48);
                    }
                }

                if(courent_level_receive_00_int == 48)
                {
                    databaseReference.child("gift_received_target_level").setValue(49);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(49);
                    }
                }

                if(courent_level_receive_00_int == 49)
                {
                    databaseReference.child("gift_received_target_level").setValue(50);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(50);
                    }
                }

                if(courent_level_receive_00_int == 50)
                {
                    databaseReference.child("gift_received_target_level").setValue(51);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(51);
                    }
                }

                if(courent_level_receive_00_int == 51)
                {
                    databaseReference.child("gift_received_target_level").setValue(52);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(52);
                    }
                }

                if(courent_level_receive_00_int == 52)
                {
                    databaseReference.child("gift_received_target_level").setValue(53);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(53);
                    }
                }

                if(courent_level_receive_00_int == 53)
                {
                    databaseReference.child("gift_received_target_level").setValue(54);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(54);
                    }
                }

                if(courent_level_receive_00_int == 54)
                {
                    databaseReference.child("gift_received_target_level").setValue(55);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(55);
                    }
                }

                if(courent_level_receive_00_int == 55)
                {
                    databaseReference.child("gift_received_target_level").setValue(56);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(56);
                    }
                }

                if(courent_level_receive_00_int == 56)
                {
                    databaseReference.child("gift_received_target_level").setValue(57);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(57);
                    }
                }

                if(courent_level_receive_00_int == 57)
                {
                    databaseReference.child("gift_received_target_level").setValue(58);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(58);
                    }
                }

                if(courent_level_receive_00_int == 58)
                {
                    databaseReference.child("gift_received_target_level").setValue(59);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(59);
                    }
                }

                if(courent_level_receive_00_int == 59)
                {
                    databaseReference.child("gift_received_target_level").setValue(60);
                    databaseReference.child("exp_targate_receive").setValue(550000);
                    progressbar_receive.setProgress(courent_exp_receive_00_int);
                    progressbar_receive.setMax(target_exp_receive_00_int);
                    if (courent_exp_receive_00_int == 550000 || courent_exp_receive_00_int>550000){
                        databaseReference.child("exp_courent_receive").setValue(courent_exp_00_int-550000);
                        databaseReference.child("gift_received_level").setValue(60);
                    }
                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        gift_test100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dimond_xp = diamond.getText().toString();
                int dimond_xp_int = Integer.parseInt(dimond_xp);

                String coins_xp = coins.getText().toString();
                int coins_xp_int = Integer.parseInt(coins_xp);

                String courent_exp_str = courent_exp.getText().toString();
                if (dimond_xp_int>100){
                    int courent_exp_str_int = Integer.parseInt(courent_exp_str)+100;
                    int courent_dimond_xp_int = dimond_xp_int-100;

                    int courent_exp_str_int_coin = Integer.parseInt(courent_exp_str)+100;
                    int courent_dimond_xp_int_coin = coins_xp_int+100;


                    databaseReference.child("exp_courent_sendin").setValue(courent_exp_str_int);
                    databaseReference.child("diamond").setValue(courent_dimond_xp_int);

                    databaseReference.child("exp_courent_receive").setValue(courent_exp_str_int_coin);
                    databaseReference.child("coin").setValue(courent_dimond_xp_int_coin);


                }
                else {
                    Toast.makeText(User_lavel_Activity.this, "don't have enough diamond", Toast.LENGTH_SHORT).show();
                }

            }
        });

        gift_test500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dimond_xp = diamond.getText().toString();
                int dimond_xp_int = Integer.parseInt(dimond_xp);

                String coins_xp = coins.getText().toString();
                int coins_xp_int = Integer.parseInt(coins_xp);

                String courent_exp_str = courent_exp.getText().toString();
                if (dimond_xp_int>500){
                    int courent_exp_str_int = Integer.parseInt(courent_exp_str)+500;
                    int courent_dimond_xp_int = dimond_xp_int-500;

                    int courent_exp_str_int_coin = Integer.parseInt(courent_exp_str)+500;
                    int courent_dimond_xp_int_coin = coins_xp_int+500;


                    databaseReference.child("exp_courent_sendin").setValue(courent_exp_str_int);
                    databaseReference.child("diamond").setValue(courent_dimond_xp_int);

                    databaseReference.child("exp_courent_receive").setValue(courent_exp_str_int_coin);
                    databaseReference.child("coin").setValue(courent_dimond_xp_int_coin);


                }
                else {
                    Toast.makeText(User_lavel_Activity.this, "don't have enough diamond", Toast.LENGTH_SHORT).show();
                }

            }
        });

        gift_test1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dimond_xp = diamond.getText().toString();
                int dimond_xp_int = Integer.parseInt(dimond_xp);

                String coins_xp = coins.getText().toString();
                int coins_xp_int = Integer.parseInt(coins_xp);

                String courent_exp_str = courent_exp.getText().toString();
                if (dimond_xp_int>1000){
                    int courent_exp_str_int = Integer.parseInt(courent_exp_str)+1000;
                    int courent_dimond_xp_int = dimond_xp_int-1000;

                    int courent_exp_str_int_coin = Integer.parseInt(courent_exp_str)+1000;
                    int courent_dimond_xp_int_coin = coins_xp_int+1000;


                    databaseReference.child("exp_courent_sendin").setValue(courent_exp_str_int);
                    databaseReference.child("diamond").setValue(courent_dimond_xp_int);

                    databaseReference.child("exp_courent_receive").setValue(courent_exp_str_int_coin);
                    databaseReference.child("coin").setValue(courent_dimond_xp_int_coin);


                }
                else {
                    Toast.makeText(User_lavel_Activity.this, "don't have enough diamond", Toast.LENGTH_SHORT).show();
                }

            }
        });

        gift_test5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dimond_xp = diamond.getText().toString();
                int dimond_xp_int = Integer.parseInt(dimond_xp);

                String coins_xp = coins.getText().toString();
                int coins_xp_int = Integer.parseInt(coins_xp);

                String courent_exp_str = courent_exp.getText().toString();
                if (dimond_xp_int>5000){
                    int courent_exp_str_int = Integer.parseInt(courent_exp_str)+5000;
                    int courent_dimond_xp_int = dimond_xp_int-5000;

                    int courent_exp_str_int_coin = Integer.parseInt(courent_exp_str)+5000;
                    int courent_dimond_xp_int_coin = coins_xp_int+5000;


                    databaseReference.child("exp_courent_sendin").setValue(courent_exp_str_int);
                    databaseReference.child("diamond").setValue(courent_dimond_xp_int);

                    databaseReference.child("exp_courent_receive").setValue(courent_exp_str_int_coin);
                    databaseReference.child("coin").setValue(courent_dimond_xp_int_coin);


                }
                else {
                    Toast.makeText(User_lavel_Activity.this, "don't have enough diamond", Toast.LENGTH_SHORT).show();
                }

            }
        });





    }


}