package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Diamond_send_filal_Activity extends AppCompatActivity {

    private CircleImageView img1;
    private TextView name,idnumber,diamond,mydiamond;
    private EditText senddiamond;
    private Button send_diamond_button;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamond_send_filal);

        img1= findViewById(R.id.img1);
        name= findViewById(R.id.name);
        idnumber= findViewById(R.id.idnumber);
        diamond= findViewById(R.id.diamond);
        mydiamond= findViewById(R.id.mydiamond);
        senddiamond= findViewById(R.id.senddiamond);
        send_diamond_button= findViewById(R.id.send_diamond_button);


        String uid = getIntent().getExtras().getString("uid");

        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();

        databaseReference = database.getReference().child("user").child(uid);
        databaseReference1 = database.getReference().child("user").child(user.getUid()); //my uid

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String picture= String.valueOf(snapshot.child("picture").getValue());
                String name1 = String.valueOf(snapshot.child("name").getValue());
                String id_number = String.valueOf(snapshot.child("id_number").getValue());//////////////
                String diamond1 = String.valueOf(snapshot.child("diamond").getValue());


                Picasso.get().load(picture).into(img1);

                name.setText("Name:"+name1);
                idnumber.setText("ID:"+id_number);
                diamond.setText(diamond1);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String diamond2 = String.valueOf(snapshot.child("diamond").getValue());

                mydiamond.setText(diamond2);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        send_diamond_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                send_diamond_mathode();
            }
        });

    }

    private void send_diamond_mathode()
    {
        if(senddiamond.getText().toString().isEmpty()){
            Toast.makeText(this, "Empty Diamond Send ", Toast.LENGTH_SHORT).show();
        }else {
            int mydiamond_int= Integer.parseInt(mydiamond.getText().toString());
            int senddiamond_int = Integer.parseInt(senddiamond.getText().toString());
            int clint_diamond_int = Integer.parseInt(diamond.getText().toString());


            if (mydiamond_int>senddiamond_int || mydiamond_int==senddiamond_int){
                int mydiamond_have_int =mydiamond_int - senddiamond_int;
                databaseReference1.child("diamond").setValue(mydiamond_have_int);

                int now_clint_diamond_int = clint_diamond_int + senddiamond_int;
                databaseReference.child("diamond").setValue(now_clint_diamond_int);

                Toast.makeText(this, "Sent "+senddiamond_int+" Diamond is \n Successful", Toast.LENGTH_SHORT).show();
                senddiamond.setText("");
                startActivity(new Intent(Diamond_send_filal_Activity.this,DiamondsellarActivity.class));

            }else if(mydiamond_int<senddiamond_int){
                Toast.makeText(this, "Don't have enough Diamond", Toast.LENGTH_SHORT).show();
            }

        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Diamond_send_filal_Activity.this,Send_diamondActivity.class));
        super.onBackPressed();
    }
}