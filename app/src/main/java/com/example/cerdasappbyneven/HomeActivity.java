package com.example.cerdasappbyneven;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.cerdasappbyneven.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class HomeActivity extends AppCompatActivity {

//    NotificationsFragment.OnDataPass dataPasser;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        NotificationsFragment profilefragment = new NotificationsFragment();

        Intent intent  = getIntent();
        email = intent.getStringExtra("email");

        Bundle bundle = new Bundle();
        bundle.putString("email",email);
        profilefragment.setArguments(bundle);

//        t.add(R.id.nav_host_fragment,profilefragment,"AdditionalFragment");
//        t.addToBackStack(null);
//        t.commit();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

    }

//    @Override
//    public void onDataPass(String data) {
//        Log.d("LOG","hello " + data);
//    }

    public String getMyData() {
        return email;
    }

}
