package com.example.cerdasappbyneven.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cerdasappbyneven.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class EmployeeList extends ArrayAdapter<Employees> {

//    private Fragment context;
    private Activity context;
    private List<Employees> employeesList;

    public EmployeeList(Activity context, List<Employees> employeesList){

        super(context, R.layout.list_layout_employee, employeesList);
        this.context = context;
        this.employeesList = employeesList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View item = inflater.inflate(R.layout.list_layout_employee, null, true);

        TextView tvname = item.findViewById(R.id.name);
        TextView tvposition = item.findViewById(R.id.position);

        Employees employee = employeesList.get(position);

        tvname.setText(employee.getName());
        tvposition.setText(employee.getPosition());

        return item;

    }
}
