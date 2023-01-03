package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
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

public class CoinsActivity extends AppCompatActivity
{
    TextView show_coin;
    TextView show_diamond;

    LinearLayout co100;
    LinearLayout co500;
    LinearLayout co1000;
    LinearLayout co5000;
    LinearLayout co10000;
    LinearLayout co20000;

    String coin;
    String diamond;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coins);

        show_coin =(TextView) findViewById(R.id.show_coin);
        show_diamond =(TextView) findViewById(R.id.show_diamond);

        co100= (LinearLayout)findViewById(R.id.co100);
        co500= (LinearLayout)findViewById(R.id.co500);
        co1000= (LinearLayout)findViewById(R.id.co1000);
        co5000= (LinearLayout)findViewById(R.id.co5000);
        co10000= (LinearLayout)findViewById(R.id.co10000);
        co20000= (LinearLayout)findViewById(R.id.co20000);

        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

       // show_coin.setText("50000");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

      //   String.valueOf(databaseReference.child("coin").setValue(50000));


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                coin = String.valueOf(snapshot.child("coin").getValue());
                diamond = String.valueOf(snapshot.child("diamond").getValue());
                show_coin.setText(coin);
                show_diamond.setText(diamond);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        co100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coco1 = Integer.parseInt(show_coin.getText().toString());
                int dodo1 = Integer.parseInt(show_diamond.getText().toString());
                if (coco1 <100)
                {
                    Toast.makeText(CoinsActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                } else if(coco1>100 || coco1 == 100)
                {
                    int coco2 =coco1-100;
                    int dodo2 = dodo1+30;
                    databaseReference.child("coin").setValue(""+coco2);
                    databaseReference.child("diamond").setValue(""+dodo2);
                }

            }
        });

        co500.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coco1 = Integer.parseInt(show_coin.getText().toString());
                int dodo1 = Integer.parseInt(show_diamond.getText().toString());
                if (coco1 <500)
                {
                    Toast.makeText(CoinsActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                } else if(coco1>500 || coco1 == 500)
                {
                    int coco2 =coco1-500;
                    int dodo2 = dodo1+150;
                    databaseReference.child("coin").setValue(""+coco2);
                    databaseReference.child("diamond").setValue(""+dodo2);
                }

            }
        });

        co1000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coco1 = Integer.parseInt(show_coin.getText().toString());
                int dodo1 = Integer.parseInt(show_diamond.getText().toString());
                if (coco1 <1000)
                {
                    Toast.makeText(CoinsActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                } else if(coco1>1000 || coco1 == 1000)
                {
                    int coco2 =coco1-1000;
                    int dodo2 = dodo1+300;
                    databaseReference.child("coin").setValue(""+coco2);
                    databaseReference.child("diamond").setValue(""+dodo2);
                }

            }
        });

        co5000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coco1 = Integer.parseInt(show_coin.getText().toString());
                int dodo1 = Integer.parseInt(show_diamond.getText().toString());
                if (coco1 <5000)
                {
                    Toast.makeText(CoinsActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                } else if(coco1>5000 || coco1 == 5000)
                {
                    int coco2 =coco1-5000;
                    int dodo2 = dodo1+1500;
                    databaseReference.child("coin").setValue(""+coco2);
                    databaseReference.child("diamond").setValue(""+dodo2);
                }

            }
        });

        co10000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coco1 = Integer.parseInt(show_coin.getText().toString());
                int dodo1 = Integer.parseInt(show_diamond.getText().toString());
                if (coco1 <10000)
                {
                    Toast.makeText(CoinsActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                } else if(coco1>10000 || coco1 == 10000)
                {
                    int coco2 =coco1-10000;
                    int dodo2 = dodo1+3000;
                    databaseReference.child("coin").setValue(""+coco2);
                    databaseReference.child("diamond").setValue(""+dodo2);
                }

            }
        });

        co20000.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int coco1 = Integer.parseInt(show_coin.getText().toString());
                int dodo1 = Integer.parseInt(show_diamond.getText().toString());
                if (coco1 <20000)
                {
                    Toast.makeText(CoinsActivity.this, "wrong", Toast.LENGTH_SHORT).show();
                } else if(coco1>20000 || coco1 == 20000)
                {
                    int coco2 =coco1-20000;
                    int dodo2 = dodo1+6000;
                    databaseReference.child("coin").setValue(""+coco2);
                    databaseReference.child("diamond").setValue(""+dodo2);
                }

            }
        });

    }
}