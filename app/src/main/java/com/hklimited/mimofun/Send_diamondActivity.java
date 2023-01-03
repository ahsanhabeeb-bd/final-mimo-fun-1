package com.hklimited.mimofun;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Send_diamondActivity extends AppCompatActivity {

    private SearchView searce;
    //for recialview start
    RecyclerView recyclerView;
    diamondselladapter mainAdapter;
    //for recialview end



    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_diamond);

        searce = (SearchView) findViewById(R.id.searce);
        searce.clearFocus();

        searce.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                prosess(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                prosess(s);
                return false;
            }
        });

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        recyclerView =(RecyclerView) findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("user"), MainModel.class)
                        .build();
        mainAdapter = new diamondselladapter(options);
        recyclerView.setAdapter(mainAdapter);

      //  mainAdapter=new MainAdapter(options);
      //  recyclerView.setAdapter(mainAdapter);


    }

    //relative start
    @Override
    protected void onStart() {
        super.onStart();
        mainAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mainAdapter.stopListening();
    }
    //relative end

    private void prosess(String s) {

        FirebaseRecyclerOptions<MainModel> options =
                new FirebaseRecyclerOptions.Builder<MainModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("user").orderByChild("id_number").startAt(s).endAt(s+"\uf8ff"), MainModel.class)
                        .build();

        mainAdapter=new diamondselladapter(options);
        mainAdapter.startListening();
        recyclerView.setAdapter(mainAdapter);

    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(Send_diamondActivity.this,DiamondsellarActivity.class));
        super.onBackPressed();
    }
}