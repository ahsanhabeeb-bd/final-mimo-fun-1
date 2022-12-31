package com.manu.finalmimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(ChatActivity.this,HomeActivity.class));
        super.onBackPressed();
    }
}