package com.manu.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OfficialActivity extends AppCompatActivity {

    Button addagency,deleteagency;

    private FirebaseAuth auth;
    private FirebaseUser user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);

        addagency = (Button) findViewById(R.id.addagency);
        deleteagency = (Button) findViewById(R.id.deleteagency);

        auth= FirebaseAuth.getInstance();
        user =auth.getCurrentUser();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("user").child(user.getUid());


      //  HashMap<String,Object> map = new HashMap<>();
        addagency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // databaseReference.child("myagency").push().setValue("name");
                openaddagencydayalog();
            }
        });

    }

    private void openaddagencydayalog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(OfficialActivity.this);
        View view = getLayoutInflater().inflate(R.layout.open_add_agency_dayalog,null);
        EditText agencyuid = view.findViewById(R.id.agencyuid);
        Button cancle = view.findViewById(R.id.cancle);
        Button ok = view.findViewById(R.id.ok);

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(false);

        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int agencyuidint = Integer.parseInt(agencyuid.getText().toString());
                database.getReference().child("user").orderByChild("id_number").equalTo(agencyuidint).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        String ans = String.valueOf(snapshot.child("id_number").getValue());
                        if (ans.isEmpty()){
                            Toast.makeText(OfficialActivity.this, "null", Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(OfficialActivity.this, "not null", Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        alertDialog.show();

    }
}