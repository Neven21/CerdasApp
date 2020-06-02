package com.example.cerdasappbyneven.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.cerdasappbyneven.MainActivity;
import com.example.cerdasappbyneven.R;

public class AddEmployeeActivity extends AppCompatActivity {

    private Spinner spin;
    EditText name, email, number, subject, salary, bio;
    Button cancelbtn, uploadbtn, addbtn;
    CheckBox A, B, C, D;
    String nametest, emailtest, numbertest, subjecttest, salarytest, biotest, position;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("employees");

        spin = (Spinner) findViewById(R.id.positions);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.hp);
        subject = findViewById(R.id.subject);
        salary = findViewById(R.id.salary);
        bio = findViewById(R.id.bio);
        cancelbtn = findViewById(R.id.cancelbtn);
        uploadbtn = findViewById(R.id.uploadbtn);
        addbtn = findViewById(R.id.addbtn);

        A = findViewById(R.id.checkboxA);
        B = findViewById(R.id.checkboxB);
        C = findViewById(R.id.checkboxC);
        D = findViewById(R.id.checkboxD);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nametest = name.getText().toString();
                emailtest = email.getText().toString();
                numbertest = number.getText().toString();
                subjecttest = subject.getText().toString();
                salarytest = salary.getText().toString();
                biotest = bio.getText().toString();

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

//                spin.setOnItemSelectedListener(new CustomOnItemSelectedListener());
                String classestest = classes.toString();
                position = spin.getSelectedItem().toString();

                if(nametest.isEmpty() || emailtest.isEmpty() || numbertest.isEmpty() || subjecttest.isEmpty() || salarytest.isEmpty() || biotest.isEmpty() || position.isEmpty() || classestest.isEmpty())
                {
                    Toast.makeText(AddEmployeeActivity.this,"All Fields Have To Be Filled",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    String id = myRef.push().getKey();
                    Employees employee  = new Employees(id, nametest, emailtest, numbertest, position, subjecttest, classestest, salarytest, biotest);
                    myRef.child(id).setValue(employee);
                    Toast.makeText(AddEmployeeActivity.this,"Employee Added",Toast.LENGTH_SHORT).show();
                    AddEmployeeActivity.this.finish();
                }
            }
        });
    }
}
