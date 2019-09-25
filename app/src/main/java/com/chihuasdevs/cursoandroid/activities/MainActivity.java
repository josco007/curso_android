package com.chihuasdevs.cursoandroid.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.chihuasdevs.cursoandroid.R;
import com.chihuasdevs.cursoandroid.activities.manager.ActivitiesManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ActivitiesManager.openMenuAC(this);

    }
}
