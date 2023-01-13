package com.hklimited.mimofun;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseException;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;

public class Log_phone_Activity extends AppCompatActivity {

    private ImageView imageView;
    private TextView textView,textView2,etPhone;

    private CountryCodePicker textView3;
    private Button btnSend;
    private ProgressBar progressBar;

    private FirebaseAppCheck firebaseAppCheck;



    private FirebaseAuth mAuth;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_phone);

        imageView= findViewById(R.id.imageView);
        textView= findViewById(R.id.textView);
        textView2= findViewById(R.id.textView2);
        textView3= findViewById(R.id.textView3);
        etPhone= findViewById(R.id.etPhone);
        btnSend= findViewById(R.id.btnSend);
        progressBar= findViewById(R.id.progressBar);


        FirebaseApp.initializeApp(Log_phone_Activity.this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());FirebaseApp.initializeApp(Log_phone_Activity.this);
        firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(
                PlayIntegrityAppCheckProviderFactory.getInstance());

        mAuth = FirebaseAuth.getInstance();



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Log_phone_Activity.this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();

                } else {
                    otpSend();
                }
            }
        });
    }




      private void otpSend() {
        progressBar.setVisibility(View.VISIBLE);
        btnSend.setVisibility(View.INVISIBLE);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                progressBar.setVisibility(View.GONE);
                btnSend.setVisibility(View.VISIBLE);
                Toast.makeText(Log_phone_Activity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(@NonNull String verificationId,
                                   @NonNull PhoneAuthProvider.ForceResendingToken token) {
                progressBar.setVisibility(View.GONE);
                btnSend.setVisibility(View.VISIBLE);
                String etPhone1= textView3.getSelectedCountryCodeWithPlus() + etPhone.getText().toString();
                Toast.makeText(Log_phone_Activity.this, "OTP is successfully send.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Log_phone_Activity.this, Varifi_otp_Activity.class);
                intent.putExtra("phone", etPhone1);
                intent.putExtra("verificationId", verificationId);
                intent.putExtra("country",textView3.getSelectedCountryName());
                startActivity(intent);
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(textView3.getSelectedCountryCodeWithPlus() + etPhone.getText().toString().trim())
                        .setTimeout(60L, TimeUnit.SECONDS)
                        .setActivity(this)
                        .setCallbacks(mCallbacks)
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }
}