package com.eestn5.museoapp;

import android.widget.Toast;

import com.android.volley.toolbox.*;
import com.android.volley.*;
import com.eestn5.museoapp.*;
import org.json.*;

import java.util.ArrayList;
import java.util.List;

public class HandlersSQL {
    private String url="";

    public HandlersSQL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
    List<Puntos> puntosList=new ArrayList<Puntos>();
    Adapter adapters;
    public void listarPuntos(String url) {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject object = jsonArray.getJSONObject(i);
                        String id = object.getString("id");
                        String titulo = object.getString("titulo");
                        String descripcion = object.getString("descripcion");
                        String orden = object.getString("orden");

                        Puntos puntos = new Puntos(id, descripcion, titulo, orden);
                        puntosList.add(puntos);
                        adapters.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(,error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(HandlersSQL.this);
        requestQueue.add(request);

    }

}
