package com.eestn5.museoapp.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.eestn5.museoapp.Handlers.LogicSQLLiteHelper;
import com.eestn5.museoapp.R;
import com.eestn5.museoapp.Static.Config;
import com.eestn5.museoapp.databinding.ActivitySeleccionDiscapacidadBinding;

public class SeleccionDeDiscapacidadActivity  extends Fragment {

    private ActivitySeleccionDiscapacidadBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = ActivitySeleccionDiscapacidadBinding.inflate(inflater, container, false);
        return binding.getRoot();





    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        View.OnClickListener test = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked( view);
            }
        };
        binding.botonIconoElegirCeguera.setOnClickListener(test);
        binding.BotonIconoElegirDiscapacidadMental.setOnClickListener(test);
        binding.botonIconoElegirSinDiscapacidad.setOnClickListener(test);
        binding.BotonIconoElegirSordera.setOnClickListener(test);

    }


    public void clicked(View view){
        int value = 0;
        System.out.println(view.getId());
        switch(view.getId()){


            case(R.id.Boton_icono_elegir_sordera):

                value  = 2;
                break;
            case(R.id.boton_icono_elegir_ceguera):

                value = 1;
                break;
            case(R.id.Boton_icono_elegir_discapacidad_mental):

                value = 3;

                break;




        }
        LogicSQLLiteHelper.getInstance().SetConfig(value);
        Config.setconfig(value);
        NavHostFragment.findNavController(SeleccionDeDiscapacidadActivity.this).navigate(R.id.action_seleccionDeDiscapacidadActivity_to_menuPrincipalFragment);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}