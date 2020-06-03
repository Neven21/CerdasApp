package com.example.cerdasappbyneven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cerdasappbyneven.ui.home.AddEmployeeActivity;
import com.example.cerdasappbyneven.ui.home.Employees;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InitialEditActivity extends AppCompatActivity {

    Button addprofilebtn;
    TextView name, position, subject, bio, obligation, salary, classes, email, hp;
    EditText nameedit, positionedit, subjectedit, bioedit, salaryedit, hpedit;
    CheckBox A, B, C, D;
    FirebaseDatabase database;
    DatabaseReference myRef;
    String nametest, postest, subjecttest, salarytest, biotest, hptest, emailtest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial_edit);

        Intent intent  = getIntent();
        String fullnamefromintent = intent.getStringExtra("fullname");
        final String emailfromintent = intent.getStringExtra("email");

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("employees");

        addprofilebtn = findViewById(R.id.editprofilebtn);
        name = findViewById(R.id.name);
        position = findViewById(R.id.position);
        subject = findViewById(R.id.subject);
        bio = findViewById(R.id.bio);
        obligation = findViewById(R.id.obligation);
        salary = findViewById(R.id.salary);
        classes = findViewById(R.id.classes);
        email = findViewById(R.id.email);
        hp = findViewById(R.id.hp);

        nameedit = findViewById(R.id.nameedit);
        positionedit = findViewById(R.id.positionedit);
        subjectedit = findViewById(R.id.subjectedit);
        bioedit = findViewById(R.id.bioedit);
        salaryedit = findViewById(R.id.salaryedit);
        hpedit = findViewById(R.id.hpedit);

        A = findViewById(R.id.checkboxA);
        B = findViewById(R.id.checkboxB);
        C = findViewById(R.id.checkboxC);
        D = findViewById(R.id.checkboxD);

        nameedit.setText(fullnamefromintent);
        email.setText(emailfromintent);

        addprofilebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametest = nameedit.getText().toString();
                postest = positionedit.getText().toString();
                subjecttest = subjectedit.getText().toString();
                biotest = bioedit.getText().toString();
                salarytest = salaryedit.getText().toString();
                hptest = hpedit.getText().toString();
                emailtest = email.getText().toString();


                StringBuilder classes = new StringBuilder();
                if(A.isChecked())
                {
                    classes.append("A and AL");
                }
                if(B.isChecked())
                {
                    classes.append(", B and BL");
                }
                if(C.isChecked())
                {
                    classes.append(", C and CL");
                }
                if(D.isChecked())
                {
                    classes.append(", D and DL");
                }

                String classestest = classes.toString();

                if(nametest.isEmpty() || postest.isEmpty() || subjecttest.isEmpty() || biotest.isEmpty() || salarytest.isEmpty() || hptest.isEmpty() || classestest.isEmpty())
                {
                    Toast.makeText(InitialEditActivity.this,"All Fields Have To Be Filled",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String id = myRef.push().getKey();
                    Employees employee  = new Employees(id, nametest, emailtest, hptest, postest, subjecttest, classestest, salarytest, biotest);
                    myRef.child(id).setValue(employee);
                    Toast.makeText(InitialEditActivity.this,"Your Profile Is Added",Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(InitialEditActivity.this, HomeActivity.class);
                    intent.putExtra("email", emailtest);
                    startActivity(intent);
                    InitialEditActivity.this.finish();
                }
            }
        });
    }
}
