package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class MainActivity extends AppCompatActivity
{

    private Button sign_phone;
    private Button sign_mail;

    private TextView pilicy;
    private TextView or;


    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sign_phone =findViewById(R.id.sign_phone);
        sign_mail =findViewById(R.id.sign_mail);



        pilicy  =findViewById(R.id.pilicy);
        or  =findViewById(R.id.or);


        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();

/*
 Calendar calendar = Calendar.getInstance();
        String current = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        String current1 = DateFormat.getDateInstance().format(calendar.getTime());

 */


        if(user != null){
           // startActivity(new Intent(MainActivity.this,HomeActivity.class));
            sign_phone.setVisibility(View.GONE);
            sign_mail.setVisibility(View.GONE);
            or.setVisibility(View.GONE);
            pilicy.setVisibility(View.GONE);


            databaseReference.child("user").child(user.getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if(task.isSuccessful()){
                        DataSnapshot snapshot = task.getResult();
                        if(snapshot.exists()){
                            sign_phone.setVisibility(View.GONE);
                            sign_mail.setVisibility(View.GONE);
                            or.setVisibility(View.GONE);
                            pilicy.setVisibility(View.GONE);
                            databaseReference.child("user").child(user.getUid()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {

                                    if(!(snapshot.child("version").exists())){

                                        databaseReference.child("user").child(user.getUid()).child("version").setValue(3);


                                        databaseReference.child("user").child(user.getUid()).child("frame1").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame2").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame3").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame4").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame5").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame6").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame7").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame8").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame9").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame10").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame11").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame12").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame13").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame14").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame15").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame16").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame17").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame18").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame19").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame20").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame21").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame22").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame23").setValue(false);
                                        databaseReference.child("user").child(user.getUid()).child("frame24").setValue(false);
                                      //  Toast.makeText(MainActivity.this, "nai", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                            startActivity(new Intent(MainActivity.this,HomeActivity.class));
                        }else {

                            sign_phone.setVisibility(View.GONE);
                            sign_mail.setVisibility(View.GONE);
                            or.setVisibility(View.GONE);
                            pilicy.setVisibility(View.GONE);
                            startActivity(new Intent(MainActivity.this,Profile_data_Activity.class));

                        }
                    }
                }
            });



          //  FirebaseDatabase.getInstance().getReference().child("user").addValueEventListener(new ValueEventListener() {
          //      @Override
          //      public void onDataChange(@NonNull DataSnapshot snapshot) {
          //          String loginvalu = String.valueOf(snapshot.getValue());
          //          String version = (String) snapshot.child("version").getValue();
//
          //          if (snapshot.child(user.getUid()).exists()){
//
//
////
          //            //  if(!(snapshot.child("version").exists())){
          //            //      Toast.makeText(MainActivity.this, "not", Toast.LENGTH_SHORT).show();
          //            //     // databaseReference.child("version").setValue(3);
          //            //      FirebaseDatabase.getInstance().getReference().child("user").child(user.getUid()).child("version").setValue(3);
////
          //            //  }
//
          //              startActivity(new Intent(MainActivity.this,HomeActivity.class));
//
          //          }else if(!(snapshot.child(user.getUid()).exists())){
          //              startActivity(new Intent(MainActivity.this,Profile_data_Activity.class));
//
//
          //          }
          //      }
//
          //      @Override
          //      public void onCancelled(@NonNull DatabaseError error) {
//
          //      }
          //  });


            ////
        //    DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        //    DatabaseReference devicesRef = db.child("user_flag");
        //    DatabaseReference deviceOneRef = devicesRef.child(""+user.getUid());
        //   // DatabaseReference keyRef = deviceOneRef.child("-NJLkBkt96yj6gg0EC1n");
        //    devicesRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
        //        @Override
        //        public void onComplete(@NonNull Task<DataSnapshot> task) {
        //            if (task.isSuccessful()) {
        //                DataSnapshot snapshot = task.getResult();
        //                if(snapshot.exists()) {
        //                    sign_phone.setVisibility(View.GONE);
        //                    sign_mail.setVisibility(View.GONE);
        //                    or.setVisibility(View.GONE);
//
        //                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
        //                } else {
        //                    sign_phone.setVisibility(View.GONE);
        //                    sign_mail.setVisibility(View.GONE);
        //                    or.setVisibility(View.GONE);
//
        //                    startActivity(new Intent(MainActivity.this,Profile_data_Activity.class));
        //                }
        //            } else {
        //                Log.d("TAG", task.getException().getMessage()); //Never ignore potential errors!
        //            }
        //        }
        //    });
          //  databaseReference.child("user_flag")
          //      .addValueEventListener(new ValueEventListener() {
          //          @Override
          //          public void onDataChange(@NonNull DataSnapshot snapshot) {
          //              String picture= String.valueOf(snapshot.child("uuid").getValue());
          //              Toast.makeText(MainActivity.this, ""+picture, Toast.LENGTH_SHORT).show();
          //              String checking=""+user.getUid();
          //              if (picture.equals(checking)) {
//
          //                  sign_phone.setVisibility(View.VISIBLE);
          //                  sign_mail.setVisibility(View.VISIBLE);
          //                  or.setVisibility(View.VISIBLE);
//
          //                  startActivity(new Intent(MainActivity.this,HomeActivity.class));
          //              }
          //              else {
          //                  sign_phone.setVisibility(View.GONE);
          //                  sign_mail.setVisibility(View.GONE);
          //                  or.setVisibility(View.GONE);
//
          //                  startActivity(new Intent(MainActivity.this,Profile_data_Activity.class));
          //              }
          //          }
//
          //          @Override
          //          public void onCancelled(@NonNull DatabaseError error) {
//
          //          }
          //      });
//



        }




        sign_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Log_phone_Activity.class));

            }
        });

        sign_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Login_mail_Activity.class));
            }
        });

        pilicy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,PolicyActivity.class));

            }
        });





    }


}