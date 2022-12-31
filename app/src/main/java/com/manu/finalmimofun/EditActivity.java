package com.manu.finalmimofun;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class EditActivity extends AppCompatActivity
{

    private EditText edit_name;
    private EditText edit_age;
    private EditText edit_bio;

    private ImageView photo_round1;
    private ImageView photomain1;
    private ImageView edit_round_photo;
    private ImageView edit_main_photo;

    private Button save_data;

    private Uri filepath;
    private Bitmap bitmap;
    private StorageReference uploader;


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

        photo_round1= (ImageView) findViewById(R.id.photo_round1) ;
        photomain1= (ImageView) findViewById(R.id.photomain1) ;
        edit_round_photo= (ImageView) findViewById(R.id.edit_round_photo) ;
        edit_main_photo= (ImageView) findViewById(R.id.edit_main_photo) ;

     //   int age = Integer.parseInt(edit_age.getText().toString());



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

                String picture= String.valueOf(snapshot.child("picture").getValue());
                String photomain = String.valueOf(snapshot.child("picturemain").getValue());

                edit_name.setText(name1);
                edit_age.setText(age1);
                edit_bio.setText(bio1);

                Picasso.get().load(picture).into(photo_round1);
                Picasso.get().load(photomain).into(photomain1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        edit_round_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditActivity.this)
                        .crop()//Crop image(Optional), Check Customization for more option
                        .cropSquare()
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(620, 620)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);
            }
        });

        edit_main_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(EditActivity.this)
                        .crop()//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(620, 620)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(2);
            }
        });


        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String name2 = String.valueOf(edit_name.getText());
               String age2 = String.valueOf(edit_age.getText());
               String bio2 = String.valueOf(edit_bio.getText());

                int age = Integer.parseInt(age2);
                    if(age<18){
                        Toast.makeText(EditActivity.this, "You must be above 18 years of age !!!", Toast.LENGTH_SHORT).show();
                    }else if(age>17){
                        databaseReference.child("age").setValue(age2);
                        databaseReference.child("name").setValue(name2);
                        //
                        databaseReference.child("bio").setValue(bio2);
                        Toast.makeText(EditActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditActivity.this,Profile_main_image_Activity.class));
                    }


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
             filepath=data.getData();

            FirebaseStorage storage;
            storage = FirebaseStorage.getInstance();
            uploader =storage.getReference("Image"+new Random().nextInt(999999999));
            uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            databaseReference.child("picture").setValue(uri.toString());

                        }
                    });

                }
            });

        }

        if(requestCode==2 && resultCode==RESULT_OK && data!=null)
        {
            filepath=data.getData();

            FirebaseStorage storage;
            storage = FirebaseStorage.getInstance();
            uploader =storage.getReference("Image"+new Random().nextInt(999999999));
            uploader.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            databaseReference.child("picturemain").setValue(uri.toString());

                        }
                    });

                }
            });

        }
    }


    @Override
    public void onBackPressed() {
        startActivity(new Intent(EditActivity.this,Profile_main_image_Activity.class));
        super.onBackPressed();
    }

}