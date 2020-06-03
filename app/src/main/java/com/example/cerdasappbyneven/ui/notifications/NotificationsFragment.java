package com.example.cerdasappbyneven.ui.notifications;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.cerdasappbyneven.EditProfileActivity;
import com.example.cerdasappbyneven.HomeActivity;
import com.example.cerdasappbyneven.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel notificationsViewModel;
    TextView name, position, subject, bio, obligation, salary, classes, email, hp;
    Button editbtn;
//    NotificationsFragment.OnDataPass dataPasser;

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

        editbtn = root.findViewById(R.id.editprofilebtn);
        name = root.findViewById(R.id.name);
        position = root.findViewById(R.id.position);
        subject = root.findViewById(R.id.subject);
        bio = root.findViewById(R.id.bio);
        obligation = root.findViewById(R.id.obligation);
        salary = root.findViewById(R.id.salary);
        classes = root.findViewById(R.id.classes);
        email = root.findViewById(R.id.email);
        hp = root.findViewById(R.id.hp);

        HomeActivity activity = (HomeActivity) getActivity();
        final String emailfragmenttest = activity.getMyData();

        Log.d("Email TESTT in fragment", emailfragmenttest);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("employees");

        reference.orderByChild("email").equalTo(emailfragmenttest).addListenerForSingleValueEvent(new ValueEventListener() {
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


        Bundle bundle = this.getArguments();
        if(getArguments() != null) {
            String emailfragment = bundle.getString("email");
            Log.d("Email in fragment", emailfragment);

        }

        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                intent.putExtra("email", emailfragmenttest);
                startActivity(intent);
            }
        });

        return root;
    }

//    public interface OnDataPass {
//        public void onDataPass(String data);
//    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        dataPasser = (NotificationsFragment.OnDataPass) context;
//    }
}
