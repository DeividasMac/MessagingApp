package com.junglist963.messagingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;


public class ForgetActivity extends AppCompatActivity {

    private TextInputEditText edtTxtForgotPsw;
    private Button btnForgotPsw;

    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);

        auth =FirebaseAuth.getInstance();

        initViews();
        btnForgotPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtTxtForgotPsw.getText().toString();
                if (!email.equals("")){
                    pswReset(email);
                }
            }
        });

    }

    private void initViews() {
        edtTxtForgotPsw =findViewById(R.id.edtTxtForgetPsw);
        btnForgotPsw =findViewById(R.id.btnForgotPsw);
    }
    public void pswReset(String email){
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgetActivity.this, "Please check your email", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ForgetActivity.this, "There was a problem", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}