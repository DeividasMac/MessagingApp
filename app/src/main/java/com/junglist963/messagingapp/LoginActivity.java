package com.junglist963.messagingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText edtTxtEmail, edtTxtPsw;
    private Button btnSignIn, btnSignUp;
    private TextView txtForgotPsw;

    FirebaseAuth auth;
    FirebaseUser firebaseUser;

    @Override
    protected void onStart() {
        super.onStart();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        auth = FirebaseAuth.getInstance();

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtTxtEmail.getText().toString();
                String psw = edtTxtPsw.getText().toString();

                if(!email.equals("") && !psw.equals("")){
                    signIn(email, psw);
                }else{
                    Toast.makeText(LoginActivity.this, "Please enter an email and password", Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(intent);

            }
        });
        txtForgotPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgetActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPsw = findViewById(R.id.edtTxtPsw);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnSignUp = findViewById(R.id.btnSignUp);
        txtForgotPsw = findViewById(R.id.txtForgotPsw);
    }
    public void signIn(String email, String psw){
        auth.signInWithEmailAndPassword(email, psw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    Toast.makeText(LoginActivity.this, "Sign In is successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginActivity.this, "Sign In is not successful", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}