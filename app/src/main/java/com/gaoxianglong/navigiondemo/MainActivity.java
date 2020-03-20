package com.gaoxianglong.navigiondemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private NavController mController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mController = Navigation.findNavController(this,R.id.nav_host);
        NavigationUI.setupActionBarWithNavController(this, mController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return mController.navigateUp();
    }
}
