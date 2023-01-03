package com.hklimited.mimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DiamondsellarActivity extends AppCompatActivity {

    private Button send_diamond_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diamondsellar);

        send_diamond_button = findViewById(R.id.send_diamond_button);

        send_diamond_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DiamondsellarActivity.this,Send_diamondActivity.class));
            }
        });


    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(DiamondsellarActivity.this,StreamerActivity.class));
        super.onBackPressed();
    }
}