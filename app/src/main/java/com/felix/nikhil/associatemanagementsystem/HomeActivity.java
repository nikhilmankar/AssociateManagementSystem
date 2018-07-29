package com.felix.nikhil.associatemanagementsystem;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.felix.nikhil.associatemanagementsystem.Fragments.AddAssociateFragment;
import com.felix.nikhil.associatemanagementsystem.Fragments.UpdateAssociateFragment;
import com.felix.nikhil.associatemanagementsystem.Fragments.ViewAssociatesFragment;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Add:
                    //mTextMessage.setText(R.string.title_home);
                    changeFragment(new AddAssociateFragment());
                    return true;
                case R.id.navigation_Update:
                    //mTextMessage.setText(R.string.title_dashboard);
                    changeFragment(new UpdateAssociateFragment());
                    return true;
                case R.id.navigation_View:
                    //mTextMessage.setText(R.string.title_notifications);
                    changeFragment(new ViewAssociatesFragment());
                    return true;
            }
            return false;
        }
    };

    public void changeFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.parent, fragment);
        fragmentTransaction.commit();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        changeFragment(new AddAssociateFragment());
    }


}
