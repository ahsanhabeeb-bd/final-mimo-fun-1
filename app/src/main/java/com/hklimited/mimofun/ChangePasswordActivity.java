package com.hklimited.mimofun;

import static com.hklimited.mimofun.R.id.submit;

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

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText password,newpassword,password_re;

    private Button submit;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        password= (EditText) findViewById(R.id.password);
        newpassword= (EditText) findViewById(R.id.newpassword);
        password_re= (EditText) findViewById(R.id.password_re);

        submit = (Button) findViewById(R.id.submit);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changePassword();
            }
        });


    }

    private void changePassword() {
        if(password.getText().toString().isEmpty() || newpassword.getText().toString().isEmpty() || password_re.getText().toString().isEmpty()){
            Toast.makeText(this, "Empty file", Toast.LENGTH_SHORT).show();

        }else if(!newpassword.getText().toString().equals(password_re.getText().toString()) ){
            Toast.makeText(this, "Re-Password not matching", Toast.LENGTH_SHORT).show();
        }else if(newpassword.getText().toString().equals(password_re.getText().toString()) ){

            if(user != null && user.getEmail().toString()!=null){
                AuthCredential credential = EmailAuthProvider
                        .getCredential(user.getEmail(),password.getText().toString() );

// Prompt the user to re-provide their sign-in credentials
                user.reauthenticate(credential)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    user.updatePassword(newpassword.getText().toString())
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        Toast.makeText(ChangePasswordActivity.this, "Password Reset", Toast.LENGTH_SHORT).show();
                                                        startActivity(new Intent(ChangePasswordActivity.this,AccountActivity.class));

                                                    }
                                                }
                                            }).addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Toast.makeText(ChangePasswordActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ChangePasswordActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                            }
                        });


            }else {
                startActivity(new Intent(ChangePasswordActivity.this,AccountActivity.class));
            }

        }
    }
}