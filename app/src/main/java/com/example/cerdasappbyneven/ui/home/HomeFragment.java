package com.example.cerdasappbyneven.ui.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cerdasappbyneven.HomeActivity;
import com.example.cerdasappbyneven.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button addemployeebtn;
    FirebaseDatabase database;
    DatabaseReference myRef;
    ListView listViewEmployee;
    List<Employees> employeesList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
//        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        Fragment fragment = getFragmentManager().findFragmentByTag("AdditionalFragment");
        getFragmentManager().beginTransaction().remove(fragment).commit(); //error tp dh dapet null object ref kalo gaada if, brt dah msk fragmentnya???

        addemployeebtn = root.findViewById(R.id.addemployeebtn);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("employees");

        listViewEmployee = root.findViewById(R.id.listViewEmployee);

        employeesList = new ArrayList<>();

        addemployeebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AddEmployeeActivity.class);
                startActivity(intent);
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                employeesList.clear();

                for (DataSnapshot employeeSnapshot: dataSnapshot.getChildren()){
                    Employees employee = employeeSnapshot.getValue(Employees.class);
                    employeesList.add(employee);
                }

                EmployeeList adapter = new EmployeeList(getActivity(), employeesList);
                listViewEmployee.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
