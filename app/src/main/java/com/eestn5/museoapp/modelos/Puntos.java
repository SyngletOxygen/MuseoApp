package com.eestn5.museoapp.modelos;



import androidx.annotation.NonNull;

import com.eestn5.museoapp.Static.Config;
import com.eestn5.museoapp.interfaces.JsonRequestObjectSYOX;

import org.json.JSONException;
import org.json.JSONObject;

public class Puntos implements JsonRequestObjectSYOX {
    private String descripcion, titulo,historia;
    private int orden = -1;

    public Puntos(String descripcion, String titulo,int orden,String historia) {
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.orden = orden ;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public Puntos() {
    }

    public String getHistoria() {
        return historia;
    }

    public void parseToJson(@NonNull JSONObject response) throws JSONException {

        String  test = "\n Si usted desea continuar, pulse el boton continuar, camine hasta escuchar el siguiente beep.";
        switch(Config.getConfig()){

            case Config.NOCONFIG:

                this.descripcion = response.getString("descripcionDeCiego")+test;

                break;

            case Config.NONDISHABLED:

                this.descripcion = response.getString("descripcionDeObjeto");

                break;

            case Config.BLIND:

                this.descripcion = response.getString("descripcionDeCiego")+test;

                break;

            case Config.MENTALYDISHABLED:
                //Ninguno del grupo: Kevin Acevedo ni Gianluca Hernando decidieron nombrar la variable como descripcion de problema... LOL
                this.descripcion = response.getString("descripcionDeProblema"+test);

                break;

        }

         this.titulo = response.getString("nombre");
         this.orden = response.getInt("orden");
         this.historia = response.getString("descripcionDeHistoria");
         System.out.println(response);
    }

    @Override
    public String toString() {
        return "Puntos{" +
                "description='" + descripcion + '\'' +
                ", title='" + titulo + '\'' +
                '}';
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

}
