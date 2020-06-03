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

import com.example.cerdasappbyneven.ui.home.Employees;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class EmployeeDetailActivity extends AppCompatActivity {

    String id;
    Boolean flagdetail = true;
    TextView name, position, subject, bio, obligation, salary, classes, email, hp;
    EditText nameedit, positionedit, subjectedit, bioedit, salaryedit;
    CheckBox A, B, C, D;
    Button editemployeebtn, savechangesbtn, deletebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        Intent intent  = getIntent();
        id = intent.getStringExtra("id");

        Log.d("ID in detail", id);

        editemployeebtn = findViewById(R.id.editemployeebtn);
        savechangesbtn = findViewById(R.id.saveeditemployee);
        deletebtn = findViewById(R.id.deleteemployee);
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
        A = findViewById(R.id.checkboxA);
        B = findViewById(R.id.checkboxB);
        C = findViewById(R.id.checkboxC);
        D = findViewById(R.id.checkboxD);

            nameedit.setVisibility(View.GONE);
            positionedit.setVisibility(View.GONE);
            subjectedit.setVisibility(View.GONE);
            bioedit.setVisibility(View.GONE);
            ;
            salaryedit.setVisibility(View.GONE);
            A.setVisibility(View.GONE);
            B.setVisibility(View.GONE);
            C.setVisibility(View.GONE);
            D.setVisibility(View.GONE);
            savechangesbtn.setVisibility(View.GONE);
            savechangesbtn.setClickable(false);

            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("employees");

            reference.orderByChild("employeeId").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                        String namedata = datas.child("name").getValue().toString();
                        String posdata = datas.child("position").getValue().toString();
                        String subdata = datas.child("subject").getValue().toString();
                        String biodata = datas.child("bio").getValue().toString();
                        String salarydata = datas.child("salary").getValue().toString();
                        String classesdata = datas.child("classes").getValue().toString();
                        String emaildata = datas.child("email").getValue().toString();
                        String hpdata = datas.child("number").getValue().toString();

                        name.setText(namedata);
                        position.setText(posdata);
                        subject.setText(subdata);
                        bio.setText(biodata);
                        salary.setText(salarydata);
                        classes.setText(classesdata);
                        email.setText(emaildata);
                        hp.setText(hpdata);

                        nameedit.setText(namedata);
                        positionedit.setText(posdata);
                        subjectedit.setText(subdata);
                        bioedit.setText(biodata);
                        salaryedit.setText(salarydata);

                        if (posdata.equals("Coordinator and Lecturer")) {
                            obligation.setText("UMN");
                        } else if (posdata.equals("Lecturer")) {
                            obligation.setText("Coordinator and Lecturer");
                        } else if (posdata.equals("Head of Lab")) {
                            obligation.setText("UMN");
                        } else if (posdata.equals("Lab Coordinator")) {
                            obligation.setText("Head of Lab");
                        } else if (posdata.equals("Lab Assistant")) {
                            obligation.setText("Lab Coordinator");
                        }
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });


        editemployeebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameedit.setVisibility(View.VISIBLE);
                positionedit.setVisibility(View.VISIBLE);
                subjectedit.setVisibility(View.VISIBLE);
                bioedit.setVisibility(View.VISIBLE);
                salaryedit.setVisibility(View.VISIBLE);
                A.setVisibility(View.VISIBLE);
                B.setVisibility(View.VISIBLE);
                C.setVisibility(View.VISIBLE);
                D.setVisibility(View.VISIBLE);
                savechangesbtn.setVisibility(View.VISIBLE);
                savechangesbtn.setClickable(true);

                name.setVisibility(View.GONE);
                position.setVisibility(View.GONE);
                subject.setVisibility(View.GONE);
                bio.setVisibility(View.GONE);
                salary.setVisibility(View.GONE);
                classes.setVisibility(View.GONE);
                editemployeebtn.setVisibility(View.GONE);
                editemployeebtn.setClickable(false);
                deletebtn.setVisibility(View.GONE);
                deletebtn.setClickable(false);

            }
        });

        savechangesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference updatereference = FirebaseDatabase.getInstance().getReference("employees").child(id);

                String nametest = nameedit.getText().toString();
                String postest = positionedit.getText().toString();
                String subjecttest = subjectedit.getText().toString();
                String biotest = bioedit.getText().toString();
                String salarytest = salaryedit.getText().toString();
                String hptest = hp.getText().toString();
                String emailtest = email.getText().toString();

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
                    Toast.makeText(EmployeeDetailActivity.this,"All Fields Have To Be Filled",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Employees employee  = new Employees(id, nametest, emailtest, hptest, postest, subjecttest, classestest, salarytest, biotest);
                    updatereference.setValue(employee);
                    Toast.makeText(EmployeeDetailActivity.this,"Edit Successfull",Toast.LENGTH_SHORT).show();
                    EmployeeDetailActivity.this.finish();
                }
            }
        });

        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference deleteref = FirebaseDatabase.getInstance().getReference("employees").child(id);
                deleteref.removeValue();
                Toast.makeText(EmployeeDetailActivity.this,"Delete Successfull",Toast.LENGTH_SHORT).show();
                EmployeeDetailActivity.this.finish();
            }
        });
    }
}
