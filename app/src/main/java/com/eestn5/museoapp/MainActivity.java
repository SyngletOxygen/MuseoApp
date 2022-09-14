package com.eestn5.museoapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    public static final String SCORE = "com.example.holamundo.SCORE";

    private boolean LockTimer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

 
        setContentView(R.layout.activity_main);

    }

}
