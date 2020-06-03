package com.example.cerdasappbyneven.ui.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cerdasappbyneven.EditProfileActivity;
import com.example.cerdasappbyneven.EmployeeDetailActivity;
import com.example.cerdasappbyneven.HomeActivity;
import com.example.cerdasappbyneven.R;
import com.example.cerdasappbyneven.ui.notifications.NotificationsFragment;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button addemployeebtn, sortbtn;
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

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();

        if(getActivity().getSupportFragmentManager().findFragmentByTag("AdditionalFragment") != null) {
            getActivity().getSupportFragmentManager().beginTransaction().remove(getActivity().getSupportFragmentManager().findFragmentByTag("AdditionalFragment")).commit();
        }

        addemployeebtn = root.findViewById(R.id.addemployeebtn);
        sortbtn = root.findViewById(R.id.sortbtn);

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

        listViewEmployee.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Employees employee = employeesList.get(i);
//                showDialog(employee.getEmployeeId(), employee.getName());
                Intent intent = new Intent(getActivity(), EmployeeDetailActivity.class);
                intent.putExtra("id", employee.getEmployeeId());
                startActivity(intent);
            }
        });

        sortbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.reverse(employeesList);
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

                if (getActivity()!=null) {
                    EmployeeList adapter = new EmployeeList(getActivity(), employeesList);
                    listViewEmployee.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

//    private void showDialog(String employeeId, String name)
//    {
//        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity()); //Context??
//        LayoutInflater inflater = getLayoutInflater();
//        final View dialogView = inflater.inflate(R.layout.edit_employee_dialog, null);
//
//        dialogBuilder.setView(dialogView);
//
//        final TextView tvname = dialogView.findViewById(R.id.name);
//        final TextView position = dialogView.findViewById(R.id.position);
//        final TextView subject = dialogView.findViewById(R.id.subject);
//        final TextView bio = dialogView.findViewById(R.id.bio);
//        final TextView obligation = dialogView.findViewById(R.id.obligation);
//        final TextView salary = dialogView.findViewById(R.id.salary);
//        final TextView classes = dialogView.findViewById(R.id.classes);
//        final TextView email = dialogView.findViewById(R.id.email);
//        final TextView hp = dialogView.findViewById(R.id.hp);
//        final Button btnupdateemployee = dialogView.findViewById(R.id.editemployeebtn);
//
//        dialogBuilder.setTitle("Employee Detail"+employeeId);
//        AlertDialog alertDialog = dialogBuilder.create();
//        alertDialog.show();
//
//    }

}
