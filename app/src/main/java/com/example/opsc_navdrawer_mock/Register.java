package com.example.opsc_navdrawer_mock;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.internal.TextScale;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    private Button Return;
    private Button register;
    private EditText txtEmail;
    private EditText txtPassword;
    private EditText txtPasswordConfirm;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Return = findViewById(R.id.btnReturn);
        register = findViewById(R.id.btnReg);
        txtEmail = findViewById(R.id.txtEmailReg);
        txtPassword = findViewById(R.id.txtPasswordReg);
        txtPasswordConfirm = findViewById(R.id.txtConfirmPass);
        mAuth = FirebaseAuth.getInstance();


        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLogin();
            }
        });

    }
    public void openLogin()
    {
        Intent intent = new Intent(Register.this, Login.class);
        startActivity(intent);
    }

    public void registerOnClick(View view)
    {
        if (view.getId() == R.id.btnReg){
            String email = txtEmail.getText().toString().trim();
            String password = txtPassword.getText().toString().trim();
            String passConfirm = txtPasswordConfirm.getText().toString().trim();

            if (TextUtils.isEmpty(email))
            {
                Toast.makeText(this, "Please Enter in an Email Address", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password))
            {
                Toast.makeText(this, "Please enter in a password", Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(passConfirm))
            {
                Toast.makeText(this, "Please enter in a confirm password", Toast.LENGTH_SHORT).show();
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {
                        Toast.makeText(Register.this, "Register Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Register.this, Login.class);
                        startActivity(intent);
                    } else
                    {
                        Toast.makeText(Register.this, "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }



    }



}