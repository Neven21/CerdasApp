package com.example.cerdasappbyneven.ui.home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.example.cerdasappbyneven.MainActivity;
import com.example.cerdasappbyneven.R;

public class AddEmployeeActivity extends AppCompatActivity {

    private Spinner spin;
    private ImageView imageView;
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

        imageView = findViewById(R.id.my_avatar);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        number = findViewById(R.id.hp);
        subject = findViewById(R.id.subject);
        salary = findViewById(R.id.salary);
        bio = findViewById(R.id.bio);
        cancelbtn = findViewById(R.id.cancelbtn);
        uploadbtn = findViewById(R.id.uploadbtn);
        addbtn = findViewById(R.id.addbtn);
        cancelbtn = findViewById(R.id.cancelbtn);

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

        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEmployeeActivity.this.finish();
            }
        });

        uploadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(AddEmployeeActivity.this);
            }
        });
    }

    private void selectImage(Context context) {
        final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == RESULT_OK && data != null) {
                        Bitmap selectedImage = (Bitmap) data.getExtras().get("data");
                        imageView.setImageBitmap(selectedImage);
                    }

                    break;
                case 1:
                    if (resultCode == RESULT_OK && data != null) {
                        Uri selectedImage =  data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getContentResolver().query(selectedImage,
                                    filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }
}
