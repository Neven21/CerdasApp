package com.example.cerdasappbyneven.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cerdasappbyneven.AboutActivity;
import com.example.cerdasappbyneven.HomeActivity;
import com.example.cerdasappbyneven.PreferenceData;
import com.example.cerdasappbyneven.R;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    Switch darkmode;
    Button aboutbtn, signoutbtn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
//        final TextView textView = root.findViewById(R.id.text_dashboard);
        dashboardViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        darkmode = root.findViewById(R.id.darkmodeswitch);
        aboutbtn = root.findViewById(R.id.aboutbutton);
        signoutbtn = root.findViewById(R.id.logoutbtn);

        darkmode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                    startActivity(new Intent(getActivity(), HomeActivity.class));
//                    getActivity().finish();
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                    startActivity(new Intent(getActivity(), HomeActivity.class));
//                    getActivity().finish();
                }
            }
        });

        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AboutActivity.class);
                startActivity(intent);
            }
        });

        signoutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PreferenceData.clearLoggedInEmailAddress(getActivity());
                getActivity().finish();
            }
        });

        return root;
    }
}
