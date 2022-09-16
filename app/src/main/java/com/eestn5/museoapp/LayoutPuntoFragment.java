package com.eestn5.museoapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.eestn5.museoapp.databinding.PuntoLayoutBinding;
public class LayoutPuntoFragment extends Fragment {

    private PuntoLayoutBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = PuntoLayoutBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        //api qrs

        //


       /* binding.BotonPaseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(MenuPrincipalFragment.this)
                        .navigate(R.id.action_menuPrincipalFragment_to_seleccionDeDiscapacidadActivity);
            }
        });

        */
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}