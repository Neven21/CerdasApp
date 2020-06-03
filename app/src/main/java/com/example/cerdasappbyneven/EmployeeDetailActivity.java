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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class EmployeeDetailActivity extends AppCompatActivity {

    String id;
    TextView name, position, subject, bio, obligation, salary, classes, email, hp;
    Button editemployeebtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        Intent intent  = getIntent();
        id = intent.getStringExtra("id");

        Log.d("ID in detail", id);

        editemployeebtn = findViewById(R.id.editemployeebtn);
        name = findViewById(R.id.name);
        position = findViewById(R.id.position);
        subject = findViewById(R.id.subject);
        bio = findViewById(R.id.bio);
        obligation = findViewById(R.id.obligation);
        salary = findViewById(R.id.salary);
        classes = findViewById(R.id.classes);
        email = findViewById(R.id.email);
        hp = findViewById(R.id.hp);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("employees");

        reference.orderByChild("employeeId").equalTo(id).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datas: dataSnapshot.getChildren()){
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

                    name.setVisibility(View.GONE);

                    if(posdata.equals("Coordinator and Lecturer"))
                    {
                        obligation.setText("UMN");
                    }
                    else if(posdata.equals("Lecturer"))
                    {
                        obligation.setText("Coordinator and Lecturer");
                    }
                    else if(posdata.equals("Head of Lab"))
                    {
                        obligation.setText("UMN");
                    }
                    else if(posdata.equals("Lab Coordinator"))
                    {
                        obligation.setText("Head of Lab");
                    }
                    else if(posdata.equals("Lab Assistant"))
                    {
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

            }
        });
    }
}
