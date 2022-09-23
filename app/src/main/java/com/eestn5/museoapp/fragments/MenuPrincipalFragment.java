package com.eestn5.museoapp.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.aware.PublishConfig;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;

import com.eestn5.museoapp.Handlers.VolleyHandler;
import com.eestn5.museoapp.R;
import com.eestn5.museoapp.Static.Config;
import com.eestn5.museoapp.databinding.ActivityMenuprincipalBinding;
import com.eestn5.museoapp.activity.TRexActivity;
import com.eestn5.museoapp.modelos.CodeExecutor;
import com.eestn5.museoapp.modelos.Puntos;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MenuPrincipalFragment extends Fragment {

    private ActivityMenuprincipalBinding binding;

   public  static int PossibleIdOfQRCode = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = ActivityMenuprincipalBinding.inflate(inflater, container, false);

        binding.BotonJuegos.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TRexActivity.class);
                startActivity(intent);
            }
        });


        return binding.getRoot();
    }




    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSelectChangeDisability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(MenuPrincipalFragment.this)

                        .navigate(R.id.action_menuPrincipalFragment_to_seleccionDeDiscapacidadActivity);


            }

        });





        binding.BotonPaseo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //esta mierda fue devhell pero me enseño que mauro me da besos
                //como me gusta mauro
                IntentIntegrator.forSupportFragment(MenuPrincipalFragment.this).initiateScan();


                /*NavHostFragment.findNavController(MenuPrincipalFragment.this)

                        .navigate(R.id.action_menuPrincipalFragment_to_seleccionDeDiscapacidadActivity);

            */
            }

        });
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        PossibleIdOfQRCode = Integer.parseInt(result.getContents());
        System.out.println("Obtained data ====== "+PossibleIdOfQRCode);

        // revisar qrcode en base de datos


        callQRCODE ( getActivity());




            NavHostFragment.findNavController(MenuPrincipalFragment.this).navigate(R.id.action_menuPrincipalFragment_to_layoutPuntoFragment);
            //popular  el siguiente fragment con caracteristicas del punto

        }






    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static void ShowDialog(FragmentActivity fa,Fragment f)
    {
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(fa);

        LinearLayout linearLayout = new LinearLayout(fa);
        final RatingBar rating = new RatingBar(fa);

        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT

        );

        rating.setLayoutParams(lp);
        rating.setNumStars(5);
        rating.setStepSize(1);
        linearLayout.setOrientation(LinearLayout.VERTICAL);


        // Set up the input
        final EditText input = new EditText(fa);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT );
        linearLayout.addView(rating);
        linearLayout.addView(input);





        //add ratingBar to linearLayout




        popDialog.setTitle("Deja un comentario sobre el Museo!" +
                " ");

        //add linearLayout to dailog
        popDialog.setView(linearLayout);




        rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                System.out.println("Rated val:"+v);
            }
        });



        // Button OK
        popDialog.setPositiveButton(android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                //textView.setText(String.valueOf(rating.getProgress()));


                                try {
                                    Thread test = new Thread(new Runnable() {
                                        @Override
                                        public void run() {
                                            VolleyHandler.enviarDatos(new String[]{"comentario","calificacion"},new String[]{String.valueOf(input.getText()), String.valueOf(rating.getProgress())},fa);

                                        }

                                    });
                                    test.run();
                                    NavHostFragment.findNavController(f).navigate(R.id.action_layoutPuntoFragment_to_menuPrincipalFragment);
                                }
                                catch (Exception e){}
                                dialog.dismiss();
                            }

                        })

                // Button Cancel
                .setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

        popDialog.create();
        popDialog.show();

    }


    public static void errorDisplay(Fragment f){
System.out.println("InLoop "+ f.toString());
        final AlertDialog.Builder popDialog = new AlertDialog.Builder(f.getContext());
        popDialog.setTitle("Error! Servidor apagado??");
        popDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        NavHostFragment.findNavController(f)

                                .navigate(R.id.action_layoutPuntoFragment_to_menuPrincipalFragment);
                    }});
        popDialog.create();
        popDialog.show();
    }




    public static void callQRCODE ( FragmentActivity fa){
        Puntos punto = new Puntos();
        List<Fragment> frag = fa.getSupportFragmentManager().getFragments();
        int id = -1;
        for(Fragment k : frag){
            id++;


        }
        System.out.println("ID DE FRAGMENT  "+id+" ID DE FRAGMENT ");

        Fragment  temp=frag.get(id);

        Thread test = new Thread(new Runnable() {
            @Override
            public void run() {

                VolleyHandler.pedirDatos(punto, (String) "https://" + Config.url + "/api/puntos?orden=" + PossibleIdOfQRCode,  temp,fa,new CodeExecutor(){

                    @Override
                    public void run(Object ... o) throws JSONException {
                        if(o[0].getClass() == Puntos.class){

                            Puntos p = (Puntos) o[0];
                            try {
                                p.parseToJson((JSONObject) o[1]);

                                LayoutPuntoFragment.replaceText(p);
                            }catch(Exception e){errorDisplay((Fragment) o[2]);System.out.println("TESTEST   "+e);}



                        }

                    }
                });

            }
        });

        test.start();




    }

    }