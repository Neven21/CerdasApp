package com.example.cerdasappbyneven.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cerdasappbyneven.R;

public class AddEmployeeActivity extends AppCompatActivity {

    private Spinner spin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);

        spin = (Spinner) findViewById(R.id.positions);
        spin.setOnItemSelectedListener(new CustomOnItemSelectedListener());

    }
}
