package com.eestn5.museoapp;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.eestn5.museoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    EditText textOpinion,textComentario;
    Button enviar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textOpinion = findViewById(R.id.editTextOpinion);

        textComentario =  findViewById(R.id.editTextOpinion);

        enviar = findViewById(R.id.enviar);


        try {
            enviar.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    new ComentariosHandler().enviarDatos(textOpinion,textComentario);

                }

            });
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
