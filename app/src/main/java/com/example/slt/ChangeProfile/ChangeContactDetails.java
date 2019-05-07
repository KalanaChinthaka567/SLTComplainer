package com.example.slt.ChangeProfile;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.slt.MainScreens.AddData;
import com.example.slt.MainScreens.History;
import com.example.slt.MainScreens.Profile;
import com.example.slt.MainScreens.Usage;
import com.example.slt.R;

public class ChangeContactDetails extends AppCompatActivity implements
        Usage.OnFragmentInteractionListener,
        AddData.OnFragmentInteractionListener,
        History.OnFragmentInteractionListener,
        Profile.OnFragmentInteractionListener{
    ActionBar actionBar;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener1
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()) {
                case R.id.navigation_usage:
                    fragment = new Usage();
                    loadFragment(fragment);
                    actionBar.setTitle("Usage Summery");
                    return true;
                case R.id.navigation_add_data:
                    fragment = new AddData();
                    loadFragment(fragment);
                    actionBar.setTitle("Add More Data");
                    return true;
                case R.id.navigation_history:
                    fragment = new History();
                    loadFragment(fragment);
                    actionBar.setTitle("Purchase History");
                    return true;
                case R.id.navigation_profile:
                    fragment = new Profile();
                    loadFragment(fragment);
                    actionBar.setTitle("Profile");
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Usage Summery");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation1);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener1);
    }

    private void loadFragment(Fragment fragment) {
        // load fragment
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container_edit_contact, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                FragmentManager fm = getSupportFragmentManager();
                if (fm.getBackStackEntryCount() > 0) {
                    fm.popBackStackImmediate();
                    actionBar.setTitle("Usage Summery");
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
