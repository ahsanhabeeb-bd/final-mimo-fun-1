package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Delete_account_Activity extends AppCompatActivity {

    private EditText password;
    private Button deleteac1;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_account);

        password= (EditText)findViewById(R.id.password);
        deleteac1= (Button) findViewById(R.id.deleteac1);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        deleteac1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete();
            }
        });
    }

    private void delete() {
        if(password.getText().toString().isEmpty()){
            Toast.makeText(this, "password is Empty", Toast.LENGTH_SHORT).show();
        }else {
            AuthCredential credential = EmailAuthProvider
                    .getCredential(user.getEmail(), password.getText().toString());


            user.reauthenticate(credential)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                user.delete()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful()){

                                                    Toast.makeText(Delete_account_Activity.this, "Good Bye", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(Delete_account_Activity.this,MainActivity.class));
                                                }

                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(Delete_account_Activity.this, ""+e, Toast.LENGTH_SHORT).show();


                                            }
                                        });
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(Delete_account_Activity.this, ""+e, Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }
}