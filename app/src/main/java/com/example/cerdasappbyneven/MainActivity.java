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
import com.google.firebase.auth.FirebaseUser;

import java.sql.Ref;

public class MainActivity extends AppCompatActivity {

    EditText email, password;
    Button btnSignIn;
    TextView tvSignUp;
    FirebaseAuth auth;
    String emailtest, passtest;
    private FirebaseAuth.AuthStateListener authListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.signinbtn);
        tvSignUp = findViewById(R.id.signupbtn);
        auth = FirebaseAuth.getInstance();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = auth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(MainActivity.this,"You are logged in",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    MainActivity.this.finish();
                }
                else{
                    Toast.makeText(MainActivity.this,"Please Login",Toast.LENGTH_SHORT).show();
                }
            }
        };

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                emailtest = email.getText().toString();
                passtest = password.getText().toString();
                if(emailtest.isEmpty()){
                    email.setError("Please Enter Email");
                    email.requestFocus();
                }
                else  if(passtest.isEmpty()){
                    password.setError("Please Enter Password");
                    password.requestFocus();
                }
                else  if(emailtest.isEmpty() && passtest.isEmpty()){
                    Toast.makeText(MainActivity.this,"Fields Can Not Be Empty",Toast.LENGTH_SHORT).show();
                }
                else  if(!(emailtest.isEmpty() && passtest.isEmpty())){
                    auth.signInWithEmailAndPassword(emailtest, passtest).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(MainActivity.this,"Login Error, Please Login Again",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Intent login = new Intent(MainActivity.this, HomeActivity.class);
                                login.putExtra("email", emailtest);
                                startActivity(login);
                                MainActivity.this.finish();
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(MainActivity.this,"Error Occurred!",Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                MainActivity.this.finish();
            }
        });
    }
}
