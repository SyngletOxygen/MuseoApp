package com.eestn5.museoapp.fragments;


import static com.eestn5.museoapp.fragments.MenuPrincipalFragment.callQRCODE;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.eestn5.museoapp.R;
import com.eestn5.museoapp.databinding.PuntoLayoutBinding;
import com.eestn5.museoapp.modelos.Puntos;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class LayoutPuntoFragment extends Fragment {

    private static PuntoLayoutBinding binding;

    private static Puntos puntofijo ;
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


        binding.NextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(puntofijo.getOrden() == 0){

                    MenuPrincipalFragment.ShowDialog(getActivity(),LayoutPuntoFragment.this);

                }
                else {
                    IntentIntegrator.forSupportFragment(LayoutPuntoFragment.this).initiateScan();
                }
            }
        });


    }


    public static void replaceText(Puntos punto){

        binding.DescriptionOfPoint.setText( punto.getDescripcion());
        binding.NameOfPoint.setText( punto.getTitulo());
        binding.historyOfPoint.setText(punto.getHistoria());
LayoutPuntoFragment.puntofijo = punto;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        MenuPrincipalFragment.PossibleIdOfQRCode = Integer.parseInt(result.getContents());
        System.out.println("Obtained data ====== "+MenuPrincipalFragment.PossibleIdOfQRCode);

        // revisar qrcode en base de datos


        MenuPrincipalFragment.callQRCODE ( getActivity());




        //popular  el siguiente fragment con caracteristicas del punto

    }
}
