package com.example.finalmimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(GameActivity.this,HomeActivity.class));
        super.onBackPressed();
    }
}