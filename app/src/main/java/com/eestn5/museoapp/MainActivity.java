package com.eestn5.museoapp;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.eestn5.museoapp.HandlersSQL;
import com.eestn5.museoapp.BuildConfig;
import com.eestn5.museoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private HandlersSQL conexion=new HandlersSQL("");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        try {
            conexion.listarPuntos(conexion.getUrl());

            binding = ActivityMainBinding.inflate(getLayoutInflater());
            setContentView(binding.getRoot());

            setSupportActionBar(binding.toolbar);

            NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
            appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
            NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

            // if (!is there any handicap defined){
            //   run through first time setup
            //
            // }

        }catch(Exception e){

        }
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
