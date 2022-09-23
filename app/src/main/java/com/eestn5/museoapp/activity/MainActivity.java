package com.eestn5.museoapp.activity;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.eestn5.museoapp.Handlers.LogicSQLLiteHelper;
import com.eestn5.museoapp.R;
import com.eestn5.museoapp.databinding.ActivityMainBinding;
import com.eestn5.museoapp.databinding.ActivitySeleccionDiscapacidadBinding;
import com.eestn5.museoapp.fragments.MenuPrincipalFragment;
import com.eestn5.museoapp.fragments.SeleccionDeDiscapacidadActivity;

public class MainActivity extends AppCompatActivity {


    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    public static NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        LogicSQLLiteHelper.createInstance(this);


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        // if (!is there any handicap defined){
        //   run through first time setup
        //
        // }


    CheckifConfigRight();





    }
    //EXAMPLE FOR SOUND
    /* WebView mauro = this.findViewById(R.id.Maurotest);
        mauro.getSettings().setJavaScriptEnabled(true);
        mauro.getSettings().setLoadWithOverviewMode(true);
        mauro.getSettings().setUseWideViewPort(true);
        mauro.getSettings().setPluginState(WebSettings.PluginState.ON);
        mauro.loadUrl("https://www.google.com");

        */


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // This is important, otherwise the result will not be passed to the fragment
        super.onActivityResult(requestCode, resultCode, data);
    }

    public static void CheckifConfigRight() {


        if (LogicSQLLiteHelper.getInstance().GetConfig() == -1) {
            if (MainActivity.navController.getCurrentDestination().getId() != R.id.seleccionDeDiscapacidadActivity) {
                MainActivity.navController.navigate(R.id.action_menuPrincipalFragment_to_seleccionDeDiscapacidadActivity);



            }


        }
    }

}