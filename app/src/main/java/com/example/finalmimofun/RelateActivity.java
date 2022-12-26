package com.example.finalmimofun;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RelateActivity extends AppCompatActivity
{
    public TextView related_id1;
    private TextView hot_id1;
    private View related_id_view1;
    private View hot_id_view1;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relate);

        related_id1= findViewById(R.id.related_id1);
        hot_id1= findViewById(R.id.hot_id1);
        related_id_view1= findViewById(R.id.related_id_view1);
        hot_id_view1= findViewById(R.id.hot_id_view1);



        related_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                related_id1.setTextSize(22);
                related_id_view1.setVisibility(View.VISIBLE);
                hot_id1.setTextSize(20);
                hot_id_view1.setVisibility(View.INVISIBLE);


            }
        });

        hot_id1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hot_id1.setTextSize(22);
                hot_id_view1.setVisibility(View.VISIBLE);
                related_id1.setTextSize(20);
                related_id_view1.setVisibility(View.INVISIBLE);
                startActivity(new Intent(RelateActivity.this,HomeActivity.class));
                overridePendingTransition(R.anim.slid_in_right,R.anim.slid_out_left);
                hot_id1.setTextSize(20);
                hot_id_view1.setVisibility(View.INVISIBLE);
                related_id1.setTextSize(22);
                related_id_view1.setVisibility(View.VISIBLE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(RelateActivity.this,HomeActivity.class));
        super.onBackPressed();
    }
}