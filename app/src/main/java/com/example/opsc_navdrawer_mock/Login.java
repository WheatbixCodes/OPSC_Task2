package com.example.opsc_navdrawer_mock;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.opsc_navdrawer_mock.ui.home.HomeFragment;
import com.example.opsc_navdrawer_mock.ui.home.HomeViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    //variables
    private Button login;
    private Button register;
    private EditText email;
    private EditText password;

FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegister2);
        email = findViewById(R.id.txtEmail);
        password = findViewById(R.id.txtPassword);

        mAuth = FirebaseAuth.getInstance();


/*
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.ActivityLogin, new HomeFragment()).commit();

            }
        });


           getSupportFragmentManager().beginTransaction().add(android.R.id.content, new HomeFragment()).commit();


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegister();
            }
        });
*/



    }

    public void HomeFragment()
    {

    }

    public void openMain(View view)
    {
        Intent intent = new Intent(Login.this, HomeFragment.class);
        startActivity(intent);
    }

    public void openRegister(View view)
    {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

    public void loginButton(View view)
    {

        try {
            //getting email and password
            String Email = email.getText().toString().trim();
            String Pass = password.getText().toString().trim();

            if (TextUtils.isEmpty(Email))
            {
                Toast.makeText(this, "Please enter your email to login", Toast.LENGTH_SHORT).show();
                email.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(Pass))
            {
                Toast.makeText(this, "Please enter your password to login", Toast.LENGTH_SHORT).show();
                password.requestFocus();
                return;
            }

            mAuth.signInWithEmailAndPassword(Email, Pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful())
                    {
                        Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                        Intent i = new Intent (Login.this, MainActivity.class);
                        //startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(Login.this, "Login Unsuccessful, please check your credentials", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }   catch(Exception ex)
        {
            Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT).show();
        }

    }



}