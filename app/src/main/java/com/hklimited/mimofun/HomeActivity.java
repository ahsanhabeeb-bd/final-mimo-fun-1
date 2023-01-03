package com.hklimited.mimofun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity
{


    private TextView related_id;
    private TextView hot_id;
    private View related_id_view;
    private View hot_id_view;
    private ImageView scarse_id;
    private ImageView live_id;



    private ImageView home1;
    private ImageView game1;
    private ImageView chat1;
    private ImageView profile1;


    //for image slider
    private ImageSlider slider;


    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);



        related_id= findViewById(R.id.related_id);
        hot_id= findViewById(R.id.hot_id);
        related_id_view= findViewById(R.id.related_id_view);
        hot_id_view= findViewById(R.id.hot_id_view);
        scarse_id= findViewById(R.id.scarse_id);
        live_id= findViewById(R.id.live_id);



        home1 = findViewById(R.id.home1);
        game1 = findViewById(R.id.game1);
        chat1 = findViewById(R.id.chat1);
        profile1 = findViewById(R.id.profile1);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());


     //   Image slider start
        slider = findViewById(R.id.slider);


        ArrayList<SlideModel> slideModels = new ArrayList<>();


        slideModels.add(new SlideModel(R.drawable.image_slider_1,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_slider_2,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_slider_3,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_slider_4,ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image_slider_5,ScaleTypes.FIT));



        slider.setImageList(slideModels,ScaleTypes.FIT);




        //slider End


        related_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                related_id.setTextSize(22);
                related_id_view.setVisibility(View.VISIBLE);
                hot_id.setTextSize(20);
                hot_id_view.setVisibility(View.INVISIBLE);
                startActivity(new Intent(HomeActivity.this,RelateActivity.class));
                overridePendingTransition(R.anim.slide_in_left,R.anim.slid_out_right);
                related_id.setTextSize(20);
                related_id_view.setVisibility(View.INVISIBLE);
                hot_id.setTextSize(22);
                hot_id_view.setVisibility(View.VISIBLE);
            }
        });

        hot_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hot_id.setTextSize(22);
                hot_id_view.setVisibility(View.VISIBLE);
                related_id.setTextSize(20);
                related_id_view.setVisibility(View.INVISIBLE);

            }
        });

        scarse_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ScarceActivity.class));
            }
        });

        live_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(HomeActivity.this,LiveActivity.class));
            }
        });

        home1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
            }
        });

        game1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startActivity(new Intent(HomeActivity.this,GameActivity.class));
           }
       });

        chat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ChatActivity.class));
            }
        });

        profile1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
            }
        });







    }

    private void golink(String s) {

        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("Confirm Exit");
        builder.setIcon(R.drawable.exit_icon);
        builder.setMessage("Press Exit to EXIT\npress Cancel to stay ");
        builder.setCancelable(false);
        builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(HomeActivity.this,HomeActivity.class));
                moveTaskToBack(true);

                android.os.Process.killProcess(android.os.Process.myPid());

                System.exit(1);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();

            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();


    }


}