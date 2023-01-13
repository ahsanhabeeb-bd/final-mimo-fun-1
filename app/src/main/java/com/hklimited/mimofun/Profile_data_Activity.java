package com.hklimited.mimofun;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;


import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;

public class Profile_data_Activity extends AppCompatActivity
{
    private ImageView photo;
    private EditText name;
    private EditText age;
    private Button save_data;
    private RadioGroup gender;
    private RadioButton m_or_fe;


    private FirebaseAuth auth;
    private FirebaseUser user;

    final int min = 10000000;
    final int max = 99999999;
//1669256456
    private StorageReference uploader;


    private Uri filepath;
    private Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_data);

        photo = findViewById(R.id.photo);
        name = findViewById(R.id.name);
        age = findViewById(R.id.age);
        save_data = findViewById(R.id.save_data);
        gender = findViewById(R.id.gender);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();



        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImagePicker.with(Profile_data_Activity.this)
                        .crop()
                        .cropSquare()//Crop image(Optional), Check Customization for more option
                        .compress(1024)			//Final image size will be less than 1 MB(Optional)
                        .maxResultSize(620, 620)	//Final image resolution will be less than 1080 x 1080(Optional)
                        .start(1);
            }
        });


        save_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                if(TextUtils.isEmpty(name.getText().toString()))
                {
                    Toast.makeText(Profile_data_Activity.this, "Name is empty", Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(age.getText().toString()))
                {
                    Toast.makeText(Profile_data_Activity.this, "age is empty", Toast.LENGTH_SHORT).show();

                }
                else if (!(TextUtils.isEmpty(age.getText().toString())))
                {       int age1 = Integer.parseInt(age.getText().toString());

                    if(age1<18){
                        Toast.makeText(Profile_data_Activity.this, "You must be above 18 years of age !!!", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        if (flag==0) {
                            Toast.makeText(Profile_data_Activity.this, "Please upload an image", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            profile_data();
                        }

                    }

                }

            }
        });

    }



int flag=0;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1 && resultCode==RESULT_OK && data!=null)
        {
            filepath=data.getData();
            try {
                InputStream inputStream =getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                photo.setImageBitmap(bitmap);
                flag=1;
            }catch (Exception ex){

            }
        }
    }



    private void profile_data()
    {
        ProgressBar progress = findViewById(R.id.progress);
        progress.setVisibility(View.VISIBLE);
        FirebaseStorage storage;
        storage = FirebaseStorage.getInstance();
        uploader =storage.getReference("Image"+new Random().nextInt(999999999));
        uploader.putFile(filepath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                            {
                                uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri)
                                    {
                                        FirebaseDatabase db = FirebaseDatabase.getInstance();
                                        DatabaseReference root = db.getReference();

                                        int seclected = gender.getCheckedRadioButtonId();
                                        m_or_fe = (RadioButton) findViewById(seclected);
                                        String gen = m_or_fe.getText().toString();
                                        Long lo=System.currentTimeMillis()/100000;
                                        String rannn=String.valueOf(lo);


                                        // make uid

                                        final int random = new Random().nextInt((max - min) + 1) + min;

                                       // HashMap<String,Object> mapflag = new HashMap<>();

                                      //  mapflag.put("uuid",user.getUid());
                                       // root.child("user_flag").child(""+user.getUid()).setValue(mapflag);


                                        // have to make database object
                                        HashMap<String,Object> map = new HashMap<>();

                                        map.put("email",user.getEmail());
                                        map.put("phone",user.getPhoneNumber());
                                        map.put("bio","Here your bio");/// have to change location

                                        map.put("id_number",""+rannn);
                                        map.put("gender",gen);

                                        map.put("uid",""+user.getUid());

                                        map.put("progress_mex",0);
                                        map.put("progress_curant",0);

                                        map.put("progress_mex_receive",0);
                                        map.put("progress_curant_receive",0);


                                        map.put("name",""+name.getText().toString());
                                        map.put("name_color",-9434747);


                                        map.put("age",""+age.getText().toString());

                                        map.put("gift_send_level",1);
                                        map.put("gift_send_target_level",1);

                                        map.put("gift_received_level",1);
                                        map.put("gift_received_target_level",1);

                                        map.put("diamond",0);
                                        map.put("coin",0);

                                        map.put("exp_targate_sendin",0);
                                        map.put("exp_courent_sendin",0);

                                        map.put("exp_targate_receive",0);
                                        map.put("exp_courent_receive",0);

                                        map.put("offer_class1",false);
                                        map.put("official",false);
                                        map.put("agency",false);
                                        map.put("diamond_seller",false);
                                        map.put("host",false);

                                        map.put("rose",0);
                                        map.put("heard",0);
                                        map.put("bike",0);
                                        map.put("car_1",0);
                                        map.put("car_2",0);//5
                                        map.put("car_3",0);
                                        map.put("air_bus",0);
                                        map.put("gift_box_1",0);
                                        map.put("gift_box_2",0);
                                        map.put("gift_box_3",0);//10
                                        map.put("cake_1",0);
                                        map.put("cake_2",0);
                                        map.put("cake_3",0);
                                        map.put("i_love_you_1",0);
                                        map.put("i_love_you_2",0);//15
                                        map.put("i_love_you_3",0);
                                        map.put("kiss_1",0);
                                        map.put("kiss_2",0);
                                        map.put("kiss_3",0);
                                        map.put("carriage",0);//20

                                        map.put("badge1",false);
                                        map.put("badge2",false);
                                        map.put("badge3",false);
                                        map.put("badge4",false);
                                        map.put("badge5",false);
                                        map.put("badge6",false);
                                        map.put("badge7",false);
                                        map.put("badge8",false);
                                        map.put("badge9",false);
                                        map.put("badge10",false);
                                        map.put("badge11",false);
                                        map.put("badge12",false);
                                        map.put("badge13",false);
                                        map.put("badge14",false);
                                        map.put("badge15",false);
                                        map.put("badge16",false);
                                        map.put("badge17",false);
                                        map.put("badge18",false);
                                        map.put("badge19",false);
                                        map.put("badge20",false);
                                        map.put("badge21",false);
                                        map.put("badge22",false);
                                        map.put("badge23",false);
                                        map.put("badge24",false);
                                        map.put("badge25",false);
                                        map.put("badge26",false);
                                        map.put("badge27",false);
                                        map.put("badge28",false);
                                        map.put("badge29",false);
                                        map.put("badge30",false);
                                        map.put("badge31",false);
                                        map.put("badge32",false);
                                        map.put("badge33",false);
                                        map.put("badge34",false);
                                        map.put("badge35",false);
                                        map.put("badge36",false);



                                        map.put("frame1",false);
                                        map.put("frame2",false);
                                        map.put("frame3",false);
                                        map.put("frame4",false);
                                        map.put("frame5",false);
                                        map.put("frame6",false);
                                        map.put("frame7",false);
                                        map.put("frame8",false);
                                        map.put("frame9",false);
                                        map.put("frame10",false);
                                        map.put("frame11",false);
                                        map.put("frame12",false);
                                        map.put("frame13",false);
                                        map.put("frame14",false);
                                        map.put("frame15",false);
                                        map.put("frame16",false);
                                        map.put("frame17",false);
                                        map.put("frame18",false);
                                        map.put("frame19",false);
                                        map.put("frame20",false);
                                        map.put("frame21",false);
                                        map.put("frame22",false);
                                        map.put("frame23",false);
                                        map.put("frame24",false);


                                        map.put("version",3);






                                        map.put("picture",uri.toString());
                                        map.put("picturemain","https://www.google.com/search?q=nature+pictures&rlz=1C1ONGR_enBD1036BD1036&sxsrf=ALiCzsa2pxU9FJuMlBy_J8LJmrfba4dDXA:1672590065195&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjr-sLF46b8AhWgSGwGHegGAfUQ_AUoAXoECAEQAw&biw=1280&bih=569&dpr=1.5#imgrc=Ivq8_BScuZQyDM");
                                        //object finish

                                        root.child("user").child(""+user.getUid()).setValue(map);
                                        progress.setVisibility(View.INVISIBLE);
                                        startActivity(new Intent(Profile_data_Activity.this,HomeActivity.class));
                                        finish();
                                    }
                                });
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot)
                            {

                            }
                        });


    }
    @Override
    public void onBackPressed() {

    }

}