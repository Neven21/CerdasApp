package com.example.cerdasappbyneven;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    EditText email, password, fullname;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth auth;
    String emailtest, passtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        fullname = findViewById(R.id.fullname);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.signupbtn);
        tvSignIn = findViewById(R.id.signinbtn);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailtest = email.getText().toString();
                passtest = password.getText().toString();
                if (emailtest.isEmpty()) {
                    email.setError("Please Enter Email");
                    email.requestFocus();
                } else if (passtest.isEmpty()) {
                    password.setError("Please Enter Password");
                    password.requestFocus();
                } else if (emailtest.isEmpty() && passtest.isEmpty()) {
                    Toast.makeText(RegisterActivity.this, "Fields Can Not Be Empty", Toast.LENGTH_SHORT).show();
                } else if (!(emailtest.isEmpty() && passtest.isEmpty())) {
                    auth.createUserWithEmailAndPassword(emailtest, passtest).addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(RegisterActivity.this, "SignUp Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(RegisterActivity.this, MainActivity.class)); //HARUS DIGANTIIIIIIIIIIIIIII
                            }
                        }
                    });
                } else {
                    Toast.makeText(RegisterActivity.this, "Error Occurred!", Toast.LENGTH_SHORT).show();
                }
            }
            });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                RegisterActivity.this.finish();
            }
        });

    }
}
