package com.eestn5.museoapp;



import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;


public class MainActivity extends AppCompatActivity {


    private boolean LockTimer = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_menuprincipal);
       // if (!is there any handicap defined){
        //   run through first time setup
        //
        // }
    }

    public void OnClick(View button){
        Intent intent = new Intent(this, SeleccionDeDiscapacidadActivity.class);
        //intent.putExtra(  );
        startActivity(intent);


    }


    //EXAMPLE FOR SOUND
    /* WebView mauro = this.findViewById(R.id.Maurotest);
        mauro.getSettings().setJavaScriptEnabled(true);
        mauro.getSettings().setLoadWithOverviewMode(true);
        mauro.getSettings().setUseWideViewPort(true);
        mauro.getSettings().setPluginState(WebSettings.PluginState.ON);
        mauro.loadUrl("https://www.google.com");

        */



}
