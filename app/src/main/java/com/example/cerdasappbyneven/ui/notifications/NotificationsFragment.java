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
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
        position = root.findViewById(R.id.position);
        subject = root.findViewById(R.id.subject);
        bio = root.findViewById(R.id.bio);
        obligation = root.findViewById(R.id.obligation);
        salary = root.findViewById(R.id.salary);
        classes = root.findViewById(R.id.classes);
        email = root.findViewById(R.id.email);
        hp = root.findViewById(R.id.hp);

        nameedit = root.findViewById(R.id.nameedit);
        positionedit = root.findViewById(R.id.positionedit);
        subjectedit = root.findViewById(R.id.subjectedit);
        bioedit = root.findViewById(R.id.bioedit);
        salaryedit = root.findViewById(R.id.salaryedit);
        hpedit = root.findViewById(R.id.hpedit);

        A = root.findViewById(R.id.checkboxA);
        B = root.findViewById(R.id.checkboxB);
        C = root.findViewById(R.id.checkboxC);
        D = root.findViewById(R.id.checkboxD);

        Bundle bundle = this.getArguments();
        if(getArguments() != null) {
            String fullnamefragment = bundle.getString("fullname");
            String emailfragment = bundle.getString("email");
            Log.d("Fullname IN FRAGMENTTTT", fullnamefragment);
            name.setText(fullnamefragment);
            email.setText(emailfragment);

            position.setVisibility(View.INVISIBLE);
            subject.setVisibility(View.INVISIBLE);
            bio.setVisibility(View.INVISIBLE);
            obligation.setVisibility(View.INVISIBLE);
            salary.setVisibility(View.INVISIBLE);
            classes.setVisibility(View.INVISIBLE);
            hp.setVisibility(View.INVISIBLE);

            positionedit.setVisibility(View.VISIBLE);
            subjectedit.setVisibility(View.VISIBLE);
            bioedit.setVisibility(View.VISIBLE);
            salaryedit.setVisibility(View.VISIBLE);
            hpedit.setVisibility(View.VISIBLE);

            A.setVisibility(View.VISIBLE);
            B.setVisibility(View.VISIBLE);
            C.setVisibility(View.VISIBLE);
            D.setVisibility(View.VISIBLE);


        }


        return root;
    }
}
