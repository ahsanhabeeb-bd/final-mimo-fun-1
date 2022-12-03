package com.example.finalmimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PrivacyActivity extends AppCompatActivity {

    private Button deleteac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);

        deleteac = (Button) findViewById(R.id.deleteac);
        deleteac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrivacyActivity.this,Delete_account_Activity.class));
            }
        });
    }
}