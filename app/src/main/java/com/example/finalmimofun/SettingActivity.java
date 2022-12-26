package com.example.finalmimofun;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SettingActivity extends AppCompatActivity {

    private Button account,privacy,share,help,feedback,logout;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        account =(Button) findViewById(R.id.account);
        privacy =(Button) findViewById(R.id.privacy);
        share =(Button) findViewById(R.id.share);
        help =(Button) findViewById(R.id.help);
        feedback =(Button) findViewById(R.id.feedback);
        logout =(Button) findViewById(R.id.logout);

        auth = FirebaseAuth.getInstance();

        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,AccountActivity.class));

            }
        });

        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,PrivacyActivity.class));

            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,ShareActivity.class));

            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,HelpActivity.class));

            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SettingActivity.this,FeedbackActivity.class));

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);
                builder.setTitle("Confirm Logout");
                builder.setIcon(R.drawable.exit_icon);
                builder.setMessage("Press Exit for Logout\npress Cancel for stay ");
                builder.setCancelable(false);
                builder.setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        startActivity(new Intent(SettingActivity.this,HomeActivity.class));
                        moveTaskToBack(true);

                        auth.signOut();
                                startActivity(new Intent(SettingActivity.this,MainActivity.class));
                                finish();
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
            //{
//
            //    {
            //        auth.signOut();
            //        startActivity(new Intent(SettingActivity.this,MainActivity.class));
            //        finish();
            //    }
//
            //}
        });

    }
}