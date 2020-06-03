package com.example.cerdasappbyneven;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

public class EditProfileActivity extends AppCompatActivity {

    String email, id;
    EditText nameedit, positionedit, subjectedit, bioedit, salaryedit, hpedit;
    TextView tvemail;
    CheckBox A, B, C, D;
    Button savebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        Intent intent  = getIntent();
        email = intent.getStringExtra("email");

        tvemail = findViewById(R.id.email);
        tvemail.setText(email);

        nameedit = findViewById(R.id.name);
        positionedit = findViewById(R.id.position);
        subjectedit = findViewById(R.id.subject);
        bioedit = findViewById(R.id.bio);
        salaryedit = findViewById(R.id.salary);
        hpedit = findViewById(R.id.hp);
        savebtn = findViewById(R.id.saveprofilebtn);
        A = findViewById(R.id.checkboxA);
        B = findViewById(R.id.checkboxB);
        C = findViewById(R.id.checkboxC);
        D = findViewById(R.id.checkboxD);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("employees");

        reference.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
                    String namedata = datas.child("name").getValue().toString();
                    String posdata = datas.child("position").getValue().toString();
                    String subdata = datas.child("subject").getValue().toString();
                    String biodata = datas.child("bio").getValue().toString();
                    String salarydata = datas.child("salary").getValue().toString();
                    String hpdata = datas.child("number").getValue().toString();
                    id = datas.child("employeeId").getValue().toString();

                    nameedit.setText(namedata);
                    positionedit.setText(posdata);
                    subjectedit.setText(subdata);
                    bioedit.setText(biodata);
                    salaryedit.setText(salarydata);
                    hpedit.setText(hpdata);

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference updatereference = FirebaseDatabase.getInstance().getReference("employees").child(id);

                String nametest = nameedit.getText().toString();
                String postest = positionedit.getText().toString();
                String subjecttest = subjectedit.getText().toString();
                String biotest = bioedit.getText().toString();
                String salarytest = salaryedit.getText().toString();
                String hptest = hpedit.getText().toString();


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
                    Toast.makeText(EditProfileActivity.this,"All Fields Have To Be Filled",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Employees employee  = new Employees(id, nametest, email, hptest, postest, subjecttest, classestest, salarytest, biotest);
                    updatereference.setValue(employee);
                    Toast.makeText(EditProfileActivity.this,"Edit Successfull",Toast.LENGTH_SHORT).show();
                    EditProfileActivity.this.finish();
                }
            }
        });
    }
}
