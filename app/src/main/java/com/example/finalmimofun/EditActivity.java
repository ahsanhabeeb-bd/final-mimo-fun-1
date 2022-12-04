package com.example.finalmimofun;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditActivity extends AppCompatActivity
{

    private EditText edit_name;
    private EditText edit_age;
    private EditText edit_bio;
    private Button save_data;


    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_age = (EditText) findViewById(R.id.edit_age);
        edit_bio = (EditText) findViewById(R.id.edit_bio);
        save_data = (Button) findViewById(R.id.save_data);






        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String name1 = String.valueOf(snapshot.child("name").getValue());
                String age1 = String.valueOf(snapshot.child("age").getValue());
                String bio1 = String.valueOf(snapshot.child("bio").getValue());

                edit_name.setText(name1);
                edit_age.setText(age1);
                edit_bio.setText(bio1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {



            }
        });

        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String name2 = String.valueOf(edit_name.getText());
               String age2 = String.valueOf(edit_age.getText());
               String bio2 = String.valueOf(edit_bio.getText());

                databaseReference.child("name").setValue(name2);
                databaseReference.child("age").setValue(age2);
                databaseReference.child("bio").setValue(bio2);
                Toast.makeText(EditActivity.this, "Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(EditActivity.this,Profile_main_image_Activity.class));
            }
        });

    }

}