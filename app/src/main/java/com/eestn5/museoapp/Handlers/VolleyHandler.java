package com.eestn5.museoapp.Handlers;


import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.eestn5.museoapp.R;
import com.eestn5.museoapp.Static.Config;
import com.eestn5.museoapp.interfaces.JsonRequestObjectSYOX;
import com.eestn5.museoapp.modelos.CodeExecutor;
import com.eestn5.museoapp.modelos.Puntos;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;


public class VolleyHandler {

    String records = "",error="";
    static boolean test = true;

    /**
     This class is a singleton,you may need to create an instance before using it throughout the application
     @param args the definition of the form in the api
     @param vars the values of the args[]
     @param context the context of the Activity
     @return returns a Boolean  (has been created  = false, needs to be created  = true).
     */
    public static void enviarDatos(String args[],String vars[], Context context)
    {

        try{
            RequestQueue queue = Volley.newRequestQueue(context);

            StringRequest request=new StringRequest(Request.Method.POST, "https://"+ Config.url+"/api/comentarios",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    System.out.println(error);
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params=new HashMap<>();
                    if (args.length==vars.length)
                    {
                        for(int i=0;i<args.length;i++){

                            params.put(args[i],vars[i]);

                        }
                    }
                    return params;
                }
            };
            queue.add(request);

        }catch (Exception e){

        }

        //conexion.execute();

    }



    /**
     this function gets a json object (ONLY ONE) from a website.
     @return the return is in the prompt as the OBJ variable.
      * @param OBJ an Object Implementing JsonRequestObjectSYOX.
     * @param url the url from where Volley will get the data.
     * @param frag the context of the Activity.
     * @param activity gets the fragmentactivity aka the "context"
     * @param code this is a Runneable-esk class, meaning. you can override the class's function to achieve whatever you need.
     */
    public static void pedirDatos(JsonRequestObjectSYOX OBJ, String url,Fragment frag, FragmentActivity activity, CodeExecutor code)
    {

        RequestQueue queue = Volley.newRequestQueue( frag.getContext());

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                    (Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    System.out.println(response.toString());


                                    try {

                                        code.run(OBJ,response, frag);

                                    } catch (Exception e) {


                                    }


                                }
                            });
                        }
                    },
                            new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // TODO: Handle error

                        }
                    });

            queue.add(jsonObjectRequest);




        //conexion.execute();



    }


}


