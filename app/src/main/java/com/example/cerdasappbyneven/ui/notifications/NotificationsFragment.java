package com.example.cerdasappbyneven.ui.notifications;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cerdasappbyneven.R;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView name, position, subject, bio, obligation, salary, classes, email, hp;
    EditText nameedit, positionedit, subjectedit, bioedit, salaryedit, hpedit;
    CheckBox A, B, C, D;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
//        final TextView textView = root.findViewById(R.id.text_notifications);
        notificationsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });

        name = root.findViewById(R.id.name);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            String fullnamefragment = bundle.getString("fullname");
            Log.d("Fullname IN FRAGMENTTTTT", fullnamefragment);
            name.setText(fullnamefragment);
        }


        return root;
    }
}
