package com.example.finalmimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.annotations.NotNull;

public class Varifi_otp_Activity extends AppCompatActivity {

    private TextView tvMobile,tvResendBtn;
    private EditText etC1,etC2,etC3,etC4,etC5,etC6;
    private Button btnVerify;
    private ProgressBar progressBarVerify;

    private String verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_varifi_otp);

        tvMobile = findViewById(R.id.tvMobile);
        etC1 = findViewById(R.id.etC1);
        etC2 = findViewById(R.id.etC2);
        etC3 = findViewById(R.id.etC3);
        etC4 = findViewById(R.id.etC4);
        etC5 = findViewById(R.id.etC5);
        etC6 = findViewById(R.id.etC6);
        tvResendBtn = findViewById(R.id.tvResendBtn);
        btnVerify = findViewById(R.id.btnVerify);
        progressBarVerify = findViewById(R.id.progressBarVerify);

        editTextInput();

        tvMobile.setText(String.format(
                "+88-%s", getIntent().getStringExtra("phone")
        ));

        verificationId = getIntent().getStringExtra("verificationId");

        tvResendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Varifi_otp_Activity.this, "OTP Send Successfully.", Toast.LENGTH_SHORT).show();
            }
        });

        btnVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBarVerify.setVisibility(View.VISIBLE);
                btnVerify.setVisibility(View.INVISIBLE);
                if (etC1.getText().toString().trim().isEmpty() ||
                        etC2.getText().toString().trim().isEmpty() ||
                        etC3.getText().toString().trim().isEmpty() ||
                        etC4.getText().toString().trim().isEmpty() ||
                        etC5.getText().toString().trim().isEmpty() ||
                        etC6.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Varifi_otp_Activity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                } else {
                    if (verificationId != null) {
                        String code = etC1.getText().toString().trim() +
                                etC2.getText().toString().trim() +
                                etC3.getText().toString().trim() +
                                etC4.getText().toString().trim() +
                                etC5.getText().toString().trim() +
                                etC6.getText().toString().trim();

                        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
                        FirebaseAuth
                                .getInstance()
                                .signInWithCredential(credential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            progressBarVerify.setVisibility(View.VISIBLE);
                                            btnVerify.setVisibility(View.INVISIBLE);
                                            Toast.makeText(Varifi_otp_Activity.this, "Welcome...", Toast.LENGTH_SHORT).show();
                                            progressBarVerify.setVisibility(View.INVISIBLE);

                                        } else {
                                            progressBarVerify.setVisibility(View.GONE);
                                            btnVerify.setVisibility(View.VISIBLE);
                                            Toast.makeText(Varifi_otp_Activity.this, "OTP is not Valid!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            }
        });


    }

    private void editTextInput() {
        etC1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC2.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC3.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        etC3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC5.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        etC5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etC6.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }


}